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
	    boolean likecheck =  (boolean)request.getAttribute("likecheck");
	   
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
 		min-width: 90px;  
	}
	.tr td:nth-child(2){
		width:32%;
		min-width: 200px;  
	}
	.tr th:nth-child(3){
		width:12%;
		min-width: 82px;
	}
	.tr td:nth-child(4){
		width:12%;
	}
	.tr th:nth-child(5){
		width:12%;
 		min-width: 82px; 
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
	
	.func{
		font-size:2em;
		margin-bottom:0;
	}
	.like{
		float:right;
		margin-bottom:0.2em;
	}
	.like>span{
		padding-top:0.2em;
	}
	#freeInsert{
		border: 1.5px solid #907bb6;
		
	}
	#insert{
		min-width: 110px;
	}
	.comment small, .recomment small{
		min-width: 70.63px;
	}
	.comment .disnone{
		display:none;
	}
</style>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="div-content">
        <div class="content-title">자유게시판 <%=view.getNo()%>번 글 상세보기</div>
        <%if(m!=null){ %>
        	<div class="func">
        		<%if(m.getMemberId().equals(view.getMemberId())){ %>
				<a href="/freeUpdate.do?freeno=<%=view.getNo()%>" type="button" class="btn btn-warning update">글 수정</a>
				<%} if(m.getMemberId().equals(view.getMemberId())||m.getMemberLevel()==0){%>
				<button onclick="deleteCheck('<%=view.getNo()%>')" type="button" class="btn btn-dark delete">글 삭제</button>   	   	
				<%} %>
			<%if(!likecheck){ %>
				<button onclick="insertLike(this,'<%=m.getMemberNo()%>','<%=view.getNo()%>')" class="btn btn-info like"><span type="button" class="material-icons">thumb_up_off_alt</span> </button>
			<%}else{ %>
				<button onclick="deleteLike(this,'<%=m.getMemberNo()%>','<%=view.getNo()%>')" class="btn btn-info like"><span type="button" class="material-icons">thumb_up</span> </button>
			<%} %>		
        	</div>
		<%} %>
		<table class="table" id="freeInsert">
			<tr class="tr">
				<th class="table-success" colspan="1">제목</th>
				<td colspan="1"><%=view.getTitle()%></td>
				<th class="table-success">조회수</th>
				<td><%=view.getView()%></td>
				<th class="table-success">좋아요</th>
				<td id="likecount"><%=view.getLike()%></td>
			</tr>
			<tr class="tr tr-2">
				<th class="table-success" colspan="1">작성자</th>
				<td colspan="1"><%=view.getWriter() %></td>
				<th class="table-success" colspan="1" id="date">작성일자</th>
				<td colspan="3"><%=view.getDate()%></td>
			</tr>
			<tr class="tr contents">
				<td colspan="10" style="text-align:left;"><%=view.getContents() %></td>
			</tr>
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
					<button type="submit" class="btn btn-primary" id="insert">등록</button>
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
						<h5 class="mb-2 cmwriter"><%=cm.getWriter() %></h5>
					    <small style="text-align:right;"><%=cm.getDate() %></small>
				    </div>
				    <p class="mb-3"><%=cm.getContent() %></p>
					<textarea name="ncContent" class="input-form" style="display:none;"><%=cm.getContent() %></textarea>
				    <%if(m!=null){ %>
						<%if(m.getMemberId().equals(cm.getMemberId())){ %>
							<button class ="btn-warning" onclick="updateComment(this,'<%=cm.getCommentNo()%>')">수정</button>
							<button class ="btn-dark" onclick="deleteComment(this,'<%=cm.getCommentNo()%>')">삭제</button>
						<%} %>
						<button class="btn-info" onclick="commentvisible(this)">답글달기</button>
					<%} %>
				</div>
				<%if(m!=null){ %>
				<div class="list-group-item disnone">
					<div class="d-flex">
						<span class="material-icons">account_box</span>
						<h5 class="mb-2 cmwriter"><%=cm.getWriter() %></h5>
					</div>
					<form class="d-flex" action="/freeInsertComment.do" method="post">
						<input type="hidden" name="memberId" value="<%=m.getMemberId() %>">
						<input type="hidden" name="freeNo" value="<%=view.getNo() %>">
						<input type="hidden" name="recomment" value="<%=cm.getCommentNo()%>">
						<textarea class="input-form" style="width:80%;" name="content"></textarea>
						<button type="submit" class="btn btn-danger">등록</button>		
						<button type="button" class="btn btn-dark" onclick="recommentCencel(this)">취소</button>		
					</form>
				</div>
				<%} %>
			</div>
			<%for(FreeComment recm : recmlist){ %>
				<%if(recm.getRecomment()==cm.getCommentNo()){ %>
				<div class="d-flex recomment">
					<span class="material-icons commenticon">subdirectory_arrow_right</span>
					<div class="list-group">
						<div class="list-group-item flex-column align-items-start">
							<div class="d-flex">
								<span class="material-icons">account_box</span>
								<h5 class="mb-2 cmwriter"><%=recm.getWriter() %></h5>
							    <small style="text-align:right;"><%=recm.getDate() %></small>
						    </div>
						    <p class="mb-3"><%=recm.getContent() %></p>
						    <textarea name="ncContent" class="input-form" style="display:none;"><%=recm.getContent() %></textarea> 
						    <%if(m!=null&&m.getMemberId().equals(recm.getMemberId())){ %>
								<button class="btn-warning" onclick="updateComment(this,'<%=recm.getCommentNo()%>')">수정</button>
								<button class="btn-dark" onclick="deleteComment(this,'<%=recm.getCommentNo()%>')">삭제</button>
							<%} %>
						</div>
					</div>
				</div>
				<%}%>
			<%}%>
		<%}%>
	</div>
	<script>
	function deleteCheck(no){
		if(confirm("삭제하시겠습니까?")){
			location.href="/freeDelete.do?freeno="+no;
		}
	}
	function deleteComment(obj,cm){
		if(confirm("삭제하시겠습니까?")){
			location.href="/freeCommentDelete.do?num="+cm;
		}
	}
	function updateComment(obj,cm){
		$(obj).prev().attr("style","display:block;width:100%;");
		$(obj).prev().prev().attr("style","display:none");
		$(obj).text("수정완료");
		$(obj).attr("onclick","updateComplete(this,'"+cm+"')");
		$(obj).next().text("수정취소");
		$(obj).next().attr("onclick","updateCancel(this,'"+cm+"')");
		$(obj).next().next().hide();
	}
	function updateCancel(obj,cm){
		$(obj).prev().prev().attr("style","display:none");
		$(obj).prev().prev().prev().attr("style","display:block");
		$(obj).prev().text("수정");
		$(obj).prev().attr("onclick","updateComment(this,'"+cm+"')");
		$(obj).text("삭제");
		$(obj).attr("onclick","deleteComment(this,'"+cm+"')");
		$(obj).next().show();
	}
	function updateComplete(obj,cm){
		$(obj).prev().attr("style","display:none;");
		$(obj).prev().prev().attr("style","display:block");
		const message = $(obj).prev().val();
		console.log(cm)
		$.ajax({
			type:"post",
			url:"/updateComment.do",
			data:{num:cm,comment:message},
			success:function(data){
				if(data=="성공"){
					$(obj).prev().prev().text(message);
				}
			}
		})
		$(obj).text("수정");
		$(obj).attr("onclick","updateComment(this,'"+cm+"')");
		$(obj).next().text("삭제");
		$(obj).next().attr("onclick","deleteComment(this,'"+cm+"')");
		$(obj).next().next().show();
	}
	function commentvisible(obj){
		const insertreComment = $(obj).parent().next();
		$(obj).hide();
		insertreComment.removeClass('disnone');
	}
	function recommentCencel(obj){
		const insertreComment = $(obj).parent().parent();
		insertreComment.addClass('disnone');
		$(obj).parent().parent().prev().last().children().last().show();
	}
	function insertLike(obj,m,f){
		$.ajax({
			type:"post",
			url:"/freeInsertLike.do",
			data:{num1:m, num2:f},
			success:function(data){
				if(data=="성공"){
					$(obj).children().text("thumb_up");
					$(obj).attr("onclick","deleteLike(this,'"+m+"','"+f+"')");
					const count = $("#likecount");
					count.text(Number.parseInt(count.text())+1);
					alert("추천!");
				}else{
					console.log(data);
				}
			},
			error:function(){
				console.log("전송실패");
			}
		})
	}
	function deleteLike(obj,m,f){
		$.ajax({
			type:"post",
			url:"/freeDeleteLike.do",
			data:{num1:m, num2:f},
			success:function(data){
				if(data=="성공"){
					$(obj).children().text("thumb_up_off_alt");
					$(obj).attr("onclick","insertLike(this,'"+m+"','"+f+"')");
					const count = $("#likecount");
					count.text(Number.parseInt(count.text())-1);
					alert("추천취소!");
				}else{
					console.log(data);
				}
			},
			error:function(){
				console.log("전송실패");
			}
		})
	}
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>