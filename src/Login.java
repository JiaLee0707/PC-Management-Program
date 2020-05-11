import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JFrame {
	JPanel login = new JPanel(new GridBagLayout());
	JPanel join = new JPanel(new GridLayout(4, 2));

	LoginListener Loginlistener = new LoginListener();
	JoinListener Joinlistener = new JoinListener();
	
//	DB db;

	JLabel name = new JLabel("NAME");
	JLabel id = new JLabel("ID");
	JLabel pw = new JLabel("PW");
	JTextField nameText = new JTextField();
	JTextField idText = new JTextField();
	JTextField pwText = new JTextField();

	JButton loginBut = new JButton("로그인");
	JButton backBut = new JButton("회원가입");

	String managerId = "manager";
	String managerPw = "1234";
	String Userid, Userpw;

	String user;
	
	public Login(String user) {
		System.out.println("login");
		setTitle(user + "pc Login");
		this.user = user;
		this.setSize(300, 200);
		Login();		
	}

	public void Login() {
		System.out.println("login-메소드");
//		LoginListener Loginlistener = new LoginListener();// "login");
//		JoinListener Joinlistener = new JoinListener();// "login");
//		lgoin = new JPanel(new GridLayout(3, 2));
//		login = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		gbc.weightx = 1.0;
		gbc.weighty = 2.0;

		loginBut.addActionListener(Loginlistener);
		backBut.addActionListener(Joinlistener);

		gbc.gridx = 0;
		gbc.gridy = 0;
		login.add(id, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;

		login.add(idText, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		login.add(pw, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		login.add(pwText, gbc);

		if (!user.equals("manager")) {
			gbc.gridx = 0;
			gbc.gridy = 2;
			login.add(loginBut, gbc);
			gbc.gridx = 1;
			gbc.gridy = 2;
			login.add(backBut, gbc);
		} else {
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridwidth = 3;
			gbc.gridheight = 1;
			login.add(loginBut, gbc);
		}

		setContentPane(login);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);

		revalidate();
		repaint();
	}

	public void join() {
		System.out.println("login-join");

		join.add(name);
		join.add(nameText);
		join.add(id);
		join.add(idText);
		join.add(pw);
		join.add(pwText);
		join.add(backBut);

		setContentPane(join);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);

		revalidate();
		repaint();
	}

	private class LoginListener implements ActionListener {

		public void Listener() {
			System.out.println("login-action메소드");
			Userid = idText.getText();
			Userpw = pwText.getText();
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("login-action");
			JButton b = (JButton) e.getSource();

			Listener();
			if (user.equals("manager")) {
				if (Userid.equals(managerId) && Userpw.equals(managerPw)) {
					dispose();
					Main.pm.PcManagement();
				} else {
					JOptionPane.showMessageDialog(null, "ID 또는 PW가 틀렸습니다.", "Message", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				String[] member = Main.pm.db.memberLogin(Userid, Userpw, user);
				if(member==null) {
					JOptionPane.showMessageDialog(null, "ID 또는 PW가 틀렸습니다.", "Message", JOptionPane.ERROR_MESSAGE);
				} else {
					Main.pm.Login(member, user);
					dispose();
				}
			}
		}
	}

	private class JoinListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.out.println("login-joinaction");
			if(getContentPane() == login) {
				join();
			} else {//if(getContentPane() == join){
				Main.pm.db.memeberInsert(nameText.getText(), idText.getText(), pwText.getText());
				dispose();
			}
		}
	}

}
