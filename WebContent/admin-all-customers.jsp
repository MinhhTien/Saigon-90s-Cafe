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
List<Order> allCustomerList = new OrderDao(DbCon.getConnection()).allCustomers();
%>
<!DOCTYPE html>
<html>
<head>
<title>All Customers</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="card-header my-3">
			<h4>
				<b>All Customers</b>
			</h4>
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Email</th>
					<th scope="col">Phone Number</th>
					<th scope="col">Address</th>
					<th scope="col">Number Of Products</th>
				</tr>
			</thead>
			<tbody>
				<%
			if(allCustomerList != null ) {
				for(Order o:allCustomerList) {%>
				<tr>
					<td><%= o.getName() %></td>
					<td><%= o.getCategory() %></td>
					<td><%= o.getUserPhone() %></td>
					<td><%= o.getImage() %></td>
					<td><button type="button" class="btn btn-secondary"><%= o.getQuantity() %></button></td>
				</tr>
				<%}
			}
			%>
			</tbody>
		</table>
	</div>

	<%@include file="includes/footer.jsp"%>
</body>
</html>