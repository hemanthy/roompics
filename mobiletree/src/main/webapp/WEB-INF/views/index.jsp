<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">  
    
   <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <script src="/resources/script/jquery.min.js"></script>
    <script src="/resources/script/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/resources/css/style.css">   
	
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<!-- script src="http://code.jquery.com/jquery-1.10.2.js"></script-->
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  	<script type="text/javascript" src="/resources/script/autosearch.js"></script>
  
</head>

<body>
<div class="index-page">
    <div class="container">
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="col-sm-4">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">
                            <img src="http://placehold.it/150x50&amp;text=Logo" alt="">
                        </a>
                    </div>
                </div>
                <div class="col-sm-4" id="mobile-search">
              	 	<input id="project" placeholder="Search">
                </div>
                <div class="col-sm-4">
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li>
                                <a href="#">About</a>
                            </li>
                            <li>
                                <a href="#">Services</a>
                            </li>
                            <li>
                                <a href="#">Contact</a>
                            </li>
                        </ul>
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
                <li class="active">
                    <a href="/index.html">Home</a>
                </li>
                <li>
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
    <div class="container">
        <div class="row">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                    </div>
                </div>
            </nav>


        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-sm-4">

                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">Latest Mobile</h3>
                    </div>
                    <div class="panel-body-mb">
                        <ul class="list-group">
                        <c:forEach items="${latestMobilesList}" var="mobile"   varStatus="status" begin="0" end="5">
	                        <li class="list-group-item">
	                         	  <c:set var="title" value="${fn:toLowerCase(mobile.title)}" />
                                  <a href="/mobiles/${fn:replace(title,' ', '-')}">${mobile.title}</a>
	                        </li>
						</c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="jumbotron">
                    <p>
                        Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, vel scelerisque nisl et.
                    </p>
                </div>

            </div>
            <div class="col-sm-4">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">Top Brand Mobiles</h3>
                    </div>
                    <div class="panel-body-mb">
                        <ul class="list-group">
                            <c:forEach items="${topBrandMobilesList}" var="mobile"   varStatus="status" begin="0" end="5">
		                        <li class="list-group-item">
									<c:set var="title" value="${fn:toLowerCase(mobile.title)}" />
                            	    <a href="/mobiles/${fn:replace(title,' ', '-')}">${mobile.title}</a>
		                        </li>
						</c:forEach>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </div>

	<div class="container">
        <div class="row">
        <div id="advanced-search-right-sect">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">Upcoming Phones</h3>
							</div>
							<div class="panel-body">
								<ul>
								  <c:forEach items="${upcomingMobilesList}" var="mobile"  varStatus="status" begin="0" end="6">
										<li>
				                                <c:set var="title" value="${fn:toLowerCase(mobile.title)}" />
                                   				<a href="/mobiles/${fn:replace(title,' ', '-')}">
				                                <img height="146" width="100" src="/resources/images${mobile.image_path1}" title="">
					                                <strong>
					                               		<span>${mobile.title}</span>
					                                </strong>
				                                </a>
				                         </li>
									</c:forEach>
									<li>
										<p class="pull-left gutter-bottom">
											<a class="btn btn-primary btn-sm" href="/mobiles/upcoming-phones">More Info</a>
										</p>
									</li>
								</ul>
							</div>
						</div>
				</div>
        </div>
    </div>
    
    <div class="container">
        <div class="row">
        <div id="advanced-search-right-sect">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">Mobile Brands</h3>
							</div>
							<div class="panel-body">
								<ul>
									<c:forEach items="${companyList}" var="company"  varStatus="status" begin="0" end="6">
										<li>
				                                <c:set var="title" value="${fn:toLowerCase(company.brandName)}" />
                                   				<a href="/mobiles/${fn:replace(title,' ', '-')}-phones">
				                                <img height="146" width="100" src="/resources/images${mobile.image_path1}" title="">
					                                <strong>
					                               		<span>${company.brandName}</span>
					                                </strong>
				                                </a>
				                         </li>
									</c:forEach>
									<li>
										<p class="pull-left gutter-bottom">
											<a class="btn btn-primary btn-sm" href="/mobiles/all-brands">More Info</a>
										</p>
									</li>
								</ul>
							</div>
						</div>
				</div>
        </div>
    </div>
</div>
<!-- <div class="container">
        <div class="row">
<footer>
        <div class="row">
          <div class="col-lg-12">

            <ul class="list-unstyled">
              <li class="pull-right"><a href="#top">Back to top</a></li>
              <li><a href="http://news.bootswatch.com" onclick="pageTracker._link(this.href); return false;">Blog</a></li>
              <li><a href="http://feeds.feedburner.com/bootswatch">RSS</a></li>
              <li><a href="https://twitter.com/bootswatch">Twitter</a></li>
              <li><a href="https://github.com/thomaspark/bootswatch/">GitHub</a></li>
              <li><a href="../help/#api">API</a></li>
              <li><a href="../help/#support">Support</a></li>
            </ul>
            <p>Made by <a href="http://thomaspark.co" rel="nofollow">Thomas Park</a>. Contact him at <a href="mailto:thomas@bootswatch.com">thomas@bootswatch.com</a>.</p>
            <p>Code released under the <a href="https://github.com/thomaspark/bootswatch/blob/gh-pages/LICENSE">MIT License</a>.</p>
            <p>Based on <a href="http://getbootstrap.com" rel="nofollow">Bootstrap</a>. Icons from <a href="http://fortawesome.github.io/Font-Awesome/" rel="nofollow">Font Awesome</a>. Web fonts from <a href="http://www.google.com/webfonts" rel="nofollow">Google</a>.</p>

          </div>
        </div>

      </footer>
      </div>
      </div> -->
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