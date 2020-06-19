<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>

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
	
<title>Meet-mainPage</title>


</head>


<body>
	<%@include file="/WEB-INF/jsp/navbar.jsp"%>

	<div class="container">
		<div class="leftSite">

			<%-- <form:form action="/searchEvent" modelAttribute="eventToSearch"
				method="post">
				<h1>filtrowanie wydarzeń</h1>
				<br>
				<label for="miasto">miasto</label>
				<form:input path="city" id="miasto"></form:input>
				<br>
				<p>
					<input type="submit" value="filtruj" />
			</form:form> --%>
			<h3>filtrowanie wydarzeń</h3>
			<div class="input-group date" data-provide="timepicker">
				<label>data od</label>
				<div class="input-group date" data-provide="datepicker">
					<input type="date" id="startDate">
				</div>
				<label>data do</label>
				<div class="input-group date" data-provide="datepicker">
					<input type="date" id="endDate">
				</div>
				<br>
				<label>miasto</label><p>
				<input id="fcity" placeholder="miasto" >
			</div>
			<button id="FilButton" onClick="filterEvent()">szukaj</button>

		</div>

		<div class="mainPage">
			<c:if test="${ not empty searchFilter}">
				<div>wyszukano: "${searchFilter }"</div>
			</c:if>
			<c:forEach var="ev" items="${eventList }">
				<c:choose>
					<c:when test="${ev != null}">
						<div class="eventDiv">
							<div class="eventDivInfo">
								<ul class="list-group">
									<li class="list-group-item">${ ev.name}|| ${ev.miasto } ||
										${ev.eventRange} || <fmt:formatDate
											pattern="yyyy-MM-dd / HH:MM" value="${ev.eventTime.time}"></fmt:formatDate>
										|| ${ev.follows }<span class="glyphicon glyphicon-user"
										style="color: grey"> </span> <a
										href="/user/followEvent/${ev.id }" id="followButt${ev.id }"
										class="btn btn-default" style="float: right"> 
												<c:choose>
													<c:when test="${ev.followed eq 1}">
														przestań obserwować
													</c:when>
													<c:otherwise>
														obserwuj
													</c:otherwise>
												</c:choose>
									</a>
									</li>
								</ul>
							</div>
							<img style="cursor: pointer" id="imageDiv" alt=""
								src="uploads/${ev.imageSource }"
								onClick="window.location.href='event/eventPage/${ev.id}'">
						</div>
					</c:when>
				</c:choose>

			</c:forEach>
			<div id="dynData"></div>
		</div>
		<div class="rightPage">
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
							method="post" enctype="multipart/form-data">

							<img id="imageToAdd" />

							<div class=AddInfo>
								<h1>Dodanie nowego wydarzenia</h1>


								<div class="form-group">
									<form:input path="name" type="text" class="form-control"
										id="name" placeholder="nazwa wydarzenia" name="name"></form:input>
								</div>
								<div class="form-group">
									<form:input path="miasto" type="text" class="form-control"
										id="miasto" placeholder="miasto" name="name"></form:input>
								</div>


								<label>zasięg wydarzenia</label>
								<div class="radio">
									<label><form:radiobutton path="eventRange"
											value="private" id="privateRange" />prywatny</label>
								</div>
								<div class="radio">
									<label><form:radiobutton path="eventRange"
											value="public" id="publicRange" />publiczny</label>
								</div>


								<div class="input-group date" data-provide="timepicker" >
									<label >data wydarzenia </label>
									<div class="input-group date" data-provide="datepicker">
										<form:input type="date" id="start" path="date"></form:input>
									</div>
								</div>

								<div class="input-group date" data-provide="timepicker">
									<label>godzina wydarzenia </label>
									<form:input type="time" id="start" path="date"></form:input>
								</div>

								<div class="form-group">
									<input type="file" name="file" accept="image/*"
										onchange="loadFile(event)"></input> 
										
										
								</div>
								<input type="submit"
										value="dodaj Event" />
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
				});

		var addEventBox = document.getElementById("addEventBox");
		var btn = document.getElementById("addbtn");
		var closeBoxBtn = document.getElementsByClassName("close")[0];
		btn.onclick = function() {
			addEventBox.style.display = "block";
		}
		closeBoxBtn.onclick = function() {
			addEventBox.style.display = "none";
		}
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

		var fBtn = document.getElementById("FilButton"),
			startDate = document.getElementById("startDate"),
			endDate = document.getElementById("endDate"),
			city = document.getElementById("fcity");

		
		function filterEvent(){
			window.location = '/filterEvent/?city='+city.value + '&startDate='+startDate.value + '&endDate='+endDate.value;
			}
	</script>

</body>

</html>


