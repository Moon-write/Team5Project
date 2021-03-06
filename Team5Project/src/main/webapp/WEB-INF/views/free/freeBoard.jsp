<%@page import="java.util.HashMap"%>
<%@page import="kr.co.iei.free.vo.FreeboardTable"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<FreeboardTable> list = (ArrayList<FreeboardTable>)request.getAttribute("list");
    	int reqPage = Integer.parseInt(request.getParameter("reqPage"));
    	String pageNavi = (String)request.getAttribute("pageNavi");
    	HashMap<Integer, Boolean> likecheck = (HashMap<Integer, Boolean>)request.getAttribute("likecheck");
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
		width:5%;
		min-width: 49px;
	}
	.tr1 th:nth-child(2){
		width:40%;
		min-width: 300px;
	}
	.tr1 th:nth-child(3){
		width:10%;
		min-width: 70px;
	}
	.tr1 th:nth-child(4){
		width:15%;
		min-width: 140px; 
	}
	.tr1 th:nth-child(5){
		width:10%;
		min-width: 97.5px;
	}
	.tr1 th:last-child{
		width:10%;
		min-width: 73px;
	}
 	.menu1{ 
		display:flex;
	}
	.menu1>.write{
		width:20%;
		min-width: 170px;
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
	.btn-group{
		min-width: 230px;
	}
	.icon{
 		min-width: 140px;  
	}
	.icon div{
		float:left;
		margin:0 auto;
		width:50%;
	}
	.icon div:first-child{
		padding-top:0.1em;
		padding-left:1.5em;
		min-width: 60px; 
	}
	.icon div:last-child{
		padding-right:1.5em;
		min-width: 60px; 
	}
	.keyword{
		min-width: 200px;
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
		      		<%if(m!=null&&likecheck.containsKey(fbt.getNo())){ %>
					    <span type="button" class="material-icons" onclick="unliked(this,'<%=m.getMemberNo()%>','<%=fbt.getNo()%>')">thumb_up</span>
			      	<%}else { %>
			      		<span type="button" class="material-icons" onclick="liked(this,'<%=m!=null?m.getMemberNo():m%>','<%=fbt.getNo()%>')">thumb_up_off_alt</span>      	
			      	<%}%>
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
		
		//추천기능
 		function loginCheck(){
			$("#login-btn").trigger("click");
		}
		function unliked(obj,m,f){
			$.ajax({
				type:"post",
				url:"/freeDeleteLike.do",
				data:{num1:m, num2:f},
				success:function(data){
					if(data=="성공"){
						$(obj).text("thumb_up_off_alt");
						$(obj).attr("onclick","liked(this,'"+m+"','"+f+"')");
						const count = $(obj).parent().next();
						count.text(Number.parseInt(count.text())-1);
						alert("추천취소!");
					}else{
						console.log(data);
					}
				},
				error:function(){
					console.log("전송실패");
				}
			})
		}
		function liked(obj,m,f){
			if(m!="null"){
				$.ajax({
					type:"post",
					url:"/freeInsertLike.do",
					data:{num1:m, num2:f},
					success:function(data){
						if(data=="성공"){
							$(obj).text("thumb_up");
							$(obj).attr("onclick","unliked(this,'"+m+"','"+f+"')");
							const count = $(obj).parent().next();
							count.text(Number.parseInt(count.text())+1);
							alert("추천!");
						}else{
							console.log(data);
						}
					},
					error:function(){
						console.log("전송실패");
					}
				})
			}else{
				$("#login-btn").trigger("click");
			}
		}
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>