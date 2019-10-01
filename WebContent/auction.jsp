<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Asta</title>
		<link rel="stylesheet" type="text/css" href="css/navBar.css" />
		<link rel="stylesheet" type="text/css" href="css/auctionDisplay.css" />
		<script type="text/javascript" src="javascript/slideMenu.js"></script>
	</head>
	<body>
		<%@include file="templates/navbar.jsp" %>
		<%
			String[] auction = (String[]) request.getAttribute("auction");
		%>
		<div class="form">
        	<form action="auction" method="post">
        		<input type="hidden" name="auctionID" value="<%= auction[0] %>" />
        		<input type="hidden" name="mod" value="<%= auction[3] %>" />
       			<div class="row">
       				<div class="col">
		        		<div class="pic">
		            		<img src="https://steamcdn-a.akamaihd.net/steam/apps/381210/header.jpg?t=1567794495" />
		        		</div>
		        	</div>
		        	<div class="col">
		        		<div class="data">
							Termine: <%= auction[4] %>
        				</div>
        				<div class="auction-type">
        					Tipo: <%= auction[5] %>
        				</div>
		        	</div>
	        	</div>
	        	<div class="row">
	        		<div class="col">
		        		<div class="title">
		        			<%= auction[1] %>
		        		</div>
		        	</div>
		        	<div class="col"></div>
	        	</div>
	        	<div class="row">
	        		<div class="col">
		        		<div class="description">
		        			<%= auction[2] %>
		        		</div>
	        		</div>
	        		<div class="col">
						<div class="credit">I tuoi crediti: </div>
	        		</div>
	        	</div>
	        	<div class="row">
	        		<div class="col" id="last-col"></div>
	        		<div class="col">
	        			<input type="number" min="0.00" max="10000.00" step="0.01" name="price" />
	        			<input type="submit" value="Invia" />
	        		</div>
	        	</div>
        	</form>
        </div>
	</body>
</html>