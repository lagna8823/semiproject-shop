package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.Customer;
import vo.Orders;

public class PointDao {
	// 구매확정 시 포인트 처리
	public int updatePoint(Connection conn, Customer customer) throws Exception {
		int row = 0;

		String sql = "UPDATE customer SET point = ? WHERE customerId = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, customer.getPoint());
		stmt.setString(2, customer.getCustomerId());
			
		row = stmt.executeUpdate();
		return row;
	}
}