<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Area personale</title>
		<link rel="stylesheet" type="text/css" href="css/form.css" />
		<link rel="stylesheet" type="text/css" href="css/navBar.css" />
		<link rel="stylesheet" type="text/css" href="css/table.css" />
		<script type="text/javascript" src="javascript/slideMenu.js"></script>
		<%@ page import ="java.util.ArrayList"%>
		<%@ page import ="java.util.List"%>
	</head>
	<body>
		<%@include file="templates/navbar.jsp" %>
		<%
			String credit = (String) request.getAttribute("userCredit");
		%>
		<div class="credit">Il tuo credito disponibile: <%= credit %></div>
		<form action="addCredit" method="post">
			<div class="credit-form">
				<input type="hidden" name="credit" value="<%= credit %>" />
				<input type="number" name="nCredit" min="1" max="10000" step="1" required />
			</div>
			<div class="credit-form">
				<input type="submit" value="Aggiungi crediti" />
			</div>
		</form>
		<section id="first">
		  <!--for demo wrap-->
		  <h1>Le tue aste</h1>
		  <div class="tbl-header">
		    <table cellpadding="0" cellspacing="0" border="0">
		      <thead>
		        <tr>
		          <th>Titolo</th>
		          <th>Descrizione</th>
		          <th>Data creazione</th>
		          <th>Data chiusura</th>
		          <th>Gestione</th>
		        </tr>
		      </thead>
		    </table>
		  </div>
		  <div class="tbl-content">
		    <table cellpadding="0" cellspacing="0" border="0">
		      <tbody>
		      	<%
		      		List<String[]> auctions = null;
				  
		      		if(request.getAttribute("userAuctions") instanceof ArrayList<?>) {
						auctions = (ArrayList<String[]>) request.getAttribute("userAuctions");
		      		}
		      	
					for(int i = 0; i < auctions.size(); ++i) {
				%>
				        <tr>
				          <td><%= auctions.get(i)[1] %></td>
				          <td><%= auctions.get(i)[2] %></td>
				          <td><%= auctions.get(i)[3] %></td>
				          <td><%= auctions.get(i)[4] %></td>
				          <td>
				          	<form action="auction" method="get">
				          		<input type="hidden" value="<%= auctions.get(i)[0] %>" name="id" />
				          		<input type="submit" class="manage-button" value="Apri" />
				          	</form>
				          </td>
				        </tr> 
		        <%
					}
		        %>
		      </tbody>
		    </table>
		  </div>
		</section>
		
		<section>
		  <!--for demo wrap-->
		  <h1>Aste che segui</h1>
		  <div class="tbl-header">
		    <table cellpadding="0" cellspacing="0" border="0">
		      <thead>
		        <tr>
		          <th>Titolo</th>
		          <th>Descrizione</th>
		          <th>Data creazione</th>
		          <th>Data chiusura</th>
		          <th>Gestione</th>
		        </tr>
		      </thead>
		    </table>
		  </div>
		  <div class="tbl-content">
		    <table cellpadding="0" cellspacing="0" border="0">
		      <tbody>
		      	<%
		      		List<String[]> auctionOffered = null;
				  
		      		if(request.getAttribute("auctionOffered") instanceof ArrayList<?>) {
						auctions = (ArrayList<String[]>) request.getAttribute("auctionOffered");
		      		}
		      	
					for(int i = 0; i < auctions.size(); ++i) {
				%>
				        <tr>
				          <td><%= auctions.get(i)[1] %></td>
				          <td><%= auctions.get(i)[2] %></td>
				          <td><%= auctions.get(i)[3] %></td>
				          <td><%= auctions.get(i)[4] %></td>
				          <td>
						  	<form action="auction" method="get">
				          		<input type="hidden" value="<%= auctions.get(i)[0] %>" name="id" />
				          		<input type="submit" class="manage-button" value="Apri" />
				          	</form>
						  </td>
				        </tr> 
		        <%
					}
		        %>     
		      </tbody>
		    </table>
		  </div>
		</section>
		
		<section>
		  <!--for demo wrap-->
		  <h1>Aste in cui sei il massimo offerente</h1>
		  <div class="tbl-header">
		    <table cellpadding="0" cellspacing="0" border="0">
		      <thead>
		        <tr>
		          <th>Titolo</th>
		          <th>Descrizione</th>
		          <th>Data creazione</th>
		          <th>Data chiusura</th>
		          <th>Gestione</th>
		        </tr>
		      </thead>
		    </table>
		  </div>
		  <div class="tbl-content">
		    <table cellpadding="0" cellspacing="0" border="0">
		      <tbody>
		        <tr>
		          <td>AAC</td>
		          <td>AUSTRALIAN COMPANY </td>
		          <td>$1.38</td>
		          <td>+2.01</td>
		          <td>-0.36%</td>
		        </tr>   
		      </tbody>
		    </table>
		  </div>
		</section>
		
		<section>
		  <!--for demo wrap-->
		  <h1>Aste vinte</h1>
		  <div class="tbl-header">
		    <table cellpadding="0" cellspacing="0" border="0">
		      <thead>
		        <tr>
		          <th>Titolo</th>
		          <th>Descrizione</th>
		          <th>Data creazione</th>
		          <th>Data chiusura</th>
		          <th>Prezzo</th>
		          <th>Restituzione</th>
		        </tr>
		      </thead>
		    </table>
		  </div>
		  <div class="tbl-content">
		    <table cellpadding="0" cellspacing="0" border="0">
		      <tbody>
		        <%
		      		List<String[]> auctionWon = null;
				  
		      		if(request.getAttribute("auctionWon") instanceof ArrayList<?>) {
						auctionWon = (ArrayList<String[]>) request.getAttribute("auctionWon");
		      		}
		      	
					for(int i = 0; i < auctionWon.size(); ++i) {
				%>
				        <tr>
				          <td><%= auctionWon.get(i)[1] %></td>
				          <td><%= auctionWon.get(i)[2] %></td>
				          <td><%= auctionWon.get(i)[3] %></td>
				          <td><%= auctionWon.get(i)[4] %></td>
				          <td></td>
				          <td>
						  	<form action="auction" method="get">
				          		<input type="hidden" value="<%= auctionWon.get(i)[0] %>" name="id" />
				          		<input type="submit" class="manage-button" value="Apri" />
				          	</form>
						  </td>
				        </tr> 
		        <%
					}
		        %>         
		      </tbody>
		    </table>
		  </div>
		</section>
	</body>
</html>