package controller;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerService;
import vo.Customer;
import vo.Emp;

@WebServlet("/order/orderPageNonMember")
public class OrderPageNonMember extends HttpServlet {
     
	private CustomerService customerService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 로그인 전에만 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginCustomer != null || loginEmp != null) {
			response.sendRedirect(request.getContextPath()+"/goods/goodsList");
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/view/order/orderPageNonMember.jsp").forward(request, response);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 전에만 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginCustomer != null || loginEmp != null) {
			response.sendRedirect(request.getContextPath()+"/goods/goodsList");
			return;
		}
		
		// 인코딩 : UTF-8
		Random rd = new Random();//랜덤 객체 생성
		String customerId = "temp"+ rd.nextInt(100) + rd.nextInt(100) + rd.nextInt(100);
		String customerPw = request.getParameter("customerPw");
		String customerName = request.getParameter("customerName");
		String customerPhone = request.getParameter("customerPhone");
		String address = request.getParameter("address");
		
		// request null & 공백 체크
		if(customerId == null || customerPw == null || customerName == null || customerPhone == null || address == null
				 || customerId.equals("") || customerPw.equals("") || customerName.equals("") || customerPhone.equals("") || address.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/customer/addCustomer");
			return;
			
		}
		
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setCustomerPw(customerPw);
		customer.setCustomerName(customerName);
		customer.setCustomerPhone(customerPhone);
		
		// 비회원 아이디 생성
		this.customerService = new CustomerService();		
		int resultRow = this.customerService.addCustomer(customer, address);

		String targetUrl = "/customer/addCustomer";
		if(resultRow == 1) {
			
			// 비회원 아이디 생성 성공시 결제완료페이지로
			targetUrl = "/";
			
		}
		
		response.sendRedirect(request.getContextPath() + targetUrl);
		
		
	}
}
