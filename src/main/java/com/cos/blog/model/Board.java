package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	//FetchType.EAGER무조건갖고와라
	@ManyToOne(fetch = FetchType.EAGER) //Many=Board, user=One 한명의 유저는 여러개의 게시그를 적을수있다. 연관관계
	@JoinColumn(name="userId") //컬럼명 userId User테이블에서 참조한 FK
	private User user; //db는 오브젝트를 저장할 수 없다 FK, 자바는 오브젝트를 저장할 수 있다.
	
	//FetchType.LAZY갖고올수도 안 갖고올수도있다
	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY)//mappedBy뜻 연관관계의 주인이 아니다(난 fk가 아니다)db에 컬럼을 만들지 말아주세요뜻 reply테이블에 board가 fk다 reply클래스에있는 board필드를적는다
	//하나의 개시글은 여러개의 댓글이있을수있다. fk설정이 필요없는이유는 1정규화가깨짐
	private List<Reply> reply; //댓글은1개이상 즉 여러개일수도있어서 List유틸쓴다
	
	@CreationTimestamp
	private Timestamp createDate;
}
