package com.cos.blog.service;

import java.util.List;

import javax.persistence.Entity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. Ioc를 해준다(메모리에 띄어줌)
@Service // 서비스란? insert, update등 기능들을 2개이상전부 합친케이스
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Transactional// 서비스는 하나의 트랜잭션이상을 가질수있다. 트랜잭션이란?:일이 처리되기 위한 가장 작은 단위. 정리:스프링할때 select할때도 트랜잭션을 붙인다 왜? 정합성을 위해
	public void 글쓰기(Board board, User user) { //title,content
		board.setCount(0); //조회수 카운터 강제설정
		board.setUser(user);
		boardRepository.save(board);
		
	}
	@Transactional(readOnly = true)
	public Page<Board>글목록(Pageable pageable){ //글을 호출할때 findAll함수로 전부 불러온다
	return boardRepository.findAll(pageable);

	}
	/* 시큐리티땜에 안씀
	 * @Transactional(readOnly = true)//select할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성 유지)
	 * public User 로그인(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
				});
	}
	@Transactional
	public void 글삭제하기(int id) {
		 boardRepository.deleteById(id);
	}
	
	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
				}); //영속화 완료
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		//해당 함수로 종료시(Service가 종료될 때)에 트랜잭션이 종료된다. 이 때 더티체킹=자동 업데이트가 됨. db flush
	}
	
	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
		
		User user= userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(()->{
			return new IllegalArgumentException("댓글 쓰기 실패:유저 id를 찾을 수 없습니다.");
		}); //영속화 완료;
		Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(()->{
			return new IllegalArgumentException("댓글 쓰기 실패:게시글 id를 찾을 수 없습니다.");
		}); //영속화 완료;
		
		Reply reply= new Reply();
		reply.update(user, board, replySaveRequestDto.getContent());
		
		replyRepository.save(reply);
	}
	@Transactional
	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
	}
}
