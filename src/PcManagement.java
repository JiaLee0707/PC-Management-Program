import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PcManagement extends JPanel{
	
	DB db;
	JPanel pc;
	JPanel menu;
	
	public void PcManagement() {
		db = new DB();
		pc = new JPanel(new GridLayout(4, 4));
		menu = new JPanel(new GridLayout(0, 1, 10, 10));
		
		setLayout(new BorderLayout(30, 20));

		// pc
		JButton[] pcButton = new JButton[16];
		for(int i=0;i<16; i++) {
			pcButton[i] = new JButton(i+1+" pc");
			pc.add(pcButton[i]);
		}
		pc.setVisible(true);

		// 메뉴
		JButton member = new JButton("회원");
		JButton order = new JButton("주문");
		JButton pcOff = new JButton("pc 끄기");
		
		menu.add(member);
		menu.add(order);
		menu.add(pcOff);
		menu.setVisible(true);
		
		this.add(pc, BorderLayout.CENTER);
		this.add(menu, BorderLayout.EAST);
	
		pc.revalidate();
		pc.repaint();
	}

}
