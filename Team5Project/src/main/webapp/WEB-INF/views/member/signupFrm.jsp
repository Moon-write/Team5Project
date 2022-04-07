<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file ="/WEB-INF/views/common/header.jsp" %>
	<div class="div-content">
		<div class="content-title">회원가입 페이지</div>
		<form action="/signup.do" method="post">
			<div class="">
				<label for="memberId">아이디</label>
				<input type="text" name="memberId" id="memberId" class="">
				<button type="button" id="idChkBtn" class="">중복체크</button>
			</div>
			<div class="">
				<label for="memberPw">비밀번호</label>
				<input type="password" name="memberPw" id=memberPw" class="">
			</div>
			<div class="">
				<label for="memberPwRe">비밀번호확인</label>
				<input type="password" name="memberPwRe" id="memberPwRe" class="">			
			</div>
			<div class="">
				<label for="memberName">이름</label>
				<input type="text" name="memberName" id="memberName" class="">
			</div>
			<!-- 여기까지 하는중 계속 폼 만들고 ref에서 골라서 적용해야됨  -->
		</form>
	</div>
	<%@include file ="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>