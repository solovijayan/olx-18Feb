<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Checkout</title>
 <script src= "https://code.jquery.com/jquery-1.12.4.min.js"> </script>
 <style type="text/css"> 
        .selectt { 
           display: none;
           padding: 100px; 
           margin-top: 30px;
           width: 20%;
           background: #7BB8FD 
        } 
        label { 
            margin-right: 20px;
        }
    </style>  
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<h1>Payment</h1>
	<h3> Select a payment Method</h3>
	
	<b>Amount to be paid: </b> <c:out value="${buyItem.item_price}"></c:out>
	<c:url var="submitImgUrl" value="/resources/submit.png" />
	<br>
	
	<div>
	  	<label><input type="radio" name="checkoutRadios"  value="Credit_Card" />Credit Card</label>
  		<label><input type="radio" name="checkoutRadios"  value="Debit_Card" />Debit Card  </label>
  		<label><input type="radio" name="checkoutRadios"  value="UPI" />UPI</label>
	</div>

		<div class="Credit_Card selectt">
		<c:url var="creditUrl" value="/payment/credit?id=${buyItem.item_id}" />
		<form:form  method="GET" action="${creditUrl}" name="credit-form" onsubmit="return validate();">
		<input type=hidden name=item_id value="${buyItem.item_id}" />
		<table border="0" style="width: 100%">
		<tr>
			<td><b>Name on the card</b></td>
			<td><input id="account_name" name="account_name"
						placeholder="Ex: Jack Maa" required> </td>
		</tr>
		<tr>
			<td><b>Card number</b></td>
			<td><input id="account"
						name="account" placeholder="5497 **** **** ****"
						required> </td>
		</tr>
		<tr>
			<td><b>Expiry Date</b></td>
			<td><input id="expiry_date"
						name="expiryDate" placeholder="MM/YY"
						required> </td>
			
		</tr>
		<tr>
			<td><b>CVV</b></td>
			<td><input id="cvv"
						name="cvv" placeholder="Ex: 123"
						required> </td>
		</tr>
		</table>
		
		<br>
		<div style="text-align:center">  
    		<input type="submit" value="Submit Payment" />  
		</div> 
		</form:form>
	</div>
	
        <div class="Debit_Card selectt">
		<c:url var="debitUrl" value="/payment/debit?id=${buyItem.item_id}" />
		<form:form  method="GET" action="${debitUrl}" name="debit-form" onsubmit="return validate();">
		<input type=hidden name=item_id value="${buyItem.item_id}" />
		<table border="0" style="width: 100%">
		<tr>
			<td><b>Name on the card</b></td>
			<td><input id="account_name" name="account_name"
						placeholder="Ex: Jack Maa" required> </td>
		</tr>
		<tr>
			<td><b>Card number</b></td>
			<td><input id="account"
						name="account" placeholder="5497 **** **** ****"
						required> </td>
		</tr>
		<tr>
			<td><b>Expiry Date</b></td>
			<td><input id="expiry_date"
						name="expiryDate" placeholder="MM/YY"
						required> </td>
			
		</tr>
		<tr>
			<td><b>CVV</b></td>
			<td><input id="cvv"
						name="cvv" placeholder="Ex: 123"
						required> </td>
		</tr>
		</table>
		
		<br>
		<div style="text-align:center">  
    		<input type="submit" value="Submit Payment" />  
		</div> 
		</form:form>
		</div>
        
        <div class="UPI selectt">
        <c:url var="upiUrl" value="/payment/upi?id=${buyItem.item_id}" />
		<form action="${upiUrl}" method="get" id="payment-form" onsubmit="return validateEmail();">
		<input type=hidden name=item_id value="${buyItem.item_id}" />
		<table border="0" style="width: 100%">
		<tr>
			<td><b>Please enter your UPI ID</b></td>
		</tr>
		<tr>
			<td><input id="upiId" name="upiId"
						placeholder="Ex: upiid@upi" required> </td>
		</tr>
		</table>
		<br>
		<div style="text-align:center">  
    		<input type="submit" value="Verify" />  
		</div> 
		</form>
		</div>
        
        <script type="text/javascript"> 
        $( 'input[name="checkoutRadios"]:radio' ).on('change', function(e) {
        	var inputValue = $(this).attr("value");
            var targetBox = $("." + inputValue);
            $(".selectt").not(targetBox).hide(); 
            $(targetBox).show(); 
       });
        
  
			function validate() {
				var account_name 		= document.getElementById("account_name").value;
				var account 	= document.getElementById("account").value;
				var expiry_date = document.getElementById("expiry_date").value;
				var cvv 		= document.getElementById("cvv").value;
				
				var regexAlpha 		= /^[a-zA-Z ]+$/;
				var regexNum	 	=  /^[0-9]+$/;
				var date 			= new Date();
				var todayDateMonth 	= date.getMonth() + 1;
				var todayDateYear 	= date.getFullYear();
				var dateFormat 		= /^[\d]{2}\/[\d]{4}$/;
				
				if (!regexAlpha.test(account_name)) {
					alert("Card name should be alpha characters");
					return false;
				}

				if (isNaN(account)) {
					alert("Account number must be a number");
					return false;
				}
				
				if (account.length > 16 || account.length < 15 )
				{
				    alert("your card number can only contain 15 to 16 digits");
				    return false;
				}
				
				if (isNaN(cvv)) {
					alert("CVV must be a number");
					return false;
				}
				
				if (!dateFormat.test(expiry_date)){
				    alert("please select a valid date range");
				    return false;
				}else if (todayDateYear > expiry_date.substring(3,7)){
				    alert("please select a valid expiry year");
				    return false;
				} else if (todayDateYear == expiry_date.substring(3,7) && todayDateMonth > expiry_date.substring(0,2)){
				    alert("please select a valid expiry month");
				    return false;
				}
				return true;
			};
			
			function validateEmail() 
			{
				
			var upi	= document.getElementById("upiId").value;
			 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)+$/.test(upi))
			  {
			    return true;
			  }
			    alert("You have entered an invalid UPI ID!")
			    return false;
			}

		</script> 
</body>
</html>