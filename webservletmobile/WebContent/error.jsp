<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false" %>
<html lang="en">


<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">  
    
<!--    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/css/style.css">   --> 
	
	<link type="text/css"    href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>

	
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<!-- script src="http://code.jquery.com/jquery-1.10.2.js"></script-->
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  	
  	
  	
  	<link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/style.css"> 
	<script type="text/javascript" src="js/autosearch.js"></script>
	
  
</head>

<body>
	??<%= request.getAttribute("excep") %>:::
</body>

<style>
#advanced-search-right-sect .panel-body li:last-child  {
			  width: 76px;
}
#advanced-search-right-sect .panel-body {
  padding-top: 36px;
  padding-bottom:0px;
  padding-right:0px;
  padding-left:0px;
}

#advanced-search-right-sect .panel-body li {
  float: left;
  height: 206px;
  position: relative;
  text-align: center;
  width: 143px;
  list-style: none !important;
  margin: 0px 0px 10px 0px;
  padding-top: 0;
}

#advanced-search-right-sect .panel strong {
  clear: both;
  display: block;
  font: 700 14px "Arimo", Arial, sans-serif;
  margin: 0px auto 0;
  padding: 28px 5px;
  background: rgba(246, 246, 246, .4);
  text-decoration: none;
  color: #777;
  position: absolute;
  bottom: 0px;
  width: 100%;
}

#advanced-search-right-sect .panel li span {
  position: absolute;
  top: 46%;
  transform: translateY(-50%);
  -webkit-transform: translateY(-50%);
  -moz-transform: translateY(-50%);
  text-align: center;
  left: 0;
  display: block;
  padding: 0 15px;
  width: 100%;
}

#advanced-search-right-sect .panel a:hover strong {
	background-color: #b23f33;
	border-color: #337ab7;
    color: #fff;
}


</style>

</html>