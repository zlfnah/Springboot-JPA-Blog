package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply { //답변테이블
	@Id //pk키
	@GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 db의 넘버링 전략 즉=auto_increment
	private int id;
	
	@Column(nullable=false, length=200)
	private String content;
	
	@ManyToOne//하나의 게시글에 여러개의 답변가능.
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne//한명은 여러개의 답변가능.
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
}
