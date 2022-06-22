<%@ page import="java.util.*"%>
<%@ page import="mt.shopping.dao.OrderDao"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="mt.shopping.connection.DbCon"%>
<%@ page import="mt.shopping.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
User auth = (User) request.getSession().getAttribute("auth");

if(auth == null) {
	response.sendRedirect("login.jsp");	
} 
if((auth.getEmail()).equals("admin123@gmail.com")) {
	response.sendRedirect("dashboard.jsp");
}
request.setAttribute("auth", auth);

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Profile Customers</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="card-header my-3">
			<h4>
				<b>Your Profile</b>
			</h4>
		</div>
		<div class="container">
			<div class="card w-50 mx-auto my-5">
				<div class="card-header text-center">
					<h3>Your Information</h3>
				</div>
				<div class="card-body">
					<form action="user-register" method="post">

						<div class="form-group">
							<label>Email Address:</label>
							<button type="button"
								class="btn btn-outline-info btn-lg btn-block"><%= auth.getEmail()%></button>
						</div>

						<div class="form-group">
							<label>Phone Number:</label>
							<button type="button"
								class="btn btn btn-outline-info btn-lg btn-block"><%= auth.getPhone()%></button>
						</div>

						<div class="form-group">
							<label>Your Name:</label>
							<button type="button"
								class="btn btn-outline-info btn-lg btn-block"><%= auth.getName()%></button>
						</div>

						<div class="form-group">
							<label>Your Address:</label>
							<button type="button"
								class="btn btn-outline-info btn-lg btn-block"><%= auth.getAddress()%></button>
						</div>



					</form>
				</div>
			</div>
		</div>
	</div>

	<%@include file="includes/footer.jsp"%>
</body>
</html>