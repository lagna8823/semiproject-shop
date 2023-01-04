package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmpService;
import vo.Emp;

@WebServlet("/emp/empList")
public class EmpListController extends HttpServlet {
	
	private EmpService empService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.empService = new EmpService();
		
		ArrayList<Emp> empList = new ArrayList<Emp>();
		
		empList = this.empService.getEmpList();
		
		request.setAttribute("empList", empList);
	
		request.getRequestDispatcher("/WEB-INF/view/emp/empList.jsp").forward(request, response);
		
		
	}

}
