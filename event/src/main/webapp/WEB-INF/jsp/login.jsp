<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Page Title</title>
</head>
<body>
<h1>Strona logowania</h1>
	<form:form action="/loginUser" modelAttribute="user" method="post">
		<br /> podaj email <form:input path="email" id="email"></form:input>

		<br /> podaj hasło <form:password path="password" id="password"></form:password>

		<input type="submit" value="zaloguj" />
	</form:form>
</body>
</html>
