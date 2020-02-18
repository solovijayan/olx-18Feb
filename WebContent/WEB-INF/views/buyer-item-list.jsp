<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Cart</title>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 75%;
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

	<a href="${contextPath}/home/">My Home</a><br/>
	<br>

	<c:url var="buyUrl" value="/cart/checkOut?id=${item.item_id}" />
	<c:url var="cancelUrl" value="/cart/delete?id=${item.item_id})" />

	<form:form name="cart" method="GET" action="${buyUrl}" commandName="item">
	<table align="center">
		<tr>
			<td><b>Item Name</b></td>
			<td><b>Item Description</b></td>
			<td><b>Seller Name</b></td>
			<td><b>Price</b></td>
			<td colspan="2"></td>
			
		</tr>
		<c:forEach var="item" items="${items}">
		<input type=hidden name=item_id value="${item.item_id}" />
			<tr>
				<td>${item.item_name}</td>
				<td>${item.item_description}</td>
				<td>${item.seller}</td>
				<td><fmt:formatNumber type="number" pattern="0.00"  value="${item.item_price}" /> </td>
				<td> 
						<div style="text-align:center">  
    						<input type="submit" value="Proceed Chekout" />  
						</div>   
				</td>
                <td> 
						<div style="text-align:center">  
    						 <button type="submit" formaction="${cancelUrl}">Cancel Purchase</button>  
						</div>   
				</td>
			</tr>
		</c:forEach>
	</table>
	</form:form>
	
</body>
</html>