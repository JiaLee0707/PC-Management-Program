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
//	timeAction timeAction = new timeAction();
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
		//System.out.println("user");
		setTitle(pcIndex + "pc 이용중");
		this.setSize(800, 600);
		setContentPane(userPanel);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		
		user = Main.pm.db.memberSelect(pcIndex);

		name = new JLabel("이름 : " + user[0]);
		id = new JLabel("ID : " + user[1]);
		time = new JLabel("시간 : " + user[2]);
		pay = new JLabel("금액 : " + user[3]);
		how = new JLabel(user[4]);
		pcLog = new JLabel(user[5] + " pc");
		
		logoutBut.addActionListener(logoutAction);
		timeBut.addActionListener(new timeAction());
		
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
	
	public void user() {
		user = Main.pm.db.memberSelect(Integer.parseInt(user[5]));
		
		name.setText("이름 : " + user[0]);
		id.setText("ID : " + user[1]);
		time.setText("시간 : " + user[2]);
		pay.setText("금액 : " + user[3]);
		how.setText(user[4]);
		
		revalidate();
		repaint();
		
		Main.pm.Login(user);
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
		@Override
		public void actionPerformed(ActionEvent e) {
			String check[] = new String[3];
			int hour=0;
			
			if(First.isSelected()) {
				check[0] = "선불";
			} else if(Later.isSelected()) {
				check[0] = "후불";
			}
			
			if(hourBox1.isSelected()) {
				hour += 1;
			}
			if(hourBox2.isSelected()) {
				hour += 2;
			}
			check[1] = Integer.toString(hour);
			if(minuteBox.isSelected()) {
				check[2] = "30";
			} else {
				check[2] = "00";
			}
			
			Main.pm.db.memberUpdate(check, user[1]);
			user();
		}
		
	}
}
