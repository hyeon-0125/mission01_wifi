<%@ page import = "db.DBFunc" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">	
	<title>와이파이 정보 구하기</title>
	<link href="style.css" rel="styleSheet" type="text/css">
	<script type="text/javascript">
		function success({ coords, timestamp }) {
			const inpLocX = document.getElementById('loc_x');
			const inpLocY = document.getElementById('loc_y');
			
			inpLocX.value = coords.latitude;
			inpLocY.value = coords.longitude;
		}
		
		function getUserLocation() {
            if (!navigator.geolocation) {
                throw "위치 정보가 지원되지 않습니다.";
            }
            navigator.geolocation.watchPosition(success);
        }
		
		function searchNearWifi() {
			
		}
    </script>
    <%
		// 1. 테이블 없으면 생성
		DBFunc.createTable();
    %>
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	
	<div>
		<a href="home.jsp">홈</a>
		<span> | </span>
		<a href="history.jsp">위치 히스토리 목록</a>
		<span> | </span>
		<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
		<span> | </span>
		<a href="bookmark-list.jsp">Open API 와이파이 정보 가져오기</a>
		<span> | </span>
		<a href="bookmark-group.jsp">Open API 와이파이 정보 가져오기</a>
	</div>
	<br/>
	<div>
    	<label>LAT: <input type="text" id="loc_x" size="15" value="0.0"></label>
    	<label>, LNT: <input type="text" id="loc_y" size="15" value="0.0"></label>
    	<input type="button" value="내 위치 가져오기" class="btnGetLocation" onclick="getUserLocation();">
    	<input type="button" value="근처 WIFI 정보 보기" class="btnSearchNearWifi" onclick="searchNearWifi();"></br>
	</div>
</body>
</html>