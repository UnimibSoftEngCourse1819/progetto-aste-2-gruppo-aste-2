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
		<div class="carousel-title">Aste più recenti</div>
		<div class="carousel-container">
			<% 
				List<List<String>> auctions = (ArrayList<List<String>>) request.getAttribute("auctions");
				for(int i = 0; i < auctions.size(); ++i) {
			%>
					<div class="box">
				      <div class="pic">
				        <img src="https://i.pinimg.com/originals/e6/a4/c5/e6a4c5c0315cd4ac330f11c86eca2a41.gif" />
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