<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Home</title>
</head>
<body>
	 <c:set var="contextPath" value="${pageContext.request.contextPath}" />
	 <c:set var="user" value="kuja" />

	<h3>Welcome User</h3>

	<a href="${contextPath}/cart/view?id=${user}">View Cart</a>
	<br />
</body>
</html>