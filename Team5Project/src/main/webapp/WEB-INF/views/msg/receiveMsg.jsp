<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>받은쪽지함</title>
    <!--기본 CSS-->
    <link rel="stylesheet" href="/css/index.css">
    <!--구글폰트-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="js/jquery-3.6.0.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    <!-- 부트스트랩 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<style>
	body{
		width:100%; height: 600px;
		padding: 0px; margin: 0px;
	}
	table th, table td{
		text-align: center;
	}
	table td{
		font-size: 0.8em;
	}
	#checkboxCol {
		width: 10%;
	}
	#nameCol{
		width: 20%;
	}
	#contentCol{
		width: 45%;
	}
	#dateCol{
		width: 25%;
	}
</style>
<body>
	<h1>받은쪽지함</h1>
	<table class="table">
		<tr class="table-light">
			<th id="checkboxCol"><span class="material-icons">face</span></th>
			<th id="nameCol">보낸이</th>
			<th id="contentCol">내용</th>
			<th id="dateCol">날짜</th>
		</tr>
		<tr class="table-default">
			<td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
			<td>가나다라마(user01)</td>
			<td>Lorem ipsum dolor sit amet, !</td>
			<td>2020-02-22 11:10</td>
		</tr>
		<tr class="table-default">
			<td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
			<td>가나다라마(user01)</td>
			<td>consectetur adipisicing elit. Natus vel nisi, ipsam reiciendis est exercitationem</td>
			<td>2020-02-22 11:10</td>
		</tr>
		<tr class="table-default">
			<td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
			<td>가나다라마(user01)</td>
			<td>consectetur adipisicing elit. Natus vel nisi, ipsam reiciendis est exercitationem</td>
			<td>2020-02-22 11:10</td>
		</tr>
		<tr class="table-default">
			<td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
			<td>가나다라마(user01)</td>
			<td>consectetur adipisicing elit. Natus vel nisi, ipsam reiciendis est exercitationem</td>
			<td>2020-02-22 11:10</td>
		</tr>
		<tr class="table-default">
			<td><input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"></td>
			<td>가나다라마(user01)</td>
			<td>consectetur adipisicing elit. Natus vel nisi, ipsam reiciendis est exercitationem</td>
			<td>2020-02-22 11:10</td>
		</tr>
	</table>
	<div class="pagination">
		<ul class="page-item">
			
		</ul>
	</div>
</body>
</html>