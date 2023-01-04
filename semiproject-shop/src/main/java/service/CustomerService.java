package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CustomerDao;
import util.DBUtil;
import vo.Customer;

public class CustomerService {
	private CustomerDao customerDao;
	
	// 로그인 LoginController
	public Customer login(Customer customer) {
		this.customerDao = new CustomerDao();
		Customer returnCustomer= new Customer();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			customerDao= new CustomerDao();
			returnCustomer = customerDao.selectCustomerLogin(conn, customer);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return returnCustomer;	
		}
}