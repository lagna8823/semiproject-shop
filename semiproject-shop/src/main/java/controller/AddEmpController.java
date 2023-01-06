package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmpService;
import vo.Emp;

@WebServlet("/emp/addEmp")
public class AddEmpController extends HttpServlet {
	
	private EmpService empService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/emp/addEmp.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 로그인 검사(세션에 로그인 했다면 홈으로)
		 * 
		 */
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		String empId = request.getParameter("empId");
		String empPw = request.getParameter("empPw");
		String empName = request.getParameter("empName");
		
		if(empId == null || empPw == null || empName == null || empId.equals("") || empPw.equals("") || empName.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/emp/addEmp");
			return;
			
		}
		
		Emp emp = new Emp();
		emp.setEmpId(empId);
		emp.setEmpPw(empPw);
		emp.setEmpName(empName);
		
		this.empService = new EmpService();
		int resultRow = this.empService.addEmp(emp);
		
		// 가입 성공이면 로그인화면으로...
		response.sendRedirect(request.getContextPath() + "/home");
		
		
	}

}
