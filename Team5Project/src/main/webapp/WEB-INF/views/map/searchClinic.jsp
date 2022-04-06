<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>위드코로나 :: 주변 진료소 찾기</title>
<style>
	.district-box{
		display:flex;
		justify-content: center;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="div-content">
		<div class="content-title">주변 진료소 찾기</div>
		<div class="district-box">
			<span>
				<select name="sido" id="sido" class="form-select">
					<option>시/도</option>
					<!-- DB에서 시정보를 불러와서 갯수만큼 옵션 추가되게 -->
				</select>
			</span>
			<span>
				<select name="gugun" id="gugun" class="form-select">
					<option>구/군</option>
					<!-- 동일 -->
				</select>
			</span>
			<button class="btn btn-lg btn-primary">검ss  ss s ss  ss  색</button>
		</div>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
	<script>
		$(function(){
			

			const xhr = new XMLHttpRequest();
			xhr.open('get', 'http://apis.data.go.kr/B551182/rprtHospService/getRprtHospService?serviceKey=aZgnd9FXHlwr%2FaUjJKh8XgW7sh9DIiuxXVRki%2Beg6LzQC3GGaLH7uVm16NtYTSmJYE6tWEZ%2BaCiHGP31GFG%2Big%3D%3D&pageNo=1&numOfRows=10');
			xhr.setRequestHeader('Ping-Other','pingpong');
			xhr.setRequestHeader('Content-Type', 'application/xml');
//			xhr.setRequestHeader('access-control-request-method','method');
			xhr.setRequestHeader('Access-Control-Allow-Origin',"*");
			xhr.onreadystatechange=function(){
				if(this.readyState==4 && this.status ==200) {
					// 요청이 다 끝나고 결과가 정상인 경우
					console.log("정상이랜다");
				} else if(this.readyState==4 && this.status == 404) {
					// 요청이 끝났는데 결과가 에러코드(404)인 경우
					console.log("에러났댄다");
				}
			}
			xhr.send('<person><name>Arun</name></person>');
				const value=xhr.requestXML;
				console.log(value)
			
		})

	</script>
</body>
</html>