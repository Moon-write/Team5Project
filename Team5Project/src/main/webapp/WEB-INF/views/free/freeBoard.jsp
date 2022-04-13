<%@page import="java.util.HashMap"%>
<%@page import="kr.co.iei.free.vo.FreeboardTable"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<FreeboardTable> list = (ArrayList<FreeboardTable>)request.getAttribute("list");
    	int reqPage = Integer.parseInt(request.getParameter("reqPage"));
    	String pageNavi = (String)request.getAttribute("pageNavi");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
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
	.menu1>.write{
		width:20%;
	}
	.menu1>.write>.btn-outline-primary{
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
	.paging>.pagination-lg{ 
		justify-content: center;
	}
	.write .btn-outline-primary{
		font-size:2em;
		line-height:1em;
	}
	.btn-group .btn-outline-primary{
		font-size:1.3em;
		line-height:1em;
	}
	.btn-group label{
		justify-content: center;
		padding-top:11px;
	}
	.icon div{
		float:left;
		width:1em;
		margin:0 auto;
		padding-left:20px;
	}
	.icon div:first-child{
		margin-left: 2em;
		margin-right:0.5em;
	}
	.icon div:first-child>span{
		padding-top:2px;
		
	}
</style>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="div-content">
        <div class="content-title">자유게시판</div>
        
        <div class="menu1">
        	<div class="write">
        		<%if(m == null) {%>
        			<button type="button" class="btn btn-outline-primary" onclick="loginCheck()" >글작성</button>
       			<%}else {%>
	        		<a type="button" class="btn btn-outline-primary" href="/freeWrite.do">글작성</a>
	        	<%} %>
        	</div>
			
			<form action="/free.do" method="get" name="search">
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
						<input type="hidden" name="reqPage" id="reqPage" value="<%=reqPage%>">
						<select class="form-select" id="exampleSelect1" name="numPage">
					        <option>10</option>
					        <option>15</option>
					        <option>20</option>
					        <option>25</option>
					        <option>30</option>
					    </select>
				      	<input type="text" class="form-control keyword" name="keyword" placeholder="키워드 입력">
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
		      <td><a href="/freeView.do?FreeNo=<%=fbt.getNo() %>"><%=fbt.getTitle() %></a></td>
		      <td><%=fbt.getWriter() %></td>
		      <td><%=fbt.getDate() %></td>
		      <td class="icon">
		      	<div><span class="material-icons">visibility</span></div>
		      	<div><%=fbt.getViewCount() %></div>
		      </td>
		      <td class="icon">
		      	<div>
			      	<span class="material-icons" style="display:none;" onclick="unliked()">thumb_up</span>
			      	<span class="material-icons" onclick="liked()">thumb_up_off_alt</span>      	
		      	</div>
		      	<div><%=fbt.getLikeCount() %></div>
		      </td>
		    </tr>
		   <%} %>
		  </tbody>
		</table>
		
		<div class="paging">
			<ul class="pagination pagination-lg">
				<%=pageNavi %>
		  	</ul>
		</div>
    </div>
	<script>
		//페이징 구현
		const pagebtn = $(".pagebtn");
		pagebtn.on("click",function(){
			//console.log($(this).text());
			$("#reqPage").val($(this).text());
			$(".btn-primary").trigger("click");
		});
		const prev = $(".prev");
		prev.on("click",function(){ 
			$("#reqPage").val(parseInt($("#reqPage").val())-1);
			$(".btn-primary").trigger("click");
		});
		const next = $(".next");
		next.on("click",function(){ 
			$("#reqPage").val( parseInt($("#reqPage").val())+1);
			$(".btn-primary").trigger("click");
		});
		//url을 읽어와서 urlParams로 값추출 후 각 조회 설정에 셋팅
		const url = new URL(document.location.href);
		const urlParams = url.searchParams;
		const sort = $(".btn-check");
		const numPage = $(".form-select>option");
		const keyword = $(".keyword");
		sort.each(function(index,item){
			if(urlParams.get("Sort")==index+1){
				$(this).trigger("click");
			}
		});
		numPage.each(function(index,item){
			if(urlParams.get("numPage")==$(this).text()){
				$(this).attr("selected",true);
			}
		});
		keyword.attr("value",urlParams.get("keyword"));
		
 		function loginCheck(){
			$("#login-btn").trigger("click");
		}
		function unliked(){
			
		}
		function liked(){
			if(m!=null){
				
			}else{
				$("#login-btn").trigger("click");
			}
		}
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>