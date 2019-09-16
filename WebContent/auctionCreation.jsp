<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/form.css">
        <link rel="stylesheet" type="text/css" href="css/navBar.css" />
        <script type="text/javascript" src="slideMenu.js"></script>
        <title>Auction Online</title>
    </head>
    <body>
		<%@include file="templates/navbar.jsp" %>
        <div class="item-form">
            <form action="servlet/ItemRegister" method="post">
                <div class="row">
                    <div class="col">
                    	<label for="titolo">
                        	Titolo
                       	</label>
                    </div>
                    <div class="col">
                        <input type="text" id="titolo" name="auctionTitle"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                    	<label for="descrip">
                        	Descrizione
                        </label>
                    </div>
                    <div class="col">
                        <textarea class="txtarea" id="descrip" name="auctionDescription"></textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                    	<label for="cat">
                        	Categorie
                        </label>
                    </div>
                    <div class="col">
                        <input type="text" id="cat" name="userEmail"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                    	<label for="mod">
                        	Modalità asta
                    	</label>
                    </div>
                    <div class="col">
                        <select id="mod">
                            <option value="firstSealed">Busta chiusa</option>
                            <option value="secondSealed">Busta chiusa al "secondo prezzo"</option>
                            <option value="englishAuction">Asta "inglese"</option>
                            <option value="dutchAuction">Asta "olandese"</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <label for="pic" class="pic-label">Seleziona immagine</label>
                    <input type="file" id="pic" name="pic" accept="image/*" style="display: none" />
                </div>
                <div class="btn-container">
                    <input type="submit" value="Registra"/>
                </div>
            </form>
        </div>
    </body>
</html>
