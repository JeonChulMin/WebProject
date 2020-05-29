package kr.or.connect.mavenweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.connect.mavenweb.dto.Role;

public class RoleDao {
	
	//db에 한 작업시에 매번 로그인 할 수 없으니 위에 아예 지정해놓고 변수로 사용
	private static String dbUrl = "jdbc:mysql://localhost:3306/connectdb";
	private static String dbUser = "connectuser";
	private static String dbPassword = "connect123!@#";
	
	public Role getRole(Integer roleId) {
		
		Role role = null; // 받아올 객체
		
		Connection conn = null; // 연결을 맺어주는 객체 
		PreparedStatement ps = null; // 명령을 선언할 객체
		ResultSet rs = null; // 결과 값을 담아낼 객체
		
		try {
			//드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			//connection 객체 얻어오기
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			
			//role 테이블에 id랑 설명을 가져옴, 조건으로는 ?를 주었는데 이 메소드를 호출할 때마다 인자값이 다를 텐때 prepareStatement를 사용하게 되면 ?안에 값을 넣어서 매번 다르게 가져올 수 있음
			String sql = "SELECT description, role_id FROM role WHERE role_id = ?";
			//mysql에서 출력해서 나오는 컬럼 순이 아니라 위에 sql 문자열에 설정한 컬럼 순으로 나오게 됨
			ps = conn.prepareStatement(sql);
			ps.setInt(1, roleId);
			
			// 실행
			rs = ps.executeQuery();
			
			//결과 값이 없을 경우를 처리하기 위해 조건문을 사용해서 처리, next()는 결과가 있다면 결과를 반환하고 다음 레코드로 커서 이동
			if(rs.next()) {
				// 값 꺼내오기
				String description = rs.getString(1); //1번째 컬럼의 값, 컬럼의 순서를 이용해 가져오는 방법
				int id = rs.getInt("role_id"); // 컬럼 이름을 이용해 가져오는 방법
				role = new Role(id, description); // 해당 객체에 넣어주기
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//객체 닫아주기
			if(rs != null) { //조건문을 사용하여 null을 체크하는 이유는 ps 부분에서 에러가 난다면 rs는 null 이므로 NullException 에러가 날 수 있음 그것을 방지하기 위해 사용
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
		
		
		
		
		
		return role;
	}
}