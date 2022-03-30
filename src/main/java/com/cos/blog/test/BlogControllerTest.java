package com.cos.blog.test;
//해당패키지 이하로 만들어야한다.

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

 //스프링이 com.cos.blog 패키지 이하를 스캔해서 모든 파일을 메모리에 new하는건 아니고 특정 어노테이션이 붙어있는 클래스 파일들을 new
//해서(ioc)스프링 컨테이너에 관리해준다
@RestController
public class BlogControllerTest {
	//http://localhost:8080/test/hello
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello spring boot. 22-03-28</h1>";
	}
}
