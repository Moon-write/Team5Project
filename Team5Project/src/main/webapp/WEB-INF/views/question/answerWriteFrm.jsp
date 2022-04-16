<%@page import="kr.co.iei.question.vo.Question"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Question q = (Question)request.getAttribute("q");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	#questionWrite td, #questionWrite th{
		border : 1px solid #98978b;
	}
	.pad>td{
		padding-top: 0px;
	}
	.tt1{
		width: 20%;
		align-content: center;
		justify-content: center;
		margin: 0 auto;
		text-align: center;
		vertical-align: middle;
	}
	#btnclick{
		font-size: 30px;
		width: 100%;
		height: 100px;
		
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	<div class="div-content">
		<div class="content-title">답변 작성</div>
		<form action="/answerWrite2.do" method="post">
				<table class="table" id="questionWrite">
				<tr >
					<th class="table-success tt1">제목</th>
					<td colspan="4">
						<input type="hidden" name="questionTitle" class="form-control" value="<%=q.getQuestionTitle()%>" placeholder="<%=q.getQuestionTitle()%>" readonly><%=q.getQuestionTitle()%>
						<input type="hidden" name="queRef" value="<%=q.getQuestionNo() %>">
					</td>
				</tr>
				<tr>
					<th class="table-success tt1">작성자</th>
					<td>
						<input type="hidden" name="questionWriter" value="<%=m.getMemberId() %>">
						<%=m.getMemberId() %>
					</td>
				</tr>
				<tr class="tr-1 pad">
					<td colspan="4" style="text-align: left;"><textarea id="questionContent" style="width: 100%;height: 500px"  name="questionContent" class="form-cotrol" ></textarea></td>
				</tr>
				<tr>
					
					<td colspan="4"><button type="submit" class="btn btn-lg btn-primary" id="btnclick">답변 등록</button></td>
				</tr>
			</table>
		</form>
	</div>
	<script>
		$("#questionContent").summernote({
			height:400,
			lang : "ko-KR",
			callbacks:{
				onImageUpload : function(files){
					uploadImage(files[0],this);
				}
			}
		});
		function uploadImage(file,editor){
			//ajax통해 서버에 이미지를 업로드하고 업로드 경로를 받아옴
			//form태그와 동일한 효과를 내는 FormData객체 생성
			const form = new FormData();
			form.append("file",file);
			$.ajax({
				url: "/questionUploadImage.do",
				type : "post",
				data : form,
				processData : false,
				contentType : false,
				success : function(data){
					//결과로 받은 이미지파일 경로를 에디터에 추가
					$(editor).summernote("insertImage",data);
				}
			});
		}
			//processData : 기본값 ture {key1:value1,key2:value2, key3:value3}
			//					-> 파일전송 시 String이아니라 파일형태로 전송하기위해서 기본값 제거
			//contentType : 기본값 "application/x-www-form-ulrencoded;charset=UTF-8"
			//				-> form태그 전송 시 enctype의 기본값임
			//				-> enctype="multipart/form-data"로 설정하기위해 기본값을 제거
			
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>