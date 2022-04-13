<%@page import="kr.co.iei.mapApi.vo.Clinic"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<Clinic> result = (ArrayList<Clinic>)request.getAttribute("result");
    	String sido = (String)request.getAttribute("sido");
    	String gugun = (String)request.getAttribute("gugun");
    	String detail = (String)request.getAttribute("detailAddr");
    	String pageNum = (String)request.getAttribute("pageNum");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>위드코로나 :: 주변 진료소 찾기</title>
<style>
	span{
		cursor: pointer;
	}
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
			font-size: 1.2em;
		}
		.pcRat>span>span{
			font-size: 1.1em;
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
			font-size: 1.1em;
			margin-bottom: 10px;	
		}
		.pcRat>span>span{
			font-size: 1em;
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
			font-size: 1em;
		}
		.pcRat>span>span{
			font-size: 0.9em;
		}
	}
	#clinic-map-box{
		width: 100%;
		height: 300px;
		margin-bottom: 30px;
	}
	.pagination{
		margin-bottom: 50px;
	}
	.noneSelect{
    cursor:default !important;
	}
	.markerDiv{
		padding: 10px;
	}
	.markerDiv>span{
		display: block;
		margin-top: 5px;
	}
	.markerDiv>span:first-child{
		
		font-size: 12px;
		background-color: #a393ac;
		font-weight: 900;
		color: #fff;
	}
	.markerDiv>span:nth-child(2){
		font-size: 11px;
		color: #828e95;
	}
	.markerDiv>span:last-child{
		font-size: 11px;
		color: #a393ac;
	}
</style>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=zuy6mbvpe0&submodules=geocoder"></script>
</head>
<body>
	<input type="hidden" id="sidoInfo" value='<%= sido %>'>
	<input type="hidden" id="gugunInfo" value="<%= gugun %>">
	<input type="hidden" id="detailInfo" value="<%= detail %>">
	<input type="hidden" id="pageInfo" value="<%= pageNum %>">
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
				<select name="sgguCdNm" id="sgguCdNm" class="form-select"  style="padding-left: 0.75em;">
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
				<span class="noneSelect">
					<span class="material-icons noneSelect" style="color:#29abe0;">verified</span>
					신속항원검사
				</span>
				<span class="noneSelect">
					<span class="material-icons noneSelect" style="color:#d9534f;">local_parking</span>
					PCR검사
				</span>
			</div>
			<table class="table" id="clinicList">
				<tr class="table-success">
					<th class="noneSelect">구분</th>
					<th class="noneSelect">이름</th>
					<th class="noneSelect">주소</th>
					<th class="noneSelect">전화번호</th>
					<th class="noneSelect">검사종류</th>
					<th class="noneSelect">위치</th>
				</tr>
				<% if(result==null) { %>
					<tr class="table-default">
						<td colspan="6" class="noneSelect">
							조회하시는 내용이 없습니다!
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
							<th><%= result.get(i).getClinicName() %></th>
							<td><%= result.get(i).getClinicAddr() %></td>
							<td><%= result.get(i).getTelNo() %></td>
							<td>
								<% if(result.get(i).getRatAble().equals("Y")){ %>
									<span class="material-icons noneSelect" style="color:#29abe0;">verified</span>
								<% } %>
								<% if(result.get(i).getPcrAble().equals("Y")){ %>
									<span class="material-icons noneSelect" style="color:#d9534f;">local_parking</span>
								<% } %>
							</td>
							<td>
								<span class="material-icons clinic-map-marker" style="color:#d9534f;">place</span>
								<input type="hidden" value="<%=result.get(i).getxPos()%>">
								<input type="hidden" value="<%=result.get(i).getyPos()%>">
							</td>
						</tr>
					<% } %>
				<%} %>
			</table>
			<div id="clinic-map-box">
			</div>
			<div id="page-wrap">
				<%if(result!=null){ %>
				<ul class="pagination pagination-lg" style="justify-content: center;">
					<% if(!pageNum.equals("1")) {%>
					<li class="page-item" id="prevpage">
						<a class="page-link" href="javascript:void(0)" style="font-size:2em;">«</a>
					</li>
					<% } %>
					<% if(result.size()==5){ %>
					<li class="page-item" id="nextpage">
						<a class="page-link" href="javascript:void(0)" style="font-size:2em;">»</a>
					</li>
					<%} %>
				</ul>
				<%} %>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
	<script>
	$("#sidoCdNm").on("change",function(){
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
	    
	    location.href= "/clinicList2.do?sidoNm="+sido+"&sgguNm="+gugun+"&detailAddr="+detailAddr+"&pageNo=1";
	});
	
	// 온로드함수
	$(function(){
		const sidoInfo = $("#sidoInfo").val();
		const gugunInfo = $("#gugunInfo").val();
		const detailInfo = $("#detailInfo").val();
		const pageNum = $("#pageInfo").val();
		
		if(sidoInfo!="null"){
			
			$('#sidoCdNm option[value="'+sidoInfo+'"]').attr("selected", true).trigger("change");
			setTimeout(function(){
				if(gugunInfo!="null"){
						$('#sgguCdNm option[value="'+gugunInfo+'"]').attr("selected", true);
				}
			},100);
		}

		if(detailInfo!="null"){
			$('#detailAddr').val(detailInfo);
		}
		
		$("#prevpage").on("click", function(){
			const prevPage = parseInt(pageNum)-1;
			location.href= "/clinicList2.do?sidoNm="+sidoInfo+"&sgguNm="+gugunInfo+"&detailAddr="+detailInfo+"&pageNo="+prevPage;
		});
		$("#nextpage").on("click", function(){
			const nextPage = parseInt(pageNum)+1;
			location.href= "/clinicList2.do?sidoNm="+sidoInfo+"&sgguNm="+gugunInfo+"&detailAddr="+detailInfo+"&pageNo="+nextPage;
		});

		if($(".clinic-map-marker").length==0){
			$("#clinic-map-box").hide();
		} else {
			$("#clinic-map-box").slideDown();
		}

		const map = new naver.maps.Map("clinic-map-box",{
			zoom: 13,
			center : new naver.maps.LatLng($(".clinic-map-marker").eq(0).next().next().val(), $(".clinic-map-marker").eq(0).next().val())
		});
		

		$(".clinic-map-marker").each(function(index, item){
			const xPos = $(item).next().val();
			const yPos = $(item).next().next().val();

			const marker = marking(yPos, xPos, map);

			naver.maps.Event.addListener(marker,"click",function(e){ // 마커에 클릭이벤트를 걸거고 그때 함수가 동작한다
				const clinicName = $(item).parent().prev().prev().prev().prev().text();
				const clinicAddr = $(item).parent().prev().prev().prev().text();
				const clinicTel = $(item).parent().prev().prev().text();
			
				makingInfoWindow(clinicName, clinicAddr, clinicTel, map, marker);
			})
		})


		$(".clinic-map-marker").on("click",function(){
			const PickxPos = $(this).next().val();
			const PickyPos = $(this).next().next().val();

			const map2 = new naver.maps.Map("clinic-map-box", {
				center : new naver.maps.LatLng(PickyPos, PickxPos),
				zoom : 15,
				zoomControl : true,
			})

			const marker = marking(PickyPos, PickxPos, map2);

			const clinicName = $(this).parent().prev().prev().prev().prev().text();
			const clinicAddr = $(this).parent().prev().prev().prev().text();
			const clinicTel = $(this).parent().prev().prev().text();
			
			makingInfoWindow(clinicName, clinicAddr, clinicTel, map2, marker);
			
		})
		
		function marking(lat, lng, mapName){
			const marker = new naver.maps.Marker({
				position : new naver.maps.LatLng(lat, lng),
				map : mapName,
			})

			return marker;
		}

		function makingInfoWindow(clinicName, clinicAddr, clinicTel, mapName, marker){
			// 인포윈도 만들기
			// 1. 객체 생성
			let infoWindow = new naver.maps.InfoWindow();
						
			if(infoWindow.getMap()){ // 마커 옮길때 정보창이 지도에 존재하면
				infoWindow.close(); // 종료
			}
			infoWindow = new naver.maps.InfoWindow({
				content : "<div class='markerDiv'><span>"+clinicName+"</span><span>"+clinicAddr+"</span><span>"+clinicTel+"</span></div>",

				// 정보창 디자인을 할거면 여기서해라
				maxWidth : 250,
				borderColor : "#d4c3de",
				borderWidth: 2,
				anchorSize : new naver.maps.Size(10,10),
			});
			// 생성된 infoWindow를 map의 marker위치에 생성
			infoWindow.open(mapName, marker); // 매개변수 map과 marker는 다 위에서 만든거	
		}
	});
	
	
	</script>
</body>
</html>