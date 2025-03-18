<%@ page import = "db.DBFunc" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">	
	<title>와이파이 정보 구하기</title>
	<link href="style.css" rel="styleSheet" type="text/css">
</head>
<body>
	<h1>북마크 그룹</h1>
	
	<div>
		<a href="home.jsp">홈</a>
		<span> | </span>
		<a href="history.jsp">위치 히스토리 목록</a>
		<span> | </span>
		<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
		<span> | </span>
		<a href="bookmark-list.jsp">북마크 보기</a>
		<span> | </span>
		<a href="bookmark-group.jsp">북마크 그룹 관리</a>
	</div>
	<br/>
	<div>
		<form method="post" action="bookmark-group-add-submit.jsp">
			<table class="table_no_title_ui">
				<tr>
					<th>북마크 이름</th>
					<td>
						<input type="text" id="group_name" name="group_name" size="15" value="">				
					</td>
				</tr>
				<tr>
					<th>순서</th>
					<td>
						<input type="text" id="group_seq" name="group_seq" size="15" value="">				
					</td>
				</tr>
				<tr>
					<td colspan = "2" align="center">
						<button type="submit">추가</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>