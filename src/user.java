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
	Timer timer;
	user user;
	logoutAction logoutAction = new logoutAction();
	JLabel name; 
	JLabel id;
	JLabel time;
	JLabel pay;
	JLabel how;
	JLabel pcLog;
		
	JButton timeBut = new JButton("시간 추가");
	JButton logoutBut = new JButton("로그아웃");
	
	ButtonGroup howGroup = new ButtonGroup();
	JRadioButton First = new JRadioButton("선불", true);
	JRadioButton Later = new JRadioButton("후불", false);
	
	JCheckBox hourBox1 = new JCheckBox("1시간");
	JCheckBox hourBox2 = new JCheckBox("2시간");
	JCheckBox minuteBox = new JCheckBox("30분");
	
	String[] userString;
	
	user(int pcIndex) {
		//System.out.println("user");
		setTitle(pcIndex + "pc 이용중");
		this.setSize(800, 600);
		setContentPane(userPanel);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		userString = Main.pm.db.memberSelect(pcIndex);
		timer = new Timer(userString);
		name = new JLabel("이름 : " + userString[0]);
		id = new JLabel("ID : " + userString[1]);
		time = new JLabel("시간 : " + userString[2]);
		pay = new JLabel("금액 : " + userString[3]);
		how = new JLabel(userString[4]);
		pcLog = new JLabel(userString[5] + " pc");
		
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
	
	public void user(user user) {
		this.user = user;
	}
	
	public void user() {
		userString = Main.pm.db.memberSelect(Integer.parseInt(userString[5]));
		
		name.setText("이름 : " + userString[0]);
		id.setText("ID : " + userString[1]);
		time.setText("시간 : " + userString[2]);
		pay.setText("금액 : " + userString[3]);
		how.setText(userString[4]);
		
		revalidate();
		repaint();
		
		Main.pm.PCrepaint(userString);
	}
	
	private class logoutAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			
			// 유저배열에 자신의 시간과 금액 시간 대입
			userString[2] = timer.userString[2];
					
			Main.pm.db.memberLogout(userString);
			Main.pm.Logout();
			timer.interrupt();
			timer = null;
			dispose();
		}
	}
	
	private class timeAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String check[] = new String[4];
			int hour=0;
			System.out.println("time");
			
			if(hourBox1.isSelected()) {
				hour += 1;
			}
			if(hourBox2.isSelected()) {
				hour += 2;
			}
			check[0] = Integer.toString(hour);
			if(minuteBox.isSelected()) {
				check[1] = "30";
			} else {
				check[1] = "00";
			}
			
			check[2] = "00";
			
			if(First.isSelected()) {
				check[3] = "선불";
			} else if(Later.isSelected()) {
				check[3] = "후불";
			}
			
			if(timer.getState() != Thread.State.NEW) {
				for(int i=0; i<3; i++) {
					check[i] = Integer.toString(Integer.parseInt(check[i]) + timer.TimeInt[i]);
					timer.TimeInt[i] = Integer.parseInt(check[i]);
				}
				String Time = Main.pm.db.memberUpdate(check, userString[1]);			
			} else {
				String Time = Main.pm.db.memberUpdate(check, userString[1]);			
				timer.Timer(Time, user);
				timer.start();
			}
			
			user();
		}
		
	}
}
