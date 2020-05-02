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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pcmanagement", "root", "apmsetup");
			System.out.println("DB 연결 완료");
			
			String sql = "insert into member (name, id) values (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "이지아이이잉");
			pstmt.setString(2, "이이이잉");
		
			pstmt.executeUpdate();
			
			sql = "select * from member";
			pstmt = conn.prepareStatement(sql);
			ResultSet srs = pstmt.executeQuery();
			while(srs.next()) {
				System.out.print(srs.getString("name")+" ");
				System.out.print(srs.getString("id")+" ");
				System.out.println();
			}
		} catch (SQLException ex) {
			System.out.println("SQLException:" + ex);
		} catch (Exception ex) {
			System.out.println("Exception:" + ex);
		}
	}
}

