<%@page import="kr.co.iei.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Member m = (Member)session.getAttribute("m");
    	System.out.println("header : "+m);
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
                    <a href="#"><img src="img/logoflex.png" height="30px"></a>
                    <span class="material-icons" id="menuBtn">expand_more</span>
                </div>
                <div class="member-box">
                    <div id="login-btn" class="member-title" >
                        <a href="#"><span class="material-icons">face</span>
                        <span class="member-content">로그인</span></a>
                    </div>
                    <div class="member-title">
                        <a href="#"><span class="material-icons">person_add_alt_1</span>
                        <span class="member-content">회원가입</span></a>
                    </div>
                    <div class="member-title">
                        <a href="#"><span class="material-icons">logout</span>
                        <span class="member-content">로그아웃</span></a>
                    </div>
                    <div class="member-title">
                        <a href="#"><span class="material-icons">message</span>
                        <span class="member-content">쪽지</span></a>
                    </div>
                    <div class="member-title">
                        <a href="#"><span class="material-icons">info</span>
                        <span class="member-content">개인정보수정</span></a>
                    </div>
                </div>
            </div>
            <div class="menu-wrap">
                <ul class="menu-list row">
                    <li class="col-md-3"><a href="#">공지사항</a></li>
                    <li class="col-md-3"><a href="#">자유게시판</a></li>
                    <li class="col-md-3"><a href="#">질문/답변</a></li>
                    <li class="col-md-3"><a href="/searchClinic.do">주변 진료소 찾기</a></li>
                </ul>
            </div>
        </div>
    </header>
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
              <div class="input-group mb-3">
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