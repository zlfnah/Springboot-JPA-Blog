package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.config.auth.PrincipalDetaillService;
//빈 등록:스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration //빈등록 어노테이션 Ioc관리
@EnableWebSecurity//시큐리티 필터 등록 추가(=spring Security가 활성화가 되어 있는데 어떤 설정을 해당 파일에서 하겠다.)
@EnableGlobalMethodSecurity(prePostEnabled = true)//특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다. 이 3개의 어노테이션은 걍 세트라고 보면된다 이해불가여도 ㄱㅊ
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetaillService principalDetaillService; //애를 통해서 패스워드를 인코드해서 알아서 비교하게 해줌
	
	@Bean //IoC가 된다.
	public BCryptPasswordEncoder encodePWD() {//BCryptPasswordEncoder=sercurity가 갖고있는 함수
		//String encPassword = new BCryptPasswordEncoder().encode("1245"); //encode()에 매개변수를 인코드해서 encPassword함수로넣는다.
		return new BCryptPasswordEncoder(); 
	}
	
	//시큐리티가 대신 로그인해주는데 password를 가로채기를 하는데 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	//같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetaillService).passwordEncoder(encodePWD()); 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()//csrf 토큰 비활성화(테스트시 걸어두는게 좋음)
			.authorizeRequests()//요청이(request)들어오면 
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**") //이주소가 아닌 모든 주소들은 인증이 되어야만 접속가능.
				.permitAll()//요청허용
				.anyRequest()//위에가 아닌 모든 requset는
				.authenticated()//인증이필요해
			.and()
				.formLogin()
				.loginPage("/auth/loginForm") 
				.loginProcessingUrl("/auth/loginProc")//이주소로 오는걸 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인.
				.defaultSuccessUrl("/"); 
	}

}
