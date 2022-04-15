<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
	.input-wrap{
		padding: 15px;
	}
	.input-wrap1{
		padding: 15px;
		display: flex;
		justify-content: center;
	}
	.input-wrap>label{
		font-size: 1.1em;
		margin-bottom: 10px;
		display: block;
	}
	[name=updateFrm] .btn-box{
		text-align: center;
		margin-top: 30px;
	}
	#signupButton{
		margin-top: 10px;
		width: 30%;
		height: 100%;
		font-size: 30px;
		line-height: 100px;
		margin-right: 10px;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="div-content">
		<div class="content-title">My Page</div>
		<form action="/updateMember.do" method="post" name="updateFrm">
			<div class="input-wrap">
				<label for="memberId">아이디</label>
				<input type="text" id="memberId" name="memberId" class="form-control" value="<%=m.getMemberId()%>" readonly><!-- id:라벨과연결 name:전송했을때 서버값추출 class:디자인  -->
			</div>
			<div class="input-wrap">
				<label for="memberPw">비밀번호</label>
				<input type="password" id="memberPw" name="memberPw" class="form-control" value="<%=m.getMemberPw()%>">
			</div>
			<div class="input-wrap">
				<label for="memberName">이름</label>
				<input type="text" id="memberName" name="memberName" class="form-control" value="<%=m.getMemberName()%>" disabled>
			</div>
			<div class="input-wrap">
				<label for="memberNickname">닉네임</label>
				<input type="text" id="memberNickname" name="memberNickname" class="form-control" value="<%=m.getMemberNickname()%>">
			</div>
			<div class="input-wrap">
				<label for="memberLevel">회원등급</label>
				<%if(m.getMemberLevel() == 0) {%>
					<input type="text" name="memberLevel" id="memberLevel" class="form-control" value="관리자" disabled>
				<%} else if(m.getMemberLevel() == 1) {%>
					<input type="text" name="memberLevel" id="memberLevel" class="form-control" value="정회원" disabled>
				<%} else if(m.getMemberLevel() == 2) {%>
					<input type="text" name="memberLevel" id="memberLevel" class="form-control" value="준회원" disabled>
				<%} %>
			</div>
			<div class="input-wrap">
				<label for="phone">전화번호</label>
				<input type="text" name="phone" id="phone" class="form-control" value="<%=m.getPhone()%>">
			</div>
			<div class="input-wrap">
				<label for="address">주소</label>
				<input type="text" name="address" id="address" class="form-control" value="<%=m.getAddress()%>">
			</div>
			<div class="input-wrap">
				<label for="email">이메일</label>
				<input type="text" name="email" id="email" class="form-control" value="<%=m.getEmail()%>">
			</div>
			<div class="input-wrap">
				<label for="enrollDate">가입일</label>
				<input type="text" name="enrollDate" id="enrollDate" class="form-control" value="<%=m.getEnrollDate()%>" disabled>
			</div>
			<div class="input-wrap1">
				<button type="submit" class="btn btn-primary button13" id="signupButton" style="width:200px;">정보수정</button>
				<%if(m.getMemberLevel() == 0) {%>
					<a class="btn btn-primary" id="signupButton" href="/adminPage.do" style="width:200px;">회원관리</a>
				<%}else{ %>
					<a class="btn btn-primary" id="signupButton" href="/deleteMember.do?memberNo=<%=m.getMemberNo()%>" style="width:200px;">회원탈퇴</a>
				<%} %>
			</div>
		</form> 
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>