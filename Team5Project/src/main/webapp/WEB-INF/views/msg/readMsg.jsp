<%@page import="kr.co.iei.msg.vo.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지 읽기</title>
    <!--기본 CSS-->
    <link rel="stylesheet" href="/css/index.css">
    <!--구글폰트-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="js/jquery-3.6.0.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    <!-- 부트스트랩 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>

<style>	/*- - - - - - - - - - - - 전체 영역*/
	body{
		width:100%; height: 600px;
		padding: 0px; margin: 0px;
		justify-content: center;
	}

	/*- - - - - - - - - - - - 전체 영역 (크기조정)*/



	/*- - - - - - - - - - - - 상단 제목처리 영역*/
	.title-wrap{
		padding-top: 5px;
		padding-bottom: 10px;
	}
	.title-wrap>span{
		font-size: 30px;
		font-weight: 900;
	}
	.btn-wrap>a{
		float: left;
	}
	.btn-wrap{
		padding-bottom: 10px;
	}
	/*- - - - - - - - - - - - 받는이 영역*/
	#receiver-wrap{
		position: relative;
		margin: 20px auto;
	}
	#receiver-wrap>button{
		position: absolute;
		right: 4px;
		top: 4px;
	}

	textarea{		
		height: 300px;
		resize: none;
	}

	input[type=submit]{
		margin-top: 20px;
		width: 100%;
		height: 50px;
		font-size: 15px;
	}

</style>
<body>
	<div class="title-wrap">
		<span>쪽지 읽기</span>
	</div>
	<div class="btn-wrap" style="text-align: right;">
		<a href="/gotoSendMsg.do" class="btn btn-sm btn-primary">보낸쪽지함으로 이동</a>
		<button class="btn btn-sm btn-secondary">수신확인</button>
		<button class="btn btn-sm btn-secondary">선택삭제</button>
	</div>
	<form action="/newMsg.do" method="post">
		<input type="hidden" value="<%=memberId%>" name="msgSender">
		<div id="receiver-wrap">
			<input type="text" class="form-control form-control-lg" placeholder="받는이" name="msgReceiver">
			<button class="btn btn-lg btn-secondary">ID 검색</button>
		</div>
		<textarea  name="msgContent" class="form-control"></textarea>
		<input type ="submit" class="btn btn-lg btn-primary" value="전송">
	</form>
	<script>
		$("#backBtn").on("click",function(){
			history.back();
		})
	</script>
</body>
</html>

