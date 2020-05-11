import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class user extends JFrame {
	JPanel userPanel = new JPanel();
	logoutAction logoutAction = new logoutAction();
	timeAction timeAction = new timeAction();
	JLabel name; 
	JLabel id;
	JLabel time;
	JLabel pay;
	JLabel how;
	JLabel pcLog;
		
	JButton timeBut = new JButton("시간 추가");
//	JButton logoutBut = new JButton("로그아웃");
	JButton logoutBut = new JButton("로그아웃");
	
	ButtonGroup howGroup = new ButtonGroup();
	JRadioButton First = new JRadioButton("선불", true);
	JRadioButton Later = new JRadioButton("후불", false);
	
	JCheckBox hourBox1 = new JCheckBox("1시간");
	JCheckBox hourBox2 = new JCheckBox("2시간");
	JCheckBox minuteBox = new JCheckBox("30분");
	
	String[] user;
	
	user(int pcIndex) {
		System.out.println("user");
		setTitle(pcIndex + "pc 이용중");
		this.setSize(800, 600);
		setContentPane(userPanel);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		
		user = Main.pm.db.memberSelect(pcIndex);
		user();
	}
	
	public void user() {
		name = new JLabel("이름 : " + user[0]);
		id = new JLabel("ID : " + user[1]);
		time = new JLabel("시간 : " + user[2]);
		pay = new JLabel("금액 : " + user[3]);
		how = new JLabel(user[4]);
		pcLog = new JLabel(user[5] + " pc");
		
		logoutBut.addActionListener(logoutAction);
		timeBut.addActionListener(timeAction);
		
		howGroup.add(First);
		howGroup.add(Later);
	
		
		userPanel.add(pcLog);
		userPanel.add(name);
		userPanel.add(id);
		userPanel.add(time);
		userPanel.add(how);
		userPanel.add(pay);
		userPanel.add(First);
		userPanel.add(Later);
		userPanel.add(hourBox1);
		userPanel.add(hourBox2);
		userPanel.add(minuteBox);
		
		userPanel.add(timeBut);
		userPanel.add(logoutBut);
		
	}
	
	private class logoutAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			Main.pm.db.memberLogout(user[5]);
			Main.pm.Logout();
			dispose();
		}
	}
	
	private class timeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			
			if(user[3] == null) {
				
			}
			Main.pm.db.memberLogout(user[5]);
			Main.pm.Logout();
			dispose();
		}
	}
	
	private class addTime implements ActionListener {
		String check[] = new String[3];
		@Override
		public void actionPerformed(ActionEvent e) {
			if(First.isSelected()) {
				check[0] = "선불";
			} else if(Later.isSelected()) {
				check[0] = "후불";
			}
			
			if()
		}
		
	}
}
