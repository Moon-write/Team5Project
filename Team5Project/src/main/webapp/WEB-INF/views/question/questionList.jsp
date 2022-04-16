<%@page import="kr.co.iei.question.vo.Question"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%
     	ArrayList<Question> list = (ArrayList<Question>)request.getAttribute("list");
     	
    	ArrayList<Question> questionList 
    	= (ArrayList<Question>)request.getAttribute("questionList");
     	ArrayList<Question> requestionList 
     	= (ArrayList<Question>)request.getAttribute("requestionList");
     	
     	String pageNavi = (String)request.getAttribute("pageNavi");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문게시판</title>
	<style>
		.tr-2>th{
			text-align: center;
		}
		.tr-1{
		
		}
		.notice-tbl a:hover{
			text-decoration: underline;
		}
		.notice-tbl tr{
			border-bottom: 1px solid #ccc;
			text-align: center;
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
		.notice-tbl tr>th:nth-child(3){
			width:15%;
		}
		.notice-tbl tr>th:nth-child(4){
			width:20%;
		}
		.notice-tbl tr>th:last-child{
			width:10%;
		}
		#pageNavi{
			margin-top: 30px;
		}
		.pageination{
			justify-content: center;
		}
		.btn-lg{
		 margin-bottom: 10px; 
		}
		
	</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class = "div-content">
		<div class="content-title">질문게시판</div>
		<%if(m != null && m.getMemberLevel()==0) {%>
		<a class="btn btn-lg btn-primary" href="/questionWriteFrm.do">글쓰기</a>	
		<%} %>
		<table class="table table-hover notice-tbl">
			<tr class="table-success tr-2">
				<th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th>
			</tr>
			<%for(Question q : list) {%>
				<tr class="table-light tr-1">
					<td><%=q.getQuestionNo() %></td>
					<td>
						<a href="/questionView.do?questionNo=<%=q.getQuestionNo() %>" style="color: #000;">
							<%=q.getQuestionTitle() %>
						</a>
					</td>	
					<td><%=q.getQuestionWriter() %></td>
					<td><%=q.getQuestionDate() %></td>
					<td><%=q.getQuestionCount() %></td>
				</tr>				
			<%} %>
		</table>
		<div id="pageNavi"><%=pageNavi %></div>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>	
</body>
</html>