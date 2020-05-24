<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<ul class="nav">
	<li class="navBox"><button id="submit"
			onclick="location.href ='/mainPage'">Home</button></li>
	<li class="navBox"><button id="submit"
			onclick="location.href ='/user/'">Społeczność</button></li>
			
		<li class="navBox"><input id="paramInput" type="text" placeholder="czego szukasz?"
		style="padding-right: 0px"></li>
	<li class="navBox" id="searchButton"><button id="submit"
			onclick="search();">szukaj</button></li>


<%-- 	<form:form action="findNavParam" modelAttribute="navResult"
		method="post">
		<form:input path="city" id="miasto"></form:input>
		<input type="submit" value="filtruj" />
	</form:form> --%>
</ul>

<script>
	

	function search(){
		var searchParam = document.getElementById("paramInput").value;

		window.location = '/search/navSearch/'+searchParam;
		console.log("'/navSearch/'+searchParam;");
		}

</script>