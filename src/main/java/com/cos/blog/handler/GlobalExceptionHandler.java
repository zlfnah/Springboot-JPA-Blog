package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

@ControllerAdvice //모든 오류때 이파일로 나오게함
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class) //Exception이 가장 부모 클래스라 모든 오류가 일로나옴
	public ResponseDto<String> handleArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()); 
		
	}
}
