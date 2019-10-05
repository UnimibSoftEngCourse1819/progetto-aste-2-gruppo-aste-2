<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Configurazione aste</title>
		<link rel="stylesheet" type="text/css" href="css/form.css" />
		<link rel="stylesheet" type="text/css" href="css/navBar.css" />
		<script type="text/javascript" src="javascript/slideMenu.js"></script>
	</head>
	<body>
		<%@include file="templates/navbar.jsp" %>
		<%
			String auctionType = (String) request.getAttribute("type");
			int percentage = (int) request.getAttribute("penalty");
			int slot = (int) request.getAttribute("slot");
			int offers = (int) request.getAttribute("offers");
		%>
        <div class="item-form">
        	<form method="post" action="update">
        		<%
					if(request.getAttribute("errorMessage") != null)
						out.println(request.getAttribute("errorMessage"));
				%>
                <div class="row">
                    <div class="col">
                    	<label for="type">
                        	Tipologia asta
                       	</label>
                    </div>
                    <div class="col">
                        <select id="type" name="type" onchange="this.form.action='configuration'; this.form.submit()">
                            <option value="FirstSealed" <% if(auctionType.equals("FirstSealed")){ %> selected <% } %>>Busta chiusa</option>
                            <option value="SecondSealed" <% if(auctionType.equals("SecondSealed")){ %> selected <% } %>>Busta chiusa al "secondo prezzo"</option>
                            <option value="English" <% if(auctionType.equals("English")){ %> selected <% } %>>Asta "inglese"</option>
                            <option value="Dutch" <% if(auctionType.equals("Dutch")){ %> selected <% } %>>Asta "olandese"</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                    	<label for="penalty">
                        	Percentuale penale
                       	</label>
                    </div>
                    <div class="col">
                        <input type="number" id="penalty" name="penalty" min="0" max="100" step="1" value="<%= percentage %>" />
                    </div>
                </div>
                <div class="row">
                	<div class="col">
                		<label for="slot">
                			N. max slot
                		</label>
                	</div>
                	<div class="col">
                		<input type="number" id="slot" name="slot" min="1" max="5" step="1" value="<%= slot %>" />
                	</div>
                </div>
                <div class="row">
                	<div class="col">
                		<label for="offers">
                			N. max offerte
                		</label>
                	</div>
                	<div class="col">
                		<input type="number" id="offers" name="offers" min="1" max="5" step="1" value="<%= offers %>" />
                	</div>
                </div>
                <div class="btn-container">
                    <input type="submit" value="Salva modifiche" />
                </div>
        	</form>
        </div>
	</body>
</html>