<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="pl" />
<link rel="stylesheet" href="/resources/css/mainCSS.css">
<title>Meet-mainPage</title>
</head>
</head>


<body>

	<header> </header>



	<ul class="nav">
		<li class="navBox"><button type="button">Home</button></li>
		<li class="navBox"><input type="text" placeholder="Szukaj"
			style="padding-right: 0px"></li>
		<li class=" navBox"><button type="button"
				style="padding-left: 0px">Zaloguj</button></li>
	</ul>

	<div class="container">
		<div class="leftSite">left site</div>

		<div class="mainPage">main page
		"${userName }"
		
		</div>

	</div>


</body>

</html>


