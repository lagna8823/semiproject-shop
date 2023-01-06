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
		<a href="${pageContext.request.contextPath}/question/questionList">고객센터</a>
		<a href="${pageContext.request.contextPath}/question/questionCommentList">comment</a>
	</header>
	<h2>고객센터</h2>
	<div>
		<!-- 문의글 작성 링크-->
		<div>
			<a href="${pageContext.request.contextPath}/question/addQuestion">문의글 작성</a>
		</div>
		
		<!-- 고객센터 내용 -->
		<div>
			<table border="0">
			<thead>
			<tr>
				<th>orderCode</th>
				<th>category</th>
				<th>questionMemo</th>
				<th>createdate</th>
				<th>답변</th>
				
				
				<th>답변일</th>
				<td>
					<c:if test="${loginCustomer == CustomerId}">
						<span>수정</span> 
					</c:if>
					<c:if test="${loginCustomer != CustomerId}">
						<span>&nbsp;</span>
					</c:if>
				</td>
				<td>
					<c:if test="${loginCustomer == CustomerId}">
						<span>삭제</span> 
					</c:if>
					<c:if test="${loginCustomer != CustomerId}">
						<span>&nbsp;</span>
					</c:if>
				</td>
			</tr>
			</thead>
			<tbody>
				<c:forEach var="q" items="${questionlist}">
					<tr>
						<td>${q.orderCode}</td>
						<td>${q.category}</td>
						<td>${q.questionMemo}</td>
						<td>${q.createdate}</td>
						<td>
							<c:if test="${commentMemo == null}">답변전</c:if> 
							<c:if test="${commentMemo != null}">답변완료</c:if> 	
						</td>
						<td>
							<c:if test="${createdate == null}">답변전</c:if> 
							<c:if test="${createdate != null}">답변시간</c:if> 
						</td>
						<td>
							<c:if test="${loginCustomer == CustomerId}">
								<a href="${pageContext.request.contextPath}/question/questionModify"?questionCode=${questionCode}">
									수정 
								</a>
							</c:if>
							<c:if test="${loginCustomer != CustomerId}">
								<span>&nbsp;</span>
							</c:if>
						</td>
						<td>
							<c:if test="${loginCustomer == CustomerId}">
								<a href="${pageContext.request.contextPath}/question/questionRemove"?questionCode=${questionCode}">
									삭제
								</a>
							</c:if>
							<c:if test="${loginCustomer != CustomerId}">
								<span>&nbsp;</span>
							</c:if>
						</td>
					</tr>	
				</c:forEach>
			</tbody>
			</table>
		</div>
		<br>
		<!-- 페이징 -->
		<div>
			<!-- 첫 페이지 -->
			<a href="${pageContext.request.contextPath}/question/questionList?rowPerPage=${rowPerPage}&currentPage=1">처음</a>
			
			<!-- 이전 페이지 -->
			<c:if test="${currentPage>1}">
				<a href="${pageContext.request.contextPath}/question/questionList?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}">이전</a>
			</c:if>	
			
			<!-- 현재 페이지 -->
			<span>${currentPage}</span>
			
			<!-- 다음 페이지 -->
			<c:if test="${currentPage<lastPage}">
				<a href="${pageContext.request.contextPath}/question/questionList?rowPerPage=${rowPerPage}&currentPage=${currentPage+1}">다음</a>
			</c:if>	
			
			<!-- 마지막 페이지 -->
			<a href="${pageContext.request.contextPath}/question/questionList?rowPerPage=${rowPerPage}&currentPage=${lastPage}">마지막</a>
		</div>
	</div>
</body>
</html>