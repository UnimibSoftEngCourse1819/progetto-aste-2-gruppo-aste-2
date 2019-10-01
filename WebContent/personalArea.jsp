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
		<section id="first">
		  <!--for demo wrap-->
		  <h1>Le tue aste</h1>
		  <div class="tbl-header">
		    <table cellpadding="0" cellspacing="0" border="0">
		      <thead>
		        <tr>
		          <th>Titolo</th>
		          <th>Descrizione</th>
		          <th>Prezzo</th>
		          <th>Data</th>
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
				          <td><%= auctions.get(i)[0] %></td>
				          <td><%= auctions.get(i)[3] %></td>
				          <td><button type="button" class="manage-button" id="<%= auctions.get(i)[0] %>">Modifica</button></td>
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
		          <th>Prezzo</th>
		          <th>Data</th>
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
				          <td><%= auctions.get(i)[0] %></td>
				          <td><%= auctions.get(i)[3] %></td>
				          <td><button type="button" class="manage-button" id="<%= auctions.get(i)[0] %>">Modifica</button></td>
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
		          <th>Prezzo</th>
		          <th>Data</th>
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
		        <tr>
		          <td>AAD</td>
		          <td>AUSENCO</td>
		          <td>$2.38</td>
		          <td>-0.01</td>
		          <td>-1.36%</td>
		        </tr>
		        <tr>
		          <td>AAX</td>
		          <td>ADELAIDE</td>
		          <td>$3.22</td>
		          <td>+0.01</td>
		          <td>+1.36%</td>
		        </tr>
		        <tr>
		          <td>XXD</td>
		          <td>ADITYA BIRLA</td>
		          <td>$1.02</td>
		          <td>-1.01</td>
		          <td>+2.36%</td>
		        </tr>
		        <tr>
		          <td>AAC</td>
		          <td>AUSTRALIAN COMPANY </td>
		          <td>$1.38</td>
		          <td>+2.01</td>
		          <td>-0.36%</td>
		        </tr>
		        <tr>
		          <td>AAD</td>
		          <td>AUSENCO</td>
		          <td>$2.38</td>
		          <td>-0.01</td>
		          <td>-1.36%</td>
		        </tr>
		        <tr>
		          <td>AAX</td>
		          <td>ADELAIDE</td>
		          <td>$3.22</td>
		          <td>+0.01</td>
		          <td>+1.36%</td>
		        </tr>
		        <tr>
		          <td>XXD</td>
		          <td>ADITYA BIRLA</td>
		          <td>$1.02</td>
		          <td>-1.01</td>
		          <td>+2.36%</td>
		        </tr>
		        <tr>
		          <td>AAC</td>
		          <td>AUSTRALIAN COMPANY </td>
		          <td>$1.38</td>
		          <td>+2.01</td>
		          <td>-0.36%</td>
		        </tr>
		        <tr>
		          <td>AAD</td>
		          <td>AUSENCO</td>
		          <td>$2.38</td>
		          <td>-0.01</td>
		          <td>-1.36%</td>
		        </tr>
		        <tr>
		          <td>AAX</td>
		          <td>ADELAIDE</td>
		          <td>$3.22</td>
		          <td>+0.01</td>
		          <td>+1.36%</td>
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
		          <th>Prezzo</th>
		          <th>Data</th>
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
		        <tr>
		          <td>AAD</td>
		          <td>AUSENCO</td>
		          <td>$2.38</td>
		          <td>-0.01</td>
		          <td>-1.36%</td>
		        </tr>
		        <tr>
		          <td>AAX</td>
		          <td>ADELAIDE</td>
		          <td>$3.22</td>
		          <td>+0.01</td>
		          <td>+1.36%</td>
		        </tr>
		        <tr>
		          <td>XXD</td>
		          <td>ADITYA BIRLA</td>
		          <td>$1.02</td>
		          <td>-1.01</td>
		          <td>+2.36%</td>
		        </tr>
		        <tr>
		          <td>AAC</td>
		          <td>AUSTRALIAN COMPANY </td>
		          <td>$1.38</td>
		          <td>+2.01</td>
		          <td>-0.36%</td>
		        </tr>
		        <tr>
		          <td>AAD</td>
		          <td>AUSENCO</td>
		          <td>$2.38</td>
		          <td>-0.01</td>
		          <td>-1.36%</td>
		        </tr>
		        <tr>
		          <td>AAX</td>
		          <td>ADELAIDE</td>
		          <td>$3.22</td>
		          <td>+0.01</td>
		          <td>+1.36%</td>
		        </tr>
		        <tr>
		          <td>XXD</td>
		          <td>ADITYA BIRLA</td>
		          <td>$1.02</td>
		          <td>-1.01</td>
		          <td>+2.36%</td>
		        </tr>
		        <tr>
		          <td>AAC</td>
		          <td>AUSTRALIAN COMPANY </td>
		          <td>$1.38</td>
		          <td>+2.01</td>
		          <td>-0.36%</td>
		        </tr>
		        <tr>
		          <td>AAD</td>
		          <td>AUSENCO</td>
		          <td>$2.38</td>
		          <td>-0.01</td>
		          <td>-1.36%</td>
		        </tr>
		        <tr>
		          <td>AAX</td>
		          <td>ADELAIDE</td>
		          <td>$3.22</td>
		          <td>+0.01</td>
		          <td>+1.36%</td>
		        </tr>     
		      </tbody>
		    </table>
		  </div>
		</section>
	</body>
</html>