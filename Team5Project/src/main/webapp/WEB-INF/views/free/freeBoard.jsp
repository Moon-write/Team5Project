<%@page import="kr.co.iei.free.vo.FreeboardTable"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<FreeboardTable> list = (ArrayList<FreeboardTable>)request.getAttribute("list");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	.tr1 td>a:hover{
		text-decoration:underline;
	}
	.tr1 td>a{
		color: inherit;	
	}
	.tr1 th, td{
		text-align: center;
	}
	.tr1 th:first-child{
		width:10%;
	}
	.tr1 th:nth-child(2){
		width:35%;
	}
	.tr1 th:nth-child(3){
		width:10%;
	}
	.tr1 th:nth-child(4){
		width:15%;
	}
	.tr1 th:nth-child(5){
		width:10%;
	}
	.tr1 th:last-child{
		width:10%;
	}
	.menu1{
		display:flex;
	}
	.menu1>{
	
	}
	.menu1>.write{
		width:20%;
	}
	.menu1>.write>.btn{
 		width: 80%;
		height:80%;
	}
	.menu1>form{
		width:80%;
		height:100%;
		margin-left:5%;
	}
	.check-button>p{
		height:100%;
	}
</style>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="div-content">
        <div class="content-title">자유게시판</div>
        
        <div class="menu1">
        	<div class="write">
	        	<a type="button" class="btn btn-outline-primary" href="/freeInsert.do">글쓰기</a>
        	</div>
			
			<form action="/search.do" method="get" name="search">
				<fieldset>
					<div class="input-group mb-3">
						<div class="btn-group" role="group" aria-label="Basic radio toggle button group">
						  <input type="radio" class="btn-check" name="Sort" id="btnradio1" value="1">
						  <label class="btn btn-outline-primary" for="btnradio1">인기순</label>
						  <input type="radio" class="btn-check" name="Sort" id="btnradio2" value="2">
						  <label class="btn btn-outline-primary" for="btnradio2">조회순</label>
						  <input type="radio" class="btn-check" name="Sort" id="btnradio3" value="3">
						  <label class="btn btn-outline-primary" for="btnradio3">최신순</label>
						</div>
						<select class="form-select" id="exampleSelect1" name="numPage">
					        <option>10</option>
					        <option>20</option>
					        <option>30</option>
					        <option>40</option>
					        <option>50</option>
					    </select>
				      	<input type="text" class="form-control" name="keyword" placeholder="게시글 제목이나 작성자 이름 입력">
				      	<button class="btn btn-primary" type="submit">
				      		<span class="material-icons">search</span>
				      	</button>
			    	</div>
			    </fieldset> 
			</form>
        </div>
		
        <table class="table table-hover">
		  <thead>
		    <tr class="table-success tr1">
		      <th scope="col">No</th>
		      <th scope="col">제목</th>
		      <th scope="col">작성자</th>
		      <th scope="col">작성일자</th>
		      <th scope="col">조회수</th>
		      <th scope="col">좋아요수</th>
		    </tr>
		  </thead>
		  <tbody>
		  <%for(FreeboardTable fbt : list){ %>
		    <tr class="btn-lg btn-light tr1">
		      <th scope="row"><%=fbt.getNo() %></th>
		      <td><a href="/noticeView.do?noticeNo=<%=fbt.getNo() %>"><%=fbt.getTitle() %></a></td>
		      <td><%=fbt.getWriter() %></td>
		      <td><%=fbt.getDate() %></td>
		      <td><%=fbt.getViewCount() %></td>
		      <td><%=fbt.getLikeCount() %></td>
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