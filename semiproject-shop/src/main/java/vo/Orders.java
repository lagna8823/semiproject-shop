package vo;

public class Orders {
	private int orderCode;
	private int goodsCode;
	private String customerId;
	private int addressCode;
	private int orderQuantity;
	private int orderPrice;
	private String orderState;
	private String createdate;
	
	public Orders() {
		super();
	}
	
	public Orders(int orderCode, int goodsCode, String customerId, int addressCode, int orderQuantity, int orderPrice,
			String orderState, String createdate) {
		super();
		this.orderCode = orderCode;
		this.goodsCode = goodsCode;
		this.customerId = customerId;
		this.addressCode = addressCode;
		this.orderQuantity = orderQuantity;
		this.orderPrice = orderPrice;
		this.orderState = orderState;
		this.createdate = createdate;
	}
	
	@Override
	public String toString() {
		return "Orders [orderCode=" + orderCode + ", goodsCode=" + goodsCode + ", customerId=" + customerId
				+ ", addressCode=" + addressCode + ", orderQuantity=" + orderQuantity + ", orderPrice=" + orderPrice
				+ ", orderState=" + orderState + ", createdate=" + createdate + "]";
	}
	
	public int getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}
	public int getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(int goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public int getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(int addressCode) {
		this.addressCode = addressCode;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
}

