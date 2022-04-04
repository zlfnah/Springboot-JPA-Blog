package com.cos.blog.repository;
import java.util.Optional;

//db연동
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;
																	//다 반환하는 인터페이스 등 기능이많음.
                                                                   //DAO
																  //자동으로 BEAN등록이된다 @Repository생략가능
public interface UserRepository extends JpaRepository<User, Integer> { //JpaRepository=User테이블이 관리하는 레포지토리, 이 유저테이블에 pk는 인티저임. 즉숫자임
	//SELECT * FROM user Where username=1?
	Optional<User> findByUsername(String username);
	 }








//JPA Naming 쿼리
//임의의 함수이지만 findByUsernameAndPassword이렇게 만드면 자동으로 select * from user where username=? AND password=?가 실행됨 앞에 ?=username 와 password가들어감
/* User findByUsernameAndPassword(String username, String password); 
//리턴값을 User오브젝트로 받음
 * @Query(value="SELECT * FROM user WHERE username = ?1 AND password=?2",
 * nativeQuery = true) User login(String username, String password); //리턴값*/