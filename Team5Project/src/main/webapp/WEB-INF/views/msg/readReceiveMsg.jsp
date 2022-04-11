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
		<span class="noneSelect">받은쪽지</span>
	</div>
	<div class="btn-wrap" style="text-align: right;">
		<button class="btn btn-sm btn-primary" onclick="gotoBack()">이전 페이지로</button>
		<a class="btn btn-sm btn-secondary" id="makeRead" value="memberId=<%=msg.getMsgReceiver() %>&msgNo=<%=msg.getMsgNo() %>">안읽음처리</a>
		<a class="btn btn-sm btn-secondary" href="/deleteMsg.do?msgBoardTitle=receiveMsg&memberId=<%=msg.getMsgReceiver() %>&msgNo=<%=msg.getMsgNo()%>" onclick="checkDel()">쪽지삭제</a>
	</div>

	<div id="receiver-wrap" style="width:100%"> <!-- 보낸사람정보 -->
		<div class="form-control form-control-lg" style="width:100%; padding-left:100px;">
		<span id="senderName" class="noneSelect"><%=msg.getSenderName() %></span>
		<span id="senderId" class="noneSelect">(<%=msg.getMsgSender() %>)</span>
		</div>
		<button class="btn btn-lg btn-secondary" disabled>보낸이</button>
	</div>
	<div id="msgContent" class="form-control"><%=msg.getMsgContentBr() %></div>
	<a id="replyBtn" href="/gotoWriteMsg.do?memberId=<%=msg.getMsgReceiver() %>&msgReceiver=<%=msg.getMsgSender()%>" class="btn btn-lg btn-primary">답장쓰기</a>

	<script>
		function checkDel(){
			const result = confirm("정말 삭제하시겠어요?");
			if(!result){
				return;
			}
		}
		$("#makeRead").on("click",function(){	
			if($(this).text()=="읽음처리") {
				$.ajax({
					url: "/checkMsg.do?"+$(this).attr("value"),
					type : "get",
					success : function(data){
						if(data>0){
							$("#makeRead").text("안읽음처리");
						}
					},
					error : function(){
						alert("오류가 발생했습니다!");
					}
				})			
			} else{
				$.ajax({
					url: "/uncheckMsg.do?"+$(this).attr("value"),
					type : "get",
					success : function(data){
						if(data>0){
							$("#makeRead").text("읽음처리");							
						}
					},
					error : function(){
						alert("오류가 발생했습니다!");
					}
				});
			}
		});		
	</script>
	<script type="text/javascript" src="/js/msg.js"></script>
</body>
</html>

