<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String memberId = (String) request.getAttribute("memberId");
    	String msgReceiver = (String) request.getAttribute("msgReceiver");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지쓰기</title>
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
	/*- - - - - - - - - - - - 받는이 영역*/
	#receiver-wrap>button{
		right: 4px;
	}	
	textarea{		
		margin-top: 10px;
		height: 260px;
		resize: none;
	}
	#receiver-wrap{
		position: relative;
	}
	#suggestList{
		display: none;
		position: absolute;
		top: 48px;
		left: 0px;
		width: 100%;
	}
	#suggestList>a{
		border : 1px solid #ced4da;
	}
</style>
</head>
<body>
	<div class="title-wrap">
		<span>쪽지 작성</span>
	</div>
	<div class="btn-wrap" style="text-align: right;">
		<button href="#" class="btn btn-sm btn-primary" onclick="gotoBack()">이전 페이지로</button>
	</div>
	<form action="/newMsg.do" method="post" onsubmit="return lastCheck()">
		<input type="hidden" value="<%=memberId%>" name="msgSender">
		<div id="receiver-wrap">
			<% if(msgReceiver==null) { %>
			<input type="text" class="form-control form-control-lg" placeholder="받는이" name="msgReceiver">
			<% } else { %>
			<input type="text" class="form-control form-control-lg" placeholder="받는이" name="msgReceiver" value=<%= msgReceiver %>>
			<% } %>
			<button type="button" id="searchId" class="btn btn-lg btn-secondary">ID 검색</button>
			<div id="suggestList" class="list-group">
				<!-- 객체 붙일자리 -->
			</div>
		</div>
		<textarea name="msgContent" class="form-control"></textarea>
		<input id="replyBtn" type="submit" class="btn btn-lg btn-primary" value="전송">
	</form>
	<script>
		function lastCheck(){
			const check = confirm("정말 보내시겠어요?");
			if(check==false){
				return false;
			}			
		};
		$("#searchId").on("click",function(){
			if($("#suggestList").css("display")=="none"){
				const keyword = $(this).prev().val();
				// ajax로 아이디 검색
				$.ajax({
					url: "/searchReceiver.do",
					type: "get",
					data: { "keyword" : keyword },
					success: function(data){
						$("#suggestList").empty();
						for(let i=0;i<data.length;i++) {
							const line = $("<button type='button' class='list-group-item list-group-item-action'>"+data[i].memberName+" ("+data[i].memberId+")"+"</button>");
							line.val(data[i].memberId);
							$("#suggestList").append(line);
							$("#suggestList").slideDown();
							$(this).text("접기");
						}
						
						$("button.list-group-item").on("click",function(){
							$(this).parent().prev().prev().val($(this).val());
							$("#suggestList").slideUp();
							$(this).text("ID 검색");
						})
					},
					error:function(){
						alert("오류! 관리자에게 문의하세요");
					}
				})

			} else {
				$("#suggestList").slideUp();
				$(this).text("ID 검색");
			}
		});		
	</script>
	<script type="text/javascript" src="/js/msg.js"></script>
</body>
</html>