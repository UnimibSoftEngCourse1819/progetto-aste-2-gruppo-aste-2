<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Home</title>
		<link rel="stylesheet" type="text/css" href="css/form.css" />
		<link rel="stylesheet" type="text/css" href="css/navBar.css" />
		<link rel="stylesheet" type="text/css" href="css/carousel.css" />
		<script type="text/javascript" src="javascript/slideMenu.js"></script>
		<%@ page import ="java.util.ArrayList"%>
		<%@ page import ="java.util.List"%>
	</head>
	<body>
		<%@include file="templates/navbar.jsp" %>
		<div class="carousel-title">Aste pi� recenti</div>
		<div class="carousel-container">
			<% 
				List<List<String>> auctions = (ArrayList<List<String>>) request.getAttribute("auctions");
				String image = "";
				
				for(int i = 0; i < auctions.size(); ++i) {
					image = auctions.get(i).get(3);
			%>
					<div class="box">
				      <div class="pic">
				        <img src="pictures/<%= image %>" />
				      </div>
				      <div class="title"><%= auctions.get(i).get(1) %></div>
				      <div class="text"><%= auctions.get(i).get(2) %></div>
				      <div class="btn">
				      	<form action="auction" method="get">
				      		<input type="hidden" name="id" value="<%= auctions.get(i).get(0) %>" />
				      		<input type="submit" value="Apri" />
				      	</form>
				      </div>
				    </div>
		    <%
				}
		    %>
	  	</div>
	  	<div class="carousel-title">Aste che scadono oggi</div>
	  	<div class="carousel-container">
	  		<% 
				List<List<String>> expiringAuctions = (ArrayList<List<String>>) request.getAttribute("expiringAuctions");
				
				for(int i = 0; i < expiringAuctions.size(); ++i) {
					image = expiringAuctions.get(i).get(3);
			%>
					<div class="box">
				      <div class="pic">
				        <img src="pictures/<%= image %>" />
				      </div>
				      <div class="title"><%= expiringAuctions.get(i).get(1) %></div>
				      <div class="text"><%= expiringAuctions.get(i).get(2) %></div>
				      <div class="btn">
				      	<form action="auction" method="get">
				      		<input type="hidden" name="id" value="<%= expiringAuctions.get(i).get(0) %>" />
				      		<input type="submit" value="Apri" />
				      	</form>
				      </div>
					</div>
		    <%
				}
		    %>
	  	</div>
	</body>
</html>