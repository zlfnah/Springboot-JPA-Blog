package com.cos.blog.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.User;

@RestController //데이터만리턴
public class UserApiController {
	@PostMapping("/api/user")
	public int save(@RequestBody User user) { //json데이터니까 무조건 requestbody
		System.out.println("UserApiController : save호출됨");
		return 1;
	}
}
