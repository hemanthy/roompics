<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>
<html lang="en">


<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/autosearch.js"></script>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css"> 
    
      <!-- Auto Complete Start -->
     <link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
      <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
      <script src="/js/bootstrap.js"></script>
     <!-- Auto Complete End -->
  
  
</head>

<body>
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
                        <a class="navbar-brand" href="#">
                            <img src="http://placehold.it/150x50&amp;text=Logo" alt="">
                        </a>
                    </div>
                </div>
                <div class="col-sm-4" id="mobile-search">
              	 	<input id="project" placeholder="Search">
                </div>
                <div class="col-sm-4">
                    <!-- <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
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
                    </div> -->
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
                    <a href="/">Home</a>
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
   <!--  <div class="container">
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
                                  <a  title="${mobile.title}" href="/mobiles/${fn:replace(title,' ', '-')}">${mobile.title}</a>
	                        </li>
						</c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">

					<div id="advanced-search-right-sect">
						<div class="panel-body-best-mobile">
							<div class="panel panel-info">
								<div class="panel-heading">
									<h3 class="panel-title">The Best Mobile of The November Month</h3>
								</div>
								<div class="panel-body">
									<div class="col-sm-6">
										<ul>
											<li>
												<a title="Samsung Galaxy S6 edge" alt="Samsung Galaxy S6 edge" href="/mobiles/samsung-galaxy-s6-edge+">
													<img  alt ="Samsung Galaxy S6 edge+" title="Samsung Galaxy S6 edge+" height="146" width="100" src="/brand_images/samsung-galaxy-s6-edge+.jpg">
					                                <strong>
					                               		<span>Samsung Galaxy S6 edge</span>
					                                </strong>
				                                </a>
											</li>
										</ul>
									</div>
									<div class="col-sm-6">
										<p>The Samsung Galaxy S6 edge+ features a 5.7- inch Super
											AMOLED capacitive touchscreen display with a resolution of
											1440 x 2560 pixels and has 4.0 GB of RAM.</p>
											<a title="Samsung Galaxy S6 edge" alt="Samsung Galaxy S6 edge" class="btn btn-primary btn-sm" href="/mobiles/samsung-galaxy-s6-edge+">More Info</a>
										</p>
									</div>
								</div>
							</div>
						</div>
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
                            	    <a title="${mobile.title}" href="/mobiles/${fn:replace(title,' ', '-')}">${mobile.title}</a>
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
				                                <img height="146" width="100" src="/brand_images/${mobile.image_path1}" title="${mobile.title}" alt="${mobile.title}">
					                                <strong>
					                               		<span>${mobile.title}</span>
					                                </strong>
				                                </a>
				                         </li>
									</c:forEach>
									<li>
										<div id="more-info" class="pull-left gutter-bottom">
											<a class="btn btn-primary btn-sm" href="/mobiles/upcoming-phones">More Info</a>
										</div>
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
									<c:forEach items="${companyList}" var="company"  varStatus="status" begin="0" end="4">
										<li style="width:200px;height:176px;">
				                                <c:set var="title" value="${fn:toLowerCase(company.brandName)}" />
                                   				<a href="/mobiles/${fn:replace(title,' ', '-')}-phones">
				                               <%--  <img height="146" width="100" src="/images/${company.brandName}/${mobile.image_path1}" title=""> --%>
				                             <img height="100" width="146" src="/brand_images/${title}.jpg" title="${company.brandName} Phones" alt="${company.brandName}">
					                                <strong>
					                               		<span>${company.brandName}</span>
					                                </strong>
				                                </a>
				                         </li>
									</c:forEach>
									<li>
										<div id="more-info-mobile-brands" class="pull-left gutter-bottom">
											<a class="btn btn-primary btn-sm" href="/mobiles/all-brands">More Info</a>
										</div>
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

#advanced-search-right-sect  .panel-body-best-mobile li:last-child  {
	  width: 100%; 
}

#advanced-search-right-sect  .panel-body-best-mobile p {
  font: 13px/15px Arial;
  color: #333;
  line-height: 1.68em;
  text-align: justify;
}

#more-info-mobile-brands {
	margin-top: 146px; 
	margin-left: 26px;
}

#more-info {
  margin-top: 176px;
  margin-left: 26px;
}

</style>

</html>