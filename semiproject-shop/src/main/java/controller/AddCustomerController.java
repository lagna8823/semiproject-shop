package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerAddressService;
import service.CustomerService;
import service.PwHistoryService;
import vo.Customer;
import vo.CustomerAddress;
import vo.Emp;
import vo.PwHistory;

@WebServlet("/customer/addCustomer")
public class AddCustomerController extends HttpServlet {

	private CustomerService customerService;
	private PwHistoryService pwHistoryService;
	private CustomerAddressService customerAddressService;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 전에만 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginCustomer != null || loginEmp != null) {
			response.sendRedirect(request.getContextPath()+"/goods/goodsList");
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/view/customer/addCustomer.jsp").forward(request, response);
				
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 로그인 전에만 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginCustomer != null || loginEmp != null) {
			response.sendRedirect(request.getContextPath()+"/goods/goodsList");
			return;
		}
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		String customerId = request.getParameter("customerId");
		String customerPw = request.getParameter("customerPw");
		String customerName = request.getParameter("customerName");
		String customerPhone = request.getParameter("customerPhone");
		String address = request.getParameter("address");
		
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

		PwHistory pwHistory = new PwHistory();
		pwHistory.setCustomerId(customerId);
		pwHistory.setPw(customerPw);
		
		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setCustomerId(customerId);
		customerAddress.setAddress(address);
		
		// customer 추가
		this.customerService = new CustomerService();
		int resultRowC = this.customerService.addCustomer(customer);

		// 비밀번호 이력 추가
		this.pwHistoryService = new PwHistoryService();
		int resultRowP = this.pwHistoryService.addPwHistory(pwHistory);
		
		// 주소 추가
		this.customerAddressService = new CustomerAddressService();
		int resultRowCA = this.customerAddressService.addAddress(customerAddress);
		
		String targetUrl = "/customer/addCustomer";
		if(resultRowC == 1 && resultRowP == 1 && resultRowCA ==1) {
			
			// 회원가입 성공시
			targetUrl = "/login";
		}
		
		response.sendRedirect(request.getContextPath() + targetUrl);
		
		
	}

}
