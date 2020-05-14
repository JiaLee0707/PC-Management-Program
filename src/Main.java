import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Main extends JFrame{

	public static PcManagement pm;

	public static JMenuBar mb = new JMenuBar();
	JMenu memberMenu = new JMenu("회원");
	JMenu orderMenu = new JMenu("주문");
	JMenu onOffMenu = new JMenu("pc 전원");
	
	public static void main(String[] args) {
		//System.out.println("Main");
		Main main = new Main();
		main.Frame();
	}
	
	public void Frame() {
		//System.out.println("Main-Frame");
		setTitle("PC방 관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(1280, 720);
		setResizable(true);
		setVisible(true);
		setLocationRelativeTo(null);
		pm = new PcManagement();
		setContentPane(pm);

		mb.add(memberMenu);
		mb.add(orderMenu);
		mb.add(onOffMenu);
//		mb.setSize(1280, 50);
		this.setJMenuBar(mb);
		
//		mb.setVisible(false);
	}
	

}
