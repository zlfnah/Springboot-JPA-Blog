package com.cos.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//html파일이 아니라 data를 리턴해주는 controller=RestController
@RestController
public class DummyControllerTest {
	
	@Autowired //위에 DummyControllerTest클래스가 사용될때 같이 동작됨 의존성주입(DI)
	private UserRepository userRepository;
	
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}") //{id}주소로 파라미터를 전달 받을 수 있음.
	
	//user/4를 찾으면 내가 데이터베이스에서 못찾아오게 되면 user이 null이 되지? 그럼 return시 null이 리턴이 되면 프로그램에 문제가 생김
	//그래서 Optional로 너의 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해라.
	public User detail(@PathVariable int id) {
	User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {//여기서 정상적으로 호출시는 findById(id)까지 바로작동이되나 만약 id값이 없을경우 orElseThrow가 실행이된다
		@Override
		public IllegalArgumentException get() {
		
			return new IllegalArgumentException("해당 유저는 없습니다. id:"+id);
		}
	});
	return user; //여기서 user객체
	                     //자바오브젝트 요청은 인터넷
						//변환해야함 (웹브라우저가 이해할 수 있는 데이터)->json(Gson 라이브러리)
						//스프링부트=MessageConverter라는 애가 응답시에 자동 작동
					    //만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson라이브러리를 호출해서 
						//user오브젝트를 json으로 변환해서 브라우저에게 던져준다. 그래서 json형태로 뜸
	}
	//람다식표현은 다음과 같다
	/*
	 * 	User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저는 없습니다. id:"+id);
	});
	 * */
	
	
	
	//http://localhost:800/blog/dummy/join (요청)
	//http의 body에 username,password,email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) { //@requestParam생략시 변수명 정확해야함 key=value
		System.out.println("id : "+user.getId());
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		System.out.println("role : "+user.getRole());
		System.out.println("createDate : "+user.getCreateDate());
		user.setRole(RoleType.User);
		userRepository.save(user);
		return "회원가입완료";
	}
}
