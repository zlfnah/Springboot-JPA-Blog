<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<!-- ctrl+shift+f -->
<div class="container">

	<form action="/user/join"> <!-- 옛날에는 method=post식 -->
		<div class="form-group">
			<label for="username">username</label> <input type="text" class="form-control" placeholder="Enter username" id="username">
		</div>
		
		<div class="form-group">
			<label for="password">Password</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		
		<div class="form-group">
			<label for="email">Email</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
		
	</form>
	
		<button id="btn-save" class="btn btn-primary">회원가입완료</button>
</div>
<script src="/blog/js/user.js"></script> <!-- /바로 static인식 -->
<%@include file="../layout/footer.jsp"%>