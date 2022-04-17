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
			<div class="content-title">비밀번호 찾기
				<h5>가입시 입력하신 이름,아이디,이메일을 통해 찾을 수 있습니다.</h5>
			</div>
			<form action="/findPwAfter.do" method="post">
				<div class="input-wrap">
					<label for="memberId12">이름</label><!-- name값이 서블릿에서 읽기위한 매핑값 -->
					<input class="form-control form-control-lg" type="text" name="memberName" id="memberName" placeholder="가입시 입력한 이름">
					<label for="memberId12">아이디</label><!-- name값이 서블릿에서 읽기위한 매핑값 -->
					<input class="form-control form-control-lg" type="text" name="memberId" id="memberId" placeholder="가입시 입력한 아이디">
					<label for="memberId12">이메일</label><!-- name값이 서블릿에서 읽기위한 매핑값 -->
					<input class="form-control form-control-lg" type="text" name="email" id="email" placeholder="가입시 입력한 이메일">
				</div>
				<div class="input-wrap">
					<button type="submit" class="btn btn-primary" id="signupButton" style="margin-bottom:5px">비밀번호 찾기</button>
					<button type="button" class="btn btn-primary button12" id="signupButton" style="margin-bottom:5px" onclick="findId()">이전화면으로 이동</button>
				</div>
			</form>
			
		</div>
	<script>
		function findId(){
			location.href="/findId.do";
		}
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>