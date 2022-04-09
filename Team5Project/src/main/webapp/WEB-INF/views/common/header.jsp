<%@page import="kr.co.iei.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
		Member m = (Member)session.getAttribute("m");
	%>
<!--기본 CSS-->
<link rel="stylesheet" href="/css/index.css">
<!--구글폰트-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="js/jquery-3.6.0.js"></script>
<link rel="stylesheet" href="css/bootstrap.css">
<!-- 부트스트랩 JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<header>
  <div class="header-wrap">
      <div class="header-box">
          <div class="logo-box">
              <a href="/"><img src="img/logoflex.png" height="30px"></a>
              <span class="material-icons" id="menuBtn">expand_more</span>
          </div>
          <div class="member-box">
              <%if(m == null) {%>
                <div id="login-btn" class="member-title" >
                  <a href="#"><span class="material-icons">face</span>
                  <span class="member-content">로그인</span></a>
                </div>
                <div class="member-title">
                  <a href="signupFrm.do"><span class="material-icons">person_add_alt_1</span>
                  <span class="member-content">회원가입</span></a>
                </div>
              <%} else { %>
                <div class="member-title">
                  <a href="/logout.do"><span class="material-icons">logout</span>
                  <span class="member-content">로그아웃</span></a>
                </div>
              <div class="member-title" id="sendMsg" style="position:relative;">
                <a href="#"><span class="material-icons">message</span>
                <span class="member-content">쪽지</span></a>          	
              </div>
              <div class="member-title">
                <a href="#"><span class="material-icons">info</span>
                <span class="member-content">회원정보</span></a>
              </div>
            <%} %>  
          </div>
      </div>
      <div class="menu-wrap">
        <ul class="menu-list row">
            <li class="col-md-3"><a href="/noticeList.do?reqPage=1">공지사항</a></li>
            <li class="col-md-3"><a href="/free.do?reqPage=1&numPage=10">자유게시판</a></li>
            <li class="col-md-3"><a href="/questionList.do?reqPage=1">질문/답변</a></li>
            <li class="col-md-3"><a href="/searchClinic.do">주변 진료소 찾기</a></li>
        </ul>
      </div>
  </div>
</header>
<%if(m == null) { %>
  <div class="modal" id="login-modal">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">SIGN IN</h5>
          <button type="button" id="btn-close" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
            <span aria-hidden="true"></span>
          </button>
        </div>
        <form action="/signin.do" method="post">
          <div class="modal-body">
            <div>
              <label class="form-label mt-4" for="memberId">ID</label>
              <input type="text" name="memberId" class="form-control" id="memberId" placeholder="아이디입력">
            </div>
            <div>
              <label class="form-label mt-4" for="input-box">Password</label>
              <input type="password" name="memberPw" class="form-control" id="memberPw" placeholder="비밀번호입력">
            </div>
            <div class="input-group">
              <a href="#">Forgot username/password?</a>
            </div>
          </div>
          <div class="modal-footer">
            <button type="submit" class="btn btn-primary">Login</button>
            <button id="modal-close" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          </div>
        </form>
      </div>
  </div>
</div>
<%}else{ %> <!-- 로그인시 쪽지모달 사용 -->
<div class="modal" style="display: none;" id="msg-modal">
  <div class="modal-dialog" role="document"  style="min-width: 470px;">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Message</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
          <span aria-hidden="true"></span>
        </button>
      </div>
      <div class="modal-body">
        <iframe id="msgBox" src="/gotoReceiveMsg.do?memberId=<%=m.getMemberId() %>&msgBoardTitle=receiveMsg&pageNo=1" style="width:100%; height:485px;"></iframe>
      </div>
      <div class="modal-footer">
        <button id="writeBtn" type="button" class="btn btn-primary">WRITE</button>
        <input type="hidden" value="<%=m.getMemberId()%>" id="memId">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<script>
$("#sendMsg").on("click",function(){
  const memberId = $("#memId").val();
  $("#msgBox").attr("src", "/gotoReceiveMsg.do?msgBoardTitle=receiveMsg&pageNo=1&memberId="+memberId);
  $("#msg-modal").show();
})
$("#writeBtn").on("click",function(){
  const memberId = $("#memId").val();
  $("#msgBox").attr("src", "/gotoWriteMsg.do?memberId="+memberId);
})
$("#msg-modal button[data-bs-dismiss=modal]").on("click",function(){         
    $("#msg-modal").hide();
})
$.ajax({
  url: "/countUnreadMsg.do",
  type: "post",
  data: { memberId : $("#memId").val() },
  success: function(data){
    if(data!=0){
      const badge = $("<span class='badge rounded-pill bg-danger' style='position:absolute; top:20px; right:23px;'></span>");
      badge.text(data);				
      badge.appendTo("#sendMsg");				
    }
  }
})
</script>
<%} %> <!-- 로그인 되었을때에는 로그인 modal필요 없으므로 if문 처리 -->
<script>
$("#login-btn").on("click",function(){
  $("#login-modal").show();
});
$("#modal-close").on("click",function(){
  $("#login-modal").hide();
});
$("#btn-close").on("click",function(){
  $("#login-modal").hide();
});
  $("#menuBtn").on("click",function(){
      if($(this).text()=="expand_more"){
          $(".menu-list>li").show();
          $(this).text("expand_less");
      }else{
          $(".menu-list>li").hide();
          $(this).text("expand_more");
      }
  });
  $(window).resize(function(){
  const currentWidth = $(window).width(); // 현재 브라우저 너비 구하기
  // 현재창 너비가 760보다 큰데 내부ul이 보이지않는 상태면
  if(currentWidth > 768 && $(".menu-list>li").is(":hidden")){
      $(".menu-list>li").removeAttr("style");
  }
})
</script>