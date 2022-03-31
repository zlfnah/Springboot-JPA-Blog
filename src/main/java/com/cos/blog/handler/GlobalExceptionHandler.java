package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //모든 오류때 이파일로 나오게함
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class) //Exception이 가장 부모 클래스라 모든 오류가 일로나옴
	public String handleArgumentException(Exception e) {
		return "<h1>"+e.getMessage()+"</h1>";
	}
}
