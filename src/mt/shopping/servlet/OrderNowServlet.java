package mt.shopping.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mt.shopping.connection.DbCon;
import mt.shopping.dao.OrderDao;
import mt.shopping.model.Cart;
import mt.shopping.model.Order;
import mt.shopping.model.User;

/**
 * Servlet implementation class OrderNowServlet
 */
@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter();) {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();

			User auth = (User) request.getSession().getAttribute("auth");
			if (auth != null) {
				String productId = request.getParameter("id");
				int productQuantity = Integer.parseInt(request.getParameter("quantity"));
				if (productQuantity <= 0) {
					productQuantity = 1;
				}

				Order orderModel = new Order();
				orderModel.setId(Integer.parseInt(productId));
				orderModel.setUid(auth.getId());
				orderModel.setQuantity(productQuantity);
				orderModel.setDate(formatter.format(date));

				OrderDao orderDao = new OrderDao(DbCon.getConnection());
				boolean result = orderDao.insertOrder(orderModel);

				if (result) {
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if (cart_list != null) {
						for (Cart c : cart_list) {
							if (c.getId() == Integer.parseInt(productId)) {
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
					}
					response.sendRedirect("orders.jsp");
				} else {
					out.print("<meta charset=\"ISO-8859-1\">\r\n"
							+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\" />\r\n"
							+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\">");
					out.print("<div class='container'>"
							+ "  <a class='navbar-brand' style='color:#8B4513; font-variant: small-caps; font-size: 30px;'><b>Saigon 90s Café</b></a></div>");
					out.print("<h4 class='alert alert-danger' role='alert' style='text-align:center'>"
							+ "  Order Failed <a href='index.jsp' class='alert-link'>Go to Home Page</a>" + "</h4>");
					out.print(
							"<p style='text-align:center;'><img src='product-images/coffeeshop.jpg' alt='Welcome to Saigon 90s Café' margin-left: auto;'"
									+ "margin-right: auto; display: block; width='1400' height='550'></p>");
				}
			} else {
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
