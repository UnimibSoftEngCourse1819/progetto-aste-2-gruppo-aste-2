<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%
	String user = null;
	if(session.getAttribute("email") == null)
		response.sendRedirect("login.jsp");
	else
		user = (String) session.getAttribute("email");
%>    
<div class="topnav">
            <div class="menu-trigger">
                <div class="bar"></div>
                <div class="bar"></div>
                <div class="bar"></div>
            </div>
<%
	if(user == null) {
%>
            <div class="login">Login</div>
<%
	}
	else {
%>
			<div class="login">${user}</div>
<%
	}
%>
</div>