package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CustomerService;
import vo.Customer;

@WebServlet("/customer/updateCustomer")
public class UpdateCustomerController extends HttpServlet {
	
	private CustomerService customerService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 로그인, 등급 확인 코드
		 */
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		// request
		String customerCode = request.getParameter("customerCode");
		
		// null, 공백 검사 
		if(customerCode == null || customerCode.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/customer/customerList");
			return;
			
		}
		
		Customer customer = new Customer();
		
		this.customerService = new CustomerService();
		customer = this.customerService.getCustomerOne(Integer.parseInt(customerCode));
		
		request.setAttribute("customer", customer);
		
		request.getRequestDispatcher("/WEB-INF/view/customer/updateCustomer.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩 : UTF-8
				request.setCharacterEncoding("UTF-8");
				
				// request
				String customerCode = request.getParameter("customerCode");
				String customerId = request.getParameter("customerId");
				String customerName = request.getParameter("customerName");
				String customerPhone = request.getParameter("customerPhone");
				String point = request.getParameter("point");
				
				// null, 공백 검사
				if(customerCode == null || customerId == null || customerName == null || customerPhone == null || point == null
						 || customerCode.equals("") || customerId.equals("") || customerName.equals("") || customerPhone.equals("") || point.equals("")) {
					
					response.sendRedirect(request.getContextPath() + "/customer/customerList");
					return;
					
				}
				
				Customer customer = new Customer();
				customer.setCustomerCode(Integer.parseInt(customerCode));
				customer.setCustomerId(customerId);
				customer.setCustomerName(customerName);
				customer.setCustomerPhone(customerPhone);
				customer.setPoint(Integer.parseInt(point));
				
				this.customerService = new CustomerService();
				int resultRow = this.customerService.updateCustomer(customer);
				
						
				if(resultRow == 1) {
					
					// 수정 성공하면
					
				}
				
				response.sendRedirect(request.getContextPath() + "/customer/customerList");
				
		
	}

}
