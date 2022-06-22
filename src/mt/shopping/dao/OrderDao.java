package mt.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import mt.shopping.model.*;

public class OrderDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public OrderDao(Connection con) {
		this.con = con;
	}

	public boolean insertOrder(Order model) {
		boolean result = false;

		try {
			query = "INSERT INTO orders (product_id, user_id, o_quantity, o_date) values(?,?,?,?)";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, model.getId());
			pst.setInt(2, model.getUid());
			pst.setInt(3, model.getQuantity());
			pst.setString(4, model.getDate());
			pst.executeUpdate();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<Order> userOrders(int id) {
		List<Order> list = new ArrayList<Order>();
		try {
			query = "SELECT * FROM orders WHERE user_id=? ORDER BY orders.o_id DESC";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				ProductDao productDao = new ProductDao(this.con);
				int pId = rs.getInt("product_id");
				Product product = productDao.getSingleProduct(pId);
				order.setStatus(rs.getString("status"));
				order.setOrderId(rs.getInt("o_id"));
				order.setId(pId);
				order.setName(product.getName());
				order.setCategory(product.getCategory());
				order.setPrice(product.getPrice() * rs.getInt("o_quantity"));
				order.setQuantity(rs.getInt("o_quantity"));
				order.setDate(rs.getString("o_date"));
				list.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Order> allCustomers() {
		List<Order> list = new ArrayList<Order>();
		try {
			query = "SELECT user_id, sum(o_quantity) FROM orders WHERE status='DONE' group by user_id order by sum(o_quantity) DESC";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				Order order = new Order();

				int uId = rs.getInt("user_id");
				UserDao userDao = new UserDao(this.con);
				User user = userDao.getSingleUser(uId);

				order.setUserPhone(user.getPhone());
				order.setName(user.getName());
				order.setCategory(user.getEmail());
				order.setImage(user.getAddress());
				order.setQuantity(rs.getInt("sum(o_quantity)"));
				list.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Order> allOrders() {
		List<Order> list = new ArrayList<Order>();
		try {
			query = "SELECT * FROM orders ORDER BY orders.o_id DESC";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				ProductDao productDao = new ProductDao(this.con);
				int pId = rs.getInt("product_id");
				Product product = productDao.getSingleProduct(pId);

				int uId = rs.getInt("user_id");
				UserDao userDao = new UserDao(this.con);
				User user = userDao.getSingleUser(uId);

				////
				order.setStatus(rs.getString("status"));
				order.setUserPhone(user.getPhone());
				order.setOrderId(rs.getInt("o_id"));
				order.setId(pId);
				order.setName(product.getName());
				order.setCategory(product.getCategory());
				order.setPrice(product.getPrice() * rs.getInt("o_quantity"));
				order.setQuantity(rs.getInt("o_quantity"));
				order.setDate(rs.getString("o_date"));
				list.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Order> allProductsReport() {
		List<Order> list = new ArrayList<Order>();
		try {
			query = "SELECT product_id, SUM(o_quantity) FROM orders WHERE status='DONE' GROUP BY product_id ORDER BY SUM(o_quantity) DESC;";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				ProductDao productDao = new ProductDao(this.con);
				int pId = rs.getInt("product_id");
				Product product = productDao.getSingleProduct(pId);
				order.setImage(product.getImage());
				order.setId(pId);
				order.setName(product.getName());
				order.setCategory(product.getCategory());
				order.setPrice(product.getPrice());
				order.setQuantity(rs.getInt("SUM(o_quantity)"));

				list.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void cancelOrder(int id) {
		try {
			query = "DELETE FROM orders WHERE o_id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean changeStatusOrder(int id, String status) {
		boolean result = false;
		if (status.equals("CHECKING")) {
			status = "DELIVERING";
		} else {
			status = "DONE";
		}
		try {
			query = "UPDATE orders SET status=? WHERE o_id=?;";
			pst = this.con.prepareStatement(query);
			pst.setString(1, status);
			pst.setInt(2, id);
			pst.executeUpdate();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
