<%@page import="kr.or.iei.notice.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Notice n = (Notice)request.getAttribute("n");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#noticeView th, #noticeView td{
		border : 1px solid #eee;
	}
	#noticeContent{
		min-height: 300px;
		text-align: left;
	}
	#buttons{
		text-align: center;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="div-content">
		<div class="content-title">공지사항 작성</div>
		<table class="table tabel-hover" id="noticeView">
			<tr class="table-success">
				<th class="table-light">작성자</th>
				<td><%=n.getNoticeWriter() %></td>
				<th class="table-light">작성일</th>
				<td><%=n.getRegDate() %></td>
				<th class="table-light">조회수</th>
				<td><%=n.getReadCount() %></td>
				<th class="table-light">추천</th>
				<td><%=n.getThumbsUp() %></td>
			</tr>
			<tr class="table-success">
				<th class="table-light">첨부파일</th>
				<td colspan="7">
				<%if(n.getFilename() != null) {%>
					<img src="/img/file.png" width="16px">
					<a href="/fileDown.do?noticeNo=<%=n.getNoticeNo() %>"><%=n.getFilename() %></a>
				<%} %>
			</tr>
			<tr class="table-success">
				<th class="table-light">내용</th>
				<td colspan="7">
					<div id="noticeContent"><%=n.getNoticeContent() %></div>
				</td>
			</tr>
			<%if(m!=null && m.getMemberId().equals(n.getNoticeWriter())) {%>
			<tr scope="row">
				<th id="buttons" colspan="8">
					<a class="btn btn-primary" href="/noticeUpdateFrm.do?noticeNo=<%=n.getNoticeNo() %>">수정</a>
					<button class="btn btn-secondary" onclick="noticeDelete('<%=n.getNoticeNo() %>');">삭제</button>
				</th>
			</tr>
			<%} %>
		</table>
		<script>
			function noticeDelete(noticeNo){
				if(confirm("삭제하시겠습니까?")){
					location.href="/noticeDelete.do?noticeNo="+noticeNo;
				}
				
			}
		</script>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>