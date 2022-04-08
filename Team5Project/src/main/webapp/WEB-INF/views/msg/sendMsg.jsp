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
<title>받은쪽지함</title>
    <!--기본 CSS-->
    <link rel="stylesheet" href="/css/index.css">
    <!--구글폰트-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="js/jquery-3.6.0.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    <!-- 부트스트랩 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<style>
	/*- - - - - - - - - - - - 전체 영역*/
	body{
		width:100%; height: 600px;
		padding: 0px; margin: 0px;
	}
	ul, li{
		margin: 0px;
		padding: 0px;
		list-style-type: none;
	}
	.table{
		border-top: 1px solid #a393ac;
		border-bottom: 1px solid #a393ac;
	}

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

	/*- - - - - - - - - - - - 리스트 제목 영역*/
	.table>li:first-child>ul{
		height: 40px;
		font-size: 1em;
		line-height: 24px;
		font-weight: 900;
	}

	/*- - - - - - - - - - - - 리스트 본문 영역*/
	.table>li>ul{ /*제목 공통적용*/
		display: flex;
		height: 56px;
		line-height: 20px;
		font-size: 0.8em;
		text-align: center;
		
	}
	
	.msg-content>li:nth-of-type(1){ /*체크칸 전용*/
		display: flex;
		justify-content: center;
		align-items: center;
	}


	.msg-content>li:nth-of-type(1)>input{ /*체크칸 전용*/
		display: block;
		width: 18px; height: 18px;
		margin: 0 auto;				
	}

	.msg-content>li:nth-of-type(3){ /*내용칸 전용*/
		height: 100%;
		display: block;
		word-break: break-all;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		line-height: 40px;
	}

	.msg-content>li>span{
		display: block;
	}

	.msg-content>li:nth-of-type(5){ /*수신확인 전용*/
		display: flex;
		justify-content: center;
		align-items: center;
	}


	/*- - - - - - - - - - - - 페이지처리 영역*/
	.pagination{
		justify-content: center;
	}

	/*- - - - - - - - - - - - 상단 제목처리 영역*/
	.title-wrap{
		padding-top: 5px;
		padding-bottom: 15px;
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
</style>
<body>
	<div class="title-wrap">
		<span>보낸쪽지함</span>
	</div>
	<div class="btn-wrap" style="text-align: right;">
		<a href="/gotoReceiveMsg.do?msgBoardTitle=receiveMsg&pageNo=1&memberId=<%=memberId %>" class="btn btn-sm btn-primary">받은쪽지함으로 이동</a>
		<button class="btn btn-sm btn-secondary">발송취소</button>
		<button class="btn btn-sm btn-secondary">선택삭제</button>
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
				<li style="width:100%">받은 쪽지가 없습니다!</li>
			</ul>
		</li>
		<% } else { %>
			<% for(int i=0;i<list.size();i++) { %>
		<li class="table-default"  style="border-top: 1px solid #ccc;">
			<ul class="msg-content">
				<li><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></li>
				<li>
					<span><b><%=list.get(i).getReceiverName() %></b></span>
					<span><%=list.get(i).getMsgReceiver() %></span>
				</li>
				<li><%=list.get(i).getMsgContent() %></li>
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
			<li class="page-item"><a href="#" class="page-link"><<</a></li>
			<% } %>
			<% if(pageNo>2) { %>
			<li class="page-item"><a href="#" class="page-link"><%=pageNo-1 %></a></li>
			<% } %>
			<li class="page-item"><a href="#" class="page-link"><%=pageNo %></a></li>
			<% if(pageNo<totalPage) { %>
			<li class="page-item"><a href="#" class="page-link"><%=pageNo+1 %></a></li>
			<% } %>
			<% if(pageNo+1<totalPage) { %>
			<li class="page-item"><a href="#" class="page-link">>></a></li>
			<% } %>
		<% } %>
		</ul>
	</div>
</body>
</html>