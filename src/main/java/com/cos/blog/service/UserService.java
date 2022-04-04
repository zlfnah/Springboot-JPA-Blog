package com.cos.blog.service;

import javax.persistence.Entity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. Ioc를 해준다(메모리에 띄어줌)
@Service // 서비스란? insert, update등 기능들을 2개이상전부 합친케이스
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional // 서비스는 하나의 트랜잭션이상을 가질수있다. 트랜잭션이란?:일이 처리되기 위한 가장 작은 단위. 정리:스프링할때 select할때도 트랜잭션을 붙인다 왜? 정합성을 위해
	public void 회원가입(User user) {
		String rawPassword=user.getPassword(); //1234원문
		String encPassword=encoder.encode(rawPassword); //해쉬화 변환
		user.setPassword(encPassword);
		user.setRole(RoleType.User); //role컬럼 직접적으로 기입 
		userRepository.save(user);
	}
	
	/* 시큐리티땜에 안씀
	 * @Transactional(readOnly = true)//select할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성 유지)
	 * public User 로그인(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */
	
}
