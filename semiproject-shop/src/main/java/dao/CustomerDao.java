package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Customer;

public class CustomerDao {
	
	// 로그인 LoginController
	public Customer selectCustomerLogin(Connection conn, Customer customer) throws Exception {
		Customer retrunCustomer = null;
		String sql = "SELECT customer_code customerCode, customer_id customerId, customer_name customerName, point "
				+ " FROM customer WHERE customer_id =? AND customer_pw = password(?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());
		stmt.setString(2, customer.getCustomerPw());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			retrunCustomer= new Customer();
			retrunCustomer.setCustomerCode(rs.getInt("customerCode"));
			retrunCustomer.setCustomerId(rs.getString("customerId"));
			retrunCustomer.setCustomerName(rs.getString("customerName"));
			retrunCustomer.setPoint(rs.getInt("point"));
		}
		rs.close();
		stmt.close();
		return retrunCustomer;
	}
}
