import javax.swing.JFrame;
import javax.swing.JPanel;

public class member extends JFrame{
	JPanel memberPanel = new JPanel();
	
	member() {
		setTitle("회원 정보");
		this.setSize(800, 600);
		setContentPane(memberPanel);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	

}
