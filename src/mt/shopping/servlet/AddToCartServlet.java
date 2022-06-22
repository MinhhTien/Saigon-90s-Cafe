package mt.shopping.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import mt.shopping.model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		try (PrintWriter out = response.getWriter()) {
			ArrayList<Cart> cartList = new ArrayList<>();

			int id = Integer.parseInt(request.getParameter("id"));
			Cart cm = new Cart();
			cm.setId(id);
			cm.setQuantity(1);

			HttpSession session = request.getSession();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

			if (cart_list == null) {
				cartList.add(cm);
				session.setAttribute("cart-list", cartList);
				response.sendRedirect("cart.jsp");
			} else {
				cartList = cart_list;
				boolean exist = false;

				for (Cart c : cartList) {
					if (c.getId() == id) {
						exist = true;
						out.print("<meta charset=\"ISO-8859-1\">\r\n"
								+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\" />\r\n"
								+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\">");
						out.print("<div class='container'>"
								+ "  <a class='navbar-brand' style='color:#8B4513; font-variant: small-caps; font-size: 30px;'><b>Saigon 90s Café</b></a></div>");
						out.print("<h4 class='alert alert-primary' role='alert' style='text-align:center'>"
								+ "  Item already exist in Cart. <a href='cart.jsp' class='alert-link'>Go to Cart Page</a>"
								+ "</h4>");
						out.print(
								"<p style='text-align:center;'><img src='product-images/coffeeshop.jpg' alt='Welcome to Saigon 90s Café' margin-left: auto;'"
										+ "margin-right: auto; display: block; width='1400' height='550'></p>");
					}
				}
				if (!exist) {
					cartList.add(cm);
					response.sendRedirect("cart.jsp");
				}
			}

		}
	}

}
