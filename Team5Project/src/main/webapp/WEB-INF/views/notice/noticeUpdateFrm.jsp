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
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
		<div class="div-content">
			<div class="content-title">공지사항 수정</div>
		
		<form action="/noticeUpdate.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="noticeNo" value="<%=n.getNoticeNo() %>">
			<table class="table tabel-hover" id="noticeUpdateFrm">
				<tr class="table-success">
					<th class="table-light">제목</th>
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
					<td><textarea class="form-control" name="noticeContent"><%=n.getNoticeContent() %></textarea></td>
				</tr>
				<tr class="table-success">
					<th class="modifybtn" colspan="2"><button class="btn btn-dark" id="modifybtn">수정 완료</button></th>
				</tr>
			</table>
		</form>
		<script>
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