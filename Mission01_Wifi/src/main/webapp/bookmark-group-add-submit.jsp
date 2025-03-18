<%@ page import="db.DBFunc" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true" %>
<%
	request.setCharacterEncoding("utf-8");

	String group_name = request.getParameter("group_name");
	String seq = request.getParameter("group_seq");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>와이파이 정보 구하기</title>
</head>
<body>
	<%
		DBFunc.insertBookmarkGroup(group_name, seq);
	%>
</body>
</html>