<%@page import="kr.or.iei.notice.vo.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
    	String pageNav = (String)request.getAttribute("pageNav");
    	String select = (String)request.getAttribute("select");
    	String value = (String)request.getAttribute("value");
    	if(select == null){
    		select = "0";
    	}
    	if(value == null){
    		value ="";
    	}
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
	.notice-tbl a:hover{
		text-decoration: underline;
		font-weight: bold;
		color: #9d91a4;
	}
	.notice-tbl tr{
		border-bottom: 1px solid #ccc;
		text-align: center;
	}
	.notice-tbl tr>th:first-child{
		width: 10%;
		min-width: 45px;
	}
	.notice-tbl tr>th:nth-child(2){
		width: 30%;
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
		width: 15%;
		min-width: 73px;
	}
	.notice-tbl tr>th:last-child{
		width: 10%;
		min-width: 46px;
	}
	.writebox{
		text-align: right;
	}
	.btn-dark{
		height: 30px;
		width: 80px;
	}
	#search-div{
    	margin: 0 auto;
   		text-align: center;
    	padding: 0px 0px;
	}
	#select:hover{
		cursor: pointer;
	}
	#content{
		border-bottom: none;
		margin-bottom: 0;
	}
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="div-content">
		<div class="content-title" id="content">공지사항 게시판</div>
		<%if(m != null && m.getMemberLevel() == 0) {%>
		<div class="writebox">
			<a class="btn btn-dark" href="/noticeWriteFrm.do" style="margin-bottom: 10px; font-size: 18px; line-height: 18px">글쓰기</a>
		</div>
		<%} %>
		<div class="searchthing">
			<div class="div-content" id="search-div">
				<form  action="/searchNotice.do" class="table tabel-hover" name="search" method="get">
					<input type="hidden" name="reqPage" value="1">
					<table class="table tabel-hover">
						<tr>
							<td>
								<%if(select == "0"){%>
									<select class="form-control" name="select" id="select">
									<option value="0" selected>선택</option>
									<option value="noticeTitle">제목</option>
									<option value="noticeWriter">작성자</option>
									</select>
								<%}else if(select == "noticeTitle"){%>
									<select class="form-control" name="select" id="select">
									<option value="0">선택</option>
									<option value="noticeTitle" selected>제목</option>
									<option value="noticeWriter">작성자</option>
									</select>
								<%}else if(select == "noticeWriter"){%>
									<select class="form-control" name="select" id="select">
									<option value="0">선택</option>
									<option value="noticeTitle">제목</option>
									<option value="noticeWriter" selected>작성자</option>
									</select>
								<%} %>
							</td>
							<td><input type="text" class="form-control"	placeholder="검색어 입력" name="value" maxlength="500px" value="<%=value%>"></td>
							<td><button type="submit" class="btn btn-primary" id="search">검색</button></td>
						</tr>
	
					</table>
				</form>
			</div>
		</div>
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
					<td>
						<%if(n.getClickLike() == 1) {%>
						<span class="material-icons">thumb_up</span>
						<%}else{ %>
						<span class="material-icons">thumb_up_off_alt</span>
						<%} %>
						<%=n.getLikeNumber() %>
					</td>
				</tr>
			<%} %>
		</table>
		<div id="pageNav"><%=pageNav %></div>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>