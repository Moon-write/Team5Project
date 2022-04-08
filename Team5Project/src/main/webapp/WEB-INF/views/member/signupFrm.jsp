<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.input-wrap{
		padding: 15px;
	}
	#inputLarge11{
	
		display: inline-block;
	}
	#memberId{
		display: block;
	}
	#signupButton{
		margin-top: 10px;
		width: 100%;
		height: 100px;
		font-size: 30px;
	}
	#memberGender{
		display: block;
	}
	.input-wrap>.id-wrap{
		display: flex;
	}
	.input-wrap>.id-wrap>#memberId{
		width: 80%;
	}
	.input-wrap>.id-wrap>#button-addon2{
		width: 20%;
	}
</style>
</head>
<body>
	<%@include file ="/WEB-INF/views/common/header.jsp" %>
	<div class="div-content">
		<div class="content-title">회원가입 페이지</div>
		<form action="/signup.do" method="post">
			<div class="input-wrap">
				<label for="memberId" id="memberId">아이디</label>
				<div class="id-wrap">
					<input class="form-control form-control-lg" id="inputLarge11" type="text" name="memberId" id="memberId">
					<button class="btn btn-primary" type="button" id="button-addon2" class="">Checked</button>
				</div>
				<span id="ajaxCheckResult"></span>
			</div>
			<div class="input-wrap">
				<label for="memberPw">비밀번호</label>
				<input class="form-control form-control-lg" type="password" name="memberPw2" id="memberPw2">
			</div>
			<div class="input-wrap">
				<label for="memberPwRe">비밀번호확인</label>
				<input class="form-control form-control-lg" type="password" name="memberPwRe" id="memberPwRe">
				<span id="ajaxCheckResult1"></span>			
			</div>
			<div class="input-wrap">
				<label for="memberName">이름</label>
				<input class="form-control form-control-lg" type="text" name="memberName" id="memberName">
			</div>
			<div class="input-wrap">
				<label for="memberNickname">별명</label>
				<input class="form-control form-control-lg" type="text" name="memberNickname" id="memberNickname">
			</div>
			<div class="input-wrap">
				<label for="phone">번호</label>
				<input class="form-control form-control-lg" type="text" name="phone" id="phone">
			</div>
			<div class="input-wrap">
				<label for="memberAddress">주소</label>
				<input class="form-control form-control-lg" type="text" name="address" id="memberAddress">
			</div>
			<div class="input-wrap">
				<label for="email">이메일</label>
				<input class="form-control form-control-lg" type="text" name="email" id="email">
			</div>
			<div class="input-wrap">
				<label for="memberGender" id="memberGender">성별</label>
				<div class="form-check">
					<label class="form-check-label">
						<input type="radio" class="form-check-input" name="memberGender" value="남">남자
					</label><br>
				</div>
				<div class="form-check">
					<label class="form-check-label">
						<input type="radio" class="form-check-input" name="memberGender" value="여">여자
					</label><br>
				</div>
			</div>
			<div class="input-wrap">
				<button type="submit" class="btn btn-primary" id="signupButton">Sign Up</button>
			</div>
		</form>
	</div>
	<script>
		$("#button-addon2").on("click",function(){
			const memberId = $(this).prev().val();
			//유효성검사(아이디 몇글자이상 영어 숫자조합)
			//유효성검사 통과된경우 DB에 중복체크
			$.ajax({
				url : "/ajaxCheckId.do",
				type : "get",
				data : {memberId:memberId},
				success : function(data){
					if(data == "1"){
						$("#ajaxCheckResult").text("이미 사용중인 아이디입니다.");
						$("#ajaxCheckResult").css("color","#ff2e63");
					}else if(data == "0"){
						$("#ajaxCheckResult").text("사용가능한 아이디입니다.");
						$("#ajaxCheckResult").css("color","#00adb5");
					}
				},
				error : function(){
					console.log("서버요청실패");
				}
			});
		});
		
		$("[name=memberPwRe]").on("change",function (){
	        var pwd1 = $("#memberPw2").val();
	        var pwd2 = $("#memberPwRe").val();
	        
	        if (pwd1 != '' && pwd2 == '') {
	            null;
	        }else if (pwd1 != "" || pwd2 != "") {
	            if (pwd1 == pwd2) {
	            	$("#ajaxCheckResult1").text("비밀번호가 일치합니다.");
					$("#ajaxCheckResult1").css("color","#00adb5");
	            } else {
	            	$("#ajaxCheckResult1").text("비밀번호가 일치하지 않습니다.");
					$("#ajaxCheckResult1").css("color","#ff2e63");
	            }
	        }
	    });
		
	</script>
	<%@include file ="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>