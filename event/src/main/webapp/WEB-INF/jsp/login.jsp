<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Page Title</title>
</head>
<body>



	<div class="container" style="width: 500px; text-align: center; margin-top:20vh">
		<h2 style="margin-bottom:20px">zaloguj się</h2>
		<form:form class="form-horizontal" action="/loginUser"
			modelAttribute="user" method="post">
			
			<label class="control-label col-sm-2" for="email">Email:</label>
			<form:input path="email" class="form-control" id="email"
				placeholder="Enter email" name="email" style="width:350px; margin-bottom:20px"></form:input>
				
				
			<label class="control-label col-sm-2" for="pwd">Hasło:</label>
			<form:input path="password" type="password" class="form-control"
				id="pwd" placeholder="Enter password" name="pwd" style="width:350px; margin-bottom:20px"></form:input>

			<div>
				<input type="submit" class="btn btn-default" value="zaloguj" style="margin-bottom:15px"/>


			</div>

			<small><a href="/rejestracja">jeśli nie masz konta, załóż je!</a></small>

		</form:form>
	</div>





	<%-- 	<form:form action="/loginUser" modelAttribute="user" method="post">
		<br /> podaj email <form:input path="email" id="email"></form:input>

		<br /> podaj hasło <form:password path="password" id="password"></form:password>

		<input type="submit" value="zaloguj" />
	</form:form> --%>
</body>
</html>
