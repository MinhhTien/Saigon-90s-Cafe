<%@ page import="java.util.*"%>
<%@ page import="mt.shopping.connection.DbCon"%>
<%@ page import="mt.shopping.dao.OrderDao"%>
<%@ page import="mt.shopping.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	User auth = (User) request.getSession().getAttribute("auth");
if (auth == null ) {
	response.sendRedirect("login.jsp");
}
if ((auth.getEmail()).equals("admin123@gmail.com")) {
	request.setAttribute("auth", auth);
} else {
	response.sendRedirect("index.jsp");
}
List<Order> allProductsList = new OrderDao(DbCon.getConnection()).allProductsReport();

%>
<!DOCTYPE html>
<html>
<head>
<title>All Products Sold</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="card-header my-3">
			<h4>
				<b>All Products</b>
			</h4>
		</div>
		<div class="row">
			<%
			if(!allProductsList.isEmpty()) {
				for(Order o:allProductsList) { %>
			<div class="col-md-3 my-3">
				<div class="card w-100" style="width: 18rem;">
					<img class="card-img-top" src="product-images/<%= o.getImage() %>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%= o.getName() %></h5>
						<h6 class="price">
							Price:
							<%= o.getPrice() %>VND
						</h6>
						<h6 class="category">
							Category:
							<%= o.getCategory() %></h6>
						<div class="mt-3 d-flex justify-content-center">
							<a class="btn btn-primary">Sold: <b><%= o.getQuantity() %></b></a>
						</div>

					</div>
				</div>
			</div>
			<%}
			}
		%>
		</div>
	</div>

	<%@include file="includes/footer.jsp"%>
</body>
</html>