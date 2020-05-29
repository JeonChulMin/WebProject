package kr.or.connect.mavenweb;

import kr.or.connect.mavenweb.dao.RoleDao4;
import kr.or.connect.mavenweb.dto.Role;


public class JDBCExam4 {

	public static void main(String[] args) {
		int roleId = 500;
		String description = "CEO";
		Role role = new Role(roleId, description);
		RoleDao4 dao4 = new RoleDao4();
		int updateCount = dao4.updateRole(role);
		
		System.out.println(updateCount); // 1이 반환됨
		//mysql에서 select 문해서 들어간 것 확인하기
	}
}