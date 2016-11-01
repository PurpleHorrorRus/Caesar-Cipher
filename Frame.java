import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	public JButton encrBtn, decrBtn;
	public JLabel status;

	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 277, 255);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		encrBtn = new JButton("Шифровка");
		encrBtn.setBounds(62, 52, 130, 51);
		contentPane.add(encrBtn);
		
		decrBtn = new JButton("Дешифровка");
		decrBtn.setBounds(62, 104, 130, 51);
		contentPane.add(decrBtn);
		
		status = new JLabel("");
		status.setBounds(10, 191, 241, 14);
		contentPane.add(status);
		
		setTitle("Шифр Цезаря");
		
		setVisible(true);
	}
}
