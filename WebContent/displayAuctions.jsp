<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Catalogo aste</title>
		<link rel="stylesheet" type="text/css" href="css/form.css" />
		<link rel="stylesheet" type="text/css" href="css/navBar.css" />
		<link rel="stylesheet" type="text/css" href="css/catalogue.css" />
		<script type="text/javascript" src="javascript/slideMenu.js"></script>
		<%@ page import ="java.util.ArrayList"%>
		<%@ page import ="java.util.List"%>
	</head>
	<body>
		<%@include file="templates/navbar.jsp" %>
		<div class="main">
			<div id="filter">
				<form action="search" method="get">
					<input type="text" id="myInput" name="search" placeholder="Cerca.." title="Type in a name">
					<b>Tipologia asta</b>
					<div class="auction-type">
						<input type="radio" name="auctionType" value="tutte" checked="checked">Tutte le tipologie di aste<br />
						<input type="radio" name="auctionType" value="FirstSealed">Asta in busta chiusa<br />
						<input type="radio" name="auctionType" value="SecondSealed">Asta in busta chiusa al "secondo prezzo"<br />
						<input type="radio" name="auctionType" value="English">Asta "inglese"<br />
						<input type="radio" name="auctionType" value="Dutch">Asta "olandese"<br />
					</div>
					<b>Categorie</b>
					<div class="categories">
						<input type="radio" name="category" value="tutte" checked="checked">Tutte le categorie<br />
						<%
		      				List<String> categories = null;
				  
				      		if(request.getAttribute("categories") instanceof ArrayList<?>) {
								categories = (ArrayList<String>) request.getAttribute("categories");
				      		}
		      	
							for(int i = 0; i < categories.size(); ++i) {
						%>
								<input type="radio" name="category" value="<%= categories.get(i) %>"><%= categories.get(i) %><br />
						<%
							}
						%>
					</div>
					<b>Mostra</b>
					<div class="show">
						<input type="checkbox" name="notOpened" value="true">Aste non aperte<br>
						<input type="checkbox" name="opened" value="true">Aste aperte<br>
					</div>
					<input type="submit" id="send" value="Cerca" />
				</form>
			</div>
			<div id="wrap">
				<div id="columns" class="columns_4">
		  			<%
		  				if(request.getAttribute("auctions") != null) {
				      		List<String[]> auctions = null;
						  
				      		if(request.getAttribute("auctions") instanceof ArrayList<?>) {
								auctions = (ArrayList<String[]>) request.getAttribute("auctions");
				      		}
				      	
							for(int i = 0; i < auctions.size(); ++i) {
					%>
				  				<figure>
				  					<img src="https://steamcdn-a.akamaihd.net/steam/apps/381210/header.jpg?t=1567794495" />
									<figcaption><%= auctions.get(i)[1] %></figcaption>
				    				<div class="price"><%= auctions.get(i)[2] %></div>
				    				<div class="button">
				    					<form action="auction" method="get">
				    						<input type="hidden" name="id" value="<%= auctions.get(i)[0] %>" />
				    						<input type="submit" value="Apri" />
				    					</form>
				    				</div>
								</figure>
					<%
							}
		  				}
					%>	
				</div>
			</div>
		</div>
	</body>
</html>