<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>empList.jsp</title>
		
	</head>
	
	
	<body>
		<div>
			<div>
				<h1>EMP LIST</h1>
			</div>
			
			<div>
				<table border = "1">
					<tr>
						<th>empCode</th>
						<th>empId</th>
						<th>empName</th>
						<th>active</th>
						<th>authCode</th>
						<th>createdate</th>
					</tr>
					
					<c:forEach var="emp" items="${empList }">
						<tr>
							<td>${emp.getEmpCode() }</td>
							<td>${emp.getEmpId() }</td>
							<td>${emp.getEmpName() }</td>
							<td>${emp.getActive() }</td>
							<td>${emp.getAuthCode() }</td>
							<td>${emp.getCreatedate() }</td>
						</tr>
					</c:forEach>
				
				</table>
			</div>
			
		
		</div>
		
	</body>
</html>