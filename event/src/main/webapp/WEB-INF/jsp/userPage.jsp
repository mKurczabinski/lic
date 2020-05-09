<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
		<li class="navBox"><button class="logoutBtn"
				id="submit" onclick="location.href ='Logout'">Wyloguj</button></li>
	</ul>

	<div class="container">
		<div class="leftSite">
			left site\

			<form:form action="/User/searchUser"
				modelAttribute="userToSearchParam" method="post">
				<h1>Dodanie usera</h1>
				<form:input path="email" id="email"></form:input>
				<input type="submit" value="szukaj" />
			</form:form>

			<p>
				<button class="btn btn-lg btn-primary" id="submit"
					onclick="location.href ='/User/invite'">Zaproszenia</button>
		</div>

		<div class="mainPage">
			<c:if test="${ not empty searchUserFilter}">
			
			email wyszukanego użytkownika "${searchUserFilter.email }"

			<form:form action="/User/inviteUser" modelAttribute="userToInvite"
					method="post">
					<p>
						<input type="submit" value="dodaj użytkownika" path="friendid" />
				</form:form>
			</c:if>


			<c:if test="${invite eq true}">
				<c:choose>

					<c:when test="${fn:length(friendList) eq 0 }">
					brak zaproszeń
					</c:when>
					<c:otherwise>
						<c:forEach var="item" items="${friendList }">
							<div>${item.email }</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</c:if>
		</div>

	</div>


</body>

</html>


