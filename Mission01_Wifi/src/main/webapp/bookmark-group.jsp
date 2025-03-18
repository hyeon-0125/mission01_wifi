<%@ page import = "db.DBConn" %>
<%@ page import = "db.DBFunc" %>
<%@ page import = "db.SqlList" %>
<%@ page import = "db.DataBookmarkGroup" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">	
	<title>와이파이 정보 구하기</title>
	<link href="style.css" rel="styleSheet" type="text/css">
</head>
<script>
	function submitAction(index) {
		if (index == 1) {
			document.groupForm.action = 'bookmark-group-edit.jsp';
		} else if (index == 2) {
			document.groupForm.action = 'bookmark-group-delete-submit.jsp';
		}
		
		document.groupForm.submit();
		
	}
</script>
<body>
	<h1>와이파이 정보 구하기</h1>
	
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
		<br/>
		<button type="button" onClick="location.href='bookmark-group-add.jsp'">북마크 그룹 이름 추가</button>
	</div>
	<br/>
	<div>
		<%
			List<DataBookmarkGroup> lstData = DBFunc.searchBookmarkGroup();
		%>
		<form method="post" name="groupForm">
			<table class="table_no_title_ui">
				<tr>
					<th>ID</th>
					<th>북마크 이름</th>
					<th>순서</th>
					<th>등록일자</th>
					<th>수정일자</th>
					<th>비고</th>
				</tr>
				<%
					for(DataBookmarkGroup data : lstData) {
				%>
					<tr>
						<td id="group_id" name="group_id"><%=data.GROUP_ID%></td>
						<td id="group_name" name="group_name"><%=data.GROUP_NAME%></td>
						<td id="group_seq" name="group_seq"><%=data.SEQ%></td>
						<td id="create_date" name="create_date"><%=data.CREATE_DATE%></td>
						<td id="update_date" name="update_date"><%=data.UPDATE_DATE%></td>
						<td align="center">
							<input type="submit" value ="GroupEdit" id="hiddenGroupEdit" onClick="submitAction(1)" >
							<input type="submit" value = "GroupDelete" id="hiddenGroupDelete" onClick="submitAction(2)'">
							<a href="javascript:document.querySelector('#hiddenGroupEdit').click();">수정</a>
							<span> | </span>
							<a href="javascript:document.querySelector('#hiddenGroupDelete').click();">삭제</a>
						</td>
					</tr>
				<%
					}
				%>
			</table>
		</form>
	</div>
</body>
</html>