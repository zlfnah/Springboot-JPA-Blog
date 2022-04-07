package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service // bean등록
public class PrincipalDetaillService implements UserDetailsService {

	@Autowired // bean불러오기
	private UserRepository userRepository;

// 스프링이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데
// password 부분 처리는 알아서 함.
// username이 db에 있는지만 확인하면됨 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을수 없습니다. :"+username);
				});
		return new PrincipalDetail(principal); //security 세션에 유저 정보가 저장이 됨. 아이디:user, 패스워드:콘솔창
	}
}

/*로그인요청시
 * 인증필터가 낚아챈뒤 토큰을만듬(토큰은 아이디랑 비번토대로 만듬)
 * 이 토큰은 AuthenticationManger에 던짐(Authentication객체를 만들기위해)
 * 객체를 만들기위한 조건은 db에 아이디가 있는지 비번이있는지
 * 확인 후 객체를만듬 그 뒤 세션에 저장
 * 
 * */
