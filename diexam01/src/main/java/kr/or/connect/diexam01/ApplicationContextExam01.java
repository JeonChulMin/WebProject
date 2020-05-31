package kr.or.connect.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextExam01 {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//객체를 생성할 스프링 공장을 생성, xml 파일에 정보 넣어놨으니 그대로 동작하면 되다고 알려줌
		//resources 안 xml은 자동으로 classpath로 등록됨
		System.out.println("초기화 완료!");
		
		UserBean userBean = (UserBean)ac.getBean("userBean");
		//getBean() 함수 인자로 "userBean"을 주었는데 인자는 xml 파일로 가서 이 인자와 일치하는 id를 찾아서 같이 등록되어 있는 class 명을 보고 class를 생성해서 리턴
		userBean.setName("kim");
		
		System.out.println(userBean.getName());
		
		UserBean userBean2 = (UserBean)ac.getBean("userBean");
		
		if(userBean == userBean2)
			System.out.println("같은 인스턴스");
		
		//싱글톤 패턴을 이용, getBean을 통해 요청을 하더라도 계속해서 만드는 것이 아니라 하나 만든 것을 계속 이용한다
		
		//이렇게 객체를 대신 생성해주는 기능을 IoC, 제어의 역전이라고 한다.
	}

}
