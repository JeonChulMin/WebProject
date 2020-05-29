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
		
		System.out.println(insertCount); // 1이 반환됨
		//mysql에서 select 문해서 들어간 것 확인하기
	}
}