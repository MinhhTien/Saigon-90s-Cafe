package mt.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mt.shopping.model.Order;
import mt.shopping.model.Product;
import mt.shopping.model.User;

public class UserDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public UserDao(Connection con) {
		this.con = con;
	}

	public User userLogin(String email) {
		User user = null;
		try {
			query = "SELECT * FROM user WHERE email=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, email);
			rs = pst.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
				user.setPassword(rs.getString("password"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		return user;
	}

	public boolean insertUser(User user) {
		boolean result = false;

		try {
			query = "INSERT INTO user (name, email, phone, password, create_at, address) values(?,?,?,?,?,?)";
			pst = this.con.prepareStatement(query);
			pst.setString(1, user.getName());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getPhone());
			pst.setString(4, user.getPassword());
			pst.setString(5, user.getCreateAt());
			pst.setString(6, user.getAddress());
			pst.executeUpdate();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public User getSingleUser(int id) {
		User row = null;

		try {
			query = "SELECT * FROM user WHERE id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			while (rs.next()) {
				row = new User();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setEmail(rs.getString("email"));
				row.setPhone(rs.getString("phone"));
				row.setAddress(rs.getString("address"));
				row.setCreateAt(rs.getString("create_at"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}
}
