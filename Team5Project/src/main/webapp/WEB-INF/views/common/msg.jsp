<%@page import="kr.co.iei.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
    	String title = (String)request.getAttribute("title");
    	String msg = (String)request.getAttribute("msg");
    	String icon = (String)request.getAttribute("icon");
    	String loc = (String)request.getAttribute("loc");
    	Member m = (Member)session.getAttribute("m"); // request가 아닌 session에서 꺼내서 쓴다 마찬가지고 오브젝트 타입으로 저장되기 때문에 형변환은 필수!
    	System.out.println("msg : "+m); //로그인 성공했을 시, 회원정보가 다 들어있다 m에는
    	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="/js/sweetalert.min.js"></script>
</head>
<body>
	<!-- alert 후 페이지 이동하는 기능용 페이지 -->
	<script>
		window.onload = function(){
			swal({
				title : "<%=title%>",
				text : "<%=msg%>",
				icon : "<%=icon%>"
			}).then(function(){
				location.href="<%=loc%>";
			});
		}
	</script>
</body>
</html>