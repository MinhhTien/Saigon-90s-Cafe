package mt.shopping.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mt.shopping.connection.DbCon;
import mt.shopping.dao.OrderDao;

/**
 * Servlet implementation class ChangeStatusOrderServlet
 */
@WebServlet("/change-status-order")
public class ChangeStatusOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			String status = request.getParameter("status");
			String id = request.getParameter("id");
			boolean result = false;
			if (id != null && status != null) {
				OrderDao orderDao = new OrderDao(DbCon.getConnection());
				result = orderDao.changeStatusOrder(Integer.parseInt(id), status);
			}
			if (result) {
				response.sendRedirect("dashboard.jsp");
			} else {
				out.print("<meta charset=\"ISO-8859-1\">\r\n"
						+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\" />\r\n"
						+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\">");
				out.print("<div class='container'>"
						+ "  <a class='navbar-brand' style='color:#8B4513; font-variant: small-caps; font-size: 30px;'><b>Saigon 90s Café</b></a></div>");
				out.print("<h4 class='alert alert-danger' role='alert' style='text-align:center'>"
						+ "  Change Status Order Failed. <a href='dashboard.jsp' class='alert-link'>Go to DashBoard</a>"
						+ "</h4>");
				out.print(
						"<p style='text-align:center;'><img src='product-images/coffeeshop.jpg' alt='Welcome to Saigon 90s Café' margin-left: auto;'"
								+ "margin-right: auto; display: block; width='1400' height='550'></p>");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
