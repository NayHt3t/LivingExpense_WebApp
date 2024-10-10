<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light mb-3">
		<div class="container-fluid">
			<a class="navbar-brand" href="item">Expense App</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="item">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="item?mode=ITEM_FORM">Add Item</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							${ user.username } </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<c:if test="${ user.role == 'admin' }">
								<li><a class="dropdown-item" href="admin">AdminDashboard</a></li>
							</c:if>
							<li><a class="dropdown-item" href="login?mode=LOGOUT">Logout</a></li>
						</ul></li>
				</ul>
				<form class="d-flex" action="item" method="get">
					<input type="hidden" name="mode" value="SEARCH">
					<input class="form-control me-2" name="query" type="search" placeholder="Search"
						aria-label="Search">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
			</div>
		</div>
	</nav>