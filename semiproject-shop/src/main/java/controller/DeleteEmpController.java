package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmpService;

@WebServlet("/emp/deleteEmp")
public class DeleteEmpController extends HttpServlet {
	
	private EmpService empService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		// request
		String empCode = request.getParameter("empCode");
		
		// null, 공백 검사 
		if(empCode == null || empCode.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/emp/empList");
			return;
			
		}
		
		this.empService = new EmpService();
		int resultRow = this.empService.deleteEmp(Integer.parseInt(empCode));
		
		if(resultRow == 1) {
			// 삭제성공하면 
		}
		
		response.sendRedirect(request.getContextPath() + "/emp/empList");
		
		
		
	}

}
