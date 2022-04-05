package com.cos.blog.repository;
import java.util.Optional;

//db연동
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
																	//다 반환하는 인터페이스 등 기능이많음.
                                                                   //DAO
																  //자동으로 BEAN등록이된다 @Repository생략가능
public interface BoardRepository extends JpaRepository<Board, Integer> { //JpaRepository=User테이블이 관리하는 레포지토리, 이 유저테이블에 pk는 인티저임. 즉숫자임

	 }


