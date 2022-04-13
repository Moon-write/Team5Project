<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member m1 = (Member)request.getAttribute("m");
	String memberPw2 = m1.getMemberPw();
	String memberId = m1.getMemberId();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.memberName12{
		color: red;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
		<div class="div-content">
			<%if(memberPw2 == null){ %>
				<div class="content-title">비밀번호 조회 결과</div>
				<h1>등록된 해당 회원이 없습니다 다시 확인해 주세요</h1>
				<form action="/findId.do" method="post" id="memberId12" name="memberId12">
				<div class="input-wrap">
					<button type="submit" class="btn btn-primary" id="signupButton" style="margin-bottom:5px">이전화면으로 이동</button>
				</div>
			</form>
			<%}else{ %>
				<div class="content-title">비밀번호 조회 결과</div>
				<h1><span class="memberName12"><%=memberId%></span>님의 비밀번호는 <span class="memberName12"><%=memberPw2 %></span>입니다.</h1>
				<form action="/" method="post">
					<div class="input-wrap">
						<button type="submit" class="btn btn-primary" id="signupButton" style="margin-bottom:5px">메인화면으로 이동</button>
					</div>
				</form>
			<%} %>
		</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
		
</body>
</html>