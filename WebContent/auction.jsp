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
		<%@ page import ="java.util.ArrayList"%>
		<%@ page import ="java.util.List"%>
	</head>
	<body>
		<%@include file="templates/navbar.jsp" %>
		<%
			boolean isLogged = false;
			boolean isOwner = false;
			int credit = 0;
			List<String> auction = (ArrayList<String>) request.getAttribute("auction");
			
			if((String) session.getAttribute("name") != null) {
				isOwner = (Boolean) request.getAttribute("isOwner");
				isLogged = true;
				credit = (Integer) request.getAttribute("credit");
			}
		%>
		<div class="form">
        	<form action="offer" method="get">
        		<input type="hidden" name="id" value="<%= auction.get(0) %>" />
        		<input type="hidden" name="mod" value="<%= auction.get(3) %>" />
       			<div class="row">
       				<div class="col">
		        		<div class="pic">
		            		<img src="pictures/<%= auction.get(7) %>" />
		        		</div>
		        	</div>
		        	<div class="col">
		        		<div class="data">
							Termine: <div class="info"><%= auction.get(4) %></div>
        				</div>
        				<div class="auction-type">
        					Tipo: <div class="info"><%= auction.get(5) %></div>
        				</div>
		        	</div>
	        	</div>
	        	<div class="row">
	        		<div class="col">
		        		<div class="title">
		        			<%= auction.get(1) %>
		        		</div>
		        	</div>
		        	<div class="col">
		        		<div class="price">
		        			<input type="hidden" name="basePrice" value="<%=  auction.get(6) %>" />
		        			Prezzo base: <div class="info"><%= auction.get(6) %></div>
		        		</div>
		        	</div>
	        	</div>
	        	<div class="row">
	        		<div class="col">
		        		<div class="description">
		        			<%= auction.get(2) %>
		        		</div>
	        		</div>
		        	<% 
		        		if(!isLogged) {
		        	%>
		        			<div class="col">
								<div class="credit">Per effettuare un'offerta esegui il login.</div>
		        			</div>	
		        	<%
		        		}
		        		else if(!isOwner) { 
		        	%>
		        		<div class="col">
							<div class="credit">
								Credito correntemente disponibile: <div class="info"><%= credit %></div>
							</div>
							<%
								if(request.getAttribute("errorMessage") != null)
									out.println(request.getAttribute("errorMessage"));
							%>
		        		</div>
		        </div>
		        <div class="row">
		        	<div class="col" id="last-col"></div>
		        	<div class="col">
		        		<input type="number" min="1" max="10000" step="1" name="price" />
		        		<input type="submit" value="Invia" />
		        	</div>
		        </div>
		        <% 
		        	}
	        	%>
        	</form>
        </div>
	</body>
</html>