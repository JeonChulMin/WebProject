package kr.or.connect.mavenweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.connect.mavenweb.dto.Role;

public class RoleDao2 {
	
	//db�� �� �۾��ÿ� �Ź� �α��� �� �� ������ ���� �ƿ� �����س��� ������ ���
	private static String dbUrl = "jdbc:mysql://localhost:3306/connectdb";
	private static String dbUser = "connectuser";
	private static String dbPassword = "connect123!@#";
	
	public int addRole(Role role) {
		int insertCount = 0; // �� ���� �����ߴ� �� �˷��ֱ� ���� ���� ex) 1�� ����
		
		Connection conn = null; // ������ �ξ��ִ� ��ü 
		PreparedStatement ps = null; // ����� ������ ��ü
		
		//insert�� ��� �ʿ� ���� rs��ü �ʿ� ����
		
		try {
			//����̹� �ε�
			Class.forName("com.mysql.jdbc.Driver");
			
			//connection ��ü ������
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			
			String sql = "INSERT INTO role (role_id, description) VALUES (?, ?)";
		
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, role.getRoleId());
			ps.setString(2, role.getDescription());
			
			insertCount = ps.executeUpdate(); //insert, update, delete �� ������ �� �� �޼ҵ� ����, select �� �ٸ���.
			//���� ����� 2������ ������. 1) INSERT, DELETE, UPDATE �� ���� ��
			// 2) �ƹ� ������ ������ 0
			
			
			
			} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//��ü �ݾ��ֱ�
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