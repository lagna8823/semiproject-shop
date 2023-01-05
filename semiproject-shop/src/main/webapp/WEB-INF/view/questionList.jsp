<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
		<a href="${pageContext.request.contextPath}/questionList">고객센터</a>
		<a href="${pageContext.request.contextPath}/questionCommentList">문의사항</a>
	</header>
	<h2>고객센터</h2>
	<div>
		<!-- 문의글 작성 링크-->
		<div>
			<a href="${pageContext.request.contextPath}/addQuestion">문의글 작성</a>
		</div>
		
		<!-- 고객센터 내용 -->
		<div>
			<table boreder="1">
			<thead>
			<tr>
				<th>주문번호</th>
				<th>분류</th>
				<th>답변 내용</th>
				<th>작성일</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach var="q" items="${questionlist}">
					<tr>
						<td>${q.orderCode}</td>
						<td>${q.category}</td>
						<td>${q.questionMemo}</td>
						<td>${q.createdate}</td>
					</tr>	
				</c:forEach>
			</tbody>
			</table>
		</div>
		<!-- 페이징 -->
		<div align="center";>
			<!-- 첫 페이지 -->
			<a href="${pageContext.request.contextPath}/questionList?rowPerPage=${rowPerPage}&currentPage=1">처음</a>
			
			<!-- 이전 페이지 -->
			<c:if test="${currentPage>1}">
				<a href="${pageContext.request.contextPath}/questionList?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}">이전</a>
			</c:if>	
			
			<!-- 현재 페이지 -->
			<span>${currentPage}</span>
			
			<!-- 다음 페이지 -->
			<c:if test="${currentPage<lastPage}">
				<a href="${pageContext.request.contextPath}/questionList?rowPerPage=${rowPerPage}&currentPage=${currentPage+1}">다음</a>
			</c:if>	
			
			<!-- 마지막 페이지 -->
			<a href="${pageContext.request.contextPath}/questionList?rowPerPage=${rowPerPage}&currentPage=${lastPage}">마지막</a>
		</div>
	</div>
</body>
</html>