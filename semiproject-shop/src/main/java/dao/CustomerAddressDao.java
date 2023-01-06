package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.CustomerAddress;

public class CustomerAddressDao {

	// 주소 추가
	// 사용하는 곳 : AddCustomerController, 추후에 배송지추가할 때
	public int addAddress(Connection conn, CustomerAddress customerAddress) throws Exception {
		
		int resultRow = 0;
		
		String sql = "INSERT INTO customer_address ("
				+ "			customer_id"
				+ "			, address"
				+ "			, createdate"
				+ ") VALUES ("
				+ "			?"
				+ "			, ?"
				+ "			, NOW()"
				+ ")";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, customerAddress.getCustomerId());
		stmt.setString(2, customerAddress.getAddress());
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("주소 추가 성공");
		} else {
			System.out.println("주소 추가 실패");
		}
		
		
		return resultRow;
		
	}
	
}
