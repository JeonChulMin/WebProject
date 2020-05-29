# 목차

### [JSTL와 EL](#jstl과-el)

### [Maven](#maven이란)

### [JDBC](#jdbc란)

### [try-with-resources](#try-with-resources)

# JSTL과 EL

JSTL(JavaServer Pages Standard Tag Library)

JSTL은 태그를 통해 JSP 코드를 관리하는 라이브러리로서, 가독성을 좋게 해준다.

라이브러리이기 때문에 다운받아서 프로젝트에 추가해서 사용해야 한다.

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
```

c라는 prefix로 시작하는 태그는 위 uri에서 가져오겠다라는 것을 알려주는 의미

HTML 코드 내에 Java 코드인 `<%= if %>`  :arrow_right: `<c:if>` , `<%= for%>`  :arrow_right: `<c:forEach>` 로 대체하여 사용

JSTM은 XML 데이터 처리와 조건문, 반복문, 국제화와 지역화와 같은 일을 처리학 위한 JSP 태그 라이브러리를 추가하여 JSP 사양을 확장

JSTL은 JSP 페이지 내에서 자바 코드를 바로 사용하지 않고 로직을 내장하는 효율적인 방법을 제공한다.



| 태그            | 설명                                                   |
| --------------- | ------------------------------------------------------ |
| `<c:if>`        | 조건문 if를 사용하는 것처럼 조건 블록 설정             |
| `<c:forEach>`   | 다른 언어의 loop 문 items 속성에 배열을 할당할 수 있음 |
| `<c:set>`       | 변수에 값을 할당                                       |
| `<c:out>`       | 값을 출력                                              |
| `<c:choose>`    | switch 문과 비슷                                       |
| `<c:when>`      | switch 문의 case에 해당                                |
| `<c:otherwise>` | swtich 문의 default에 해당                             |



예

```html
1. <c:set> 변수 선언하는 태그
<c:set var="변수이름" value="값" />
이렇게 선언하면 ${변수이름}으로 사용할 수 있다.
이 변수는 내부적으로 자바 변수로 선언되는게 아니라 page 데이터 영역의 애트리뷰트로 선언되기 때문에 <%=변수이름%> 형태로 출력될 수 없다.

2. <c:remove> 변수 제거할 때 사용하는 태그
<c:remove var="변수이름" />

특정 영역의 변수만 제거하고 싶으면
<c:remove var="변수이름" scope="request"/>

3. <c:out> 변수 내용을 출력할 때 사용하는 태그
EL로도 출력할 수 있지만, 아래와 같이 태그가 포함된 변수를 escapeXml 항목에 true|false 지정해주어 태그를 포함해서 출력할지, 적용해서 출려갈지 결정할 수 있다.
<c:set var="aaa" value="<font color=red>123</font>"></c:set>
<c:out value="${aaa}" escapeXml="true"/>

4. <c:if test="true|false"> test 안의 내용이 t/f에 따라서 출력할 지 안할 지를 결정
<c:if test="${ 10 > 0 }">
	True<br>
</c:if>
test 부분에 EL을 사용해서 구체적으로 활용할 수 있다.

5. <c:choose> switch문
<c:choose>
	<c:when test="${10 > 9}">
		10<br>
	</c:when>
	<c:when test="${9 > 8}">
		9<br>
	</c:when>
	<c:otherwise>
		0이상<br>
	</c:otherwise>
</c:choose>
맨 처음 10만 출력된다.

6. <c:forEach> for문과 비슷
1~10 출력
<c:forEach var="임시변수명" begin="1" end="10">
	${임시변수명}<br>
</c:forEach>

1~10에서 2씩 증가하면서 출력
<c:forEach var="임시변수명" begin="1" end="10" step="2">
	${임시변수명}<br>
</c:forEach>

배열의 내용 하나씩 출력
<c:forEach var="임시변수명" items="${배열이름}">
	${임시변수명}<br>
</c:forEach>

7. <c:forTokens> 문자열에 포함된 토큰을 분리해서 각각의 토큰에 대해 반복 처리를 수행하도록 만드는 기능
<c:forTokens var="임시변수명" items="111!222@333" delims="!@">
	${임시변수명}<br>
</c:forTokens>
111, 222가 따로 분리되어 출력된다.

8. <fmt:formatDate> 날짜와 관련된 태그
<fmt:formatDate value="<%=new Date() %>" type="both"/>
type이 both이면 날짜와 시간 모두 출력, date/time 둘 중 하나만 쓰면 하나만 출력

9. <fmt:formatNumber> 숫자와 관련된 태그
세 자리마다 쉼표가 출력
<fmt:formatNumber value="1000000" groupingUsed="true"/>
소수점 둘째짜리까지만 출력
<fmt:formatNumber value="3.141592" pattern="#.##"/>
```



EL(Expression Language)

EL은 JSP의 출력 문법을 대체하는 표현 언어

`<%= i %>` JSP에서의 값 표기법이며, i는 변수

`${ i }` EL에서의 값 표기법이며, i는 이름



EL 표기법에서 파라미터 값은 param 키워드를 통해 가져올 수 있다.

또한 JSP 값 표기법에서 파라미터는 문자열이지만, EL에서 숫자는 숫자로, 문자열은 문자열로 인식한다.

예

요청 : ~ / ~ /example.jsp?a=10

```
<%= request.getParameter("a") + 100 %> // 10100, a가 문자열이기 때문에
${param.a + 100}  // 110, 10을 정수형으로 인식
```





JSP 파일에 스크립틀릿 <%= %> 를 사용하는 것보다 JSTL, EL을 사용하는 것을 권장한다고 한다.

그 이유는 HTML 태그 들여쓰기시 가독성이 떨어지기 때문이다.

보통 JSTL와 EL은 함께 사용



## REFERENCES

- https://daesuni.github.io/jstl/
- https://victorydntmd.tistory.com/156
- https://programmingsummaries.tistory.com/84





---

# Maven이란

Maven은 개발시에 반복적으로 진행했던 작업들을 지원하기 위한 도구이다.

Maven을 사용하면 빌드, 패키징, 문서화, 테스트와 테스트 리포팅, git, 의존성 관리, SVN 등과 같은 형상관리서버와 연동, 배포 등의 작업을 손쉽게 할 수 있다.

Maven을 이해하려면 CoC(Convention over Configuration)라는 단어를 먼저 이해해야 한다.

CoC란 일종의 관습을 말하는데, 예를 들면 소스 파일은 특정 위치에 컴파일된 파일들은 어떤 위치에 있어야 한다는 등 미리 위치를 정해놨다는 것이다.

이 말은 관습에 이미 익숙한 사용자는 Maven을 잘 사용할 수 있지만 익숙하지 않은 사용자는 이러한 제약사항에 대해 어려움을 느낄 수 있다.



Maven의 이점

편리한 의존성 라이브러리 관리, JSTL의 경우 사용하기 위해서는 파일을 다운로드 하여 /WEB-INF/lib 폴더에 복사하여 사용했었는데 이와 같은 라이브러리들이 많아진다면 관리하기 불편해진다.

Maven을 사용하면 설정 파일(pom.xml)에 몇 줄 추가해줌으로써 직접 다운로드할 필요없이 라이브러리를 사용할 수 있다.

프로젝트가 클수록 개발자 또한 많아지는데 Maven을 사용하게 되면 Maven에 설정한 대로 모든 개발자가 일관된 방식으로 빌드를 수행할 수 있게 된다.

Maven은 또한 다양한 플러그인을 제공해줘서, 굉장히 많은 일들을 자동화시킬 수 있다.



Maven 기본

Maven 프로젝트를 생성하면 하위 폴더에 pom.xml 파일이 생긴다.

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>kr.or.connect</groupId>
    <artifactId>examples</artifactId>
    <packaging>jar</packaging>
    <version>1.0-SNAPSHOT</version>
    <name>mysample</name>
    <url>http://maven.apache.org</url>
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```



| 태그         | 설명                                                         |
| ------------ | ------------------------------------------------------------ |
| project      | pom.xml 파일의 Root Element                                  |
| modelVersion | POM model의 버전                                             |
| groupId      | 프로젝트를 생성하는 조직의 고유 아이디를 결정, 일반적으로 도메인 이름을 거꾸로 적는다. |
| artifactId   | 해당 프로젝트에 의하여 생성되는 artifact의 고유 아이디를 결정, Maven을 이용하여 pom.xml을 빌 드할 경우 다음과 같은 규칙으로 artifact가 생성된다. artifactid-version.packaging, examples-1.0-SNAPSHOT.jar |
| packaging    | 해당 프로젝트를 어떤 형태로 packaging 할 것인지 결정, jar, war, ear 등이 해당 |
| version      | 프로젝트의 현재 버전, 추후 살펴보겠지만 프로젝트가 개발 중일 때는 SNAPSHOT을 접미사로 사용, Maven의 버전 관리 기능은 라이브러리 관리를 편하게 한다. |
| name         | 프로젝트 이름                                                |
| url          | 프로젝트 사이트 URL                                          |



Maven을 이용할 경우 얻게 되는 이점 중 하나는 Dependency Management 기능이다.

`<dependencies>` 엘리먼트 안에 필요한 라이브러리를 지정하여 사용.



## Maven 프로젝트 생성

1. 이클립스에서 File에서 New를 선택
2. Maven Project를 찾아서 클릭
3. New Maven Project 창이 나오는데 Next 클릭

<img src="images/maven_3.PNG" alt="maven_3" style="zoom: 60%;" />



4.  그 다음 프로젝트 탬플릿인 Archetype을 선택해야 한다. 여기서는 Maven을 이용하여 웹 애플리케이션을 개발할 것이므로 아래 그림과 같이 `maven-archtype-webapp` 을 선택한 후 Next 클릭
   -  :heavy_plus_sign: 어떤 아키타입을 선택하느냐에 따라 생성하는 파일이나 라이브러리가 다르다.

![maven_1](images/maven_1.PNG)



5. 프로젝트 이름 설정
   - Group Id 는 보통 프로젝트의 도메인이나 회사의 이름을 거꾸로 적는다.
   - Artifact Id 는 프로젝트 이름
   - 버전은 보통 아래와 같이 설정하므로 따로 수정 x
   - Package 이름은 Group Id와 Artifact Id가 조합된 이름이 된다.
   - Finish를 누르면 프로젝트 생성

![maven_2](images/maven_2.PNG)



6. 추가로 필요한 폴더 추가
   - 아래 그림은 프로잭트의 디렉터리 구조
   - 빨간색 부분은 자동으로 생성되지 않아서 필요에 따라 만들어서 사용
   - Maven으로 생성된 프로젝트의 경우 자바 소스는 src/main/java 폴더에 생성된다.
   - 웹 애플리케이션과 관련된 HTML, CSS 등은 src/main/webapp 폴더에서 작성해야 한다.



![maven_4](images/maven_4.PNG)

----

# JDBC란

- JDBC(Java Database Connectivity)
- 자바를 이용한 DB 접속과 SQL 문장 실행, 그리고 결과로 얻어진 데이터의 핸들링을 제공하는 방법과 절차에 관한 규약

- 자바 프로그램 내에서 SQL문을 실행하기 위한 자바 API

- JAVA는 표준 인터페이스인 JDBC API를 제공





## JDBC 환경 구축

- JDK 설치
- JDBC 드라이버 설치(Maven(pom.xml)에 의존성 추가)

```xml
<dependency>   
	<groupId>mysql</groupId>   
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.45</version>
 </dependency>
```





## JDBC 동작 순서

1. import java.sql.*; 
2. 드라이버 로드
3. Connection 객체를 생성 (DB에 **접속**하는 부분, connection 인터페이스)
4. Statement 객체를 생성 및 질의 수행 (SELECT 문과 같은 쿼리문 생성하고 실행, Statement 인터페이스)
5. SQL 문에 결과물이 있다면 ResultSet 객체를 생성 
6. 모든 객체를 닫음





## JDBC 클래스의 생성 관계

DriverManager :arrow_right: Connection :arrow_right: Statement :arrow_right: ResultSet





## JDBC 단계별 설명

1. import

```java
import java.sql.*;
```

2. 드라이버 로드

```java
Class.forName( "com.mysql.jdbc.Driver" );
```

- 패키지명.클래스, 해당 객체가 메모리에 올라감, DB 벤더에 따라 이름 달라짐

3. Connection 객체를 생성

```java
String dburl  = "jdbc:mysql://localhost/dbName";
Connection con =  DriverManager.getConnection ( dburl, ID, PWD );
```

- DB에 접속, IP 혹은 URL, ID, PWD 입력해서 접속



### 예 (oracle DB에 접속할 경우)

```java
public static Connection getConnection() throws Exception{
	String url = "jdbc:oracle:thin:@117.16.46.111:1521:xe";
	String user = "smu";
	String password = "smu";
	Connection conn = null;
	Class.forName("oracle.jdbc.driver.OracleDriver");
	conn = DriverManager.getConnection(url, user, password);
	return conn;
}
```



4. Statement 객체를 생성 및 질의 수행

```java
// Statement 생성
Statement stmt = con.createStatement();

// 질의 수행
ResultSet rs = stmt.executeQuery("select no from user");

// 참고
stmt.execute(“query”);             //any SQL
stmt.executeQuery(“query”);     //SELECT
stmt.executeUpdate(“query”);   //INSERT, UPDATE, DELETE
```

5. SQL 문에 결과물이 있다면 ResultSet 객체를 생성하고 결과 받기

```java
ResultSet rs = stmt.executeQuery("select no from user");
while (rs.next()) // false는 반환값이 없을 경우를 뜻함 
      System.out.println( rs.getInt( "no") );
```

6. 모든 객체를 닫음 (순차적으로 닫아줘야함)

```java
rs.close();
stmt.close();
con.close();
```



### 예)

```java
public List<GuestBookVO> getGuestBookList(){
		List<GuestBookVO> list = new ArrayList<>();
		GuestBookVO vo = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from guestbook";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				vo = new GuestBookVO();
				vo.setNo(rs.getInt(1));
				vo.setId(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setConetnt(rs.getString(4));
				vo.setRegDate(rs.getString(5));
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, ps, rs);
		}		
		return list;		
	}
```



```java
public int addGuestBook(GuestBookVO vo){
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "insert into guestbook values("
					+ "guestbook_seq.nextval,?,?,?,sysdate)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getConetnt());
			result = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, ps);
		}
		
		return result;
	}
```



```java
public static void close(Connection conn, PreparedStatement ps){
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {e.printStackTrace(); }
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {e.printStackTrace();}
		}
	}
```

- Spring JDBC를 이용할 것이므로 여기서는 JDBC가 이러한 순서로 동작되는 것 위주로 이해
- 어떻게 동작되는 것을 알아야 에러 발생시 잘 찾아낼 수 있다.





---

# try-with-resources



