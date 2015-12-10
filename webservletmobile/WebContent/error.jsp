<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>

<body>
<div class="error-div">
??<%= request.getAttribute("excep") %>:::
	
	:::
	<%= request.getAttribute("requestURI") %>
	
	:::
	
	??<%= request.getAttribute("excep1") %>
	
	
	...........block...........<%= request.getAttribute("block") %>
	
	...........message...........<%= request.getAttribute("msg") %>
	
	...........message1...........<%= request.getAttribute("msg1") %>

</div>
</body>

