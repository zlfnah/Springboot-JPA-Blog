package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //restcontroller는 문자 그 자체를 리턴해주면 @contoller는 해당결로 이하에있는 파일을 리턴해줌
public class TempControllerTest {
	//http://localhost:8080/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()실행완");
		//파일리턴 기본경로 : src/main/resources/static
		//리턴명:/home.html
		//풀경로:src/main/resources/static/home.html
		return "/home.html";
	}
	@GetMapping("/temp/jsp")
	public String tempJSP() {
		//prefix:/WEB-INF/views/
		//suffix:.jsp
		//풀네임:/WEB-INF/views//test.jsp.jsp 즉 슬래쉬랑 확장자명 빼줘야함
		return "test";
	}
}
