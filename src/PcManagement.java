import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class PcManagement extends JPanel{
	
	DB db;
//	JPanel pc;
	
	pcListener pclistener = new pcListener();
	
	public PcManagement() {
		Login login = new Login("manager");
	}
	
	public void PcManagement() {
		db = new DB();
		
		Main.mb.setSize(1280, 50);
		setLayout(new GridLayout(4, 4, 10, 10));


		// pc
		JButton[] pcButton = new JButton[16];
		pcButton[0] = new JButton("<html>" + 1+" pc" + "<br>"  + "ID : customer1 " + "<br>"  + "시간 : 00:30:24" + "<br>"  +"선불" + "</html>");
		pcButton[0].addActionListener(pclistener);
		add(pcButton[0]);
		for(int i=1;i<16; i++) {
			pcButton[i] = new JButton(i+1+" pc");
			pcButton[i].addActionListener(pclistener);
			add(pcButton[i]);
		}
		setVisible(true);
	
		revalidate();
		repaint();
	}
	
//	public void repaint() {
//		revalidate();
//		repaint();
//	}
	
	private class pcListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			new Login(b.getText());
		}
	}

}
