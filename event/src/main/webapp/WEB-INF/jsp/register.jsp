<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"> </script>

<title>Page Title</title>
</head>
<body>

	<div class="container"
		style="width: 500px; text-align: center; margin-top: 20vh">
		<h2 style="margin-bottom: 20px">zarejestruj się</h2>
		<div id="badPass"></div>
		<form:form class="form-horizontal" action="/addUser" 
			modelAttribute="user"  method="post" >

			<label class="control-label col-sm-2" for="email">Email:</label>
			<form:input path="email" class="form-control" id="email" name="email" 
				type="email" placeholder="podaj email"
				style="width:350px; margin-bottom:20px"></form:input>


			<label class="control-label col-sm-2" for="pwd">Hasło:</label>
			<form:input path="password" type="password" class="form-control"
				id="pwd" placeholder="podaj Hasło" name="pwd"
				style="width:350px; margin-bottom:20px"></form:input>

			<label class="control-label col-sm-2" for="pwd2">powtórz
				hasło:</label>
			<input type="password" class="form-control" id="pwd2"
				placeholder="powtórz hasło" name="pwd2"
				style="width: 350px; margin-bottom: 20px"></input>


			<div>
				<input type="submit" class="btn btn-default" value="zarejestruj" id="but"
					style="margin-bottom: 15px" />
			</div>


			<small><a href="/logowanie">jeśli masz konto, zaloguj się!</a></small>
		</form:form>
	</div>

<script>
		var password = document.getElementById("pwd"), 
		password2 = document.getElementById("pwd2"),
		email = document.getElementById("email")
		button = document.getElementById("but"),
		userIsInBase = "${userIsInBase}";

		function validatePassword() {
			if (password.value != password2.value || password.value == '') {
				password2.setCustomValidity("podane hasła nie są identyczne lub pola są puste");
			}
			else if(email.value == ''){
				email.setCustomValidity("email jest pusty");
			}
		}
		button.onclick = validatePassword;
   		password.onchange = validatePassword;
		password2.onkeyup = validatePassword;

		window.onload = function(){
		if(userIsInBase == "true"){
			$('<p><div class="alert alert-danger">istnieje już taki użytkownik, podaj inny email</div></p>').appendTo('#badPass');
			}
		}
		
	</script>

</body>
</html>
