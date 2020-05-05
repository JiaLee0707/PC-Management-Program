import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	DB() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pcmanagement?serverTimezone=Asia/Seoul&useSSL=false", "root", "mirim2");
			System.out.println("DB 연결 완료");
		} catch (SQLException ex) {
			System.out.println("SQLException:" + ex);
		} catch (Exception ex) {
			System.out.println("Exception:" + ex);
		}
	}
	
	public void select(String id, String pw) {
		try {
			String sql = "SELECT * FROM memeber WHERE id=? AND pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
		} catch (SQLException ex) {
			System.out.println("SQLException:" + ex);
		} catch (Exception ex) {
			System.out.println("Exception:" + ex);
		}
	}
	
	public void memeberInsert(String name, String id, String pw) {
		try {
			String sql = "insert into member (name, id, pw, time, pay, LOG) values (?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			pstmt.setString(4, "00:00:00");
			pstmt.setString(5, "0");
			pstmt.setBoolean(6, false);
		
			pstmt.executeUpdate();
			
//			sql = "select * from member";
//			pstmt = conn.prepareStatement(sql);
//			ResultSet srs = pstmt.executeQuery();
//			while(srs.next()) {
//				System.out.print(srs.getString("name")+" ");
//				System.out.print(srs.getString("id")+" ");
//				System.out.print(srs.getString("pw")+" ");
//				System.out.print(srs.getString("time")+" ");
//				System.out.print(srs.getString("pay")+" ");
//				System.out.println();
//			}
		} catch (SQLException ex) {
			System.out.println("SQLException:" + ex);
		} catch (Exception ex) {
			System.out.println("Exception:" + ex);
		}
	}
	
	
}

