import javax.swing.JFrame;

public class Main extends JFrame{

	public static PcManagement pm;
	public static void main(String[] args) {
		Main main = new Main();
		main.Frame();
	}
	
	public void Frame() {
		setTitle("PC방 관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(1280, 720);
		setResizable(true);
		setVisible(true);
		setLocationRelativeTo(null);
		pm = new PcManagement();
		setContentPane(pm);
		
		member member = new member();
	}
	

}
