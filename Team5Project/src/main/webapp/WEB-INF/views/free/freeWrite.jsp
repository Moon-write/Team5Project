<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글 작성</title>
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
	.tr-2 *{
		width:25%;
	}
	#insertbtn{
		width:100%;
		height:80px;
		font-size:30px;
		color:black;
	}
</style>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	<div class="div-content">
        <div class="content-title">자유게시판 글 작성</div>
		<form action="/freeInsert.do" method="post">
			<table class="table" id="freeInsert">
				<tr class="tr">
					<th class="table-success">제목</th>
					<td colspan="3">
						<input type="text" name="Title" class="form-control">
					</td>
				</tr>
				<tr class="tr tr-2">
					<th class="table-success">작성자</th>
					<td>
						<input type="hidden" name="Id" value="<%=m.getMemberId() %>">
						<%=m.getMemberNickname() %>
					</td>
					<th class="table-success">작성 일자</th>
					<td><%=LocalDate.now() %></td>
				</tr>
				<tr class="tr">
					<td colspan="4" style="text-align:left;"><textarea id="Content" name="Content" class="input-form"></textarea></td>
				</tr>
				<tr class="tr">
					<td colspan="4"><button type="submit" class="btn btn-primary" id="insertbtn">글 등록</button></td>
				</tr>
			</table>
		</form>
	</div>
	<script>
		$("#Content").summernote({
			height:400,
			lang:"ko-KR",
			callbacks:{
				onImageUpload:function(files){
					uploadImage(files[0],this);
				}
			}
		});
		function uploadImage(file,editor){
			const form = new FormData();
			form.append("file",file);
			$.ajax({
				url:"/freeImageUpload.do",
				type:"post",
				data:form,
				processData:false,
				contentType:false,
				success:function(data){
					$(editor).summernote("insertImage",data);
				}
			});
		}
	</script>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>