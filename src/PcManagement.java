import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PcManagement extends JPanel{
	
	DB db;
//	JPanel pc;
	Login login = null; 
	pcListener pclistener = new pcListener();
	JButton[] pcButton;
	int pcIndext=0;
	
	public PcManagement() {
		System.out.println("pcmanagement");
		login = new Login("manager");
	}
	
	public void PcManagement() {		
		System.out.println("pcmanagement-메소드");
		db = new DB();
		
		Main.mb.setSize(1280, 50);
		setLayout(new GridLayout(4, 4, 10, 10));

		// pc
		pcButton = new JButton[16];
//		pcButton[0] = new JButton("<html>" + 1+" pc" + "<br>"  + "ID : customer1 " + "<br>"  + "시간 : 00:30:24" + "<br>"  +"선불" + "</html>");
//		pcButton[0].addActionListener(pclistener);
//		add(pcButton[0]);
		for(int i=0;i<16; i++) {
			pcButton[i] = new JButton(i+1+" pc");
			pcButton[i].addActionListener(pclistener);
			add(pcButton[i]);
		}
		setVisible(true);
	
		revalidate();
		repaint();
	}
	
	public void Login(String[] member) {
		System.out.println("pm-Login");
		String pcbutString = "<html>" + 1+" pc" + "<br>"  + "ID : " + member[0] + "<br>"  + "시간 : " + member[1] + "<br>"  + member[3] + "</html>";
		if(member!=null) {
			pcButton[pcIndext].setText();
		} else {
			JOptionPane.showMessageDialog(null, "ID 또는 PW가 틀렸습니다.", "Message", JOptionPane.ERROR_MESSAGE);
		}
		
		revalidate();
		repaint();
	}
	
	public void Join() {
		System.out.println("pm-join");
		login = new Login(pcIndext + " pc");
	}
	
	private class pcListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("pm-action");
			JButton b = (JButton)e.getSource();
			for(int i=0; i<16; i++) {
				if(b == pcButton[i]) {
					pcIndext = i;
					break;
				}				
			}
			login = new Login(b.getText());
		}
	}

}
