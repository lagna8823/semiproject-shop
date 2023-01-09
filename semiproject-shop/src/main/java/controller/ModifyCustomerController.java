package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerService;
import vo.Customer;
import vo.Emp;

@WebServlet("/customer/modifyCustomer")
public class ModifyCustomerController extends HttpServlet {
	
	private CustomerService customerService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 후 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginCustomer == null && loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		// request
		int customerCode = loginCustomer.getCustomerCode();
		Customer customer = new Customer();
		
		this.customerService = new CustomerService();
		customer = this.customerService.getCustomerOne(customerCode);
		customer.setCustomerCode(customerCode);
		
		request.setAttribute("customer", customer);
		
		request.getRequestDispatcher("/WEB-INF/view/customer/modifyCustomer.jsp").forward(request, response);
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 후 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginCustomer == null && loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		// request
		String customerCode = request.getParameter("customerCode");
		String customerId = request.getParameter("customerId");
		String customerName = request.getParameter("customerName");
		String customerPhone = request.getParameter("customerPhone");
		String point = request.getParameter("point");
		
		Customer customer = new Customer();
		customer.setCustomerCode(Integer.parseInt(customerCode));
		customer.setCustomerId(customerId);
		customer.setCustomerName(customerName);
		customer.setCustomerPhone(customerPhone);
		customer.setPoint(Integer.parseInt(point));
		
		customer.toString();
		// null, 공백 검사
		if(customerCode == null || customerId == null || customerName == null || customerPhone == null || point == null
				 || customerCode.equals("") || customerId.equals("") || customerName.equals("") || customerPhone.equals("") || point.equals("")) {
			
			System.out.println(123);
			response.sendRedirect(request.getContextPath() + "/home");
			return;
			
		}
		
		
		this.customerService = new CustomerService();
		int resultRow = this.customerService.modifyCustomer(customer);
		
				
		if(resultRow == 1) {
			
			// 수정 성공하면
			
		}
		
		response.sendRedirect(request.getContextPath() + "/home");
		
	}

}
