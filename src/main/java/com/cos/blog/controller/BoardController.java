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
		public String index() {//컨트롤러에서 세션을 어떻게 찾는지? 
			//WEB-INF/views/index.jsp
			return "index";//application.yml에 jsp확장자명 적어서 생략가능
		}

		//USER 권한이 필요
		@GetMapping("/board/saveForm")
		public String saveForm() {
			return "board/saveForm";
		}
}
