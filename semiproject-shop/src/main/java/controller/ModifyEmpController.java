package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmpService;
import vo.Emp;

@WebServlet("/emp/modifyEmp")
public class ModifyEmpController extends HttpServlet {

	private EmpService empService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 로그인, 등급 확인 코드
		 */
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		// request
		String empCode = request.getParameter("empCode");
		
		// null, 공백 검사 
		if(empCode == null || empCode.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/emp/empList");
			return;
			
		}
		
		Emp emp = new Emp();
		
		this.empService = new EmpService();
		emp = this.empService.getEmpOne(Integer.parseInt(empCode));
		
		request.setAttribute("emp", emp);
		
		request.getRequestDispatcher("/WEB-INF/view/emp/modifyEmp.jsp").forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		// request
		String empCode = request.getParameter("empCode");
		String empId = request.getParameter("empId");
		String empName = request.getParameter("empName");
		String active = request.getParameter("active");
		String authCode = request.getParameter("authCode");
		
		// null, 공백 검사
		if(empCode == null || empId == null || empName == null || active == null || authCode == null
				 || empCode.equals("") || empId.equals("") || empName.equals("") || active.equals("") || authCode.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/emp/empList");
			return;
			
		}
		
		Emp emp = new Emp();
		emp.setEmpCode(Integer.parseInt(empCode));
		emp.setEmpId(empId);
		emp.setEmpName(empName);
		emp.setActive(active);
		emp.setAuthCode(Integer.parseInt(authCode));
		
		this.empService = new EmpService();
		int resultRow = this.empService.modifyEmp(emp);
		
				
		if(resultRow == 1) {
			
			// 수정 성공하면
			
		}
		
		response.sendRedirect(request.getContextPath() + "/emp/empList");
		
		
		
		
	}
	
	

}
