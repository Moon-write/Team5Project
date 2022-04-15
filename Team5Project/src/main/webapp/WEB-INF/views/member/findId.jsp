<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.form-control{
		margin-bottom: 30px;
	}
	.btn{
		width: 100%;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
		<div class="div-content" style="width:70%">
			<div class="content-title">아이디 찾기
				<h5>가입시 입력하신 이메일을 통해 찾을 수 있습니다.</h5>
			</div>
			<form action="/findIdAfter.do" method="post" id="email12" name="email12">
				<div class="input-wrap">
					<label for="email">이메일</label><!-- name값이 서블릿에서 읽기위한 매핑값 -->
					<input class="form-control form-control-lg" type="text" name="email" id="email" placeholder="가입시 입력한 이메일 주소">
				</div>
				<div class="input-wrap">
					<button type="submit" class="btn btn-primary" id="signupButton" style="margin-bottom:5px">아이디 찾기</button>
				</div>
			</form>
			<form action="/findPw.do" method="post" id="" name="">
				<button type="submit" class="btn btn-primary" id="signupButton">비밀번호 찾기</button>
			</form>
			
		</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>