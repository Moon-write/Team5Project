<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%	//Arraylist보내준 것 꺼내온다. Object객체이므로 다운캐스팅 필요
    	ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리페이지</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="div-content">
		<div class="content-title">회원관리</div>
		<table class="table" id="clinicList">
			<tr class="table-success">
				<th>선택</th>
				<th>회원번호</th>
				<th>아이디</th>
				<th>이름</th>
				<th>별명</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>이메일</th>
				<th>가입일</th>
				<th>회원등급</th>
				<th>등급변경</th>
				<th>회원추방</th>
			</tr>
			<%for(Member member : list){ %>
				<tr class="">
					<td><input type="checkbox" class="chk form-check-input"></td>
					<td><%=member.getMemberNo() %></td>
					<td><%=member.getMemberId() %></td> 
					<td><%=member.getMemberName() %></td>
					<td><%=member.getMemberNickname() %></td>
					<td><%=member.getPhone() %></td>
					<td><%=member.getAddress() %></td>
					<td><%=member.getEmail() %></td>
					<td><%=member.getEnrollDate() %></td>
					<td>
						<%if(member.getMemberLevel() == 0) {%>
						<select class="form-select">
							<option value="0" selected>관리자</option>
							<option value="1">정회원</option>
							<option value="2">준회원</option>
						</select>
						<%}else if(member.getMemberLevel() == 1) {%>
						<select class="form-select">
							<option value="0">관리자</option>
							<option value="1" selected>정회원</option>
							<option value="2">준회원</option>
						</select>
						<%}else if(member.getMemberLevel() == 2) {%>
						<select class="form-select">
							<option value="0">관리자</option>
							<option value="1">정회원</option>
							<option value="2" selected>준회원</option>
						</select>
						<%} %>
					</td>
					<td>
						<button class="btn btn-primary changeLevel">등급변경</button>
					</td>
					<td>
						<button class="btn btn-danger changeLevel">추방!!</button>
					</td>
				</tr>
			<%} %>
			<tr>
				<th colspan="12">
					<button class="btn btn-primary btn-lg checkedChangeLevel" style="width:100%">선택회원 등급변경</button>
				</th>
			</tr>
		</table>
	</div>
	<script>
		$(".changeLevel").on("click",function(){
				const memberLevel = $(this).parent().prev().children().val();
				const memberNo = $(this).parent().parent().children().eq(1).text();
				console.log(memberNo, memberLevel);
				//컨트롤러로 값 주면서 페이지 이동
				location.href="/changeLevel.do?memberNo="+memberNo+"&memberLevel="+memberLevel;
		});
		$(".checkedChangeLevel").on("click",function(){
			const check = $(".chk:checked"); //chk중 선택된옵션
			if(check.length == 0){
				alert("선택된 회원이 없습니다.");
				return;//함수종료
			}
			const num = new Array();	//회원번호 저장용 배열
			const level = new Array();	//회원등급 저장용 배열
			check.each(function(index,item){
				num.push($(item).parent().next().text());
				//td의 부모인 tr의 후손들중 select태그를 가져와서 val값을 push
				level.push($(item).parent().parent().find("select").val());
			});
			//console.log(num,level);
			//자바스크립트에서의 join은 여러개의 배열을 하나로 합쳐 구분자 / 를 주어 하나의 문자열로 리턴해주는 것
			//아래의 코드는 선택한 여러개를 하나의 배열로 구분자 / 로 넘겨주는 것
			location.href="/checkedChangeLevel.do?num="+num.join("/")+"&level="+level.join("/");
		});
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>