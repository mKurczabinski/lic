<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="pl" />
<link rel="stylesheet" href="/resources/css/mainCSS.css">
</head>

<body>
	<ul class="nav">
		<li class="navBox"><button id="submit"
				onclick="location.href ='/mainPage'">Home</button></li>
		<li class="navBox"><button id="submit"
				onclick="location.href ='/user/'">Społeczność</button></li>

		<li class="navBox"><input id="paramInput" type="text"
			placeholder="wpisz szukane miasto..." style="padding-right: 0px"></li>
		<li class="navBox" id="searchButton" ><button id="submit"
				onclick="search();" >szukaj</button></li>

		<li class="navBox" style="float: right"><button id="submit"
				onclick="location.href ='/logout'" >wyloguj </button></li>

	</ul>
</body>
<script>
	function search() {
		var searchParam = document.getElementById("paramInput").value;

		window.location = '/search/navSearch/' + searchParam;
	}
</script>
</html>
