<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registrazione</title>
		<link rel="stylesheet" type="text/css" href="css/form.css" />
		<link rel="stylesheet" type="text/css" href="css/navBar.css" />
		<script type="text/javascript" src="javascript/slideMenu.js"></script>
	</head>
	<body>
		<%@include file="templates/navbar.jsp" %>
        <div class="item-form">
        	<form action="register" method="post">
        		<div class="row">
                    <div class="col">
                    	<label for="name">
                        	Nome
                       	</label>
                    </div>
                    <div class="col">
                        <input type="text" id="name" name="name"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                    	<label for="surname">
                        	Cognome
                       	</label>
                    </div>
                    <div class="col">
                        <input type="text" id="surname" name="surname"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                    	<label for="date">
                        	Data di nascita
                       	</label>
                    </div>
                    <div class="col">
                        <input type="date" id="date" name="date"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                    	<label for="city">
                        	Città
                       	</label>
                    </div>
                    <div class="col">
                        <input type="text" id="city" name="city"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                    	<label for="street">
                        	Via
                       	</label>
                    </div>
                    <div class="col">
                        <input type="text" id="street" name="street"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                    	<label for="email">
                        	Email
                       	</label>
                    </div>
                    <div class="col">
                        <input type="email" id="email" name="email"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                    	<label for="phone">
                        	Cellulare
                       	</label>
                    </div>
                    <div class="col">
                        <input type="text" id="phone" name="phone"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                    	<label for="password">
                        	Password
                       	</label>
                    </div>
                    <div class="col">
                        <input type="password" id="password" name="password"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                    	<label for="passwordRep">
                        	Ripeti password
                       	</label>
                    </div>
                    <div class="col">
                        <input type="password" id="passwordRep" name="passwordRep"/>
                    </div>
                </div>
                <div class="btn-container">
                    <input type="submit" value="Registrati"/>
                </div>
        	</form>
        </div>
	</body>
</html>