<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<!-- ctrl+shift+f -->
<div class="container">

	<form action="/user/join"> <!-- 옛날에는 method=post식 -->
	<input type="hidden" id="id" value="${principal.user.id}" />
		<div class="form-group">
			<label for="username">username</label> 
			<input type="text" class="form-control" placeholder="Enter username" id="username" readonly value="${principal.user.username}">
		</div>
		<!--oauth값이 있으면 수정을 못하게함.  -->
		<c:if test="${empty principal.user.oauth}"> 
		<div class="form-group">
			<label for="password">Password</label> 
			<input type="password" class="form-control" placeholder="Enter password" id="password" >
		</div>

		<div class="form-group">
			<label for="email">Email</label> 
			<input type="email" class="form-control" placeholder="Enter email" id="email" value="${principal.user.email}">
		</div>
	
		<div class="form-group">
			<label for="address">Address</label> 
			<input type="text" class="form-control" placeholder="Enter address" id="address" readonly="readonly" value="${principal.user.address}">
		</div>
		
		<div class="form-group">
			<label for="AddressDetails">AddressDetails</label> 
			<input type="text" class="form-control" placeholder="Enter AddressDetails" id="address_details" value="${principal.user.address_details}">
		</div></c:if>
		
	</form>
	
		<button id="btn-update" class="btn btn-primary">회원수정완료</button>
</div>
<script src="/js/user.js"></script> <!-- /바로 static인식 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
window.onload = function(){
    document.getElementById("address").addEventListener("click", function(){ //주소입력칸을 클릭하면
        //카카오 지도 발생
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
                document.getElementById("address").value = data.address; // 주소 넣기
                document.querySelector("input[id=address_details]").focus(); //상세입력 포커싱
            }
        }).open();
    });
}
</script>
<%@include file="../layout/footer.jsp"%>