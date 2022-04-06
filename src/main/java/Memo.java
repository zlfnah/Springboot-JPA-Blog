
public class Memo {
/*스프링작동원리
 * 1.톰캣시작
 * 2.필터들이 메모리들로 올라감(ex권한필터, 한글인코딩필터등등)
 * 3.디스패처 메모리들로 올라감(중간에 어떤 주소가 들어오는지 확인하고 주소에 맞는 컨트롤러를 연결시켜줌)
 * 4.컨트롤러, 서비스, JPA Repository, 영속성 컨텍스트(대기상태 리퀘스트 요청이와야지 뜸) datasource는 자동으로 뜸
 * 5. 예시 request=http://localhost:8000/login(post)->body에 username들어오고,password데이터들어옴 이 2개의 값을 디스패처가확인
 * 그 후 Controller가 저위에 데이터를 받고 끝남 즉 함수를 만들고 body데이터를 넣음 그 후  서비스로 넘김
 * 서비스한테 login시작 로그인을 하기위해 db에 셀렉트 질의응답을함. 이 역할을->JPA repository에서 함
 * JPA repository는 자기가 들고있는 함수를 호출해 영속성 컨테스트에 db에 로그인정보 있는지확인 select * from user where username=? and password=? 
 * 영속성 컨텍스트는 데이터소스에 db정보있는지 확인 그후 확인결과값  리턴리턴 다시 서비스까지
 * */
	
	
	
	/*스프링작동원리 ex 회원가입
	 * 1.톰캣시작
	 *2.회원가입은 insert.
	 *Controller에서 회원가입(){바디데이터}메소드 생성 후 서비스에 회원가입 해줘요청 컨트롤러에서들어온순간 데이터베이스연동됨
	 *Service에서는 트랜잭션 실행.
	 *서비스에서는 레포지토리에 insert요청. insert는 처음이니까
	 *영속성컨텍스트에서 db에 질의 다시거꾸로 리턴응답해서 컨트롤러까지감 컨트롤러에서-> 서비스종료 
	 *
	 *
	 **/
}
