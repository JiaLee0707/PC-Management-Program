import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DB {

	Connection conn = null;
	PreparedStatement pstmt = null;

	DB() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/pcmanagement?serverTimezone=Asia/Seoul&useSSL=false", "root",
					"mirim2");
			System.out.println("DB 연결 완료");
		} catch (SQLException ex) {
			System.out.println("SQLException:" + ex);
		} catch (Exception ex) {
			System.out.println("Exception:" + ex);
		}
	}

	public String[] memberLogin(String id, String pw, String pcIndex) {
		//System.out.println("db select");
		String[] member = new String[6];
		try {
			String sql = "SELECT * FROM member WHERE id=? AND pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet srs = pstmt.executeQuery();
//			System.out.println("asdf");
			if(srs.next()) {
				sql = "Update member SET pcLOG=? WHERE id=? AND pw=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pcIndex);
				pstmt.setString(2, id);
				pstmt.setString(3, pw);				
				pstmt.executeUpdate();
			}

			sql = "SELECT * FROM member WHERE id=? AND pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			srs = pstmt.executeQuery();				
			
			if(srs.next()) {
				member[0] = srs.getString("name");				
				member[1] = srs.getString("id");
				member[2] = srs.getString("time");
				member[3] = srs.getString("pay");
				member[4] = srs.getString("how");
				member[5] = srs.getString("pcLOG");
				return member;
			}
		}catch(SQLException ex) {
			System.out.println("SQLException:" + ex);
		}catch(Exception ex) {
			System.out.println("Exception:" + ex);
		}
		return null;
	}
	
	public void memberLogout(String pcIndex) {
		try {
			String sql = "UPDATE member SET pcLOG=? WHERE pcLOG=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "0");			
			pstmt.setString(2, pcIndex);			
			pstmt.executeUpdate();
				
		}catch(SQLException ex) {
			System.out.println("Logout SQLException:" + ex);
		}catch(Exception ex) {
			System.out.println("Logout Exception:" + ex);
		}
	}

	public void memeberInsert(String name, String id, String pw) {
		//System.out.println("db insert");
		try {
			String sql = "insert into member (name, id, pw, time, pay, how, pcLOG) values (?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			pstmt.setString(4, "00:00:00");
			pstmt.setString(5, "0");
			pstmt.setString(6, null);
			pstmt.setString(7, "0");

			pstmt.executeUpdate();
			Main.pm.Join();
		} catch (SQLException ex) {
			System.out.println("SQLException:" + ex);
		} catch (Exception ex) {
			System.out.println("Exception:" + ex);
		}
	}
	
	public String[] memberSelect(int pcIndext) {
		String[] user = new String[6];
		try {
			String sql = "SELECT * FROM member WHERE pcLog=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pcIndext);
			ResultSet srs = pstmt.executeQuery();
			if(srs.next()) {
				user[0] = srs.getString("name");
				user[1] = srs.getString("id");
				user[2] = srs.getString("time");
				user[3] = srs.getString("pay");
				user[4] = srs.getString("how");
				user[5] = srs.getString("pcLog");
				return user;
			}
		} catch (SQLException ex) {
			System.out.println("SQLException:" + ex);
		} catch (Exception ex) {
			System.out.println("Exception:" + ex);
		}
		
		return null;
	}

	public void memberUpdate(String[] check, String id) { 
		int pay = Integer.parseInt(check[1])*1000;
		if(check[2]!=null) pay+=500;
		try {
			String sql = "UPDATE member SET TIME=?, pay=?, how=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "0"+check[1]+":"+ check[2] + ":00");			
			pstmt.setString(2, Integer.toString(pay));			
			pstmt.setString(3, check[0]);			
			pstmt.setString(4, id);			
			pstmt.executeUpdate();
				
		} catch(SQLException ex) {
			System.out.println("Logout SQLException:" + ex);
		} catch(Exception ex) {
			System.out.println("Logout Exception:" + ex);
		}
	}
	
//	public void Time() {
//		try {
//			String sql = "UPDATE member SET TIME=?, pay=?, how=? WHERE id=?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, "0"+check[1]+":"+ check[2] + ":00");			
//			pstmt.setString(2, Integer.toString(pay));			
//			pstmt.setString(3, check[0]);			
//			pstmt.setString(4, id);			
//			pstmt.executeUpdate();
//			
//		} catch(SQLException ex) {
//			System.out.println("Time SQLException:" + ex);
//		} catch(Exception ex) {
//			System.out.println("Time Exception:" + ex);
//		}
//	}
}
