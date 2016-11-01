import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;

public class FrameDecrypt extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	public JComboBox most_popular_degs;
	public JLabel decrText;
	public JButton acceptBtn, declineBtn;
	public JCheckBox checkMostDegBox;

	public FrameDecrypt() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 444, 207);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		most_popular_degs = new JComboBox();
		most_popular_degs.setBounds(10, 11, 237, 32);
		contentPane.add(most_popular_degs);
		
		decrText = new JLabel("");
		decrText.setBounds(10, 77, 408, 38);
		contentPane.add(decrText);
		
		JLabel label = new JLabel("Наиболее успешные сдвиги");
		label.setBounds(257, 20, 161, 14);
		contentPane.add(label);
		
		acceptBtn = new JButton("Принять");
		acceptBtn.setBounds(59, 134, 144, 23);
		contentPane.add(acceptBtn);
		
		declineBtn = new JButton("Отклонить");
		declineBtn.setBounds(213, 134, 144, 23);
		contentPane.add(declineBtn);
		
		checkMostDegBox = new JCheckBox("Наиболее удачные");
		checkMostDegBox.setBounds(10, 47, 179, 23);
		contentPane.add(checkMostDegBox);
		
		setTitle("Выбрать сдвиг");
		
	}
}
