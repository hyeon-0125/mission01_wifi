<%@ page import = "db.DBFunc" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String group_id = request.getParameterValues("group_id")[0];
	String group_name = request.getParameterValues("group_name")[0];
	String group_seq = request.getParameterValues("group_seq")[0];
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">	
	<title>와이파이 정보 구하기</title>
	<link href="style.css" rel="styleSheet" type="text/css">
</head>
<body>
	<h1>북마크 수정 </h1>
	
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
		<form method="post" action="bookmark-group-edit-submit.jsp">
			<table class="table_no_title_ui">
				<tr>
					<th>북마크 이름</th>
					<td>
						<input type="text" id="group_name" name="group_name" size="15" value=<%=group_name%>>				
					</td>
				</tr>
				<tr>
					<th>순서</th>
					<td>
						<input type="text" id="group_seq" name="group_seq" size="15" value=<%=group_seq%>>				
					</td>
				</tr>
				<tr>
					<td colspan = "2" align="center">
						<input type="text" id="group_id" name="group_id" style="display:none;"><%=group_id%>
						<a herf="javascript:window.history.back();">돌아가기</a>
						<button type="submit">수정</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>