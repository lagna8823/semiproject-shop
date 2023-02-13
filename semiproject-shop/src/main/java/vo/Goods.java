package vo;

public class Goods {
	private int goodsCode;
	private String goodsName;
	private String goodsMemo;
	private int goodsPrice;
	private String soldout;
	private String empId;
	private String hit;
	private String createdate;
	private String filename;
	private int cartQuantity;
	
	public Goods() {
		super();
	}
	
	public Goods(int goodsCode, String goodsName, String goodsMemo, int goodsPrice, String soldout, String empId,
			String hit, String createdate, String filename, int cartQuantity) {
		super();
		this.goodsCode = goodsCode;
		this.goodsName = goodsName;
		this.goodsMemo = goodsMemo;
		this.goodsPrice = goodsPrice;
		this.soldout = soldout;
		this.empId = empId;
		this.hit = hit;
		this.createdate = createdate;
		this.filename = filename;
		this.cartQuantity = cartQuantity;
	}
	
	@Override
	public String toString() {
		return "Goods [goodsCode=" + goodsCode + ", goodsName=" + goodsName + ", goodsMemo=" + goodsMemo
				+ ", goodsPrice=" + goodsPrice + ", soldout=" + soldout + ", empId=" + empId + ", hit=" + hit
				+ ", createdate=" + createdate + ", filename=" + filename + ", cartQuantity=" + cartQuantity + "]";
	}
	
	public int getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(int goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsMemo() {
		return goodsMemo;
	}
	public void setGoodsMemo(String goodsMemo) {
		this.goodsMemo = goodsMemo;
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getSoldout() {
		return soldout;
	}
	public void setSoldout(String soldout) {
		this.soldout = soldout;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getCartQuantity() {
		return cartQuantity;
	}
	public void setCartQuantity(int cartQuantity) {
		this.cartQuantity = cartQuantity;
	}
}
