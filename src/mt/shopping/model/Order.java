package mt.shopping.model;

public class Order extends Product {
	private int orderId;
	private int uid;
	private int quantity;
	private String date;
	private String userPhone;
	private String status;

	public Order() {

	}

	public Order(int id, String name, String category, int price, String image, int orderId, int uid, int quantity,
			String date, String userPhone, String status) {
		super(id, name, category, price, image);
		this.orderId = orderId;
		this.uid = uid;
		this.quantity = quantity;
		this.date = date;
		this.userPhone = userPhone;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", uid=" + uid + ", quantity=" + quantity + ", date=" + date + "]";
	}

}
