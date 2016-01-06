<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>
<html lang="en-US">

<head>
    <title>All Mobile Phones Brands | AllGadgetsRoundup.com</title>
	<meta name="description" content="List all mobile phones brands" />
    <meta name="keywords" content=" -" />
    
    <meta name="robots" content="index, follow" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link rel="canonical" href="http://allgadgetsroundup.com/${requestURI}" />
  	
  	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    
	<script type="text/javascript" src="/js/autosearch.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/style.css"> 
    
      <!-- Auto Complete Start -->
     <link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
      <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
      <script src="/js/bootstrap.js"></script>
     <!-- Auto Complete End -->
	
  
</head>

<body>

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-71447534-1', 'auto');
  ga('send', 'pageview');

</script>

<div class="index-page">
    <div class="container">
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="col-sm-4">
                    <div class="navbar-header">
                        <!-- <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button> -->
                        <a class="navbar-brand" href="/"> <b> <font
									face="Trebuchet MS" color="#808080"
									style="color: #ffffff; text-decoration: none;">AllGadgetsRoundup.com</font>
							</b>
						</a>
                    </div>
                </div>
                <div class="col-sm-4" id="mobile-search">
              	 	<input id="project" placeholder="Search">
                </div>
                <div class="col-sm-4">
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <!-- <ul class="nav navbar-nav">
                            <li>
                                <a href="#">About</a>
                            </li>
                            <li>
                                <a href="#">Services</a>
                            </li>
                            <li>
                                <a href="#">Contact</a>
                            </li>
                        </ul> -->
                    </div>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>
    </div>
   <div class="container">
		<div class="block-div">&nbsp;</div>
  </div>
    <div class="container">
        <div id="nav-header-id" class="row">
            <ul class="nav  nav-justified nav-pills">
                <li>
                    <a href="/">Home</a>
                </li>
                <li  class="active">
                    <a href="/mobiles/all-brands">Mobiles</a>
                </li>
                <li>
                    <a href="/mobiles/compare">Compare Mobiles</a>
                </li>
                <li>
                    <a href="/mobiles/advance-search">Advance Search</a>
                </li>
            </ul>
        </div>
    </div>
    <hr>
    <!-- <div class="container">
        <div class="row">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                    </div>
                </div>
            </nav>
        </div>
    </div> -->
    
    <div class="container">
        <div class="row">
        <div id="advanced-search-right-sect">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">Brand Names</h3>
							</div>
							<div class="panel-body">
								<ul>
								  <c:forEach items="${companyList}" var="company">
										<li>
				                                <c:set var="title" value="${fn:toLowerCase(company.brandName)}" />
                                   				<a href="/mobiles/${fn:replace(title,' ', '-')}-phones">
				                               <img height="100" width="146" src="/brand_images/${title}.jpg" title="${company.brandName} Phones" alt="${company.brandName}">
					                                <strong>
					                               		<span>${company.brandName}</span>
					                                </strong>
				                                </a>
				                         </li>
									</c:forEach>
								</ul>
							</div>
						</div>
				</div>
        </div>
    </div>
</div>
</body>

<style>
#advanced-search-right-sect .panel-body {
  padding-top: 36px;
  padding-bottom:0px;
  padding-right:0px;
  padding-left:0px;
}

#advanced-search-right-sect .panel-body li {
  float: left;
  height: 173px;
  position: relative;
  text-align: center;
  width: 179px;							/*Increased width*/
  list-style: none !important;
  margin: 0px 0px 27px 0px;				/*Increased margin bottom*/
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

#advanced-search-right-sect .panel-body li:hover {
	border:1px #337ab7 solid;
}

</style>

</html>