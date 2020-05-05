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
		
		setLayout(new GridLayout(4, 4, 10, 10));

		// pc
		JButton[] pcButton = new JButton[16];
		for(int i=0;i<16; i++) {
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
