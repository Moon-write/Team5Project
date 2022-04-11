<%@page import="kr.co.iei.msg.vo.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
		Message msg = (Message)request.getAttribute("msg");
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지 읽기</title>
    <!--기본 CSS-->
    <link rel="stylesheet" href="/css/index.css">
	<link rel="stylesheet" href="/css/msg.css">
    <!--구글폰트-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="js/jquery-3.6.0.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    <!-- 부트스트랩 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<style>
		#receiver-wrap>button{
			left: 4px;
		}
	</style>
</head>
<body>
	<div class="title-wrap">
		<span class="noneSelect">보낸쪽지</span>
	</div>
	<div class="btn-wrap" style="text-align: right;">
		<button href="#" class="btn btn-sm btn-primary" onclick="gotoBack()">이전 페이지로</button>
		<a class="btn btn-sm btn-secondary" href="/cancelMsg.do?msgBoardTitle=sendMsg&memberId=<%=msg.getMsgSender() %>&msgNo=<%=msg.getMsgNo()%>">전송취소</a>
		<a class="btn btn-sm btn-secondary" href="/deleteMsg.do?msgBoardTitle=sendMsg&memberId=<%=msg.getMsgSender() %>&msgNo=<%=msg.getMsgNo()%>" onclick="checkDel()">쪽지삭제</a>
	</div>

	<div id="receiver-wrap" style="width:100%"> <!-- 받는사람정보 -->
		<div class="form-control form-control-lg" style="width:100%; padding-left:100px;">
		<span id="senderName" class="noneSelect"><%=msg.getReceiverName() %></span>
		<span id="senderId" class="noneSelect">(<%=msg.getMsgReceiver() %>)</span>
		</div>
		<button class="btn btn-lg btn-secondary" disabled>받는이</button>
	</div>
	<div id="msgContent" class="form-control"><%=msg.getMsgContentBr() %></div>
	<a id="replyBtn" href="/gotoWriteMsg.do?memberId=<%=msg.getMsgSender() %>&msgReceiver=<%=msg.getMsgReceiver() %>" class="btn btn-lg btn-primary">새로쓰기</a>
	
	<script>
		function checkDel(){
			const result = confirm("보낸쪽지함에서만 삭제되며, 받는이의 쪽지함에서는 삭제되지 않습니다. 정말 삭제하시겠어요?");
			if(!result){
				alert("받는이가 읽지않음 상태의 쪽지는 전송취소를 통해 쪽지 취소 가능합니다.")
				return;
			}
		}		
	</script>	
	<script type="text/javascript" src="/js/msg.js"></script>
</body>
</html>

