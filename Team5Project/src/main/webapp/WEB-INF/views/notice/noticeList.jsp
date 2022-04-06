<%@page import="kr.or.iei.notice.vo.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style>
	.notice-tbl a{
		color: black;
		text-decoration: none;	
	}
	.notice-tbl a{
		color: ##996bf0;
	}
	.notice-tbl a:hover{
		text-decoration: underline;
		font-weight: bold;
	}
	.notice-tbl tr{
		border-bottom: 1px solid #ccc;
		text-align: center;
	}
	.notice-tbl tr>th:first-child{
		width: 10%;
	}
	.notice-tbl tr>th:nth-child(2){
		width: 35%;
	}
	.notice-tbl tr>td:nth-child(2){
		text-align: left;
	}
	.notice-tbl tr>th:nth-child(3){
		width: 15%;
	}
	.notice-tbl tr>th:nth-child(4){
		width: 20%;
	}
	.notice-tbl tr>th:nth-child(5){
		width: 10%;
	}
	.notice-tbl tr>th:last-child{
		width: 10%;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="div-content">
		<div class="content-title">공지사항 게시판</div>
		<table class="table tabel-hover notice-tbl">
			<tr class="table-success">
				<th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th><th>추천</th>
			</tr>
			<%for(Notice n : list) {%>
				<tr class="table-light">
					<td><%=n.getNoticeNo() %></td>
					<td>
					<a href="/noticeView.do?noticeNo=<%=n.getNoticeNo() %>">
					<%=n.getNoticeTitle() %>
					</a>
					</td>
					<td><%=n.getNoticeWriter() %></td>
					<td><%=n.getRegDate() %></td>
					<td><%=n.getReadCount() %></td>
					<td><%=n.getThumbsUp() %></td>
				</tr>
			<%} %>
		</table>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>