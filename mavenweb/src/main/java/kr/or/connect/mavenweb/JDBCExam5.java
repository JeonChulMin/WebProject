package kr.or.connect.mavenweb;

import kr.or.connect.mavenweb.dao.RoleDao4;
import kr.or.connect.mavenweb.dao.RoleDao5;
import kr.or.connect.mavenweb.dto.Role;
import java.util.List;

public class JDBCExam5 {

	public static void main(String[] args) {
		
		RoleDao5 dao5 = new RoleDao5();
		List<Role> list = dao5.getRoles();
		
		for(Role role : list) {
			System.out.println(role);
		}
	}
}