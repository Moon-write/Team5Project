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
<title><%=n.getNoticeNo() %>. 수정</title>
<style>
	.file-box{
		display: flex;
		align-items: center;
		justify-content: center;
	}
	.file-box{
		padding: 0.2rem 0.8rem;
	}
	.modifybtn{
		text-align: center;
	}
	#modifybtn{
		width: 150px;
	}
	.note-editable{
		background-color: #fff; 
	}
	#modifybtn1{
		background-color: #fff;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
		<div class="div-content">
			<div class="content-title">공지사항 수정</div>
		
		<form action="/noticeUpdate.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="noticeNo" value="<%=n.getNoticeNo() %>">
			<table class="table tabel-hover" id="noticeUpdateFrm">
				<tr class="table-success">
					<th class="table-light" style="min-width: 100px;">제목</th>
					<td>
						<input type="text" name="noticeTitle" class="form-control" value="<%=n.getNoticeTitle()%>">
					</td>
				</tr>
				<tr class="table-success">
					<th class="table-light">첨부파일</th>
					<td>
						<input type="hidden" name="status" value="stay">
						<%if(n.getFilepath() != null) {%>
							<div class="file-box">
								<img src="/img/file.png" width="16px" class="delFile">
								<span class="delFile"><%=n.getFilename() %></span>
								<button type="button" class="btn btn-secondary delFile" id="fileDelBtn" style="margin-left: 10px;">삭제</button>
								<input type="file" name="file" style="display:none;">
								<input type="hidden" name="oldFilename" value="<%=n.getFilename() %>">
								<input type="hidden" name="oldFilepath" value="<%=n.getFilepath() %>">
						    </div>
						<%} else {%>						
							<input type="file" name="file">
						<%} %>
					</td>
				</tr>
				<tr class="table-success">
					<th class="table-light">내용</th>
					<td><textarea class="form-control" name="noticeContent" id="noticeContent" style="height: 400px;"><%=n.getNoticeContent() %></textarea></td>
				</tr>
				<tr class="table-success">
					<th class="modifybtn" id="modifybtn1" colspan="2"><button class="btn btn-dark" id="modifybtn">수정 완료</button></th>
				</tr>
			</table>
		</form>
		<script>
			$("#noticeContent").summernote({
				height:400,
				lang:"ko-KR",
				callbacks:{
					onImageUpload : function(files){
						uploadImage(files[0],this);
					}
				}
			});
			function uploadImage(file,editor){
				//FormData객체 생성
				const form = new FormData();
				form.append("file",file);
				$.ajax({
					url: "/uploadImage.do",
					type: "post",
					data: form,
					processData: false,
					contentType: false,
					success: function(data){
						//결과로 받은 이미지 파일 경로를 에디터에 추가
						$(editor).summernote("insertImage",data);
					}
				});
			}
			$("#fileDelBtn").on("click",function(){
				$(".delFile").hide();
				$(this).next().show();
				$("[name=status]").val("delete");
			});
		</script>
		</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>