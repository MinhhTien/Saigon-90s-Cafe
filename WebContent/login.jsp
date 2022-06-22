<%@ page import="java.util.*"%>
<%@ page import="mt.shopping.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth!=null) {
    	if((auth.getEmail()).equals("admin123@gmail.com")) {
    		response.sendRedirect("dashboard.jsp");
    	}
    	response.sendRedirect("index.jsp");
    }
    
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list != null) {
    	request.setAttribute("cart_list", cart_list);
    }
    %>
<!DOCTYPE html>
<html>
<head>
<title>Coffee Login</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">
				<h4>
					<b>Welcome To Login</b>
				</h4>
			</div>
			<div class="card-body">
				<form action="user-login" method="post">

					<div class="form-group">
						<label>Email Address</label> <input type="email"
							class="form-control" name="login-email"
							placeholder="Enter Your Email" required> <small
							class="form-text text-muted">Please don't forget your
							email for login.</small>
					</div>

					<div class="form-group">
						<label>Password</label> <input type="password"
							class="form-control" name="login-password"
							placeholder="***************" maxlength="20"
							pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"
							required> <small class="form-text text-muted">Your
							password must be 8-20 characters long, contain at least one
							lowercase, uppercase letter; number; special characters and no
							space.</small>
					</div>

					<div class="text-center">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>


				</form>
			</div>
		</div>
	</div>

	<%@include file="includes/footer.jsp"%>
</body>
</html>