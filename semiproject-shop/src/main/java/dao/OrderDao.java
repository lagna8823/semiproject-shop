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
	
	// 주문목록 검색추가 - (goods_code로 goods_name 조인해와야 함)
	public ArrayList<Orders> selectBoardListByPage(Connection conn, int beginRow, int endRow, String word) throws Exception {
		Orders o = null;
		
		ArrayList<Orders> orders = new ArrayList<Orders>();
		String sql = "SELECT order_code orderCode, goods_code goodsCode, customer_id customerId, address_code addressCode"
				+ ", order_quantity orderQuantity, order_price orderPrice, order_state orderState, createdate"
				+ " FROM (SELECT rownum rnum, order_code, goods_code, customer_id, address_code, order_quantity, order_price, order_state, createdate"
				+ "			FROM (SELECT order_code, goods_code, customer_id, address_code, order_quantity, order_price, order_state, createdate"
				+ "					FROM orders ORDER BY to_number(order_code) DESC))"
				+ " WHERE rnum BETWEEN ? AND ? (goods_code LIKE ? OR order_state LIKE ?)";
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
			orders.add(o);
		}
		return orders;
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
	public int cntBoardList (Connection conn, String word) throws Exception {
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
	
	// 주문수정(배송 전까지만 가능) - 작업중
	public int updateBoardList(Connection conn, Orders orders) throws Exception {
		int row = 0;

		String sql = "UPDATE orders SET board_title = ?, board_content = ?, updatedate = ? WHERE order_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, orders.getBoardTitle());
		stmt.setString(2, orders.getBoardContent());
		stmt.setString(3, orders.getUpdatedate());
		stmt.setInt(4, board.getBoardNo());
			
		row = stmt.executeUpdate();
		return row;
	}
	
	// 주문취소(배송 전까지만 가능) - 작업중
	public int deleteBoardList(Connection conn, Orders orders) throws Exception {
		int row = 0;

		String sql = "DELETE FROM orders WHERE order_code = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, board.getBoardNo());
			
		row = stmt.executeUpdate();		
		return row;
	}
}
