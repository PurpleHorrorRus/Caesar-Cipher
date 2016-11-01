/*
 * Author: https://github.com/PurpleHorrorRus
 * Build on 29.10.2016
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class EntryPoint {
	
	//trash
	private Scanner textScan;
	
	private File 
	choosed, encrFile, decrFile, reportFile;
	
	private BufferedWriter output;
	private Frame frame; private FrameDecrypt frameDecr;
	private int indexForMenu = 0;
	private static int deg = 1;
	private MouseAdapter eventAccept, eventDecline; private ActionListener eventMost;
	
	//Ошибки
	private String 
	create = "crate",
	write = "wirte", 
	digit = "digit",
	read = "read",
	nullm = "nullm";
	
	protected Crypt 
	crypt = new Crypt();
	
	void initBtns(){
		frame.encrBtn.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent arg0) { opWFiles(); startEncrypt(); } });
		frame.decrBtn.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent arg0) { opWFiles(); startDecrypt(); } });
	}
	
	void opWFiles(){
		JFileChooser manager = new JFileChooser();
		manager.showOpenDialog(null); 
		
		crypt.ABS_PATH_TO_FILE = manager.getSelectedFile().getAbsolutePath();
		crypt.PATH_TO_FILE = manager.getSelectedFile().getParent();
		
	}
	
	void startEncrypt(){
		choosed = new File(crypt.ABS_PATH_TO_FILE);
		encrFile = new File(crypt.ABS_PATH_TO_FILE+"_encr");
		
		try { textScan = new Scanner(new FileReader(choosed)); while(textScan.hasNextLine()) crypt.source += textScan.nextLine(); 
		System.out.println("Файл прочитан."); } 
		catch (FileNotFoundException e1) { throwError(read); }
		
		textScan.close();
		
		if(!encrFile.exists())
			try { encrFile.createNewFile(); System.out.println("Файл успешно создан."); } 
		catch (IOException e) { throwError(create); }
		
		String degOP = JOptionPane.showInputDialog(null, "Введите необходимый сдвиг:", "Сдвиг", JOptionPane.QUESTION_MESSAGE);
		int deg = 0;
		try { deg = Integer.parseInt(degOP); } catch(NumberFormatException ex) { throwError(digit); }
		
		if(deg > 0 && deg < crypt.alphabet.length){
			crypt.encrypt(crypt.source, deg);
			try {
				output = new BufferedWriter(new FileWriter(encrFile));
				output.write(crypt.exp_encr);
				crypt.exp_encr = "";
				output.close();
			} catch (IOException e) { throwError(write); }
		}
	}
	
	void startDecrypt(){
		
		choosed = new File(crypt.ABS_PATH_TO_FILE);
		decrFile = new File(crypt.ABS_PATH_TO_FILE+"_decr");
		
		try { textScan = new Scanner(new FileReader(choosed)); while(textScan.hasNextLine()) crypt.exp_encr += textScan.nextLine(); 
		System.out.println("Файл прочитан."); } 
		catch (FileNotFoundException e1) { throwError(read); }
		
		textScan.close();
		
		if(!decrFile.exists())
			try { decrFile.createNewFile(); System.out.println("Файл успешно создан."); } 
		catch (IOException e) { throwError(create); }
		
		report();
		
		frameDecr.setVisible(true);
		
		int deg = 1;
		
		crypt.decrypt(crypt.exp_encr, deg);
		frameDecr.decrText.setText("Сдвиг " + deg + ": " + crypt.exp_decr);
		
		
		eventAccept = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(frameDecr.checkMostDegBox.isSelected()){ 
					int index = frameDecr.most_popular_degs.getSelectedIndex();
					if(index >= 0)
						getSetExistBest(crypt.modelCount[index]);
					 else throwError(nullm);
				} else getSetExistBest(EntryPoint.deg);
				
				try {
					output = new BufferedWriter(new FileWriter(decrFile));
					output.write(crypt.exp_decr);
					crypt.exp_decr = "";
					output.close();
				} catch (IOException ex) { throwError(write); }
				
				frameDecr.acceptBtn.removeMouseListener(this);
				frameDecr.declineBtn.removeMouseListener(eventDecline);
				resetDecr();
			}
		};
		
		eventDecline = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(EntryPoint.deg < crypt.alphabet.length-1){
					crypt.exp_decr = "";
					if(!frameDecr.checkMostDegBox.isSelected()){
						EntryPoint.deg += 1;
						crypt.decrypt(crypt.exp_encr,  EntryPoint.deg);
						frameDecr.decrText.setText("");
						frameDecr.decrText.setText("Сдвиг " +  EntryPoint.deg + ": " + crypt.exp_decr);
					}
				}
			};
		};
		
		eventMost = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { actionWM(); }
		};
		
		frameDecr.checkMostDegBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(frameDecr.checkMostDegBox.isSelected()) {
					actionWM();
					frameDecr.most_popular_degs.addActionListener(eventMost);
					frameDecr.declineBtn.setVisible(false); 
				}
				else { 
					frameDecr.most_popular_degs.removeActionListener(eventMost);
					crypt.exp_decr = "";
					crypt.decrypt(crypt.exp_encr, EntryPoint.deg);
					frameDecr.decrText.setText("Сдвиг " +  EntryPoint.deg + ": " + crypt.exp_decr);
					frameDecr.declineBtn.setVisible(true); 
				}
			}
		});
		
		frameDecr.acceptBtn.addMouseListener(eventAccept);
			
		frameDecr.declineBtn.addMouseListener(eventDecline);
	}
	
	void actionWM(){
		int index = frameDecr.most_popular_degs.getSelectedIndex(); crypt.exp_decr = "";
		if(index >= 0){
			crypt.decrypt(crypt.exp_encr,  crypt.modelCount[index]);
			frameDecr.decrText.setText("Сдвиг " +  crypt.modelCount[index] + ": " + crypt.exp_decr);
		}
	}
	
	void resetDecr(){ 
		frameDecr.most_popular_degs.removeAllItems(); indexForMenu = 0;
		choosed = decrFile = null; textScan = null; frameDecr.dispose(); crypt.exp_encr = ""; crypt.exp_decr = ""; EntryPoint.deg = 1; }
	
	void getSetExistBest(int deg){
		
		try { textScan = new Scanner(new FileReader(reportFile)); } 
		catch (FileNotFoundException e) { throwError(read); }
		
		String allTxt = "";
		while(textScan.hasNextLine()) allTxt += textScan.nextLine() + "\n";
		textScan.close();
		
		try { textScan = new Scanner(new FileReader(reportFile)); } 
		catch (FileNotFoundException e) { throwError(read); }
		
		String line = ""; boolean find = false; String[] divs = { "", "" };
		while(textScan.hasNextLine()) {
			line = textScan.nextLine();
			
			divs = line.split(",");
			
			if(Integer.parseInt(divs[0]) == deg) { 
				
				find = true; 
				break;
			
			}
		}
		
		textScan.close();
		textScan = null;
		
		EntryPoint.deg = 1;
		if(find){
			int nextnmb = Integer.parseInt(divs[1])+1;
			line = divs[0] + "," + nextnmb;
			
			try {
				output = new BufferedWriter(new FileWriter(reportFile));
				String temp = allTxt.replace(divs[0] + "," + divs[1], line);
				output.write(temp);
				output.close();
			} catch (IOException ex) { throwError(write); }
			line = divs[0] = divs[1] = ""; find = false; allTxt = "";
			return;
		}
		
		else if(!find){
			try {
				output = new BufferedWriter(new FileWriter(reportFile));
				output.write(allTxt + deg + "," + 1);
				output.close();
			} catch (IOException ex) { throwError(write); }
		}
		line = divs[0] = divs[1] = ""; find = false; allTxt = "";
		
	}
	
	void readText(String text, int s){
		String[] exps = text.split("\n"); int deg = 0, success = 0;
		for(int i = 0; i < exps.length; i++){
			try{
				String[] divs = exps[i].split(",");
				deg = Integer.parseInt(divs[0]);
				success = Integer.parseInt(divs[1]);
				if(crypt.count[s] == success) { 
					crypt.modelCount[indexForMenu] = deg;
					frameDecr.most_popular_degs.addItem("Сдвиг " + deg + ". Удачных: " + success); 
					indexForMenu++;
				}
			}catch(ArrayIndexOutOfBoundsException ex) { break; }
		}
	}
	
	void report(){
		reportFile = new File(crypt.PATH_TO_FILE + "//report");
		if(!reportFile.exists())
			try { reportFile.createNewFile(); System.out.println("Файл статистики успешно создан."); } 
		catch (IOException e) { throwError(create); }
		
		int s = 0;
		String line = ""; int lines = 0;
		
		try { textScan = new Scanner(new FileReader(reportFile)); } 
		catch (FileNotFoundException e) { throwError(read); }
		
		while(textScan.hasNextLine()) { line += textScan.nextLine() + "\n"; lines++; } crypt.count = crypt.modelCount = new int[lines];
		
		if(line != ""){
			String[] exps = line.split("\n");
			for(int i = 0; i < exps.length; i++){
				String[] divs = exps[i].split(",");

				int b = Integer.parseInt(divs[1]);
				
				crypt.count[s] = b;
				s++;
			}
			
			textScan.close();
			crypt.count = getMaxDegs();
			
			if(exps.length < 3) for(int i = 0; i < exps.length; i++) readText(exps[i], i); else for(int i = 0; i < 3; i++) readText(exps[i], i);
		}
		
		
					
	}
	
	int[] getMaxDegs(){
		for(int i = 0; i < crypt.count.length-1; i++){
			for(int j = i + 1; j < crypt.count.length; j++){
				if(crypt.count[i] < crypt.count[j]){
					int tmp = crypt.count[i];
					crypt.count[i] = crypt.count[j];
					crypt.count[j] = tmp;
				}
			}
		}
		return crypt.count;
	}
	
	void throwError(String err){
		String text = "";
		switch(err) {
		case "create": text = "Не удалось создать файл."; break;
		case "write": text = "Не удалось записать в файл."; break;
		case "digit": text = "Необходимо ввести цифру"; break;
		case "read": text = "Не удалось прочитать файл"; break;
		case "nullm": text = "Пустое меню успешных сдвигов"; break;
		default: text = "Неизвестная ошибка"; break;
		}
		
				crypt.source = 
				crypt.exp_decr = 
				crypt.exp_encr = "";
				
		frame.status.setText(text);
		JOptionPane.showMessageDialog(null, text, "Ошибка", JOptionPane.ERROR_MESSAGE);
		System.err.println(text);
		return;
	}
	
	EntryPoint(){
		frame = new Frame(); frameDecr = new FrameDecrypt();
		initBtns();
	}
	
	public static void main(String[] args){ new EntryPoint(); }
}