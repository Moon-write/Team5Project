<%@page import="kr.co.iei.question.vo.Question"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%
    	ArrayList<Question> list = (ArrayList<Question>)request.getAttribute("list");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문게시판</title>
<style>
		.notice-tbl a:hover{
			text-decoration: underline;
		}
		.noitce-tbl tr{
			border-bottom: 1px solid #ccc;
		}
		.notice-tbl tr>th:frist-child{
			width:10%;
		}
		.notice-tbl tr>th:nth-child(2){
			width:45%;
		}
		.notice-tbl tr>td:nth-child(2) {
			text-align: left;
		}
		.notice-tbl tr>th:nth-child(4){
			width:20%;
		}
		.notice-tbl tr>th:last-child{
			width:10%;
		}
		
	</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class = "page-content">
		<div class="page-title">공지사항</div>
		<%if(m != null ) {%>
		<a class="btn bc4 writeBtn" href="/questionWriteFrm.do">글쓰기</a>	
		<%} %>
		<table class="tbl tbl-hover notice-tbl">
		<tr class="tr-2">
			<th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th>
		</tr>
		<%for(Question q : list) {%>
		<tr class="tr-1">
			<td><%=q.getQuestionNo() %></td>
			<td>
				<a href="/questionView.do?questionNo=<%=q.getQuestionNo() %>">
					<%=q.getQuestionTitle() %>
				</a>
			</td>	
			<td><%=q.getQuestionWriter() %></td>
			<td><%=q.getQuestionDate() %></td>
			<td><%=q.getQuestionCount() %></td>
		</tr>
		<%} %>
		</table>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>	
</body>
</html>