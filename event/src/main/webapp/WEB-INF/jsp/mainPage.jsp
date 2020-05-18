<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="pl" />
<link rel="stylesheet" href="/resources/css/mainCSS.css">
<title>Meet-mainPage</title>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
</head>


<body>

	<header> </header>


	<%@include file="/WEB-INF/jsp/navbar.jsp"%>

	<div class="container">
		<div class="leftSite">
			left site


			<form:form action="/searchEvent" modelAttribute="eventToSearch"
				method="post">
				<h1>filtrowanie wydarzeń</h1>
				<br>
				<label for="miasto">miasto</label>
				<form:input path="city" id="miasto"></form:input>
				<br>
				<p>
					<input type="submit" value="filtruj" />
			</form:form>
			<input type="submit" value="Społeczność" name="userPage" />

		</div>

		<div class="mainPage">
			<c:if test="${ not empty searchFilter}">
				<div>wyszukano: "${searchFilter }"</div>
			</c:if>
			<c:forEach var="ev" items="${eventList }">
				<c:choose>
					<c:when test="${ev != null}">
						<div class="eventDiv">
							<div class="eventDivInfo">${ev.name } 
							${ev.miasto }
							${ev.eventRange}
							<a href="/user/followEvent/${ev.id }">obserwuj wydarzenie</a>
							</div>

							
							<img id="imageDiv" alt="" src="uploads/${ev.imageSource }">
							
						</div>
					</c:when>
				</c:choose>

			</c:forEach>
			<div id="dynData"></div>
		</div>
		<div class="rightPage">
			right site

			<!-- DODAWANIE NOWEGO WYDARZENIA -->

			<!-- Trigger/Open The Modal -->
			<button id="addbtn">Dodaj wydarzenie</button>

			<!-- The Modal -->
			<div id="addEventBox" class="addEventBox">

				<!-- Modal content -->
				<div class="addEventBox-content">
					<span class="close">&times;</span>

					<div class="addEvent">
						<form:form action="/addEvent" modelAttribute="eventToAdd"
							method="post" enctype="multipart/form-data">\
							
							<img id="imageToAdd" />
							<div class=AddInfo>
								<h1>Dodanie nowego wydarzenia</h1>
								<br> <label for="name">nazwa wydarzenia</label>
								<form:input path="name" id="name"></form:input>
								<br> <label for="miasto">miasto</label>
								<form:input path="miasto" id="miasto"></form:input>
								<br> <label>zasięg wydarzenia</label> <br>
								<form:radiobutton path="eventRange" value="private"
									id="privateRange" />
								prywatny<br>
								<form:radiobutton path="eventRange" value="public"
									id="publicRange" />
								publiczny<br> <br />data wydarzenia
								<form:input type="date" id="start" path="date"></form:input>
								<br />godzina wydarzenia
								<form:input type="time" id="start" path="date"></form:input>

								<br />przeslij obrazek<input type="file" name="file"
									accept="image/*" onchange="loadFile(event)"></input>
								<p>

									<input type="submit" value="dodaj Event" />
							</div>
						</form:form>
					</div>

				</div>

			</div>

		</div>

	</div>


	<script>
		var offSet = 0;
		$(window).scroll(
				function() {
					if ($(window).scrollTop() + $(window).height() == $(
							document).height()) {
						offSet += 3;
						getEvents(offSet);
					} else {
					}
					//  $("#dynData").text("");}
				});

		// Get the modal
		var addEventBox = document.getElementById("addEventBox");

		// Get the button that opens the modal
		var btn = document.getElementById("addbtn");

		// Get the <span> element that closes the modal
		var closeBoxBtn = document.getElementsByClassName("close")[0];

		// When the user clicks the button, open the modal 
		btn.onclick = function() {
			addEventBox.style.display = "block";
		}

		// When the user clicks on <span> (x), close the modal
		closeBoxBtn.onclick = function() {
			addEventBox.style.display = "none";
		}

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == addEventBox) {
				addEventBox.style.display = "none";
			}
		}

		function getEvents(offset) {
			$.ajax({
				url : '/dynLoad?offset=' + offset,
				success : function(data, status) {
					$(data).appendTo('#dynData');
				}
			});
		}

		var loadFile = function(event) {
			var output = document.getElementById('imageToAdd');
			output.src = URL.createObjectURL(event.target.files[0]);
			output.onload = function() {
				URL.revokeObjectURL(output.src)
			}
		};
	</script>
	</script>

</body>

</html>


