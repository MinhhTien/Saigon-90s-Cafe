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
    	} else {
    		response.sendRedirect("index.jsp");
    	}
    }
    
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list != null) {
    	request.setAttribute("cart_list", cart_list);
    }
    %>
<!DOCTYPE html>
<html>
<head>
<title>Coffee Register</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">
				<h4>
					<b>Register For Account</b>
				</h4>
			</div>
			<div class="card-body">
				<form action="user-register" method="post">

					<div class="form-group">
						<label>Email Address</label> <input type="email"
							class="form-control" name="register-email"
							placeholder="Enter Your Email" required> <small
							class="form-text text-muted">We'll never share your email
							with anyone else.</small>
					</div>

					<div class="form-group">
						<label>Phone Number</label> <input type="tel" class="form-control"
							name="register-phone" placeholder="Enter Your Phone"
							pattern="^0[0-9]{9}$" required>
					</div>

					<div class="form-group">
						<label>Your Name</label> <input type="text" class="form-control"
							name="register-name" placeholder="Enter Your Name" required>
					</div>

					<div class="form-group">
						<label>Your Address</label> <input type="text"
							class="form-control" name="register-address"
							placeholder="Enter Your Address" required>
					</div>

					<div class="form-group">
						<label>Password</label> <input type="password"
							class="form-control" name="register-password"
							placeholder="***************"
							pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"
							required> <small class="form-text text-muted">Your
							password must be 8-20 characters long, contain at least one
							lowercase, uppercase letter; number; special characters and no
							space.</small>
					</div>

					<div class="form-group">
						<label>Confirm Password</label> <input type="password"
							class="form-control" name="register-confirm-password"
							placeholder="***************"
							pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"
							required> <small class="form-text text-muted">Confirm
							password must be similar to your password.</small>
					</div>

					<div class="form-check">
						<input class="form-check-input" type="checkbox" value=""
							id="invalidCheck" required> <label
							class="form-check-label" for="invalidCheck"> Agree to
							terms and conditions </label>
					</div>

					<div class="text-center">
						<button type="submit" class="btn btn-primary">Register
							Now</button>
					</div>

				</form>
			</div>
		</div>
	</div>

	<%@include file="includes/footer.jsp"%>
</body>
</html>