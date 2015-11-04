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
</head>
<body>
<h1>
	Update Mobile
</h1>

<c:url var="addAction" value="/mobile/update" ></c:url>

<c:if test="${!empty mobile}">
	<form:form action="${addAction}" commandName="mobile">
	<table>
		<c:if test="${!empty mobile.name}">
		<tr>
			<td>
				<form:label path="id">
					<spring:message text="ID"/>
				</form:label>
			</td>
			<td>
				<form:input path="id" readonly="true" size="8"  disabled="true" />
				<form:hidden path="id" />
			</td> 
		</tr>
		</c:if>
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
				<c:if test="${!empty mobile.name}">
					<input type="submit"
						value="<spring:message text="Edit company"/>" />
				</c:if>
				<c:if test="${empty mobile.name}">
					<input type="submit"
						value="<spring:message text="Add company"/>" />
				</c:if>
			</td>
		</tr>
	</table>	
	</form:form>
</c:if>

<br>
<h3>Company List</h3>
${mobile}...
<c:if test="${!empty mobile}">
	<table class="tg">
	<tr>
		<th width="80">Mobile ID</th>
		<th width="120">Mobile Name</th>
		<th width="120">Mobile model</th>
		<th width="60">Mobile title</th>
		<th width="60">Mobile screenType</th>
		<th width="60">Mobile screenSize</th>
		<th width="60">Mobile screenResolution</th>
	</tr>
		<tr>
			<td>${mobile.id}</td>
			<td>${mobile.name}</td>
			<td>${mobile.model}</td>
			<td>${mobile.title}</td>
			<td>${mobile.screenType}</td>
			<td>${mobile.screenSize}</td>
			<td>${mobile.screenResolution}</td>
			<td><a href="<c:url value='/mobile/edit/${mobile.company.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/mobile/remove/${mobile.company.id}' />" >Delete</a></td>
		</tr>
	</table>
</c:if>
</body>
</html>
