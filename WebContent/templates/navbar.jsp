<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	String name = (String) session.getAttribute("name");
%>
<nav id="slide-menu" class="slide-menu">
	<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
	<a href="index.jsp">Home</a>
	<a href="#">Sfoglia aste</a>	
<%
    if(name != null) {
%>	
	<a href="auctionCreation.jsp">Apri nuova asta</a>
	<a href="#">Gestisci profilo</a>
	<a href="logout">Logout</a>
<%
    }
%>
</nav>
<div class="topnav">
            <div class="menu-trigger" onclick="openNav()">
                <div class="bar"></div>
                <div class="bar"></div>
                <div class="bar"></div>
            </div>
            <div class="login">
          <%
           	if(name == null || name.equals("")) {
          %>
            	<a href="login.jsp">Login</a>
          <%
            }
           	else {
          %>
          		<a href="login.jsp">Ciao ${name}!</a>
          <%
         	}
          %>
            </div>
</div>