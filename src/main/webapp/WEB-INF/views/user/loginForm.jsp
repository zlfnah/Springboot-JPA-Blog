<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<!-- ctrl+shift+f -->
<div class="container">

	<form action="/auth/loginProc" method="post"><!--  action="/blog/api/user/login"는 옛날방식 -->
		<div class="form-group">
			<label for="username">username</label> <input type="text" class="form-control" placeholder="Enter username" id="username" name="username">
		</div>
		
		<div class="form-group">
			<label for="password">Password</label> <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
		</div>

		<button id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=ec70a9f497d24fa20e40c2571115fa8d&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img height="38px" src="/image/kakao_login_button.png" /></a>
	</form>

</div>
<!-- <script src="/js/user.js"></script>  --><!-- /바로 static인식 -->
<%@include file="../layout/footer.jsp"%>