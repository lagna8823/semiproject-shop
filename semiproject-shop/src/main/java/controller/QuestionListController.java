package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.QuestionService;
import vo.Customer;
import vo.Emp;
import vo.Question;
@WebServlet("/question/questionList")
public class QuestionListController extends HttpServlet {
	private QuestionService questionService;
	
	// 고객센터
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// VIEW -> /WEB-INF/view/question/question.jsp
		// 로그인 후에만 진입가능(세션값 request)
		HttpSession session = request.getSession(); 
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		if(loginCustomer == null && loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
	
		// 페이징에 쓸 값 세팅
		int cnt = 0;
		int currentPage = 1;
		   if(request.getParameter("currentPage") != null) {
			   currentPage = Integer.parseInt(request.getParameter("currentPage"));
		   }
		int rowPerPage = 10;
		int beginRow = (currentPage-1) * rowPerPage;
		
		// 모델 호출
		this.questionService = new QuestionService();
		cnt = questionService.count(); 
		ArrayList<Question> list = questionService.getQuestionListByPage(beginRow, rowPerPage);
		int lastPage = (int)(Math.ceil((double)cnt / (double)rowPerPage));
		request.setAttribute("questionlist", list);
		request.setAttribute("currentPage", currentPage); 
		request.setAttribute("rowPerPage", rowPerPage); 
		request.setAttribute("lastPage", lastPage);
		
		// 고객센터 폼 View
		request.getRequestDispatcher("/WEB-INF/view/question/questionList.jsp").forward(request, response);
	}
}
