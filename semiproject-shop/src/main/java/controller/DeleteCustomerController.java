package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CustomerService;

@WebServlet("/customer/deleteCustomer")
public class DeleteCustomerController extends HttpServlet {
	
	private CustomerService customerService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		// request
		String customerCode = request.getParameter("customerCode");
		
		// null, 공백 검사 
		if(customerCode == null || customerCode.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/customer/customerList");
			return;
			
		}
		
		this.customerService = new CustomerService();
		int resultRow = this.customerService.deleteCustomer(Integer.parseInt(customerCode));
		
		if(resultRow == 1) {
			// 삭제성공하면 
		}
		
		response.sendRedirect(request.getContextPath() + "/customer/customerList");
		
		
	}

}
