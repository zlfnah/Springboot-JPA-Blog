<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<!-- 상세보기 페이지-->
<div class="container">
		<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
		
		<c:if test="${board.user.id==principal.user.id }"><%--만약 아이디가 같을경우에만 수정 삭제할수있게 if문 --%>
		<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
		<button id="btn-delete" class="btn btn-danger">삭제</button>
		</c:if>
		
		<br/><br/>
		<div>
			글 번호 : <span id="id"><i>${board.id} </i></span>
			작성자 : <span><i>${board.user.username} </i></span> <!--egal전략으로 다 갖고올수있어서 username도호출가능  -->
		</div>
		<br/>
		<div class="form-group">
			<h3>${board.title}</h3>
		</div>
		<hr />
		<div>
			<div>${board.content}</div>
		</div>
			<hr />
</div>
    <script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>