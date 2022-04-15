<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String member = (String)request.getAttribute("memberId");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#memberId12{
		color: red;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="div-content">
		<%if(member==null) {%>
			<div class="content-title">아이디 조회 결과</div>
			<h1>등록된 이메일이 없습니다 다시 확인해 주세요</h1>
			<form action="/findId.do" method="post" id="memberId12" name="memberId12">
				<div class="input-wrap">
					<button type="submit" class="btn btn-primary" id="signupButton" style="margin-bottom:5px">이전화면으로 이동</button>
				</div>
			</form>
		<%}else {%>
			<div class="content-title">아이디 조회 결과</div>
			<h1>회원가입 시 사용한 아이디는 <span id="memberId12"><%=member %></span>입니다.</h1>
			<form action="/" method="post" id="memberId12" name="memberId12">
				<div class="input-wrap">
					<button type="submit" class="btn btn-primary" id="signupButton" style="margin-bottom:5px">메인화면으로 이동</button>
				</div>
			</form>
		<%} %>
		
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>