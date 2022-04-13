<%@page import="kr.co.iei.free.vo.FreeView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
		FreeView view = (FreeView)request.getAttribute("FV");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
</head>
<style>
	.div-content td, .div-content th{
		border :1px solid #ccc;
	}
	.table-success{
		text-align: center;
		justify-content: center;
	}
	.tr td, .tr th{
		text-align: center;
		justify-content: center;
	}
	.tr th:first-child{
		width:20%;
	}
	.tr td:nth-child(2){
		width:32%;
	}
	.tr th:nth-child(3){
		width:12%;
	}
	.tr td:nth-child(4){
		width:12%;
	}
	.tr th:nth-child(5){
		width:12%;
	}
	.tr td:last-child{
		width:12%;
	}
	.tr .btn-primary{
		width:100%;
		height:80px;
		font-size:30px;
		color:black;
		justify-content: center;
	}
	.contents{
		height:600px;
	}
	tr .update{
		line-height:2.2em;
	}
</style>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="div-content">
        <div class="content-title">자유게시판 <%=view.getNo()%>번 글 상세보기</div>
		<form action="/freeInsert.do" method="post">
			<table class="table" id="freeInsert">
				<tr class="tr">
					<th class="table-success" colspan="1">제목</th>
					<td colspan="1"><%=view.getTitle()%></td>
					<th class="table-success">조회수</th>
					<td><%=view.getView()%></td>
					<th class="table-success">좋아요</th>
					<td><%=view.getLike()%></td>
					
				</tr>
				<tr class="tr tr-2">
					<th class="table-success" colspan="1">작성자</th>
					<td colspan="1"><%=view.getWriter() %></td>
					<th class="table-success" colspan="1">작성 일자</th>
					<td colspan="3"><%=view.getDate()%></td>
				</tr>
				<tr class="tr contents">
					<td colspan="10" style="text-align:left;"><%=view.getContents() %></td>
				</tr>
				<tr class="tr">
					<td colspan="10"><a href="/freeUpdate.do?freeno=<%=view.getNo()%>" type="button" class="btn btn-primary update">글 수정</a></td>
				</tr>
			</table>
		</form>
	</div>
	<script>
		
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>