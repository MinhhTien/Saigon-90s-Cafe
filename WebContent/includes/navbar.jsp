<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
		<a class="navbar-brand"
			style="color: #8B4513; font-variant: small-caps; font-size: 30px;"><b>Saigon
				90s Café</b></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<%
				if ((auth == null) || (auth != null && !(auth.getEmail()).equals("admin123@gmail.com"))) {
			%>
			<form class="form-inline my-4 my-lg-2" action="search-products"
				method="post">
				<input class="form-control mr-sm-2" type="search" name="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
			</form>
			<%
				}
			%>

			<ul class="navbar-nav ml-auto">
				<%
					if (auth == null) {
				%>
				<li class="nav-item active"><a class="nav-link"
					href="index.jsp">Home </a></li>
				<li class="nav-item"><a class="nav-link" href="cart.jsp">Cart<span
						class="badge badge-danger">${ cart_list.size() }</span></a></li>
				<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="register.jsp">Register</a>
				</li>

				<%
					} else if (auth != null && auth.getEmail().equals("admin123@gmail.com")) {
				%>
				<li class="nav-item active"><a class="nav-link"
					href="dashboard.jsp">All Orders</a></li>
				<li class="nav-item"><a class="nav-link"
					href="admin-all-products.jsp">All Products</a></li>
				<li class="nav-item"><a class="nav-link"
					href="admin-all-customers.jsp">All Customers</a></li>
				<li class="nav-item"><a class="nav-link" href="log-out">Logout</a>
				</li>
				<%
					} else {
				%>
				<li class="nav-item active"><a class="nav-link"
					href="index.jsp">Home </a></li>
				<li class="nav-item"><a class="nav-link" href="cart.jsp">Cart<span
						class="badge badge-danger">${ cart_list.size() }</span></a></li>
				<li class="nav-item"><a class="nav-link" href="orders.jsp">Orders</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="profile.jsp">Profile</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="log-out">Logout</a>
				</li>
				<%
					}
				%>



			</ul>
		</div>
	</div>
</nav>