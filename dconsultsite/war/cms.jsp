<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.dconsult.model.SalesLeads" %>
<%@page import="com.dconsult.jdo.PMF"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

            
<%@page import="com.dconsult.model.CMSEntry"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>DConsult</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="language" content="bg" />
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
    
    <link rel="stylesheet" href="jwysiwyg/jquery.wysiwyg.css" type="text/css" />
    <script type="text/javascript" src="jwysiwyg/jquery.wysiwyg.js"></script>
            
		    
          
	<script>
	$(document).ready(function() {
		 $('#body').wysiwyg();
	  });
			
	</script>
	</head>
  
	<body>
	<div id="main"><div id="main2">
    <div id="corner1">&nbsp;</div>
    <div id="corner2">&nbsp;</div>
    <div id="header">
    <h1><a href="/index.jsp">D<span>consult</span>ing</a></h1>
    <div id="menu">
    <ul>
	    <li><a href="/index.jsp">начало</a></li>
	    <li><a href="/services.jsp">услуги</a></li>
	    <li><a href="/products.jsp">продукти</a></li>
	    <li><a href="/about.jsp">за нас</a></li>
    </ul>
    </div>
    </div>  
    <div id="about_container">  
		<div id="left_cms">
		<%
	    PersistenceManager pm = PMF.get().getPersistenceManager();
	    String query = "select from " + CMSEntry.class.getName() + " order by displayOrder";
	    List<CMSEntry> entries = (List<CMSEntry>) pm.newQuery(query).execute();
	    for (CMSEntry s : entries) {
		%>
		<p style="text-align:left">
				 <%=s.getDisplayOrder() %>.&nbsp;<a href="/cms?key=<%=KeyFactory.keyToString(s.getKey())%>&order=up">+</a>&nbsp; 
				 <a href="/cms?key=<%=KeyFactory.keyToString(s.getKey())%>&order=down">-</a>&nbsp; 
				 <a href="/cms?key=<%=KeyFactory.keyToString(s.getKey())%>&delete=true"><img  src="images/button-cancel.png"/></a>&nbsp; 
				 <a href="/cms?key=<%=KeyFactory.keyToString(s.getKey())%>"><%=s.getTitle()%></a>
		</p> 
		<%
    }
    pm.close();
%>		
		</div>
		<div id="right_wysiwyg">
			<form method="post" action="/cms">
				
				<% if (request.getParameter("key") != null && request.getParameter("delete") == null)	{
				   	pm = PMF.get().getPersistenceManager();
				   	CMSEntry entry = pm.getObjectById(CMSEntry.class, KeyFactory.stringToKey(request.getParameter("key")));
				%>
				<input type="hidden" value="<%=request.getParameter("key")%>" name="key"/>
				<label for="firstname">Title: </label>
				<input name="title" id="title" type="text" value="<%=entry.getTitle()%>"/>
				
				<textarea name="body" id="body" rows="18" cols="70"><%=entry.getBody().getValue()%></textarea>
				<div><button type="submit">Save Changes</button> </div>
				<%}
				else {%>
				
				<label for="firstname">Title: </label>
				<input name="title" id="title" type="text"/>
				
				<textarea name="body" id="body" rows="18" cols="70"></textarea>
				<div><button type="submit">Save Changes</button> </div>
				<%} %>
				<div><a href="cms.jsp">New Entry</a></div>
				
			</form>
		</div>
	</div>
<jsp:include page="/footer.jsp" flush="false" />
            