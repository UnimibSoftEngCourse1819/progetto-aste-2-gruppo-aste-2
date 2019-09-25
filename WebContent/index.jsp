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
		<script type="text/javascript" src="javascript/carousel.js"></script>
	</head>
	<body>
		<%@include file="templates/navbar.jsp" %>
		<div class="carousel-container">
			<%
				String[][] auctions = (String[][])request.getAttribute("auctions");
				for(int i = 0; i < auctions.length; ++i) {
			%>
					<div class="box">
				      <div class="pic">
				        <img src="https://i.pinimg.com/originals/e6/a4/c5/e6a4c5c0315cd4ac330f11c86eca2a41.gif" />
				      </div>
				      <div class="title"><%= auctions[i][1] %></div>
				      <div class="text"><%= auctions[i][2] %></div>
				      <div class="btn"><button id="<%= auctions[i][0] %>">Apri</button></div>
				    </div>
		    <%
				}
		    %>
	  	</div>
	  	<div class="carousel-container">
			<div class="box">
		      <div class="pic">
		        <img src="https://i.pinimg.com/originals/e6/a4/c5/e6a4c5c0315cd4ac330f11c86eca2a41.gif" />
		      </div>
		      <div class="title">Titolo</div>
		      <div class="text">Descrizione</div>
		      <div class="btn">Prezzo</div>
		    </div>
		    <div class="box">
		      <div class="pic">
		        <img src="https://i.pinimg.com/originals/e6/a4/c5/e6a4c5c0315cd4ac330f11c86eca2a41.gif" />
		      </div>
		      <div class="title">Titolo</div>
		      <div class="text">Descrizione</div>
		      <div class="btn">Prezzo</div>
		    </div>
		    <div class="box">
		      <div class="pic">
		        <img src="https://i.pinimg.com/originals/e6/a4/c5/e6a4c5c0315cd4ac330f11c86eca2a41.gif" />
		      </div>
		      <div class="title">Titolo</div>
		      <div class="text">Descrizione</div>
		      <div class="btn">Prezzo</div>
		    </div>
		    <div class="box">
		      <div class="pic">
		        <img src="https://i.pinimg.com/originals/e6/a4/c5/e6a4c5c0315cd4ac330f11c86eca2a41.gif" />
		      </div>
		      <div class="title">Titolo</div>
		      <div class="text">Descrizione</div>
		      <div class="btn">Prezzo</div>
		    </div>
		    <div class="box">
		      <div class="pic">
		        <img src="https://i.pinimg.com/originals/e6/a4/c5/e6a4c5c0315cd4ac330f11c86eca2a41.gif" />
		      </div>
		      <div class="title">Titolo</div>
		      <div class="text">Descrizione</div>
		      <div class="btn">Prezzo</div>
			</div>
	  	</div>
	</body>
</html>