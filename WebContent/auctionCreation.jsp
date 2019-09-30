<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/form.css">
        <link rel="stylesheet" type="text/css" href="css/navBar.css" />
        <script type="text/javascript" src="javascript/slideMenu.js"></script>
        <script type="text/javascript" src="javascript/append.js"></script>
        <script type="text/javascript" src="javascript/sendData.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <title>Auction Online</title>
    </head>
    <body>
		<%@include file="templates/navbar.jsp" %>
        <div class="item-form">
            <form action="auctionCreation" method="post">
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
                    	<label for="mod">
                        	Modalità asta
                    	</label>
                    </div>
                    <div class="col">
                        <select id="mod" name="mod">
                            <option value="firstSealed">Busta chiusa</option>
                            <option value="secondSealed">Busta chiusa al "secondo prezzo"</option>
                            <option value="englishAuction">Asta "inglese"</option>
                            <option value="dutchAuction">Asta "olandese"</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                	<div class="col">
                		<label for="date">
                			Termine
                		</label>
                	</div>
                	<div class="col">
                		<input type="date" id="date" name="date" />
                	</div>
                </div>
                <div class="row">
                	<div class="col">
                		<label for="time">
                			Ora
                		</label>
                	</div>
                	<div class="col">
                		<input type="time" id="time" name="time" />
                	</div>
                </div>
                <div class="row">
                    <div class="col">
                    	<label for="cat">
                        	Categorie
                        </label>
                    </div>
                    <div class="col" id="txt-btn">
                        <input type="text" id="cat" name="category" />
                    	<button type="button" onclick="append(document.getElementById('cat').value)">Aggiungi</button>
                    </div>
                </div>
                <div class="row">
                	<div class="col"></div>
                	<div class="col" id="category-container">
                	</div>
                </div>
                <div class="row">
                	<div class="col">Risarcimento</div>
                	<div class="col">
                		<label style="font-weight:normal;"><input type="radio" name="refund" value="noPenalty" />Risarcimento completo</label>
                		<label style="font-weight:normal;"><input type="radio" name="refund" value="penalty" checked="checked">Nessun risarcimento</label>
                	</div>
                </div>
                <div class="row">
                    <label for="pic" class="pic-label">Seleziona immagine</label>
                    <input type="file" id="pic" name="pic" accept="image/*" style="display: none" />
                </div>
                <div class="btn-container">
                    <button type="button" onclick="send()" id="register-button">Registra</button>
                </div>
            </form>
        </div>
    </body>
</html>
