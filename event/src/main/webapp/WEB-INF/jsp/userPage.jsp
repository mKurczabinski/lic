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
<title>Meet-mainPage</title>
</head>


<body>

	<header> </header>



	<%@include file="/WEB-INF/jsp/navbar.jsp"%>

	<div class="container">
		<div class="leftSite">
			left site\
			${email }
			<form:form action="/searchUser" modelAttribute="userToSearchParam"
				method="post">
				<h1>Dodanie usera</h1>
				<form:input path="email" id="email"></form:input>
				<input type="submit" value="szukaj" />
			</form:form>

			<p>
				<button id="submit" onclick="location.href ='/invite'">Zaproszenia</button>
		</div>

		<div class="mainPage">

			<c:if test="${not empty searchUserFilter}">
			email szukanego użytkownika "${searchUserFilter }"
				<c:choose>
					<c:when test="${findUser eq null }">
					nie ma takiego użytkownika
					</c:when>
					<c:when
						test="${not empty findUser && friendInfo.sendInvite eq true}">
						<form:form action="/deleteInvite" method="post">
							<p>
								<input type="submit" value="cofnij zaproszenie" path="friendid" />
						</form:form>
					</c:when>
					<c:when test="${not empty findUser}">
						<form:form action="/inviteUser" method="post">
							<p>
								<input type="submit" value="dodaj użytkownika" path="friendid" />
						</form:form>
					</c:when>

					<c:otherwise>

					</c:otherwise>
				</c:choose>
			</c:if>

			<c:if test="${invite eq true}">
				<c:choose>

					<c:when test="${fn:length(friendList) eq 0 }">
					brak zaproszeń
					</c:when>
					<c:otherwise>
						<c:forEach var="item" items="${friendList }">
							<div>${item.email }</div>
								
							<a href="acceptInvite/${item.id }">zaakceptuj zaproszenie</a>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</c:if>
		</div>

	</div>


</body>

</html>


