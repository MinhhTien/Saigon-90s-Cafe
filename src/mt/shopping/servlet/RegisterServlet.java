package mt.shopping.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mt.shopping.aes.EncryptAndDecryptPassword;
import mt.shopping.connection.DbCon;
import mt.shopping.dao.UserDao;
import mt.shopping.model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/user-register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession hs = request.getSession();
		try (PrintWriter out = response.getWriter()) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();

			String email1 = request.getParameter("register-email");
			String phone = request.getParameter("register-phone");
			String name = request.getParameter("register-name");
			String address = request.getParameter("register-address");
			String password = request.getParameter("register-password");
			String confirmPassword = request.getParameter("register-confirm-password");

			if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>])(?=\\S+$).{8,20}$",
					password)) {
				out.print("<meta charset=\"ISO-8859-1\">\r\n"
						+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\" />\r\n"
						+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\">");
				out.print("<div class='container'>"
						+ "  <a class='navbar-brand' style='color:#8B4513; font-variant: small-caps; font-size: 30px;'><b>Saigon 90s Café</b></a></div>");
				out.print("<h4 class='alert alert-danger' role='alert' style='text-align:center'>"
						+ "  Register User failed: <b>Wrong Password Format</b> <a href='register.jsp' class='alert-link'>Go to Register Page</a>"
						+ "</h4>");
				out.print(
						"<p style='text-align:center;'><img src='product-images/coffeeshop.jpg' alt='Welcome to Saigon 90s Café' margin-left: auto;'"
								+ "margin-right: auto; display: block; width='1400' height='550'></p>");
			} else if (!password.equals(confirmPassword)) {
				out.print("<meta charset=\"ISO-8859-1\">\r\n"
						+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\" />\r\n"
						+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\">");
				out.print("<div class='container'>"
						+ "  <a class='navbar-brand' style='color:#8B4513; font-variant: small-caps; font-size: 30px;'><b>Saigon 90s Café</b></a></div>");
				out.print("<h4 class='alert alert-danger' role='alert' style='text-align:center'>"
						+ "  Register User failed: <b>Wrong Confirm Password</b> <a href='register.jsp' class='alert-link'>Go to Register Page</a>"
						+ "</h4>");
				out.print(
						"<p style='text-align:center;'><img src='product-images/coffeeshop.jpg' alt='Welcome to Saigon 90s Café' margin-left: auto;'"
								+ "margin-right: auto; display: block; width='1400' height='550'></p>");
			} else {
				User userModel = new User();
				userModel.setName(name);
				userModel.setEmail(email1);
				userModel.setPhone(phone);
				
				///////////////////// encrypt password///////////////////
				EncryptAndDecryptPassword encryptAndDecryptPassword = null;
				try {
					encryptAndDecryptPassword = new EncryptAndDecryptPassword(DbCon.getConnection());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				userModel.setPassword(encryptAndDecryptPassword.encryptedPassword(password));
				userModel.setAddress(address);
				userModel.setCreateAt(formatter.format(date));
				try {
					UserDao userDao = new UserDao(DbCon.getConnection());
					boolean result = userDao.insertUser(userModel);

					if (result) {
						out.print("<meta charset=\"ISO-8859-1\">\r\n"
								+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\" />\r\n"
								+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\">");
						out.print("<div class='container'>"
								+ "  <a class='navbar-brand' style='color:#8B4513; font-variant: small-caps; font-size: 30px;'><b>Saigon 90s Café</b></a></div>");
						out.print("<h4 class='alert alert-info' role='alert' style='text-align:center'>"
								+ "  Register User successfully. <a href='login.jsp' class='alert-link'>Go to Login Page</a>"
								+ "</h4>");
						out.print(
								"<p style='text-align:center;'><img src='product-images/coffeeshop.jpg' alt='Welcome to Saigon 90s Café' margin-left: auto;'"
										+ "margin-right: auto; display: block; width='1400' height='550'></p>");
					} else {
						out.print("<meta charset=\"ISO-8859-1\">\r\n"
								+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\" />\r\n"
								+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\">");
						out.print("<div class='container'>"
								+ "  <a class='navbar-brand' style='color:#8B4513; font-variant: small-caps; font-size: 30px;'><b>Saigon 90s Café</b></a></div>");
						out.print("<h4 class='alert alert-warning' role='alert' style='text-align:center'>"
								+ "  Register User failed: <b>Email or  Phone Number already Existed</b> <a href='register.jsp' class='alert-link'>Go to Register Page</a>"
								+ "</h4>");
						out.print(
								"<p style='text-align:center;'><img src='product-images/coffeeshop.jpg' alt='Welcome to Saigon 90s Café' margin-left: auto;'"
										+ "margin-right: auto; display: block; width='1400' height='550'></p>");
					}
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
