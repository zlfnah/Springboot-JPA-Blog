package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
		@GetMapping({"/",""})
		public String index() {
			//WEB-INF/views/index.jsp
			return "index";//application.yml에 jsp확장자명 적어서 생략가능
		}
}
