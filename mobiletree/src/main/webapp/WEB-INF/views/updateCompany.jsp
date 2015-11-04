<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Company Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
	<script type="text/javascript">
	   function changeFunc() {
	    var selectBox = document.getElementById("cmpyName");
	    var cmpyId = selectBox.options[selectBox.selectedIndex].value;
	    window.location.href ="/Mobilestree/company/"+cmpyId;
   }
  </script>
</head>
<body>
<h1>
	Add a Company
</h1>

<c:url var="addAction" value="/company/add" ></c:url>
<c:url var="addAction" value="/company/add" ></c:url>


<select id="cmpyName" onchange="changeFunc();">
	<c:forEach items="${listCompanies}" var="cmpy">
	   <option value="${cmpy.id}">${cmpy.name}</option>
   	</c:forEach>
</select>

<form:form action="${addAction}" commandName="company">
	<table>
		<tr>
			<td>
				<form:label path="name">
					<spring:message text="Name"/>
				</form:label>
			</td>
			<td>
				<form:input path="name" />
			</td> 
		</tr>
		<tr>
			<td colspan="2">
					<input type="submit"
						value="<spring:message text="Edit company"/>" />
			</td>
		</tr>
	</table>	
</form:form>
<br>
<h3>Company List</h3>
<c:if test="${!empty listCompanies}">
	<table class="tg">
	<tr>
		<th width="80">Company ID</th>
		<th width="120">Company Name</th>
		<th width="120">Company Country</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listCompanies}" var="cmpy">
		<tr>
			<td>${cmpy.id}</td>
			<td>${cmpy.name}</td>
			<td>${cmpy.enabled}</td>
			<td><a href="<c:url value='/edit/${cmpy.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/remove/${cmpy.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>
