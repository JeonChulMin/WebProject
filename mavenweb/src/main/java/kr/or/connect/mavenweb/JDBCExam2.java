package kr.or.connect.mavenweb;

import kr.or.connect.mavenweb.dao.RoleDao2;
import kr.or.connect.mavenweb.dto.Role;

public class JDBCExam2 {

	public static void main(String[] args) {
		int roleId = 500;
		String description = "CTO";
		
		Role role = new Role(roleId, description);
		
		RoleDao2 dao2 = new RoleDao2();
		int insertCount = dao2.addRole(role);
		
		System.out.println(insertCount); // 1�� ��ȯ��
		//mysql���� select ���ؼ� �� �� Ȯ���ϱ�
	}
}