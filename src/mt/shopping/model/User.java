package mt.shopping.model;

public class User {
	private int id;
	private String name;
	private String email;
	private String address;
	private String password;
	private String phone;
	private String createAt;

	public User() {

	}

	public User(int id, String name, String email, String address, String password, String phone, String createAt) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.password = password;
		this.phone = phone;
		this.createAt = createAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

}
