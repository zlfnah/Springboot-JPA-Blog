package com.cos.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
//여긴 "/"임
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;



@Controller
public class BoardController {
	
		@Autowired
		private BoardService boardService;
		
		//컨트롤러에서 세션을 어떻게 찾는지? 
		@GetMapping({"/",""})
		public String index(Model model,@PageableDefault(size=3, sort="id",direction = Sort.Direction.DESC)Pageable pageable) {//WEB-INF/views/index.jsp
			model.addAttribute("boards",boardService.글목록(pageable)); //여기서boards가 index로 넘어감
			return "index";//viewResolver 작동!
		}
		
		@GetMapping("/board/{id}")
		public String findById(@PathVariable int id, Model model) { //@PathVariable url경로에변수를 넣어줌
			model.addAttribute("board",boardService.글상세보기(id));
			return"board/detail";
			
		}
		
		@GetMapping("/board/{id}/updateForm")
		public String updateForm(@PathVariable int id, Model model) {//model=해당 데이터를 가지고 뷰까지 이동
			model.addAttribute("board",boardService.글상세보기(id));
			return"board/updateForm";
		}
		
		//USER 권한이 필요
		@GetMapping("/board/saveForm") //로그인뒤 이 주소로 호출을하면 saveForm리턴으로 열리고 saveForm에서 title과 content정보를 갖고온다.
		public String saveForm() {
			return "board/saveForm";
		}
}
