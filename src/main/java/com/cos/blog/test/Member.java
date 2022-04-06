package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
@Data //lombok 게터세터 한번에 만듬
//@AllArgsConstructor //생성자만듬 그러나 요즘 final씀
@NoArgsConstructor //빈생성자
public class Member {
	private  int id;
	private  String username;
	private  String password;
	private  String email;
	
	@Builder //순서에 상관없이 바꿀수있게함
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	
	}
	
}