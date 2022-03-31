package com.cos.blog;
//기본적으로 설치되는 클래스
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}
/*1. get 요청은 주소에 담아서 브라우저를 통해 보낼 수 있고, body 데이터 없음. form태그 형식으로.
2. post 요청도 form 태그로 가능하지만 put, delete 요청과 모두 통일을 위해 javascript로 ajax요청+데이터는 json형태로 통일(수업에서는 이 방식 사용)하거나 form:form 태그로 4가지 요청을 다 커버한다.
3. 스프링 컨트롤러는 key=value 형태의 데이터를 자동으로 파싱하여 변수에 담거나 object로 파싱해서 받아줄 수 있다.(해당 object의 setter 꼭 필요)
4. key=value 형태가 아닌 Json데이터나 일반 text 데이터는 @requestbody 어노테이션이 필요하다.
 * 
 * 
 * 1. get요청 (select) 
 * http://localhost:800/blog/user?username=ssar
 * 특징:body로 데이터를 담아 보내지 않음.
 * 
 * 
 * 2.Post, put, Delete 요청 (데이터를 변경) (insert, update)
 * 데이터를 담아보내야 할 것이 많다.
 * -ex)username, password, email, addresss, gender, createDate등
 * post보내는법 ->form태그 method='post'
 * 여기서 form태그는 =>get요청과 post요청밖에 안됨 (key=value)
 * 그래서 자바스크립트 요청을 해야함.
 * 통일:자바스크립트 ajax요청+데이터는 json으로 통일하는게 좋다.
 *form:form태그->post요청,put,delete,get요청 다가능. 스프링프레임워크임. 허나 이번 프로젝트에서 사용x
 * 
 * 스프링 컨트롤러는 key=value 데이터를 자동으로 파싱하여 변수에 담아준다.

가령 get요청은 key=value이고 post요청중에 x-www-form-urlencoded (form태그를 만들어서 데이터 전송) 시에도 key=value 이기 때문에 이러한 데이터는 아래와 같이 함수의 파라메터로 받을 수 있다.

PostMapping("/home")
public String home(String username, String email){
	
    return "home";
}

3. 오브젝트로 데이터 받기
post방식의 key=value (x-www-form-urlencoded)
username=ssar
password=1234
 * 
 * 5. key=value가 아닌 데이터는 어떻게 파싱할까?
json 데이터나 일반 text데이터는 스프링 컨트롤러에서 받기 위해서는 @RequestBody 어노테이션이 필요하다.

** 기본전략이 스프링 컨트롤러는 key=value 데이터를 파싱해서 받아주는 일을 하는데 다른 형태의 데이터 가령 json 같은 데이터는 아래와 같이 생겼다.

{
    "username":"ssar",
    "password":"1234"000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000
     
}
이런 데이터는 스프링이 파싱해서 오브젝트로 받지 못한다. 그래서 @RequestBody 어노테이션을 붙이면 MessageConverter 클래스를 구현한 Jackson 라이브러리가 발동하면서 json 데이터를 자바 오브젝트로 파싱하여 받아준다.
 * */


 