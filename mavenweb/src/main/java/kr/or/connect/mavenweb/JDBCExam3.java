package kr.or.connect.mavenweb;

import kr.or.connect.mavenweb.dao.RoleDao3;


public class JDBCExam3 {

	public static void main(String[] args) {
		int roleId = 500;
		
		RoleDao3 dao3 = new RoleDao3();
		int insertCount = dao3.deleteRole(roleId);
		
		System.out.println(insertCount); // 1이 반환됨
		//mysql에서 select 문해서 들어간 것 확인하기
	}
}