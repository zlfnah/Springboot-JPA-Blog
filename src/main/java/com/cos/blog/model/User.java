package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM ->Java(다른언어)object->jpa 테이블로 매핑해주는 기술 즉 실시간으로 바뀌어짐 


@Data //게터세터
@NoArgsConstructor //빈생성자
@AllArgsConstructor //생성자
@Builder //빌더패턴
@Entity //테이블생성을위한 어노테이션 역할:User클래스가 MySQL에 테이블이 생성이된다
//@DynamicInsert //insert시에 널값인 필드를 제외함
public class User {
	@Id //Primary key선언 어노테이션
	@GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연렬된 DB넘버링 전략을 따라간다. MYSQL이냐 ORACLE이냐
	private int id; //시퀀스, auto_increment
	
	@Column(nullable = false,length=100, unique = true) //컬럼 선언 및 null이 될수없다 선언 유니크 키 선언
	private String username;//아이디

	@Column(nullable = false,length=100) //컬럼 선언 및 null이 될수없다 선언 =>해쉬(비밀번호 암호화) 화 할거라 넉넉하게 잡자
	private String password;

	@Column(nullable = false,length=50) 
	private String email;
	
	@Column(nullable = true,length=50) 
	private String address;
	
	@Column(nullable = true,length=50)
	private String address_details;
	
	
	//@ColumnDefault("'user'") //홀따옴표주의
	@Enumerated(EnumType.STRING)//DB는 RoleType이라는게 없음
	private RoleType role; //Enum을 쓰는게 좋다. //ADMIN, USER, manager Enum=도메인설정가능(도메인이란?:ex 성별:남녀, 초등학생:1~6)
	
	private String oauth;//kakao, google 카카오로그인인지 일반로그인인지 구분점
	
	@CreationTimestamp //시간이 자동입력
	private Timestamp createDate;
}