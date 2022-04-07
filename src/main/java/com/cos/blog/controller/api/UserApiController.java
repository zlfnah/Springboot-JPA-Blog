package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
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
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping("/auth/joinProc")												//받는값 username,password,email
	public ResponseDto<Integer> save(@RequestBody User user) { //json데이터니까 무조건 requestbody
		System.out.println("UserApiController : save호출됨");
		//실제로 db에 insert를 하고 아래에서 return이 되면됨.
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //리턴값.  아마200뜰거임 자바오브젝트를 json으로 변환해서 리턴(Jackson)
		
	}
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){ //@RequestBody=json데이터받기위해
		userService.회원수정(user); //서비스호출
		//여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이됨 허나 세션값은 변경되지 않은 상태이기 때문에 직접 세션값은 변경해야함.
		
		//세션 등록
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())); //강제 로그인처리
		SecurityContextHolder.getContext().setAuthentication(authentication);//세션 등록끝
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //호출 끝나면 리턴값
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
