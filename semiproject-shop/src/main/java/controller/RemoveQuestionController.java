package controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.QuestionService;
import vo.Customer;
import vo.Emp;

@WebServlet("/question/removeQuestion")
public class RemoveQuestionController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// VIEW -> /WEB-INF/view/question/removeQuestion.jsp
		// 로그인 후에만 진입가능(세션값 request)
		HttpSession session = request.getSession(); 
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		if(loginCustomer == null && loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// 매개변수, request 값세팅
		Customer customerId = loginCustomer;
		int questionCode=Integer.parseInt(request.getParameter("questionCode"));
		
		// 모델 호출
		QuestionService questionService = new QuestionService();
		int resultRow = questionService.removeQuestion(customerId, questionCode);
		
		// 삭제성공
		if(resultRow !=0) {
		response.sendRedirect(request.getContextPath()+"/question/questionList");
		return;
		}
		// 삭제실패 (아이디 확인)
		String msg = URLEncoder.encode("삭제되지 않습니다. 관리자에게 문의하세요.", "utf-8");
		response.sendRedirect(request.getContextPath() + "/question/questionList?msg="+msg);
	}
}
