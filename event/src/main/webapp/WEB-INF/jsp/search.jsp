<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="pl" />
<link rel="stylesheet" href="/resources/css/mainCSS.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js">
	
</script>
<title>Meet-mainPage</title>
</head>
<body>
	<%@include file="/WEB-INF/jsp/navbar.jsp"%>

	<div class="container mt-3">
		<h2>Lista miast oraz liczba imprez</h2>

		<ul class="list-group">
			<c:forEach var="city" items="${ cityList}">
				<li
					class="list-group-item d-flex justify-content-between align-items-center" style="max-width:200px">
					<c:if test="${ city.id==1}">
					${ city.cityName} <a href="/search/followCity/${city.id }" id="followButt${city.id }"> obserwuj </a><span class="badge badge-primary badge-pill">12</span>
					</c:if>
					<c:if test="${ city.id!=1}">
					${ city.cityName} <a href="/search/followCity/${city.id }" id="followButt${city.id }"> obserwuj </a><span class="badge badge-primary badge-pill">2</span>
					</c:if>
				
				</li>
			</c:forEach>

		</ul>
	</div>

</body>
</html>
