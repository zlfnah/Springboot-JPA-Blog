package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController //데이터만리턴
public class UserApiController {
	
	@Autowired //각 상황의 타입에 맞는 IoC컨테이너 안에 존재하는 Bean을 자동으로 주입해주게 된다.
	private UserService userService;

	/*
	 * @Autowired //빈자동생성 private HttpSession session;
	 */
	
	
	@PostMapping("/auth/joinProc")												//받는값 username,password,email
	public ResponseDto<Integer> save(@RequestBody User user) { //json데이터니까 무조건 requestbody
		System.out.println("UserApiController : save호출됨");
		//실제로 db에 insert를 하고 아래에서 return이 되면됨.
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //리턴값.  아마200뜰거임 자바오브젝트를 json으로 변환해서 리턴(Jackson)
		
	}
	/*
	 * //옛날방식의 로그인!!
	 * 
	 * @PostMapping("/api/user/login") 
	 * public ResponseDto<Integer>login(@RequestBody User user, HttpSession session){ //세션호출 위에 , @Autowired private HttpSession session; 없이
	 * ResponseDto매개변수에 HttpSession session적어도 가능
	 * 
	 * System.out.println("UserApiController : login호출됨"); User principal =
	 * userService.로그인(user); //principal(접근주체) if(principal!=null) { //세션실행
	 * session.setAttribute("principal", principal); } return new
	 * ResponseDto<Integer>(HttpStatus.OK.value(),1); //리턴값. 아마200뜰거임 자바오브젝트를 json으로
	 * 변환해서 리턴(Jackson) }
	 */
	
}
