<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<c:import url="../common/header.jsp" />
<link href="../static/css/style.css" rel="stylesheet" >
</head>
<body>

	<!-- Form -->
	<!-- container -->
	<div class="container-md">
		<div class="row">
			<div class="col-md-6 mx-auto">
				<div class="card mb-3 mt-5">
					<div class="card-header"> Fill All Information </div>
					<div class="card-body">
					
					<c:if test="${ not empty signUpOk }">
							<c:choose>
								<c:when test="${ signUpOk }">
									<div class="alert alert-success" role="alert">
										 Successfully created account. 
									</div>
								</c:when>
								<c:otherwise>
									<div class="alert alert-danger" role="alert"> 
										Account creation failed! 
									</div>
								</c:otherwise>
							</c:choose>
						</c:if>
					
						<form action="user" method="post">
							<input type="hidden" name="mode" value="SIGNUP">
							
							<div class="mb-3">
								<label for="firstname" class="form-label">
									FirstName </label> <input type="text" class="form-control" id="firstname"
									name="firstname" required="required">
							</div>
							
							<div class="mb-3">
								<label for="lastname" class="form-label">
									LastName </label> <input type="text" class="form-control" id="lastname"
									name="lastname" required="required">
							</div>
							
													
							<div class="mb-3">
								<label for="username" class="form-label">
									Username </label> <input type="text" class="form-control" id="username"
									name="username" required="required">
							</div>
							
							<div class="mb-3">
								<label for="email" class="form-label">
									Email </label> <input type="text" class="form-control" id="email"
									name="email" required="required">
							</div>

							<div class="mb-3">
								<label for="password" class="form-label">
									Password </label> <input type="password" class="form-control" id="password"
									name="password" required="required">
							</div>
							
							<button type="submit" class="d-block btn btn-primary float-end">Sign Up</button>
							<p style="clear: both; font-size: 0.8rem;"> Already has an account? Sign in <a href="login" class="text-decoration-none text-muted">Here</a> </p>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<c:import url="../common/footer.jsp" />
</body>
</html>