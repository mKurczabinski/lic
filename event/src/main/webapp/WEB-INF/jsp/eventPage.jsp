<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html>
<head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="pl" />
<link rel="stylesheet" href="/resources/css/eventPageCSS.css">
<title>Meet-mainPage</title>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
</head>


<body>

	<header> </header>



	<%@include file="/WEB-INF/jsp/navbar.jsp"%>

	<div class="container">
		<div class="flankPageLeft">

			<div class="userList">
				<h3>uczestnicy wydarzenia <span class="glyphicon glyphicon-user"style="color:grey"> </span><span class="badge">${event.follows }</span></h3>
				<ul class="list-group list-group-flush">
					<c:forEach var="us" items="${ userList}">
						<li class="list-group-item" style="max-width: 200px">${us.email }</li>
					</c:forEach>
				</ul>
			</div>
			<button>Zaproś znajomego</button>
		</div>

		<div class="eventPage">
			<div id="eventDiv">

				<div id="photo">
					<img id="imageDiv" alt="" src="/uploads/${event.imageSource }">
				</div>
				<ul class="list-group">
					<li class="list-group-item">nazwa wydarzenia :${ event.name}</li>
					<li class="list-group-item">data wydarzenia: <fmt:formatDate pattern="yyyy-MM-dd || HH:MM"  value="${event.eventTime.time}" /></li>
				</ul>
			</div>


		</div>

		<div class="flankPageRight"></div>

	</div>
<script>

</script>

</body>

</html>


