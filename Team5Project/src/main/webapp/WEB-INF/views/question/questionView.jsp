<%@page import="kr.co.iei.question.vo.QuestionComment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.iei.question.vo.Question"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Question q = (Question)request.getAttribute("q");
    	ArrayList<QuestionComment> commentList 
    	= (ArrayList<QuestionComment>)request.getAttribute("commentList");
    	ArrayList<QuestionComment> reCommentList
    	= (ArrayList<QuestionComment>)request.getAttribute("reCommentList");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style>
		#questionView th, #questionView td{
			border : 1px solid #eee;
			text-align: center;
		}
		#questionView {
			 border: 1.5px solid #907bb6;
		}
		#questionContent{
			min-height: 300px;
			text-align: left;
			font-family: ns-light;
		}
		.inputCommentBox{
			margin: 50px;
		}
		.inputCommentBox>form>ul{
			list-style-type: none;
			display: flex;
		}
		.inputCommentBox>form>ul>li{
			display: flex;
		}
		.inputCommentBox>form>ul>li:first-child{
			width: 15%;
			display: flex;
			justify-content: center;
			align-items: center;
		}
		.inputCommentBox>form>ul>li:first-child>span{
			font-size: 70px;
			color: #ccc;
		}
		.inputCommentBox>form>ul>li:nth-child(2){
			width: 75%
		}
		.inputCommentBox>form>ul>li:nth-child(2)>textarea.input-form{
			width: 100%;
			height: 70px;
			min-height: 70px;
		}
		.inputCommentBox>form>ul>li:last-child {
			width: 10%;
		}
		
		
		.commentBox{
			margin: 20px;
		}
		.commentBox>ul{
			list-style-type: none;
			display: flex;
		}
		.commentBox>ul>li:first-child{
			width: 10%;
			display: flex;
			justify-content: center;
			align-items: center;
		}
		.commentBox>ul>li:nth-child(2){
			display: flex;
			text-align: center;
			font-size: 15px;
			justify-content: center;
			align-items: center;
		}
		.commentBox>ul>li:first-child>span{
			font-size: 50px;
			color: #000;
		}
		.commentBox>ul>li:nth-child(3){
			margin-top: 10px;
			width: 80%
		}
		.commentBox>ul>li:nth-child(3)>textarea.input-form{
			width: 100%;
			height: 80px;
			min-height: 80px;
		}
		.commentBox>ul>li:nth-child(4) {
			display:inline-block;
			width: 10%;
		}
		
		
	
		.inputRecommentBox{
			margin: 30px 0px;
			display: none;
		}
		.inputRecommentBox>form>ul{
			margin-left: 30px;
			list-style-type: none;
			display: flex;
			width: 100%;
		}
		.inputRecommentBox>form>ul>li :first-child{
			width: 15%;
			display: flex;
			justify-content: center;
			align-items: center;
		}
		.inputRecommentBox>form>ul>li :first-child>sapn{
			font-size: 50px;
			color:#ccc;
		}
		.inputRecommentBox>form>ul>li:nth-child(2) {
			width: 77%;
		}
		.inputRecommentBox>form>ul>li:nth-child(2)>textarea.input-form{
			width: 100%;
			height: 80px;
			min-height: 80px;
		}
		.inputRecommentBox>form>ul>li:last-child{
			width:8%;
		}
		.inputRecommentBox>form>ul>li:last-child>button{
			width:100%;
			height: 80px;
		}
		
		
		.updel{
			margin: 0 auto;
			justify-content: center;
			text-align: center;
		}
		.main-title>.table-light{
			width: 10%;
		}
		.comment-link{
			float: right;
			font-size: 15px;
		}
		.comment-link>a{
			color: #000;
		}
		.comment-link>a:hover{
			text-decoration: underline;
		}
		
		.answer{
			float:right;
			margin-bottom: 18px;
		}
		#answerblock{
			display: inline-block;
		    text-align: center;
		    text-decoration: none;
		    vertical-align: middle;
		    user-select: none;
		    background-color: transparent;
		    border: 1px solid transparent;
		    padding: 0.375rem 0.75rem;;
		    border-radius: 0.25rem;
		    
		}
		
		.reply{
			margin: 20px;
		}
		ul.reply{
			list-style-type: none;
			display: flex;
		}
		ul.reply>li:first-child{
			width: 10%;
			display: flex;
			justify-content: center;
			align-items: center;
		}
	
		ul.reply>li:first-child>span{
			font-size: 50px;
			color: #000;
		}
		ul.reply>li:last-child>p:first-child{
			width: 20%;
		}
		ul.reply>li:nth-child(2)>textarea.input-form{
			width: 70%;
			height: 80px;
			min-height: 80px;
		}
		ul.reply>li:last-child>p:last-child{
			width: 10%;
		}
		ul.reply>li:last-child {
		margin-top: 10px;
			width: 80%;
		}
		ul.reply>li:last-child>p:nth-child(2){
			text-align: left;
		}
		ul.reply>li:last-child>p:last-child{
			float: right;
		}
	</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="div-content">
		<%if(q.getQueRef() == 0) {%>
			<div class="content-title" style="margin-bottom: 0px;">질문</div>
		<%}else {%>
			<div class="content-title" style="margin-bottom: 0px;">답변</div>
		<%} %>
		<%if(m != null) {%>
			<form action="/answerWriteFrm.do" method="post">
				<input type="hidden" name="questionWriter" value="<%=m.getMemberId()%>">
				<input type="hidden" name="questionTitle" value="<%=q.getQuestionTitle()%>">
				<input type="hidden" name="questionNo" value="<%=q.getQuestionNo()%>">
				<button class="answer btn btn-lg btn-light" id="answer">답변달기</button>
			</form>
		<%}else {%>
			<div class="answer" id="answerblock" style="text-decoration: none;color:transparent;">없애기</div>
		<%} %>
		<table class="tbl table" id="questionView">
			<tr class="main-title">
				<th class="table-light">제목</th>
				<th colspan="6"><%=q.getQuestionTitle() %></th>
			</tr>
			<tr class="tr-1">
				<th class="td-3 table-light">작성자</th>
				<td><%=q.getQuestionWriter() %></td>
				<th class="td-3 table-light">조회수</th>
				<td><%=q.getQuestionCount() %></td>
				<th class="td-3 table-light">작성일</th>
				<td><%=q.getQuestionDate() %></td>
			</tr>
			<tr class="tr-1">
				<th class="td-3">질문내용</th>
				<td colspan="5">
					<div id="questionContent"><%=q.getQuestionContentBr() %></div>
				</td>
			</tr>
			<%if(m!=null&& m.getMemberId().equals(q.getQuestionWriter())) {%>
			<tr class= "tr-1 updel">
				<th colspan="6">
				<a class="btn btn-primary" href="/questionUpdateFrm.do?questionNo=<%=q.getQuestionNo() %>">수정</a>
				<button class="btn btn-secondary" onclick="questionDelete('<%=q.getQuestionNo() %>');">삭제</button>
			</tr>
			<%} %>
		</table>
		<%if(m != null) {%>
		<div class="inputCommentBox">
			<form action="/insertqComment.do" method="post">
				<ul>
					<li>
						<span class="material-icons">account_box</span>
					</li>
					<li>
						<input type="hidden" name="qcWriter" value="<%=m.getMemberId()%>">
						<input type="hidden" name="qcRef" value="<%=q.getQuestionNo()%>">
						<input type="hidden" name="qcCommentRef" value="0">
						<textarea class="input-form" name="qcComment"></textarea>
					</li>
					<li>
						<button type="submit" class="btn btn-lg btn-primary">등록</button>
					</li>
				</ul>
			</form>
		</div>
		<%} %>
		<div class="commentBox">
			<%for(QuestionComment qc : commentList){ %>
				<ul class="form-control">
					<li>
						<span class="material-icons">account_box</span>
					</li>
					<li>
						<p class="comment-info">
							<span><%=qc.getQcWriter() %></span><br>
							<span><%=qc.getQcDate() %></span>
						</p>
					</li>
					<li>
						<p class="form-control" style="margin-left:10px;margin-bottom:5px;"><%=qc.getQcComment() %></p>
						<textarea name="qcComment" class="input-form" style="display:none;min-height:90px;"><%=qc.getQcComment() %></textarea>
						<p class="comment-link">
						<%if(m != null) {%>
						 	<%if(m.getMemberId().equals(qc.getQcWriter())) {%>
						 		<a href="javascript:void(0)" onclick="modifyComment(this,'<%=qc.getQcNo()%>','<%=q.getQuestionNo()%>')">수정</a>
						 		<a href="javascript:void(0)" onclick="deleteComment(this,'<%=qc.getQcNo()%>','<%=q.getQuestionNo()%>')">삭제</a>
						 	<%} %>
						 	<a href="javascript:void(0)" class="recShow">답글달기</a>
						<%}//댓글 링크모음 로그인 체크 %>
						</p>
					</li>
				</ul>
			
				<%if(m != null) {%>
					<div class="inputRecommentBox">
						<form action="/insertqComment.do" method="post">
							<ul>
								<li>
									<span class="material-icons">subdirectory_arrow_right</span>
								</li>
								<li>
									<input type="hidden" name="qcWriter" value="<%=m.getMemberId()%>">
									<input type="hidden" name="qcRef" value="<%=q.getQuestionNo()%>">
									<input type="hidden" name="qcCommentRef" value="<%=qc.getQcNo()%>">
									<textarea class="input-form" name="qcComment"></textarea>
								</li>
								<li>
									<button type="submit" class="btn btn-lg btn-primary">등록</button>
								</li>
							</ul>
						</form>
					</div>
				<%}//대댓글 입력 form 작성조건 %>
				<%for(QuestionComment qcc : reCommentList) {%>
					<%if(qcc.getQcCommentRef() == qc.getQcNo()) {%>
						<ul class="form-control reply">
							<li>
								<span class="material-icons">subdirectory_arrow_right</span>
								<span class="material-icons">account_box</span>
							</li>
							<li>
								<p class="comment-info">
									<span><%=qcc.getQcWriter() %></span><br>
									<span><%=qcc.getQcDate() %></span>
								</p>
								<p class="form-control"style="margin-left:10px;margin-bottom:5px;"><%=qcc.getQcComment() %></p>
								<textarea name="qcComment" class="input-form" style="display:none;min-height:90px;"><%=qcc.getQcComment() %></textarea>
								<p class="comment-link">
									<%if(m!=null) {%>
										<%if(m.getMemberId().equals(qcc.getQcWriter())) {%>
											<a href="javascript:void(0)" onclick="modifyComment(this,'<%=qcc.getQcNo()%>','<%=q.getQuestionNo()%>')">수정</a><br>
											<a href="javascript:void(0)" onclick="deleteComment(this,'<%=qcc.getQcNo()%>','<%=q.getQuestionNo()%>')">삭제</a>
										<%} %>
									<%} %>
								</p>
							</li>
						</ul>
					<%}//해당 댓글의 대댓글인지 검사하는 조건 %>
				<%}//대댓글 출력용 for문 종료 %>
			<%} //댓글 출력용 for문 종료 %>
		</div>
		<script>
			function questionDelete(questionNo){
				if(confirm("삭제하겠습니다?")){
					location.href="/questionDelete.do?questionNo="+questionNo;	
				}
			}
			$(".recShow").on("click",function(){
				const idx = $(".recShow").index(this);
				if($(this).text() == "답글달기") {
					$(this).text("취소");
				}else{
					$(this).text("답글달기");
				}
				$(".inputRecommentBox").eq(idx).toggle();
				$(".inputRecommentBox").eq(idx).find("textarea").focus();
			});
			function modifyComment(obj,qcNo,questionNo){
				$(obj).parent().prev().show();	//textarea를 화면에 보여줌
				$(obj).parent().prev().prev().hide();	//기존 댓글은 화면에서 숨김
				//수정 -> 수정완료
				$(obj).text("수정완료");
				$(obj).attr("onclick","modifyComplete(this,'"+qcNo+"','"+questionNo+"')");
				//삭제 -> 수정취소
				$(obj).next().text("수정취소");
				$(obj).next().attr("onclick","modifyCancel(this,'"+qcNo+"','"+questionNo+"')");
				//답글달기버튼 숨김
				$(obj).next().next().hide();
			}
			function modifyCancel(obj,qcNo,questionNo){
				$(obj).parent().prev().hide();			//textarea 숨김
				$(obj).parent().prev().prev().hide();	//기존댓글 보여줌
				//수정완료 -> 수정
				$(obj).prev().text("수정");
				$(obj).prev().attr("onclick","modifyComment(this,'"+qcNo+"','"+questionNo+"')");
				//수정취소 -> 삭제
				$(obj).text("삭제");
				$(obj).attr("onclick","deleteComment(this,'"+qcNo+"','"+questionNo+"')");
				//답글달기버튼 보여줌
				$(obj).next().show();
			}
			function modifyComplete(obj,qcNo,questionNo){
				//form태그 생성
				const form = $("<form action='/questionCommentUpdate.do' method='post'></form>");
				//form태그 자식으로 input태그 추가(ncNo)
				form.append($("<input type='text' name='qcNo' value='"+qcNo+"'>"));
				//form태그 자식으로 input태그 추가(noticeNo)
				form.append($("<input type='text' name='questionNo' value='"+questionNo+"'>"));
				//form태그 자식으로 수정한 댓글 내용이 들어있는 textarea를 추가
				form.append($(obj).parent().prev());
				//생성된 form태그를 html 본문으로 삽입
				$("body").append(form);
				//form태그 submit
				form.submit();
			}
			function deleteComment(obj,qcNo,questionNo){
				if(confirm("댓글을 삭제하시겠습니까?")){
					location.href="/deleteqComment.do?qcNo="+qcNo+"&questionNo="+questionNo;
				}
			}
		</script>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>