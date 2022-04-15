<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.iei.free.vo.FreeComment"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="kr.co.iei.free.vo.FreeView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
		FreeView view = (FreeView)request.getAttribute("FV");
	    ArrayList<FreeComment> cmlist = (ArrayList<FreeComment>)request.getAttribute("cmlist");
	    ArrayList<FreeComment> recmlist = (ArrayList<FreeComment>)request.getAttribute("recmlist"); 
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
	.freeFreeinsertComment>form>ul{
		list-style-type:none;
		display:flex;
	}
	.freeFreeinsertComment>form>ul>li:first-child>span{
		padding-left:1em;
	}
	.freeFreeinsertComment>form>ul>li:first-child>span{
		width:25%;
		font-size:80px;
		color:#ccc;
	}
	.freeFreeinsertComment>form>ul>li:nth-child(2){
		width:65%;
	}
	.freeFreeinsertComment>form>ul>li:nth-child(2)>textarea.input-form{
		width:100%;
		height:96px;
		min-height:96px;
	}
	.freeFreeinsertComment>form>ul>li:last-child{
		width:10%;
		height:96px;
	}
	.freeFreeinsertComment>form>ul>li:last-child>button.btn-primary{
		width:100%;
		height:100%;
		font-size:2.5em;
	}
	.cmwriter{
		width:90%;
	}
	.commenticon{
		height:3em;
	}
	.recomment{
		margin-left:3em;
	}
	.recomment div:nth-child(2){
		width:100%;
	}
	.comment:last-child{
		margin-bottom:2em;
	}
</style>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="div-content">
        <div class="content-title">자유게시판 <%=view.getNo()%>번 글 상세보기</div>
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
			<%if(m!=null&&m.getMemberId().equals(view.getMemberId())){ %>
			<tr class="tr">
				<td colspan="10"><a href="/freeUpdate.do?freeno=<%=view.getNo()%>" type="button" class="btn btn-primary update">글 수정</a></td>
			</tr>
			<%} %>
		</table>
		<%if(m!=null){ %>
		<div class="freeFreeinsertComment">
			<form action="/freeInsertComment.do" method="post">
			<ul>
				<li>
					<span class="material-icons">account_box</span>
				</li>
				<li>
					<input type="hidden" name="memberId" value="<%=m.getMemberId() %>">
					<input type="hidden" name="freeNo" value="<%=view.getNo() %>">
					<input type="hidden" name="recomment" value="0">
					<textarea class="input-form" name="content"></textarea>
				</li>
				<li>
					<button type="submit" class="btn btn-primary">등록</button>
				</li>
			</ul>
			</form>
		</div>
		<%} %>
		<%for(FreeComment cm : cmlist){ %>
			<div class="list-group comment">
				<div class="list-group-item flex-column align-items-start">
					<div class="d-flex">
						<span class="material-icons">account_box</span>
						<h5 class="mb-1 cmwriter"><%=cm.getWriter() %></h5>
					    <small style="text-align:right;"><%=cm.getDate() %></small>
				    </div>
				    <p class="mb-1"><%=cm.getContent() %></p>
				    <%if(m!=null){ %>
						<%if(m.getMemberId().equals(cm.getMemberId())){ %>
							<a href="javascript:void(0)" onclick="updateComment('<%=cm.getCommentNo()%>')">수정</a>
							<a href="javascript:void(0)" onclick="deleteComment('<%=cm.getCommentNo()%>')">삭제</a>
						<%} %>
						<a href="javascript:void(0)" class="recShow">답글달기</a>
					<%} %>
				</div>
			</div>
			<%for(FreeComment recm : recmlist){ %>
				<%if(recm.getRecomment()==cm.getCommentNo()){ %>
				<div class="d-flex recomment">
					<span class="material-icons commenticon">subdirectory_arrow_right</span>
					<div class="list-group">
						<div class="list-group-item flex-column align-items-start">
							<div class="d-flex">
								<span class="material-icons">account_box</span>
								<h5 class="mb-1 cmwriter"><%=recm.getWriter() %></h5>
							    <small style="text-align:right;"><%=recm.getDate() %></small>
						    </div>
						    <p class="mb-1"><%=cm.getContent() %></p>
						    <%if(m!=null&&m.getMemberId().equals(recm.getMemberId())){ %>
								<a href="javascript:void(0)" onclick="updateComment('<%=cm.getCommentNo()%>')">수정</a>
								<a href="javascript:void(0)" onclick="deleteComment('<%=cm.getCommentNo()%>')">삭제</a>
							<%} %>
						</div>
					</div>
				</div>
				<%} %>
			<%} %>
		<%}%>
	</div>
	<script>
		
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>