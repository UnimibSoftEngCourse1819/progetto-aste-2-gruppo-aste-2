<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<link rel="stylesheet" type="text/css" href="css/form.css" />
		<link rel="stylesheet" type="text/css" href="css/navBar.css" />
		<link rel="stylesheet" type="text/css" href="css/catalogue.css" />
		<script type="text/javascript" src="javascript/slideMenu.js"></script>
	</head>
	<body>
		<%@include file="templates/navbar.jsp" %>
		<div class="main">
			<div id="filter">
				<b>Tipologia asta</b>
				<div class="auction-type">
					<input type="radio" name="auctionType" value="busta chiusa">Asta in busta chiusa<br />
					<input type="radio" name="auctionType" value="secondo prezzo">Asta in busta chiusa al "secondo prezzo"<br />
					<input type="radio" name="auctionType" value="inglese">Asta "inglese"<br />
					<input type="radio" name="auctionType" value="olandese">Asta "olandese"<br />
				</div>
				<b>Categorie</b>
				<div class="categories">
					<input type="radio" name="category" value="abbigliamento">Abbigliamento<br />
					<input type="radio" name="category" value="auto e moto">Auto e moto<br />
					<input type="radio" name="category" value="bellezza">Bellezza<br />
					<input type="radio" name="category" value="casa e cucina">Casa e cucina<br />
					<input type="radio" name="category" value="CD e vinili">CD e vinili<br />
					<input type="radio" name="category" value="elettronica">Elettronica<br />
					<input type="radio" name="category" value="gioielli">Gioielli<br />
					<input type="radio" name="category" value="grandi elettrodomestici">Grandi elettrodomestici<br/>
					<input type="radio" name="category" value="illuminazione">Illuminazione<br />
					<input type="radio" name="category" value="informatica">Informatica<br />
					<input type="radio" name="category" value="orologi">Orologi<br />
					<input type="radio" name="category" value="strumenti musicali">Strumenti musicali<br />
				</div>
				<b>Mostra</b>
				<div class="show">
					<input type="checkbox" name="all" value="Tutte le aste">Tutte le aste<br>
					<input type="checkbox" name="notOpened" value="Aste non aperte">Aste non aperte<br>
					<input type="checkbox" name="opened" value="Aste aperte">Aste aperte<br>
					<input type="checkbox" name="closing" value="Aste in chiusura">Aste in chiusura<br>
				</div>
			</div>
			<div id="wrap">
				<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search.." title="Type in a name">
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