<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>login</title>
		
	</head>
	<body>
		<h2>Login</h2>
		<div>
		<!-- 로그인 label -->
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Customer</label>
    	<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Emp</label>
		  
		<!--Customer 로그인-->
		<form action="${pageContext.request.contextPath}/login" method="post">
			<div>
				<input type="text"  name="customerId">
				<label>Customer ID</label>
			</div>
			<div>
				<input type="password" name="customerPw">
				<label>Password</label>
			</div>
			<div>
		    	<button type="submit">로그인<button>
		    </div>
	    </form>
			<div>
			 <a href="${pageContext.request.contextPath}/addCustomer">회원가입</a>  
		    </div>
		
		<!--Emp 로그인-->
		<form action="${pageContext.request.contextPath}/login" method="post">
			<div>
				<input type="text"  name="empId">
				<label>Emp ID</label>
			</div>
			<div>
				<input type="password" name="empPw">
				<label>Password</label>
			</div>
			<div>
		    	<button type="submit">로그인<button>
		    </div>
	    </form>
			<div>
			 <a href="${pageContext.request.contextPath}/addEmp">사원가입</a>  
		    </div>
		</div>
	</body>
</html>