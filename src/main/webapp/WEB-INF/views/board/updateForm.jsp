<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<!-- ctrl+shift+f -->
<div class="container">

	<form>
	<input type="hidden" id="id" value="${board.id}">
		<div class="form-group">
			<input type="text" class="form-control" placeholder="Enter title" id="title" value="${board.title}"><!--모델데이터를 갖고오기위해 value사용  -->
		</div>

		<div class="form-group">
		
			<textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
		</div>
	</form>
		<button id="btn-update" class="btn btn-primary">글수정완료</button> <!-- Json요청을위해 폼태그 밖으로 뺌 -->

</div>
 <script> 
      $('.summernote').summernote({ 
        tabsize: 2,
        height: 300
      });
    </script>
    <script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>