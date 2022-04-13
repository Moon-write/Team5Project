<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	//Arraylist보내준 것 꺼내온다. Object객체이므로 다운캐스팅 필요
	String member = (String)request.getAttribute("memberId");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="div-content">
		<div class="content-title">아이디 조회 결과</div>
		<h1>아이디찾았다!! 찾은 아이디는?? <%=member %></h1>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>