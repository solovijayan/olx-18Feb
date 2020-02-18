<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Order Confirmation</title>

<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 50%;
}

td, th {
	border: 1px solid #7BB8FD;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #7BB8FD;
}
</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />


	<h3 align="center">Order confirmation</h3>
	<c:url var="buyNowImgUrl" value="/resources/buyNow.png" />
	<c:url var="confirmBuyUrl"
		value="/payment/confirm?id=${buyItem.item_id}" />

	<form:form name="cart" action="${confirmBuyUrl}" method="GET"
		commandName="item">
		<table align="center">
			<tr>
				<td><b>Item Name</b></td>
				<td><b>Item Description</b></td>
				<td><b>Seller Name</b></td>
				<td><b>Price</b></td>
			</tr>
			<tr>
				<td>${buyItem.item_name}</td>
				<td>${buyItem.item_description}</td>
				<td>${buyItem.seller}</td>
				<td><fmt:formatNumber type="number" pattern="0.00"
						value="${buyItem.item_price}" /></td>
			</tr>
		</table>
		<br>
		<input type=hidden name=item_id value="${buyItem.item_id}" />
		<div style="text-align:center">  
    		<input type="submit" value="Confirm Purchase" />  
		</div>  
		
	</form:form>

	<br />
</body>
</html>