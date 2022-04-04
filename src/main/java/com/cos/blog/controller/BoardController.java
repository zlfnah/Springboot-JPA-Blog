package com.cos.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
//여긴 "/"임
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;



@Controller
public class BoardController {
	

	
		@GetMapping({"/",""})
		public String index(@AuthenticationPrincipal PrincipalDetail principal) {//컨트롤러에서 세션을 어떻게 찾는지? 
			//WEB-INF/views/index.jsp
			System.out.println("로그인사용자 아이디 :"+principal.getUsername());
			return "index";//application.yml에 jsp확장자명 적어서 생략가능
		}
}
