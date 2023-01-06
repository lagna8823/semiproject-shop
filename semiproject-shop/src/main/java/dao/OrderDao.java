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
		
		ArrayList<Orders> list = new ArrayList<Orders>();
		String sql = "SELECT order_code orderCode, goods_code goodsCode, customer_id customerID, address_code addressCode"
				+ ", order_quantity orderQuantity, order_price orderPrice, order_state orderState, createdate"
				+ " FROM (SELECT ROW_NUMBER() OVER(ORDER BY order_code desc) rnum, order_code, goods_code, customer_id, address_code"
				+ ", order_quantity, order_price, order_state, createdate"
				+ " FROM orders) r  WHERE rnum BETWEEN 1 AND 10;"; // WHERE rnum >=? AND rnum <=?;
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
			list.add(o);
		}
		return list;
	}
	
	// 주문목록 검색추가 - (goods_code로 goods_name 조인해와야 함)
	public ArrayList<Orders> selectOrderListByPage(Connection conn, int beginRow, int endRow, String word) throws Exception {
		Orders o = null;
		
		ArrayList<Orders> list = new ArrayList<Orders>();
		String sql = "SELECT order_code orderCode, goods_code goodsCode, customer_id customerID, address_code addressCode"
				+ ", order_quantity orderQuantity, order_price orderPrice, order_state orderState, createdate"
				+ " FROM (SELECT ROW_NUMBER() OVER(ORDER BY order_code desc) rnum, order_code, goods_code, customer_id, address_code"
				+ ", order_quantity, order_price, order_state, createdate"
				+ " FROM orders) r  WHERE rnum BETWEEN 1 AND 10 AND (goods_code LIKE ? OR order_state LIKE ?)"; // WHERE rnum >=? AND rnum <=?;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, endRow);
		stmt.setString(3, "%"+word+"%");
		stmt.setString(4, "%"+word+"%");
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
			list.add(o);
		}
		return list;
	}
	
	// 페이징을 위한 주문목록 페이지 수
	public int cntOrderList (Connection conn) throws Exception {
		int cnt = 0;
		String sql = "SELECT COUNT(*) cnt FROM orders";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}		
		return cnt;	
	}
	
	// 페이징+검색을 위한 주문목록 페이지 수
	public int cntOrderList (Connection conn, String word) throws Exception {
		int cnt = 0;
		String sql = "SELECT COUNT(*) cnt from orders WHERE goods_code LIKE ? OR order_state LIKE ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+word+"%");
		stmt.setString(2, "%"+word+"%");
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}		
		return cnt;
	}
	
	// 주문상세보기
	public Orders selectOrderOne (Connection conn, int orderCode) throws Exception {
		Orders o = null;
		
		String sql = "SELECT order_code orderCode, goods_code goodsCode, customer_id customerId, address_code addressCode"
				+ ", order_quantity orderQuantity, order_price orderPrice, order_state orderState, createdate"
				+ " FROM orders where order_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orderCode);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			o = new Orders();
			o.setOrderCode(rs.getInt("orderCode"));
			o.setGoodsCode(rs.getInt("goodsCode"));
			o.setCustomerId(rs.getString("customerID"));
			o.setAddressCode(rs.getInt("addressCode"));
			o.setOrderQuantity(rs.getInt("orderQuantity"));
			o.setOrderPrice(rs.getInt("orderPrice"));
			o.setOrderState(rs.getString("orderState"));
			o.setCreatedate(rs.getString("createdate"));
		}		
		return o;
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
	
	// 주문수정(배송 전까지만 가능)
	public int updateOrderList(Connection conn, Orders orders) throws Exception {
		int row = 0;

		String sql = "UPDATE orders SET address_code = ?, order_quantity = ?, order_price = ?WHERE order_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orders.getAddressCode());
		stmt.setInt(2, orders.getOrderQuantity());
		stmt.setInt(3, orders.getOrderPrice());
		stmt.setInt(4, orders.getOrderCode());
			
		row = stmt.executeUpdate();
		return row;
	}
	
	// 주문취소(배송 전까지만 가능)
	public int deleteOrderList(Connection conn, Orders orders) throws Exception {
		int row = 0;

		String sql = "DELETE FROM orders WHERE order_code = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orders.getOrderCode());
			
		row = stmt.executeUpdate();
		return row;
	}
}
