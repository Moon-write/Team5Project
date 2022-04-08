<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	.input-form{
		width: 100%;
	}
	.note-editable{
		background-color: #fff; 
	}
	#submitbutton{
		text-align: center;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	<div class="div-content">
		<div class="content-title">공지사항 작성</div>
		<form action="/noticeWrite.do" method="post" enctype="multipart/form-data">
			<table class="table tabel-hover" id="noticeWrite">
				<tr class="table-success">
					<th class="table-light">제목</th>
					<td colspan="8">
						<input type="text" name="noticeTitle" class="form-control">
					</td>
				</tr>
				<tr class="table-success">
					<th class="table-light">작성자</th>
					<td>
						<input type="hidden" name="noticeWriter" value="<%=m.getMemberId() %>"><%=m.getMemberId() %>
					</td>
					<th class="table-light">첨부파일</th>
					<td><input type="file" name="file"></td>
				</tr>
				<tr class="table-success">
					<td colspan="4" style="text-align: left;"><textarea id="noticeContent" name="noticeContent" class="form-control"></textarea></td>
				</tr>
				<tr>
					<td colspan="4" id="submitbutton"><button type="submit" class="btn btn-primary">공지사항등록</button></td>
				</tr>
			</table>
		</form>
	</div>
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
		</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>