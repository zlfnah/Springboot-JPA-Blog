package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data //게터세터
@NoArgsConstructor //빈생성자
@AllArgsConstructor //생성자
@Builder //빌더패턴
@Entity //Board클래스가 mysql에서 테이블로 생김
public class Board { //게시판
	@Id//pk키
	@GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob //대용량 데이터
	private String content;//섬머노트 라이브러리<html>태그가 섞여서 디자인이 됨 db에는 longtext로 들어감
	
	@ColumnDefault("0") //넘버값이니 홀따옴표 필요없음
	private int count;
	
	@ManyToOne //Many=Board, user=One 한명의 유저는 여러개의 게시그를 적을수있다. 연관관계
	@JoinColumn(name="userId") //컬럼명 userId User테이블에서 참조한 FK
	private User user; //db는 오브젝트를 저장할 수 없다 FK, 자바는 오브젝트를 저장할 수 있다.
	
	@CreationTimestamp
	private Timestamp createDate;
}
