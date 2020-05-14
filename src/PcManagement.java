import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PcManagement extends JPanel {

	DB db;
//	JPanel pc;
	Login login = null;
	user user = null;
	pcListener pclistener = new pcListener();
	JButton[] pcButton;
	int pcIndex = 0;

	public PcManagement() {
		//System.out.println("pcmanagement");
		login = new Login("manager");
	}

	public void PcManagement() {
		//System.out.println("pcmanagement-메소드");
		db = new DB();

		Main.mb.setSize(1280, 50);
		setLayout(new GridLayout(4, 4, 10, 10));

		// pc
		pcButton = new JButton[16];
		for (int i = 0; i < 16; i++) {
			pcButton[i] = new JButton(i + 1 + " pc");
			pcButton[i].addActionListener(pclistener);
			add(pcButton[i]);
		}
		setVisible(true);

		revalidate();
		repaint();
	}

	public void Login(String[] member) {
		//System.out.println("pm-Login");
		if (member[3] != null) {
			pcButton[pcIndex-1].setText("<html>" + member[5] + " pc" + "<br>" + "ID : " + member[1] + "<br>" + "시간 : "
					+ member[2] + "<br>" + member[4] + "</html>");
		} else {
			pcButton[pcIndex-1].setText(
					"<html>" + member[4] + " pc" + "<br>" + "ID : " + member[0] + "<br>" + "시간 : " + member[1] + "</html>");
		}
		revalidate();		
		repaint();
	}

	public void Logout() {
		pcButton[pcIndex-1].setText(pcIndex + " pc");
		revalidate();		
		repaint();
	}

	public void Join() {
		//System.out.println("pm-join");
		login = new Login(pcIndex + " pc");
	}

	private class pcListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//System.out.println("pm-action");
			JButton b = (JButton) e.getSource();
			for (int i = 0; i < 16; i++) {
				if (b == pcButton[i]) {
					pcIndex = i+1;
					break;
				}
			}
			if(b.getText().contains("ID")) {
				user = new user(pcIndex);
			} else {				
				login = new Login(Integer.toString(pcIndex));
			}
		}
	}

}
