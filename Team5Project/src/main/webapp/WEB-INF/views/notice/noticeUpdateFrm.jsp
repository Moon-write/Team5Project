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
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
		<div class="div-content">
			<div class="content-title">공지사항 수정</div>
		
		<form action="/noticeUpdate.do" method="post" enctype="multipart/form-data">
			<table class="table tabel-hover" id="noticeUpdateFrm">
				<tr class="table-success">
					<th class="table-light">제목</th>
					<td>
						<input type="text" name="noticeTitle" class="input-form" value="<%=n.getNoticeTitle()%>">;
					</td>
				</tr>
				<tr class="table-success">
					<th class="table-light">첨부파일</th>
					<td><textarea class="input-form" name="noticeContent"><%=n.getNoticeContent() %></textarea>
				</tr>
				<tr class="table-success">
					<th colspan="2"><button class="btn btn-primary">수정 완료</button>
				</tr>
			</table>
		</form>
		</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>