<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
			<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Page Title</title>
</head>
<body>



	<div class="container" style="width: 500px; text-align: center; margin-top:20vh">
		<h2 style="margin-bottom:20px">zaloguj się</h2>
		<div id=badPass></div>

		<form:form class="form-horizontal" action="/loginUser"
			modelAttribute="user" method="post">
			
			<label class="control-label col-sm-2" for="email">Email:</label>
			<form:input path="email" class="form-control" id="email" 
				placeholder="Enter email" name="email" style="width:350px; margin-bottom:20px"></form:input>
				
			<label class="control-label col-sm-2" for="pswd">Hasło:</label>
			<form:input path="password" type="password" class="form-control"
				id="pswd" placeholder="podaj Hasło" name="pswd" style="width:350px; margin-bottom:20px"></form:input>
			<div>
				<input type="submit" class="btn btn-default" value="zaloguj" style="margin-bottom:15px"/>
			</div>

			<small><a href="/rejestracja">jeśli nie masz konta, załóż je!</a></small>

		</form:form>
	</div>
	<script>
	var badPass = "${badPass}",
	userIsInBase = "${userIsInBase}";
		
	if(badPass=="true"){
		$('<p><div class="alert alert-danger">chyba podałeś błędne dane logowania</div></p>').appendTo('#badPass');
		}
	if(userIsInBase == "false"){
		$('<p><div class="alert alert-success">założyłeś konto, teraz się zaloguj</div></p>').appendTo('#badPass');
		}
	
	</script>
</body>
</html>
