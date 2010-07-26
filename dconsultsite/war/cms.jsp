<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

            <html xmlns="http://www.w3.org/1999/xhtml">
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
		 $('#wysiwyg').wysiwyg();
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
		<div>
		  <textarea name="wysiwyg" id="wysiwyg" rows="5" cols="47"></textarea>
		</div>
		<div><button type="submit">Save Changes</button> </div>
	
<jsp:include page="/footer.jsp" flush="false" />
            