package kr.or.connect.mavenweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.connect.mavenweb.dto.Role;

public class RoleDao2 {
	
	//db에 한 작업시에 매번 로그인 할 수 없으니 위에 아예 지정해놓고 변수로 사용
	private static String dbUrl = "jdbc:mysql://localhost:3306/connectdb";
	private static String dbUser = "connectuser";
	private static String dbPassword = "connect123!@#";
	
	public int addRole(Role role) {
		int insertCount = 0; // 몇 건을 수정했는 지 알려주기 위해 선언 ex) 1건 삽입
		
		Connection conn = null; // 연결을 맺어주는 객체 
		PreparedStatement ps = null; // 명령을 선언할 객체
		
		//insert는 결과 필요 없음 rs객체 필요 없음
		
		try {
			//드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			//connection 객체 얻어오기
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			
			String sql = "INSERT INTO role (role_id, description) VALUES (?, ?)";
		
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, role.getRoleId());
			ps.setString(2, role.getDescription());
			
			insertCount = ps.executeUpdate(); //insert, update, delete 는 실행할 때 이 메소드 실행, select 와 다르다.
			//리턴 결과는 2가지로 나뉜다. 1) INSERT, DELETE, UPDATE 된 행의 수
			// 2) 아무 리턴이 없으면 0
			
			
			
			} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//객체 닫아주기
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return insertCount;
	}
}