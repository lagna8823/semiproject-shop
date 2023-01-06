package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CustomerAddressDao;
import util.DBUtil;
import vo.CustomerAddress;

public class CustomerAddressService {

	private CustomerAddressDao customerAddressDao;
	
	// 주소 추가
	// 사용하는 곳 : AddCustomerController
	public int addAddress(CustomerAddress customerAddress) {
		
		int resultRow = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.customerAddressDao = new CustomerAddressDao();
			
			resultRow = this.customerAddressDao.addAddress(conn, customerAddress);
			
			conn.commit();
			
		} catch (Exception e) {
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return resultRow;
		
	}	
	
}
