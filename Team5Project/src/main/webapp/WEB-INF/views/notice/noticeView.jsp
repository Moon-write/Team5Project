<%@page import="kr.or.iei.notice.vo.NoticeComment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.iei.notice.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Notice n = (Notice)request.getAttribute("n");
    	ArrayList<NoticeComment> commentList = (ArrayList<NoticeComment>)request.getAttribute("commentList");
    	ArrayList<NoticeComment> reCommentList = (ArrayList<NoticeComment>)request.getAttribute("reCommentList"); 
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=n.getNoticeNo()%>. <%=n.getNoticeTitle() %></title>
<style>
	#noticeView{
		border : 1.5px solid #907bb6;
	}
	#noticeView th, #noticeView td{
		border : 1px solid #ccc;
	}
	#noticeView td{
		background-color: #fff;
	}
	#noticeContent{
		min-height: 300px;
		text-align: left;
	}
	#buttons{
		text-align: center;
	}
	.noticeListbtn{
		text-align: center;
		padding-bottom: 5px;
	}
	.inputCommentBox{
		margin: 20px;
	}
	.inputCommentBox>form>ul{
		list-style-type: none;
		display: flex;
		padding: 0;
	}
	.inputCommentBox>form>ul>li:first-child{
		width: 10%;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.inputCommentBox>form>ul>li:first-child>span{
		font-size: 30px;
		color: #ccc;
	}
	.inputCommentBox>form>ul>li:nth-child(2){
		width: 80%;
	}
	.inputCommentBox>form>ul>li:nth-child(2)>textarea.form-control{
		height: 58px;
		min-height: 58px;
	}
	.inputCommentBox>form>ul>li:last-child{
		width: 10%;
	}
	.commentBox>ul{
		list-style-type: none;
	}
	.commentBox>ul>li{
		list-style-type: none;
		display: flex;
	}
	.comment-info>span:nth-child(2){
		display: inline;
	}
	.commentBox>ul>li:first-child{
		width: 7.5%;
		display: flex;
	}
	.form-control{
		width: 70px;
		margin-bottom: 10px;
	}
	.comment-info{
		font-size: 12px;
		width: 100px;
		margin: 0;
		text-align: center;
	}
	.comment-info>span:nth-child(2){
		font-size: 8px;
	}
	.form-control>li:first-child{
		justify-content: center;
	}
	.form-control>li:first-child{
		justify-content: center;
	}
	.form-control>.form-control-sm{
		width: 60%;
	}
	.btn-link{
		width: 100px;
		margin: 0;
		padding: 0;
	}
	.inputRecommentBox{
		margin: 30px 0px;
		display: none;
	}
	.inputRecommentBox>form>ul{
		list-style-type: none;
		display: flex;
	}
	.inputRecommentBox>form>ul>li:first-child{
		width: 5%;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.inputRecommentBox>form>ul>li:first-child>span{
		font-size: 20px;
		color: #ccc;
	}
	.inputRecommentBox>form>ul>li:nth-child(2){
		width: 80%;
	}
	.inputRecommentBox>form>ul>li:nth-child(2)>textarea.input-form{
		height: 20px;
		min-height: 20px;
	}
	.inputRecommentBox>form>ul>li:last-child{
		width: 15%;
	}
	#replyComment{
		margin-left: 30px;
		background-color: #E4DAF7;
	}
	#commentstyle{
		text-decoration: none;
	}
	#commentstyle>a{
		color:purple;
		background-color: #fff;
		padding: 2px;
	}
	.material-icons{
		font-size: 15px;
	}
	#good:hover{
		cursor: pointer;
	}
	#content{
		border-bottom: none;
		margin-bottom: none;
	}
	.goodgood{
		float: left;	
	}
	.table-success{
		text-align: center;
	}
	#contentleft{
		text-align: left;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="div-content">
		<div class="content-title" id="content">????????????</div>
		
		<table class="table tabel-hover" id="noticeView">
			<tr class="table-success">
				<th class="table-light" style="min-width: 100px; ">??????</th>
				<td colspan="7"><%=n.getNoticeTitle() %></td>
			</tr>
			<tr class="table-success">
				<th class="table-light">?????????</th>
				<td><%=n.getNoticeWriter() %></td>
				<th class="table-light">?????????</th>
				<td><%=n.getRegDate() %></td>
				<th class="table-light">?????????</th>
				<td><%=n.getReadCount() %></td>
				<th class="table-light">??????</th>
				<td>
					<%if(m != null) {%>
						<input type="hidden" name="ncWriter" id="memberId" value="<%=m.getMemberId() %>">
						<input type="hidden" name="noticeRef" id="noticeNo"value="<%=n.getNoticeNo() %>">
						<div class="goodgood">
							<%if(n.getClickLike() == 1) {%>						
							<span class="material-icons" id="good">thumb_up</span>
							<%}else{ %>
							<span class="material-icons" id="good">thumb_up_off_alt</span>
							<%} %>
						<%} %>
						</div>
						<div>
						<span id="likeCount"><%=n.getLikeNumber() %></span>
						</div>
				</td>
			</tr>
			<tr class="table-success">
				<th class="table-light">????????????</th>
				<td colspan="7">
					<%if(n.getFilename() != null) {%>
						<img src="/img/file.png" width="16px">
						<a href="/fileDown.do?noticeNo=<%=n.getNoticeNo() %>"><%=n.getFilename() %></a>
					<%} %>
				</td>
			</tr>
			<tr class="table-success">
				<th class="table-light">??????</th>
				<td colspan="7" id="contentleft">
					<div id="noticeContent"><%=n.getNoticeContent() %></div>
				</td>
			</tr>
			<%if(m!=null && m.getMemberId().equals(n.getNoticeWriter())) {%>
			<tr scope="row">
				<th id="buttons" colspan="8">
					<a class="btn btn-primary" href="/noticeUpdateFrm.do?noticeNo=<%=n.getNoticeNo() %>">??????</a>
					<button class="btn btn-secondary" onclick="noticeDelete('<%=n.getNoticeNo() %>');">??????</button>
				</th>
			</tr>
			<%} %>
		</table>
		<div class="noticeListbtn">
			<button class="btn btn-light" id="noticeListbtn" onclick="noticeList()">????????????</button>
		</div>
		<%if(m!=null) {%>
		<div class="inputCommentBox">
			<form action="/insertComment.do" method="post">
				<ul>
					<li>
						<span class="material-icons">account_box</span>
					</li>
					<li>
						<input type="hidden" name="ncWriter" value="<%=m.getMemberId() %>">
						<input type="hidden" name="noticeRef" value="<%=n.getNoticeNo() %>">
						<input type="hidden" name="ncRef" value="0">
						<textarea class="form-control" name="ncContent"></textarea>					
					</li>
					<li>
						<button type="submit" class="btn btn-primary" style="height: 59px;margin-left: 20px;">??????</button>
					</li>
				</ul>
			</form>
		</div>
		<%} %>
		<div class="commentBox">
			<%for(NoticeComment nc : commentList) {%>
				<ul class="form-control" style="padding-top: 12px;">
					<li>
						<span class="material-icons">account_box</span>
					</li>
					<li>
						<p class="comment-info">
							<span><%=nc.getNcWriter() %></span><br>
							<span><%=nc.getNcDate() %></span>
						</p>
						<p class="form-control form-control-sm"><%=nc.getNcContent() %></p>
						<textarea name="ncContent" class="input-form" style="display:none; min-height: 44px; min-width: 650px"><%=nc.getNcContent() %></textarea>
						<p class="btn btn-link" id="commentstyle" style="padding-top: 0px;">
							<%if(m != null) {%>
								<%if(m.getMemberId().equals(nc.getNcWriter())) {%>
									<a href="javascript:void(0)" onclick="modifyComment(this,'<%=nc.getNcNo()%>','<%=n.getNoticeNo()%>')">??????</a><br>
									<a href="javascript:void(0)" onclick="deleteComment(this,'<%=nc.getNcNo()%>','<%=n.getNoticeNo()%>')">??????</a><br>
								<%} %>
								<a href="javascript:void(0)" class="recommentShow">????????????</a>
							<%} %>
						</p>
					</li>
				</ul>
				
				<%for(NoticeComment ncc : reCommentList) {%>
					<%if(ncc.getNcRef() == nc.getNcNo()) {%>
						<ul class="form-control" id="replyComment" style="padding-top: 12px;">
							<li style="padding-right: 15px;">
								<span class="material-icons">subdirectory_arrow_right</span><br>
								<span class="material-icons">account_box</span>
							</li>
							<li>
								<p class="comment-info">
									<span><%=ncc.getNcWriter() %></span><br>
									<span><%=ncc.getNcDate() %></span>
								</p>
								<p class="form-control form-control-sm"><%=ncc.getNcContent() %></p>
								<textarea name="ncContent" class="form-control" style="display:none; min-height: 90px;"><%=ncc.getNcContent() %></textarea>
								<p class="btn btn-link" id="commentstyle">
									<%if(m!=null) {%>
										<%if(m.getMemberId().equals(ncc.getNcWriter())) {%>
											<a href="javascript:void(0)" onclick="modifyComment(this,'<%=ncc.getNcNo()%>','<%=n.getNoticeNo()%>')">??????</a><br>
											<a href="javascript:void(0)" onclick="deleteComment(this,'<%=ncc.getNcNo()%>','<%=n.getNoticeNo()%>')">??????</a>
										<%} %>
									<%} %>
								</p>
							</li>
						</ul>
					<%} %>
				<%} //?????????for??? ?????? %>
				<%if(m!= null) {%>
				<div class="inputRecommentBox">
					<form action="/insertComment.do" method="post">
						<ul style="padding-top: 12px;">
							<li>
								<span class="material-icons">subdirectory_arrow_right</span>
							</li>
							<li>
								<input type="hidden" name="ncWriter" value="<%=m.getMemberId() %>">
								<input type="hidden" name="noticeRef" value="<%=n.getNoticeNo() %>">
								<input type="hidden" name="ncRef" value="<%=nc.getNcNo()%>">
								<textarea class="form-control" name="ncContent"></textarea>
							</li>
							<li>
								<button type="submit" class="btn btn-link"style="padding-left: 0px;padding-right: 0px;margin-top: 12px;">??????</button>
							</li>
						</ul>
					</form>
				</div>
				<%} //????????? form%>
			<%}	//??????for????????? %>
		</div>
		<script>
			function noticeDelete(noticeNo){
				if(confirm("?????????????????????????")){
					location.href="/noticeDelete.do?noticeNo="+noticeNo;
				}
				
			}
			$(".recommentShow").on("click",function(){
				const idx = $(".recommentShow").index(this);
				if($(this).text() == "????????????"){
					$(this).text("??????");
				}else{
					$(this).text("????????????");
				}
				$(".inputRecommentBox").eq(idx).toggle();
			});
			function modifyComment(obj,ncNo,noticeNo){
				$(obj).parent().prev().show();	//textarea??? ????????? ?????????
				$(obj).parent().prev().prev().hide();	//?????? ????????? ???????????? ??????
				//?????? -> ????????????
				$(obj).text("????????????");
				$(obj).attr("onclick","modifyComplete(this,'"+ncNo+"','"+noticeNo+"')");
				//?????? -> ????????????
				$(obj).next().text("????????????");
				$(obj).next().attr("onclick","modifyCancel(this,'"+ncNo+"','"+noticeNo+"')");
				//?????????????????? ??????
				$(obj).next().next().hide();
			}
			function modifyCancel(obj,ncNo,noticeNo){
				$(obj).parent().prev().hide();	//textarea??? ??????
				$(obj).parent().prev().prev().show();//?????? ?????? ?????????
				//???????????? -> ??????
				$(obj).prev().text("??????");
				$(obj).prev().attr("onclick","modifyComment(this,'"+ncNo+"','"+noticeNo+"')");
				//????????????-> ??????
				$(obj).text("??????");
				$(obj).attr("onclick","deleteComment(this,'"+ncNo+"','"+noticeNo+"')");
				//?????????????????? ?????????
				$(obj).next().show();
			}
			function modifyComplete(obj,ncNo,noticeNo){
				//form?????? ??????
				const form = $("<form action='/noticeCommentUpdate.do' method='post'></form>");
				//form?????? ???????????? input?????? ??????(ncNo)
				form.append($("<input type='text' name='ncNo' value='"+ncNo+"'>"));	
				//form?????? ???????????? input?????? ??????(noticeNo)
				form.append($("<input type='text' name='noticeNo' value='"+noticeNo+"'>"));
				//form?????? ???????????? ????????? ?????? ????????? ???????????? textarea??? ??????
				form.append($(obj).parent().prev());
				//????????? form????????? html???????????? ??????
				$("body").append(form);
				//form?????? submit
				form.submit();
			}
			function deleteComment(obj,ncNo,noticeNo){
				if(confirm("????????? ?????????????????????????")){
					location.href="/deleteComment.do?ncNo="+ncNo+"&noticeNo="+noticeNo;
				}
			}
			$("#good").on("click",function(){
				let status = 1;
				if($(this).text() == "thumb_up"){
					//?????? ???????????? ????????? ???????????? -> ???????????? ???????????????
					status = 1;
				}else{
					//?????? ???????????? ??????????????? ???????????? -> ???????????? ????????????.
					status = 2;
				}
				$.ajax({
	                url : "/clicklike.do",
	                type : "get",
	                data : {
	                	noticeNo : $("#noticeNo").val(),
	                	memberId : $("#memberId").val(),
	                	status : status
	                },	                
	                success : function(data){
	                	
	                	if(data == -1){
	                		
	                	}else{
		    				if($("#good").text() == "thumb_up"){
		    					$("#good").text("thumb_up_off_alt");
		    					confirm("?????? ?????? ????????? ?????????????????????.");
		    				}else{
		    					$("#good").text("thumb_up");
		    					confirm("?????? ?????? ?????????????????????.");
		    				}	       
		    				$("#likeCount").text(data);
	                	}
	                	/*

	                	*/
	                },
	                error : function(){
	                    console.log("??????");
	                },
	                complete : function(){
	                	
	                }
	            });
			});
			function noticeList(){
				location.href="/noticeList.do?reqPage=1";
			}
		</script>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>