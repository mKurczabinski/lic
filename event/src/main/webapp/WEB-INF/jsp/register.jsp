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

	<div class="container">
		<h2>rejestracja</h2>
		<form:form class="form-horizontal" action="/addUser" modelAttribute="user" method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Email:</label>
				<div class="col-sm-10">
					<form:input path="email" class="form-control" id="email"
						placeholder="Enter email" name="email"></form:input>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Hasło:</label>
				<div class="col-sm-10">
					<form:input path="password" type="password" class="form-control"
						id="pwd" placeholder="Enter password" name="pwd"></form:input>
				</div>
			</div>
			<!-- 			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="checkbox">
						<label><input type="checkbox" name="remember">
							Remember me</label>
					</div>
				</div>
			</div> -->
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-default" value="zaloguj" />

				</div>

			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<small><a href="/logowanie">jeśli masz kont,
							zaloguj się!</a></small>
				</div>

			</div>
		</form:form>
	</div>



</body>
</html>
