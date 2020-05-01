import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class member extends JFrame{
	
	JPanel member;
	
	JLabel name  = new JLabel("NAME");
	JLabel id = new JLabel("ID");
	JLabel pw = new JLabel("PW");
	JTextField nameText = new JTextField();
	JTextField idText = new JTextField();
	JTextField pwText = new JTextField();

	public member() {
		member = new JPanel(new GridLayout(3, 2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 200);
		
		
		JButton loginBut = new JButton("로그인");
		loginBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton but = (JButton)e.getSource();
				
				//manager 아이디
				if((idText.getText()).equals("manager")) {
					if((pwText.getText()).equals("1234")) {
						dispose();
						Main.pm.PcManagement();
					}else {
						pwText.setText("");
//						pwText.setToolTipText("비밀번호가 잘못되었습니다");
					}
				} else {
					idText.setText("");
//					idText.setToolTipText("아이디가 잘못되었습니다");
				}
			}
			
		});
		
		JButton backBut = new JButton("회원가입");
		backBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton but = (JButton)e.getSource();
				join();
			}
		});
		
		setContentPane(member);
		//managerFrame.setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		
		member.add(id);
		member.add(idText);
		member.add(pw);
		member.add(pwText);
		member.add(loginBut);
		member.add(backBut);
	}
	
	public void join() {
		member.setLayout(new GridLayout(4, 2));
	}
}
