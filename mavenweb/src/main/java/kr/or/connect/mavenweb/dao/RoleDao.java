package kr.or.connect.mavenweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.connect.mavenweb.dto.Role;

public class RoleDao {
	
	//db�� �� �۾��ÿ� �Ź� �α��� �� �� ������ ���� �ƿ� �����س��� ������ ���
	private static String dbUrl = "jdbc:mysql://localhost:3306/connectdb";
	private static String dbUser = "connectuser";
	private static String dbPassword = "connect123!@#";
	
	public Role getRole(Integer roleId) {
		
		Role role = null; // �޾ƿ� ��ü
		
		Connection conn = null; // ������ �ξ��ִ� ��ü 
		PreparedStatement ps = null; // ����� ������ ��ü
		ResultSet rs = null; // ��� ���� ��Ƴ� ��ü
		
		try {
			//����̹� �ε�
			Class.forName("com.mysql.jdbc.Driver");
			
			//connection ��ü ������
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			
			//role ���̺� id�� ������ ������, �������δ� ?�� �־��µ� �� �޼ҵ带 ȣ���� ������ ���ڰ��� �ٸ� �ٶ� prepareStatement�� ����ϰ� �Ǹ� ?�ȿ� ���� �־ �Ź� �ٸ��� ������ �� ����
			String sql = "SELECT description, role_id FROM role WHERE role_id = ?";
			//mysql���� ����ؼ� ������ �÷� ���� �ƴ϶� ���� sql ���ڿ��� ������ �÷� ������ ������ ��
			ps = conn.prepareStatement(sql);
			ps.setInt(1, roleId);
			
			// ����
			rs = ps.executeQuery();
			
			//��� ���� ���� ��츦 ó���ϱ� ���� ���ǹ��� ����ؼ� ó��, next()�� ����� �ִٸ� ����� ��ȯ�ϰ� ���� ���ڵ�� Ŀ�� �̵�
			if(rs.next()) {
				// �� ��������
				String description = rs.getString(1); //1��° �÷��� ��, �÷��� ������ �̿��� �������� ���
				int id = rs.getInt("role_id"); // �÷� �̸��� �̿��� �������� ���
				role = new Role(id, description); // �ش� ��ü�� �־��ֱ�
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//��ü �ݾ��ֱ�
			if(rs != null) { //���ǹ��� ����Ͽ� null�� üũ�ϴ� ������ ps �κп��� ������ ���ٸ� rs�� null �̹Ƿ� NullException ������ �� �� ���� �װ��� �����ϱ� ���� ���
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