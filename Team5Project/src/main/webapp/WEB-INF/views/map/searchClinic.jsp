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
				<select name="sidoCdNm" id="sidoCdNm" class="form-select">
					<option>시/도</option>
					<!-- DB에서 시정보를 불러와서 갯수만큼 옵션 추가되게 -->
				</select>
			</span>
			<span>
				<select name="sgguCdNm" id="sgguCdNm" class="form-select">
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
			
			$.ajax({
				type :'GET',
				url : '/clinicList2.do',
				data : { "sidoNm" : "서울", "sgguNm" : "강북", "detailAddr" : "" , "pageNo" : "1" },
				success : function(data){
					// xml형식으로 파일리턴 완료.
					console.log(data[3].clinicAddr);
				},
				error : function(){
					console.log("실패했댄다");
				}
			})
		
/*			$.ajax({
				type :'GET',
				url : '/clinicList.do',
				data : { "pageNo" : "1", "numOfRows" : "10"},
				dataType : 'xml',
				success : function(data){
					// xml형식으로 파일리턴 완료.
					console.log(data);
				},
				error : function(){
					console.log("실패했댄다");
				}
			})*/
			
			
		})

	</script>
</body>
</html>