<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<link rel="stylesheet" type="text/css" href="css/form.css" />
		<link rel="stylesheet" type="text/css" href="css/navBar.css" />
	</head>
	<body>
		<%@include file="templates/navbar.jsp" %>
        <div class="item-form">
        	<form action="login" method="post">
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
                    	<label for="password">
                        	Password
                       	</label>
                    </div>
                    <div class="col">
                        <input type="password" id="password" name="password"/>
                    </div>
                </div>
                <div class="btn-container">
                    <input type="submit" value="Login"/>
                </div>
        	</form>
        </div>
	</body>
</html>