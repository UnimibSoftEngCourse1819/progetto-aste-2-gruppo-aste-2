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
				<form action="search" method="post">
					<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Cerca.." title="Type in a name">
					<b>Tipologia asta</b>
					<div class="auction-type">
						<input type="radio" name="auctionType" value="tutte" checked="checked">Tutte le tipologie di aste<br />
						<input type="radio" name="auctionType" value="busta chiusa">Asta in busta chiusa<br />
						<input type="radio" name="auctionType" value="secondo prezzo">Asta in busta chiusa al "secondo prezzo"<br />
						<input type="radio" name="auctionType" value="inglese">Asta "inglese"<br />
						<input type="radio" name="auctionType" value="olandese">Asta "olandese"<br />
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
						<input type="checkbox" name="notOpened" value="Aste non aperte">Aste non aperte<br>
						<input type="checkbox" name="opened" value="Aste aperte">Aste aperte<br>
						<input type="checkbox" name="closing" value="Aste in chiusura">Aste in chiusura<br>
					</div>
					<input type="submit" id="send" value="Cerca" />
				</form>
			</div>
			<div id="wrap">
				<div id="columns" class="columns_4">
	  				<figure>
	  					<img src="https://steamcdn-a.akamaihd.net/steam/apps/381210/header.jpg?t=1567794495" />
						<figcaption>Green Checkered Shirt</figcaption>
	    				<span class="price">$44</span>
	    				<a class="button" href="#">Buy Now</a>
					</figure>
		
					<figure>
						<img src="https://steamcdn-a.akamaihd.net/steam/apps/381210/header.jpg?t=1567794495" />
						<figcaption>Green and Black Flowers</figcaption>
	    				<span class="price">$44</span>
	    				<a class="button" href="#">Buy Now</a>
					</figure>
		
	  				<figure>
						<img src="https://steamcdn-a.akamaihd.net/steam/apps/381210/header.jpg?t=1567794495" />
						<figcaption>Black Dots</figcaption>
	    				<span class="price">$44</span>
	    				<a class="button" href="#">Buy Now</a>
					</figure>
	  
					<figure>
						<img src="https://steamcdn-a.akamaihd.net/steam/apps/381210/header.jpg?t=1567794495" />
						<figcaption>Red Flowy</figcaption>
	    				<span class="price">$44</span>
	    				<a class="button" href="#">Buy Now</a>
					</figure>
		
	   				<figure>
						 <img src="https://steamcdn-a.akamaihd.net/steam/apps/381210/header.jpg?t=1567794495" />
						<figcaption>Yellow Button-Up</figcaption>
	    				<span class="price">$44</span>
	    				<a class="button" href="#">Buy Now</a>
					</figure>
		
	   				<figure>
		 				<img src="https://steamcdn-a.akamaihd.net/steam/apps/381210/header.jpg?t=1567794495" />
						<figcaption>Put a Bird On It</figcaption>
	     				<span class="price">$44</span>
	     				<a class="button" href="#">Buy Now</a>
					</figure>
	  
					<figure>
						<img src="https://steamcdn-a.akamaihd.net/steam/apps/381210/header.jpg?t=1567794495" />
						<figcaption>Polka Dots</figcaption>
						<span class="price">$44</span>
						<a class="button" href="#">Buy Now</a>
					</figure>	
					 
					<figure>
						<img src="https://steamcdn-a.akamaihd.net/steam/apps/381210/header.jpg?t=1567794495">
						<figcaption>Blue Spots</figcaption>
					    <span class="price">$44</span>
					    <a class="button" href="#">Buy Now</a>
					</figure>
					 
					<figure>
						<img src="https://steamcdn-a.akamaihd.net/steam/apps/381210/header.jpg?t=1567794495">
					   	<figcaption>Pink Dots</figcaption>
					    <span class="price">$44</span>
					    <a class="button" href="#">Buy Now</a>
					</figure>	
				</div>
			</div>
		</div>
	</body>
</html>