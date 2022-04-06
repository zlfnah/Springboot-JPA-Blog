<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="layout/header.jsp"%>
<!-- ctrl+shift+f -->
<div class="container">
	<c:forEach var="board" items="${boards.content}">
		<%--jstl식 for문 el표현식 boards데이터는 컬렉션이기 때문에 items에서 받을수있음--%>

		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4>
				<%--게시글 불러오기 --%>
				<a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
			</div>
		</div>

	</c:forEach>
	<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${boards.first}">			<%--만약 첫번째페이지라면 --%>
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
				<!--페이징  -->
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${boards.last}">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item "><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
			</c:otherwise>
		</c:choose>

	</ul>
	<!--  sHm2jpz4R6-->
</div>
<%@include file="layout/footer.jsp"%>