package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.UserService;

@RestController //데이터만리턴
public class boardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")											                                                        //PrincipalDetail클래스에있는 유저정보필요
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) { //json데이터니까 무조건 requestbody
		boardService.글쓰기(board,principal.getUser());
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
