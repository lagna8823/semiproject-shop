package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Orders;

public class OrderDao {
	/*
	 * order_code
	 * goods_code
	 * customer_id
	 * address_code
	 * order_quantity
	 * order_price
	 * order_state
	 * createdate
	 */
	// 주문목록
	public ArrayList<Orders> selectOrderListByPage(Connection conn, int beginRow, int endRow) throws Exception {
		Orders o = null;
		
		ArrayList<Orders> orders = new ArrayList<Orders>();
		String sql = "SELECT order_code orderCode, goods_code goodsCode, customer_id customerId, address_code addressCode"
				+ ", order_quantity orderQuantity, order_price orderPrice, order_state orderState, createdate"
				+ " FROM (SELECT rownum rnum, order_code, goods_code, customer_id, address_code, order_quantity, order_price, order_state, createdate"
				+ "			FROM (SELECT order_code, goods_code, customer_id, address_code, order_quantity, order_price, order_state, createdate"
				+ "					FROM orders ORDER BY to_number(order_code) DESC))"
				+ " WHERE rnum BETWEEN ? AND ?"; // WHERE rnum >=? AND rnum <=?;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, endRow);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			o = new Orders();
			o.setOrderCode(rs.getInt("orderCode"));
			o.setGoodsCode(rs.getInt("goodsCode"));
			o.setCustomerId(rs.getString("customerID"));
			o.setAddressCode(rs.getInt("addressCode"));
			o.setOrderQuantity(rs.getInt("orderQuantity"));
			o.setOrderPrice(rs.getInt("orderPrice"));
			o.setOrderState(rs.getString("orderState"));
			o.setCreatedate(rs.getString("createdate"));
			orders.add(o);
		}
		return orders;
	}
	
	// 주문상세보기
	public Orders selectOrderOne (Connection conn, int orderCode) throws Exception {
		Orders orders = null;
		
		String sql = "SELECT order_code orderCode, goods_code goodsCode, customer_id customerId, address_code addressCode"
				+ ", order_quantity orderQuantity, order_price orderPrice, order_state orderState, createdate"
				+ " FROM orders where order_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orderCode);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			orders = new Orders();
			orders.setOrderCode(rs.getInt("orderCode"));
			orders.setGoodsCode(rs.getInt("goodsCode"));
			orders.setCustomerId(rs.getString("customerID"));
			orders.setAddressCode(rs.getInt("addressCode"));
			orders.setOrderQuantity(rs.getInt("orderQuantity"));
			orders.setOrderPrice(rs.getInt("orderPrice"));
			orders.setOrderState(rs.getString("orderState"));
			orders.setCreatedate(rs.getString("createdate"));
		}		
		return orders;
	}
	
	// 주문하기
	public int addOrder(Connection conn, Orders orders) throws Exception {
		int row = 0;

		String sql = "INSERT INTO orders(goods_code goodsCode, customer_id customerId, address_code addressCode, order_quantity orderQuantity, order_price orderPrice, order_state orderState, createdate)"
					+ " VALUES(?, ?, ?, ?, ?, ?, now)"; //sysdate는 시시각각 변함, now는 한 번 호출되면 변하지 않음
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orders.getGoodsCode());
		stmt.setString(2, orders.getCustomerId());
		stmt.setInt(3, orders.getAddressCode());
		stmt.setInt(4, orders.getOrderPrice());
		stmt.setString(5, orders.getOrderState());
		stmt.setString(6, orders.getCreatedate());
		
		row = stmt.executeUpdate();
		return row;
	}
}
