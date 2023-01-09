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
	public ArrayList<Orders> selectOrderListByPage(Connection conn, int beginRow, int endRow, String customerId) throws Exception {
		Orders o = null;
		
		ArrayList<Orders> list = new ArrayList<Orders>();
		String sql = "SELECT o.order_code orderCode"
				+ ", g.goods_code goodsCode, g.goods_name goodsName, g.goods_price goodsPrice, g.soldout soldout"
				+ ", c.customer_id customerID, c.customer_name customerName, c.customer_phone customerPhone, c.point point"
				+ ", ca.address_code addressCode, ca.address address"
				+ ", o.order_quantity orderQuantity, o.order_price orderPrice, o.order_state orderState, o.createdate createdate"
				+ " FROM (SELECT ROW_NUMBER() OVER(ORDER BY order_code desc) rnum, order_code, goods_code, customer_id, address_code"
				+ ", order_quantity, order_price, order_state, createdate"
				+ " FROM orders) o"
				+ " INNER JOIN goods g ON o.goods_code = g.goods_code"
				+ " INNER JOIN customer c ON o.customer_id = c.customer_id"
				+ " INNER JOIN customer_address ca ON o.address_code = ca.address_code"
				+ " WHERE rnum BETWEEN ? AND ? AND o.customer_id = ? ORDER BY o.order_code desc"; // WHERE rnum >=? AND rnum <=?;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, endRow);
		stmt.setString(3, customerId);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			o = new Orders();
			o.setOrderCode(rs.getInt("orderCode"));
			o.setGoodsCode(rs.getInt("goodsCode"));
			o.setGoodsName(rs.getString("goodsName"));
			o.setGoodsPrice(rs.getInt("goodsPrice"));
			o.setSoldout(rs.getString("soldout"));
			o.setCustomerId(rs.getString("customerID"));
			o.setCustomerName(rs.getString("customerName"));
			o.setCustomerPhone(rs.getString("customerPhone"));
			o.setPoint(rs.getInt("point"));
			o.setAddressCode(rs.getInt("addressCode"));
			o.setAddress(rs.getString("address"));
			o.setOrderQuantity(rs.getInt("orderQuantity"));
			o.setOrderPrice(rs.getInt("orderPrice"));
			o.setOrderState(rs.getString("orderState"));
			o.setCreatedate(rs.getString("createdate"));
			list.add(o);
		}
		return list;
	}
	
	// 주문목록 검색추가 - (goods_code로 goods_name 조인해와야 함)
	public ArrayList<Orders> selectOrderListByPage(Connection conn, int beginRow, int endRow, String customerId, String word) throws Exception {
		Orders o = null;
		
		ArrayList<Orders> list = new ArrayList<Orders>();
		String sql = "SELECT o.order_code orderCode"
				+ ", g.goods_code, g.goods_name, g.goods_price, g.soldout"
				+ ", c.customer_id, c.customer_name, c.customer_phone, c.point point"
				+ ", ca.address_code, ca.address"
				+ ", o.order_quantity, o.order_price, o.order_state , o.createdate"
				+ " FROM (SELECT ROW_NUMBER() OVER(ORDER BY order_code desc) rnum, order_code, goods_code, customer_id, address_code"
				+ ", order_quantity, order_price, order_state, createdate"
				+ " FROM orders) o"
				+ " INNER JOIN goods g ON o.goods_code = g.goods_code"
				+ " INNER JOIN customer c ON o.customer_id = c.customer_id"
				+ " INNER JOIN customer_address ca ON o.address_code = ca.address_code"
				+ " WHERE rnum BETWEEN ? AND ? AND o.customer_id = ? AND g.goods_name LIKE ? ORDER BY o.order_code desc"; // WHERE rnum >=? AND rnum <=?;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, endRow);
		stmt.setString(3, customerId);
		stmt.setString(4, "%"+word+"%");
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			o = new Orders();
			o.setOrderCode(rs.getInt("orderCode"));
			o.setGoodsCode(rs.getInt("goodsCode"));
			o.setGoodsName(rs.getString("goodsName"));
			o.setGoodsPrice(rs.getInt("goodsPrice"));
			o.setSoldout(rs.getString("soldout"));
			o.setCustomerId(rs.getString("customerID"));
			o.setCustomerName(rs.getString("customerName"));
			o.setCustomerPhone(rs.getString("customerPhone"));
			o.setPoint(rs.getInt("point"));
			o.setAddressCode(rs.getInt("addressCode"));
			o.setAddress(rs.getString("address"));
			o.setOrderQuantity(rs.getInt("orderQuantity"));
			o.setOrderPrice(rs.getInt("orderPrice"));
			o.setOrderState(rs.getString("orderState"));
			o.setCreatedate(rs.getString("createdate"));
			list.add(o);
		}
		return list;
	}
	
	// 페이징을 위한 주문목록 페이지 수
	public int cntOrderList (Connection conn, String customerId) throws Exception {
		int cnt = 0;
		String sql = "SELECT COUNT(*) cnt FROM orders WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}		
		return cnt;	
	}
	
	// 페이징+검색을 위한 주문목록 페이지 수
	public int cntOrderList (Connection conn, String customerId, String word) throws Exception {
		int cnt = 0;
		String sql = "SELECT COUNT(*) cnt from orders WHERE customer_id = ? AND goods_name LIKE ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);
		stmt.setString(2, "%"+word+"%");
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}		
		return cnt;
	}
	
	// 주문상세보기
	public Orders selectOrderOne (Connection conn, int orderCode, String customerId) throws Exception {
		Orders o = null;
		
		String sql = "SELECT o.order_code orderCode"
				+ ", g.goods_code, g.goods_name, g.goods_price, g.soldout"
				+ ", c.customer_id, c.customer_name, c.customer_phone, c.point point"
				+ ", ca.address_code, ca.address"
				+ ", o.order_quantity, o.order_price, o.order_state , o.createdate"
				+ ", p.point_kind pointKind, p.point pointHistory"
				+ "FROM orders o INNER JOIN goods g ON o.goods_code = g.goods_code"
				+ " INNER JOIN customer c ON o.customer_id = c.customer_id"
				+ " INNER JOIN customer_address ca ON o.address_code = ca.address_code"
				+ " INNER JOIN point_history p On o.order_code = p.order_code"
				+ " WHERE o.customer_id = ? AND o.order_code = ? ORDER BY o.order_code desc;"; // WHERE rnum >=? AND rnum <=?;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);
		stmt.setInt(2, orderCode);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			o = new Orders();
			o.setOrderCode(rs.getInt("orderCode"));
			o.setGoodsCode(rs.getInt("goodsCode"));
			o.setGoodsName(rs.getString("goodsName"));
			o.setGoodsPrice(rs.getInt("goodsPrice"));
			o.setSoldout(rs.getString("soldout"));
			o.setCustomerId(rs.getString("customerID"));
			o.setCustomerName(rs.getString("customerName"));
			o.setCustomerPhone(rs.getString("customerPhone"));
			o.setPoint(rs.getInt("point"));
			o.setAddressCode(rs.getInt("addressCode"));
			o.setAddress(rs.getString("address"));
			o.setOrderQuantity(rs.getInt("orderQuantity"));
			o.setOrderPrice(rs.getInt("orderPrice"));
			o.setOrderState(rs.getString("orderState"));
			o.setCreatedate(rs.getString("createdate"));
			o.setPointKind(rs.getString("pointKind"));
			o.setPoint(rs.getInt("pointHistory"));
		}		
		return o;
	}
	
	// 주문하기
	public int addOrder(Connection conn, Orders orders) throws Exception {
		int row = 0;

		String sql = "INSERT ALL INTO orders(goods_code goodsCode, customer_id customerId, address_code addressCode"
				+ ", order_quantity orderQuantity, order_price orderPrice, order_state orderState, createdate)"
				+ " VALUES (?, ?, ?, ?, ?, ?, now)"
				+ " INTO point_history(point_kind pointKind, point)"
				+ " VALUES (?, ?)"; //sysdate는 시시각각 변함, now는 한 번 호출되면 변하지 않음
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orders.getGoodsCode());
		stmt.setString(2, orders.getCustomerId());
		stmt.setInt(3, orders.getAddressCode());
		stmt.setInt(4, orders.getOrderQuantity());
		stmt.setInt(5, orders.getOrderPrice());
		stmt.setString(6, orders.getOrderState());
		stmt.setString(7, orders.getPointKind());
		stmt.setInt(8, orders.getPoint());
		
		row = stmt.executeUpdate();
		return row;
	}
	
	// 주문수정(배송 전까지만 가능)
	public int updateOrderList(Connection conn, Orders orders) throws Exception {
		int row = 0;

		String sql = "UPDATE orders o, point_history p"
				+ " SET o.address_code = ?, o.order_quantity = ?, o.order_price = ?"
				+ " p.point_kind = ?, p.point = ?"
				+ " WHERE o.order_code = ? AND p.order_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orders.getAddressCode());
		stmt.setInt(2, orders.getOrderQuantity());
		stmt.setInt(3, orders.getOrderPrice());
		stmt.setString(4, orders.getPointKind());
		stmt.setInt(5, orders.getPoint());
		stmt.setInt(6, orders.getOrderCode());
		stmt.setInt(7, orders.getOrderCode());
			
		row = stmt.executeUpdate();
		return row;
	}
	
	// 주문취소(배송 전까지만 가능)
	public int deleteOrderList(Connection conn, Orders orders) throws Exception {
		int row = 0;

		String sql = "DELETE o, p FROM orders o INNER JOIN point_history p ON o.order_code = p.order_code WHERE o.order_code = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orders.getOrderCode());
			
		row = stmt.executeUpdate();
		return row;
	}
}
