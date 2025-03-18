<%@ page import ="db.DBFunc" %>
<%@ page import ="db.SqlList" %>
<%@ page import ="api.WifiAPI" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="style.css" rel="styleSheet" type="text/css">
	<title>와이파이 정보 구하기</title>
</head>
<body>
<%
//Class.forName("org.sqlite.JDBC");
	
	// 1. 테이블 없으면 생성
	DBFunc.createTable();
	
	// 2. Wifi 정보 가져오기 위해 객체 생성
	WifiAPI wifiInfo = new WifiAPI();
	
	// 3. Wifi 정보 api 활용하여 저장
	int intTotalDataCnt = wifiInfo.getWifiInfo();
%>
	<h1 style="text-align:center">
		<%=String.format("%d개의 WIFI 정보를 정상적으로 저장하였습니다.", intTotalDataCnt)%>
	</h1>
	<div class="atag">
		<a href="home.jsp" >홈 으로 가기</a>
	</div>
</body>
</html>
