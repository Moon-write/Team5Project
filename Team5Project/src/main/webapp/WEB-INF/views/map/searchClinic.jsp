<%@page import="kr.co.iei.mapApi.vo.Clinic"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<Clinic> result = (ArrayList<Clinic>)request.getAttribute("result");
    	String sido = (String)request.getAttribute("sido");
    	String gugun = (String)request.getAttribute("gugun");
    	String detail = (String)request.getAttribute("detailAddr");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>위드코로나 :: 주변 진료소 찾기</title>
<style>
	.district-box{
		display:flex;
		justify-content: center;
		margin-bottom: 30px;
	}
	#clinicList th, #clinicList td{
		text-align: center;
		height: 40px;
	}

	#clinicList th:first-child{
		width: 10%;
	}

	#clinicList th:nth-child(2){
		width: 15%;
	}
	#clinicList th:nth-child(3){
		width: 40%;
	}
	#clinicList th:nth-child(4){
		width: 15%;
	}
	#clinicList th:nth-child(5){
		width: 10%;
	}
	#clinicList th:last-child{
		width: 10%;
	}
	.selectDistrict input,.selectDistrict select, .selectDistrict button{
		width: 100%;
		text-align: center;
	}
	

	@media screen and (max-width: 768px) {
		#clinicList {
			font-size: 0.7em;
		}
		.selectDistrict{
		width: 100%;
		}	

		.district-box{
			flex-wrap: wrap;
		}
		.pcRat{
			text-align: center;
			margin-bottom: 10px;
		}
		.pcRat>span>span{
			font-size: 0.9em;
		}
		
	}
	@media screen and (min-width: 768px) and (max-width: 1000px) {
		#clinicList {
			font-size: 0.8em;
		}
		.selectDistrict{
		width: 20%;
		margin-left: 5px;
		margin-right: 5px;
		}	
		.pcRat{
			text-align: right;
			font-size: 1em;
			margin-bottom: 5px;	
		}
		.pcRat>span>span{
			font-size: 0.9em;
		}
	}

	@media screen and (min-width: 1000px) {
		#clinicList {
			font-size: 0.9em;
		}
		.selectDistrict{
		width: 15%;
		margin-left: 5px;
		margin-right: 5px;
		}
		.pcRat{
			text-align: right;
			font-size: 0.9em;
		}
		.pcRat>span>span{
			font-size: 0.8em;
		}
	}

</style>
</head>
<body>
	<input type="text" id="sidoInfo" value='<%= sido %>'>
	<input type="hidden" id="gugunInfo" value="<%= gugun %>">
	<input type="hidden" id="detailInfo" value="<%= detail %>">
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="div-content">
		<div class="content-title">주변 진료소 찾기</div>
		<div class="district-box">
			<span class="selectDistrict">
				<select name="sidoCdNm" id="sidoCdNm" class="form-select">
					<option>시/도</option>
					<option value="서울">서울특별시</option>
					<option value="경기">경기도</option>
					<option value="인천">인천광역시</option>
					<option value="강원">강원도</option>
					<option value="충남">충청남도</option>
					<option value="대전">대전광역시</option>
					<option value="충북">충청북도</option>
					<option value="세종">세종특별자치시</option>
					<option value="부산">부산광역시</option>
					<option value="울산">울산광역시</option>
					<option value="대구">대구광역시</option>
					<option value="경북">경상북도</option>
					<option value="경남">경상남도</option>
					<option value="전남">전라남도</option>
					<option value="광주">광주광역시</option>
					<option value="전북">전라북도</option>
					<option value="제주">제주도</option>
				</select>
			</span>
			<span class="selectDistrict">
				<select name="sgguCdNm" id="sgguCdNm" class="form-select">
					<option>구/군</option>
					<!-- 선택정렬 -->
				</select>
			</span>
			<span class="selectDistrict">
				<input type="text" id="detailAddr" class="form-control" placeholder="상세주소 (동/로)">
			</span>
			<span class="selectDistrict">
				<button id="searchList" class="btn btn-lg btn-primary selectDistrict">검색</button>
			</span>
		</div>
		<div class="result-list">
			<div class="pcRat">
				<span>
					<span class="material-icons">face</span>
					신속항원검사
				</span>
				<span>
					<span class="material-icons">face</span>
					PCR검사
				</span>
			</div>
			<table class="table" id="clinicList">
				<tr class="table-success">
					<th>구분</th>
					<th>이름</th>
					<th>주소</th>
					<th>전화번호</th>
					<th>검사종류</th>
					<th>위치</th>
				</tr>
				<% if(result==null) { %>
					<tr class="table-default">
						<td colspan="6">
							조회하시는 내용이 없습니땨!
						</td>
					</tr>
				<% } else { %>
					<% for(int i=0;i<result.size();i++) { %>
						<tr class="table-default">
							<td>
								<% if(result.get(i).getClinicCode().equals("31")){ %>
									의원
								<% } else if(result.get(i).getClinicCode().equals("21")){ %>
									병원
								<% } else if(result.get(i).getClinicCode().equals("11")){ %>
									종합병원
								<% } %>
							</td>
							<td><%= result.get(i).getClinicName() %></td>
							<td><%= result.get(i).getClinicAddr() %></td>
							<td><%= result.get(i).getTelNo() %></td>
							<td>
								<% if(result.get(i).getRatAble().equals("Y")){ %>
									<span class="material-icons">face</span>
								<% } %>
								<% if(result.get(i).getPcrAble().equals("Y")){ %>
									<span class="material-icons">face</span>
								<% } %>
							</td>
							<td><span class="material-icons">face</span></td>
						</tr>
					<% } %>
				<%} %>
			</table>
			<div id="clinic-map-box">
				여기에 지도가 들어갈것임
			</div>
			<div id="page-wrap">
				<ul class="pagination" style="justify-content: center;">
					<li class="page-item">
						<a class="page-link" href="#">«</a>
					</li>
					<li class="page-item active">
						<a class="page-link" href="#">1</a>
					</li>
					<li class="page-item">
						<a class="page-link" href="#">2</a>
					</li>
					<li class="page-item">
						<a class="page-link" href="#">3</a>
					</li>
					<li class="page-item">
						<a class="page-link" href="#">4</a>
					</li>
					<li class="page-item">
						<a class="page-link" href="#">5</a>
					</li>
					<li class="page-item">
						<a class="page-link" href="#">»</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
	<script>
	$("#sidoCdNm").on("change",function(){
	    console.log($(this).val());
	    $.ajax({
	        type: 'post',
	        url : '/selectDistrict.do',
	        data : {
	            "district":$(this).val()
	        },
	        dataType : "json",
	        success : function(data){
	            // 군구정보 리턴
	                $("#sgguCdNm").empty();
	                $("#sgguCdNm").append('<option>구/군</option>');
	            $.each(data, function(index, item){
	                $("#sgguCdNm").append('<option value="'+item+'">'+item+'</option>');
	            })
	        },
	        error : function(){
	            console.log("실패했댄다");
	        }
	    })
	});
	$("#searchList").on("click",function(){
	    const sido = $("#sidoCdNm").val();
	    const gugun = $("#sgguCdNm").val();
	    const detailAddr = $("#detailAddr").val();
	    
	    console.log(sido);
	    console.log(gugun);
	    console.log(detailAddr);
	    
	    location.href= "/clinicList2.do?sidoNm="+sido+"&sgguNm="+gugun+"&detailAddr="+detailAddr+"&pageNo=1";
	});
	$(function(){
		const sidoinfo = $("#sidoInfo").val()

		if(sidoinfo==null){
			console.log("정보없땨");
		}else if(sidoinfo==""){
			console.log("공백");
		}else{
			console.log(sidoinfo);
		}
	})
	</script>
</body>
</html>