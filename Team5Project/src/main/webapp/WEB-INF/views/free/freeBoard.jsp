<%@page import="kr.co.iei.free.vo.Free"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<Free> list = (ArrayList<Free>)request.getAttribute("list");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="div-content">
        <div class="content-title">자유게시판</div>
        
		<a class="btn btn-primary" href="/freeInsert.do">글쓰기</a>
		
        <table class="table table-hover">
		  <thead>
		    <tr class=table-success>
		      <th scope="col">No</th>
		      <th scope="col">제목</th>
		      <th scope="col">작성자</th>
		      <th scope="col">작성일자</th>
		      <th scope="col">조회수</th>
		    </tr>
		  </thead>
		  <tbody>
		  <%for(Free f :list){ %>
		    <tr class="table-light">
		      <th scope="row"><%=f.getFree_No() %></th>
		      <td><%=f.getFree_Title() %></td>
		      <td><%=f.getFree_Id() %></td>
		      <td><%=f.getFree_No() %></td>
		      <td><%=f.getFree_No() %></td>
		    </tr>
		   <%} %>
		  </tbody>
		</table>
    </div>
	<script>
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>