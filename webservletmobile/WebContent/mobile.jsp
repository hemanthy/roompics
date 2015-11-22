<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<html lang="en">

<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
  	
  	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    
	<script type="text/javascript" src="/js/autosearch.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/style.css">  
  	 
    <style>
        .product-img {
            float: left;
            text-align: center;
            margin: 8px 15px 0 -15px;
        }
        .product-img .main-img {
            line-height: 360px;
            min-height: 365px;
        }
        #prod-gal {
            display: inline-block;
            margin-top: 10px;
            margin-bottom: 40px;
        }
        #prod-gal a {
            float: left;
            display: inline-block;
            line-height: 40px;
            min-height: 46px;
            width: 46px;
            vertical-align: top;
            margin-right: 5px;
            margin-bottom: 5px;
            border: 1px solid #ccc;
        }
        .feature-key {
            font-weight: bold;
        }
        .key-feature {
            float: left;
            margin: 11px 0;
            width: 100%;
        }
        
        #panel-body-id .panel-body {
            padding-top: 10px;
            padding-bottom: 10px;
        }
        .panel-info {
            border-color: #337ab7;
        }
        .panel-body li {
            float: left;
            height: 206px;
            position: relative;
            text-align: center;
            width: 143px;
            list-style: none !important;
            margin: 0px 0px 10px 0px;
            padding-top: 0;
        }
        .panel strong {
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
        .panel li span {
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
        .panel a:hover strong {
            background-color: #b23f33;
            border-color: #337ab7;
            color: #fff;
        }
        
    </style>
</head>


<body>
<c:if test="${mobile.ram gt 0}">
  <c:set var="ram" value="${mobile.ram} GB" />
</c:if>
<c:if test="${mobile.ramSizeInMB gt 0}">
  <c:set var="ram" value="${mobile.ramSizeInMB} MB" />
</c:if>
<c:if test="${mobile.internal_Memory gt 0}">
  <c:set var="internal_Memory" value="${mobile.internal_Memory} GB" />
</c:if>
<c:if test="${mobile.internal_Memory_In_MB gt 0}">
  <c:set var="internal_Memory" value="${mobile.internal_Memory_In_MB} MB" />
</c:if>
	
    <div class="container">
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="col-sm-4">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar">df</span>
                            <span class="icon-bar">dfsa</span>
                            <span class="icon-bar">dfa</span>
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
				<div id="nav-header-id"  class="row">
					 
					<ul class="nav  nav-justified nav-pills">
							<li>
								<a href="/">Home</a>
							</li>
							<li class="active">
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
                    <div class="navbar-header">Ad</div>
                </div>
            </nav>
            <!-- <div class="alert alert-dismissible alert-warning">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <h4>Warning!</h4>
                <p>Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna,
                    <a href="#" class="alert-link">vel scelerisque nisl consectetur et</a>.
                </p>
            </div> -->
        </div>
    </div>
    <div class="container">
        <div class="row">
            <ul class="breadcrumb">
            	<li>
                    <a href="/">Home</a>
                </li>
                <li>
                    <a href="/mobiles/all-brands">Mobiles</a>
                </li>
                <li><c:set var="brandName" value="${mobile.brandName}" />
                <c:set var="brandNameLowerCase" value="${fn:toLowerCase(brandName)}" />
                    <a href="/mobiles/${fn:replace(brandNameLowerCase,' ', '-')}-phones">${mobile.brandName}</a>
                </li>
                <li class="active">${mobile.title}</li>
            </ul>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-sm-4">
                <div id="gallery" class="product-img">
                    <div class="main-img">
                        <div class="content">
                            <img src="/images${mobile.image_path1}" class="image_1" alt="" />
                            <!--
                            	 <img src="http://biqcdn.com.s3.amazonaws.com/m/img/p/p96617-pi201295-ci0-ui166762-s360x360.jpg" class="image_2" style="display:none" alt="" />
                            	<img src="http://biqcdn.com.s3.amazonaws.com/m/img/p/p96617-pi201296-ci0-ui166763-s360x360.jpg" class="image_3" style="display:none" alt="" />
                            	<img src="http://biqcdn.com.s3.amazonaws.com/m/img/p/p43596-pi330325-ci0-ui295792-s360x360.jpg" class="image_4" style="display:none" alt="" />
                            	<img src="http://biqcdn.com.s3.amazonaws.com/m/img/p/p43596-pi330326-ci0-ui295793-s360x360.jpg" class="image_5" style="display:none" alt="" />
                       	 	--> 
                        </div>
                    </div>
                    <div class="clear">&nbsp</div>
                    <!-- <div id="prod-gal" class="clearfix">
                        <div class="thumbnails">
                            <span class="thumb">
								<a href="#" rel="1" class="img-thumb">
									<img src="http://biqcdn.com.s3.amazonaws.com/m/img/p/p96617-pi201293-ci0-ui166760-s40x40.jpg" id="thumb_1" alt="" />
								</a>
							</span>
                            <span class="thumb">
								<a href="#" rel="2" class="img-thumb">
									<img src="http://biqcdn.com.s3.amazonaws.com/m/img/p/p96617-pi201295-ci0-ui166762-s40x40.jpg" id="thumb_2" alt="" />
								</a>
							</span>
                            <span class="thumb">
								<a href="#" rel="3" class="img-thumb">
									<img src="http://biqcdn.com.s3.amazonaws.com/m/img/p/p96617-pi201296-ci0-ui166763-s40x40.jpg" id="thumb_3" alt="" />
								</a>
							</span>
                            <span class="thumb">
								<a href="#" rel="4" class="img-thumb">
									<img src="http://biqcdn.com.s3.amazonaws.com/m/img/p/p43596-pi330325-ci0-ui295792-s40x40.jpg" id="thumb_4" alt="" />
								</a>
							</span>
                            <span class="thumb">
								<a href="#" rel="5" class="img-thumb">
									<img src="http://biqcdn.com.s3.amazonaws.com/m/img/p/p43596-pi330326-ci0-ui295793-s40x40.jpg" id="thumb_5" alt="" />
								</a>
							</span>
                        </div>
                    </div> -->
                </div>
            </div>
            <div class="col-sm-8">
                <div class="product-key-features">
                    <h1 class="key-features-heading">${mobile.title} Key Features</h1>
                    <div class="col-sm-6">
                    <c:if test="${!empty mobile.os}">
                        <div class="key-feature">
                            <span class="feature-key">OS</span>
                            <span class="feature-value" title="Android v4.2 (Jelly Bean)">: ${mobile.os}</span>
                        </div>
                      </c:if>
                        <div class="key-feature">
                            <span class="feature-key">Memory</span>
                            <span class="feature-value" title="${ram},  ${internal_Memory} ROM">
	                          	   ${ram},  ${internal_Memory} ROM</span>
                        </div>
                        <c:if test="${!empty mobile.processor}">
	                        <div class="key-feature">
	                            <span class="feature-key">CPU</span>
	                            <span class="feature-value" title="${mobile.processor}">:  ${mobile.processor}</span>
	                       </div>
                        </c:if>
                        <div class="key-feature">
                            <span class="feature-key">Screen </span>
                            <span class="feature-value" title="${mobile.screenSize} inches Screen">: ${mobile.screenSize} inches Screen</span>
                        </div>
                    </div>
                    <div class="col-sm-6">
	                    <c:if test="${mobile.primary_Camera > 0}">
	                        <div class="key-feature">
	                            <span class="feature-key">Camera</span>
	                            <span class="feature-value" title="${mobile.primary_Camera} Megapixels">: ${mobile.primary_Camera} Megapixels</span>
	                        </div>
	                      </c:if>
                        <c:if test="${mobile.secondary_Camera > 0}">
	                        <div class="key-feature">
	                            <span class="feature-key">Secondary Camera</span>
	                            <span class="feature-value" title="${mobile.secondary_Camera} inches Screen">: ${mobile.secondary_Camera} Megapixels</span>
	                        </div>
                        </c:if>
                          <c:if test="${!empty internal_Memory}">
                          	 <div class="key-feature">
                            <span class="feature-key">Internal Storage</span>
                            <span class="feature-value" title="${internal_Memory}">: ${internal_Memory}</span>
                        </div>
                          </c:if>
                        <c:if test="${!empty mobile.card_Slot}">
	                        <div class="key-feature">
	                            <span class="feature-key">Memory Slot</span>
	                            <span class="feature-value" title="${mobile.card_Slot}">: ${mobile.card_Slot}</span>
	                        </div>
                        </c:if>
                    </div>
					<hr>
					<hr>
                    <div class="row">
							<table id="full-specs-sub-list-table" width="100%">
								<tbody>
									<tr>
										<th colspan="2">
											<h4 class="full-specs-sub-list">Summary of ${mobile.title}</h4>
										</th>
									</tr>
								<tr>
									<td> 
											<p>
												The ${mobile.title} features a ${mobile.screenSize}-inch ${mobile.screenType}
												display with a resolution of ${mobile.resolution}
												<c:if test="${!empty processor}">
												 and is powered by a ${mobile.processor} ${mobile.chipset} processor 
												</c:if>
												<c:if test="${!empty ram}">
													and has ${ram} of RAM.
												</c:if>
													<c:if test="${!empty internal_Memory && !empty mobile.card_Slot}">
														 It comes with ${internal_Memory} of inbuilt storage, expandable ${mobile.card_Slot}
													</c:if>
													<c:if test="${!empty internal_Memory  && empty mobile.card_Slot}">
														 It comes with ${internal_Memory} of inbuilt storage
													</c:if>
													<c:if test="${empty internal_Memory  && !empty mobile.card_Slot}">
														 It can expandable upto  ${mobile.card_Slot} storage
													</c:if>
														  and has a
												 	<c:if test="${mobile.primary_Camera gt 0.1 && mobile.secondary_Camera gt 0.1}">
												 		  ${mobile.primary_Camera}-MP primary camera and ${mobile.secondary_Camera}-MP front camera.  
												 	</c:if>
												 	<c:if test="${mobile.primary_Camera gt 0.1 && mobile.secondary_Camera eq 0.0}">
												 		 ${mobile.primary_Camera}-MP Primary camera.  
												 	</c:if>
													It comes with Android OS and support a ${mobile.batteryCapactiy}mAh battery ${mobile.batteryType}
													capacity with <c:if test="${!empty mobile.band_3G}">3G</c:if>  <c:if test="${!empty mobile.band_4G}">and 4G.</c:if>
									</p>										
									</td>
								</tr>
							</tbody>
							</table>
                    </div>
                </div>


            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-sm-4">
                <div style="border:1px red solid; height: 250px">
                    Adv
                </div>
                <c:if test="${!empty sameBrandMobiles}">
	                <hr>
	                <div id="border-color-div" class="panel panel-info">
	                    <div class="panel-heading">
	                        <h3 class="panel-title">${sameBrandMobiles[0].brandName} Mobiles</h3>
	                    </div>
						<div id="mobile-left-nav-sect">
							<div class="panel-body">
								<div class="panel-body-id">
									<ul>
										<c:forEach items="${sameBrandMobiles}" var="mobile"
											varStatus="status" begin="0" end="3">
											<li>
											<c:set var="title" value="${fn:toLowerCase(mobile.title)}" />
											 <a
												href="/mobiles/${fn:replace(title,' ', '-')}">
												<img height="146" width="100" src="/images${mobile.image_path1}" title="${mobile.title}">
													<strong> <span>${mobile.title}</span>
												</strong>
											</a></li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${!empty relatedMobiles}">
	                <hr>
	                <div id="border-color-div" class="panel panel-info">
		                    <div class="panel-heading">
		                        <h3 class="panel-title">Related Mobiles</h3>
		                    </div>
		                    <div id="mobile-left-nav-sect">
			                    <div class="panel-body">
			                        <ul>
					                        <c:forEach items="${relatedMobiles}" var="mobile"  varStatus="status" begin="0" end="3">
						                         <li>
						                              <c:set var="title" value="${fn:toLowerCase(mobile.title)}" />
			                                  		   <a href="/mobiles/${fn:replace(title,' ', '-')}">
						                               	<img height="146" width="100" src="/images${mobile.image_path1}" title="${mobile.title}">
							                                <strong>
							                               		<span>${mobile.title}</span>
							                                </strong>
						                                </a>
						                            </li>
					                        </c:forEach>
			                        </ul>
			                    </div>
		                    </div>
	                </div>
                </c:if>
            </div>
            <div class="col-sm-8">
                <!--div class="panel-heading">
                        <h2 class="panel-title">Full Specifications</h2>
                    </div-->
                <div id="border-color-div" class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">Full Specifications</h3>
                    </div>
                    <div class="panel-body">
                        <table id="full-specs-sub-list-table" width="100%">
                            <tr>
                                <th colspan="2">
                                    <h4 class="full-specs-sub-list">Display</h4>
                                </th>
                            </tr>
                            <tr>
                                <td width="30%">Screen Type</td>
                                <td width="70%">${mobile.screenType}</td>
                            </tr>
                            <tr>
                                <td width="30%">Screen Size</td>
                                <td width="70%">${mobile.screenSize} inches</td>
                            </tr>
                            <tr>
                                <td width="30%">Screen Resolution</td>
                                <td width="70%">${mobile.resolution}</td>
                            </tr>
                              <c:if test="${!empty mobile.ppl}">
		                            <tr>
		                                <td width="30%">PPI</td>
		                                <td width="70%">${mobile.ppl} ppl</td>
		                            </tr>
                            </c:if>
                            <c:if test="${!empty mobile.protection}">
								<tr>
	                                <td width="30%">Protection</td>
	                                <td width="70%">${mobile.protection}</td>
	                            </tr>
							</c:if>
                        </table>
                        <c:if test="${!empty mobile.processor || !empty mobile.chipset}">
	                        <table id="full-specs-sub-list-table" width="100%">
	                            <th colspan="2">
	                                <h4 class="full-specs-sub-list">Processor</h4>
	                            </th>
	                            <tr>
	                                <td width="30%">Processor</td>
	                                <td width="70%">${mobile.processor}</td>
	                            </tr>
	                             <c:if test="${!empty mobile.chipset}">
		                            <tr>
		                                <td width="30%">Processor Chipset</td>
		                                <td width="70%">${mobile.chipset}</td>
		                            </tr>
		                         </c:if>
	                        </table>
                        </c:if>
                        <table id="full-specs-sub-list-table" width="100%">
                            <th colspan="2">
                                <h4 class="full-specs-sub-list">Memory</h4>
                            </th>
                            <tr>
                                <td width="30%">Internal Memory</td>
                                <td width="70%">${internal_Memory}</td>
                            </tr>
                            <tr>
                                <td width="30%">Extendable Memory</td>
                                <td width="70%">${mobile.card_Slot}</td>
                            </tr>
                            <tr>
                                <td width="30%">RAM</td>
                                <td width="70%">${ram}</td>
                            </tr>
                        </table>
                          <c:if test="${mobile.primary_Camera ge 0.3}">
		                        <table id="full-specs-sub-list-table" width="100%">
		                            <th colspan="2">
		                                <h4 class="full-specs-sub-list">Camera Features</h4>
		                            </th>
		                            <tr>
		                                <td width="30%">Camera</td>
		                                <td width="70%">${mobile.primary_Camera} MP</td>
		                            </tr>
		                            <c:if test="${mobile.secondary_Camera ge 0.3}">
			                            <tr>
			                                <td width="30%">Front Camera</td>
			                                <td width="70%">${mobile.secondary_Camera} MP</td>
			                            </tr>
			                        </c:if>
		                            <tr>
		                                <td width="30%">Flash</td>
		                                <td width="70%">
			                                <c:if test="${!empty mobile.flash}">
			                                	<img src="/images/yes.gif">
			                                </c:if>
		                                </td>
		                            </tr>
		                        </table>
                        </c:if>
                        <table id="full-specs-sub-list-table" width="100%">
                            <th colspan="2">
                                <h4 class="full-specs-sub-list">General Features</h4>
                            </th>
                            <tr>
                                <td width="30%">OS</td>
                                <td width="70%">${mobile.os}</td>
                            </tr>
                            <tr>
                                <td width="30%">Version</td>
                                <td width="70%">${mobile.osVersion}</td>
                            </tr>
                            <tr>
                                <td id="no-border" width="30%">Dual Sim</td>
                                <td id="no-border" width="70%">
                                	<c:if test="${!empty mobile.dualSim}">
	                                	<img src="/images/yes.gif">
	                                </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td id="no-border" width="30%">GPS</td>
                                <td id="no-border" width="70%">
									<c:if test="${!empty mobile.gps}">
	                                	<img src="/images/yes.gif">
	                                </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td id="no-border" width="30%">Messaging</td>
                                <td id="no-border" width="70%">${mobile.messaging}</td>
                            </tr>
                            <tr>
                                <td id="no-border" width="30%">USB Connector</td>
                                <td id="no-border" width="70%">
	                                <c:if test="${!empty mobile.usb}">
		                                	<img src="/images/yes.gif">
		                            </c:if>
                                </td>
                            </tr>
							<tr>
                                <td id="no-border" width="30%">Available Colours</td>
                                <td id="no-border" width="70%">${mobile.colors}</td>
                            </tr>
                        </table>
						<table id="full-specs-sub-list-table" width="100%">
                            <th colspan="2">
                                <h4 class="full-specs-sub-list">Carrier Networks</h4>
                            </th>
                           <%--  <tr>
                                <td width="30%">2G</td>
                                <td width="70%">
                               		${mobile.band_2G}
                                </td>
                            </tr> --%>
                            <tr>
                                <td width="30%">3G</td>
                                <td width="70%">
										<c:if test="${!empty mobile.band_3G}">
			                                	<img src="/images/yes.gif">
			                         	</c:if>
			                         	<c:if test="${empty mobile.band_3G}">
			                                	<img src="/images/no.png">
			                         	</c:if>
			                    </td>
                            </tr>
                            <tr>
                                <td width="30%">4G</td>
                                <td width="70%">
									<c:if test="${!empty mobile.band_4G}">
			                                	<img src="/images/yes.gif">
			                         </c:if>
			                         <c:if test="${empty mobile.band_4G}">
			                                	<img src="/images/no.png">
			                         </c:if>
								</td>
                            </tr>
                        </table>
						<table id="full-specs-sub-list-table" width="100%">
                            <th colspan="2">
                                <h4 class="full-specs-sub-list">Data</h4>
                            </th>
                            <tr>
                                <td width="30%">GPRS</td>
                                <td width="70%">
	                                 <c:if test="${!empty mobile.gprs}">
			                                	<img src="/images/yes.gif">
			                         </c:if>
			                          <c:if test="${empty mobile.gprs}">
			                                	<img src="/images/no.png">
			                         </c:if>
                            </tr>
                            <tr>
                                <td width="30%">EDGE</td>
                                <td width="70%">
									<c:if test="${!empty mobile.edge}">
			                                	<img src="/images/yes.gif">
			                         </c:if>
			                         <c:if test="${empty mobile.edge}">
			                                	<img src="/images/no.png">
			                         </c:if>
								</td>
                            </tr>
                        </table>
						<table id="full-specs-sub-list-table" width="100%">
                            <th colspan="2">
                                <h4 class="full-specs-sub-list">Body</h4>
                            </th>
                            <tr>
                                <td width="30%">Dimensions</td>
                                <td width="70%">${mobile.dimensions}</td>
                            </tr>
                            <tr>
                                <td width="30%">Weight  (grams)</td>
                                <td width="70%"><c:if test="${mobile.weight gt 0}">${mobile.weight} g</c:if></td>
                            </tr>
                        </table>
						<table id="full-specs-sub-list-table" width="100%">
                            <th colspan="2">
                                <h4 class="full-specs-sub-list">Sound</h4>
                            </th>
                          <!--   <tr>
                                <td width="30%">Media Player</td>
                                <td width="70%">Music formats: MP3, 3GPP, MP4, SMF, WAV, OTA, Ogg vorbis, FLAC, ASF<br>
								- Video formats: 3GPP, MP4, VP8, VP9, ASF, AVI, FLV</td>
                            </tr> -->
                            <tr>
                                <td width="30%">Speakerphone</td>
                                <td width="70%"><img src="/images/yes.gif"></td>
                            </tr>
							 <tr>
                                <td width="30%">HeadSet</td>
                                <td width="70%"><img src="/images/yes.gif"></td>
                            </tr>
                        </table>
						<table id="full-specs-sub-list-table" width="100%">
                            <th colspan="2">
                                <h4 class="full-specs-sub-list">Battery</h4>
                            </th>
                            <tr>
                                <td width="30%">Type</td>
                                <td width\="70%">${mobile.batteryType}</td>
                            </tr>
                            <tr>
                                <td width="30%">Capacity  (mAh)</td>
                                <td width="70%">${mobile.batteryCapactiy}</td>
                            </tr>
							 <tr>
                                <td width="30%">Talk Time with Standard Battery   (Minutes)</td>
                                <td width="70%">${mobile.talkTime}</td>
                            </tr>
							<tr>
                                <td width="30%">Standby Time with Standard Battery   (Hours)</td>
                                <td width="70%">${mobile.standBy}</td>
                            </tr>
                        </table>
                    </div>

                </div>

            </div>
        </div>
</div>
    <style>
        
        #no-border {
            border: 0px;
        }
       
		.product-key-features #full-specs-sub-list-table td {
			  font: 14px/24px Arial;
		}
		
		#mobile-left-nav-sect .panel-body {
			   padding-bottom: 10px;
  			   padding-top: 10px;
  			   padding-left: 0px;
  			   padding-right: 0px;
		}
    </style>

</body>

<!-- <script language="JavaScript" type="text/javascript">
    $(document).ready(function() {

        $('#gallery').simplegallery({
            galltime: 400,
            gallcontent: '.content',
            gallthumbnail: '.thumbnails',
            gallthumb: '.thumb'
        });

    });
</script> -->

</html>