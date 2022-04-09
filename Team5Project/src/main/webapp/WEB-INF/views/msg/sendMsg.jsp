<%@page import="kr.co.iei.msg.vo.Message"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   	<%
		ArrayList<Message> list = (ArrayList<Message>) request.getAttribute("list");
   		Integer pageNo = (Integer) request.getAttribute("pageNo");
   		Integer totalPage = (Integer) request.getAttribute("totalPage");
   		String memberId = (String)request.getAttribute("memberId");
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보낸쪽지함</title>
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
	/*- - - - - - - - - - - - 전체 영역 (크기조정)*/
	.table>li>ul>li:first-child{
		width: 10%;
	}
	.table>li>ul>li:nth-child(2){
		width: 20%;
	}
	.table>li>ul>li:nth-child(3){
		width: 30%;
	}
	.table>li>ul>li:nth-child(4){
		width: 20%;
	}
	.table>li>ul>li:nth-child(5){
		width: 20%;
	}	
	.msg-content>li:nth-of-type(5){ /*수신확인 전용*/
		display: flex;
		justify-content: center;
		align-items: center;
	}	
	</style>
</head>
<body>
	<div class="title-wrap">
		<span>보낸쪽지함</span>
	</div>
	<div class="btn-wrap" style="text-align: right;">
		<a href="/gotoReceiveMsg.do?msgBoardTitle=receiveMsg&pageNo=1&memberId=<%=memberId %>" class="btn btn-sm btn-primary">받은쪽지함으로 이동</a>
		<button id="checkCancel" class="btn btn-sm btn-secondary">전송취소</button>
		<button id="checkDelete" class="btn btn-sm btn-secondary">선택삭제</button>
	</div>
	<ul class="table">
		<li class="table-light">
			<ul>
				<li id="checkboxCol"><span class="material-icons" style="color:#a393ac;">check_box</span></li>
				<li id="nameCol">받는이</li>
				<li id="contentCol">내용</li>
				<li id="dateCol">날짜</li>
				<li id="readCol">수신확인</li>
			</ul>
		</li>
		<% if(list==null||list.size()==0){ %>
		<li class="table-default"  style="border-top: 1px solid #ccc;">
			<ul class="msg-content">
				<li style="width:100%">보낸 쪽지가 없습니다!</li>
			</ul>
		</li>
		<% } else { %>
			<% for(int i=0;i<list.size();i++) { %>
		<li class="table-default"  style="border-top: 1px solid #ccc;">
			<ul class="msg-content">
				<li>
					<input class="form-check-input" type="checkbox" value="<%=list.get(i).getMsgNo() %>" id="flexCheckChecked">
					<input type="hidden" id="memberHidden" value="<%=list.get(i).getMsgSender() %>">	
				</li>
				<li>
					<div class="nameWrap">
						<span><b><%=list.get(i).getReceiverName() %></b></span>
						<span><%=list.get(i).getMsgReceiver() %></span>
					</div>
					<a class="writePopup">쪽지보내기</a>
				</li>
				<li><a href="/readSendMsg.do?msgNo=<%=list.get(i).getMsgNo()%>" style="color:#383d40;"><%=list.get(i).getMsgContent() %></a></li>
				<li>
					<span><%=list.get(i).getMsgDate().substring(0, 10) %></span>
					<span><%=list.get(i).getMsgDate().substring(11) %></span>
				</li>
				<li>
					<% if(list.get(i).getMsgRead()==0) { %>
					읽지않음
					<% } else {%>
					읽음
					<% } %>
				</li>
			</ul>
		</li>	
			<% } %>
		<% } %>

	</ul>
	<div class="page-wrap">
		<ul class="pagination">
		<% if(totalPage==0) { %>
		<% } else { %>
			<% if(pageNo>3){ %>
			<li class="page-item"><a href="gotoReceiveMsg.do?msgBoardTitle=sendMsg&pageNo=<%=pageNo-2%>&memberId=<%=memberId %>" class="page-link"><<</a></li>
			<% } %>
			<% if(pageNo>=2) { %>
			<li class="page-item"><a href="gotoReceiveMsg.do?msgBoardTitle=sendMsg&pageNo=<%=pageNo-1%>&memberId=<%=memberId %>" class="page-link"><%=pageNo-1 %></a></li>
			<% } %>
			<li class="page-item"><a href="#" class="page-link active" onclick="javascript:void(0)"><%=pageNo %></a></li>
			<% if(pageNo<totalPage) { %>
			<li class="page-item"><a href="gotoReceiveMsg.do?msgBoardTitle=sendMsg&pageNo=<%=pageNo+1%>&memberId=<%=memberId %>" class="page-link"><%=pageNo+1 %></a></li>
			<% } %>
			<% if(pageNo+1<totalPage) { %>
			<li class="page-item"><a href="gotoReceiveMsg.do?msgBoardTitle=sendMsg&pageNo=<%=pageNo+2%>&memberId=<%=memberId %>" class="page-link">>></a></li>
			<% } %>
		<% } %>
		</ul>
	</div>	
	<script>
		$("#checkCancel").on("click",function(){
			const checked = $(".msg-content>li>input:checked");
			const memberId = $("#memberHidden").val();
			if(checked.length==0){
				alert("선택된 쪽지가 없습니다!");
				return;
			}
			const val = new Array(); // 글번호 저장용 배열
			checked.each(function(index, item){
				val.push($(this).val());
			})
			location.href="/cancelAllMsg.do?msgBoardTitle=sendMsg&memberId="+memberId+"&list="+val.join("/");		
		})
		$("#checkDelete").on("click",function(){
			const checked = $(".msg-content>li>input:checked");
			const memberId = $("#memberHidden").val();
			if(checked.length==0){
				alert("선택된 쪽지가 없습니다!");
				return;
			}
			const result = confirm("보낸쪽지함에서만 삭제되며, 받는이의 쪽지함에서는 삭제되지 않습니다. 정말 삭제하시겠어요?");
			if(!result){
				alert("받는이가 읽지않음 상태의 쪽지는 전송취소를 통해 쪽지 취소 가능합니다")
				return;
			}
			const val = new Array(); // 글번호 저장용 배열
			checked.each(function(index, item){
				val.push($(this).val());
			})
			location.href="/deleteAllMsg.do?msgBoardTitle=sendMsg&memberId="+memberId+"&list="+val.join("/");				
		})
	</script>
	<script type="text/javascript" src="/js/msg.js"></script>
</body>
</html>