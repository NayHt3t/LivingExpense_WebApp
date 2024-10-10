<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>

<!-- head -->
<head>
	<c:import url="../common/header.jsp" />
	<link href="../static/css/style.css" rel="stylesheet" >
</head>

<body>
<!-- nav -->
<c:import url="../common/nav.jsp" />

<!-- item details card -->
<div class="container">
		<div class="row">
				<div class="col-8 mx-auto">
					<div class="card mb-3 details-card">
						<img
							src="${ item.image }"
							class="card-img-top card-image details-card-image" alt="${ item.title }">
						<div class="card-body">
							<h5 class="card-title"> ${ item.title } </h5>
							<p class="card-text"> Category : ${ item.category } </p>
							<p class="card-text"> Price : $ ${ item.price } </p>
							<p class="card-text"> quantity : ${ item.quantity } </p>
							<p class="card-text"> SubTotal : $ ${ item.subTotal } </p>
							<p class="card-text"> Issued Date : ${ item.issuedDate } </p>
							<p class="card-text"> Essential : ${ item.essential ? 'Yes':'No' } </p>
							<h3>Item Description</h3>
							<p class="card-text"> ${ item.description } </p>
							
							
							<c:url var="updateLink" value="item">
								<c:param name="mode" value="LOAD" />
								<c:param name="itemId" value="${ item.id }" />
							</c:url>
							
							<c:url var="deleteLink" value="item">
								<c:param name="mode" value="DELETE" />
								<c:param name="itemId" value="${ item.id }" />
							</c:url>
														
							<div class="text-center">
								<a href="${ updateLink }" class="btn btn-info">Update</a>
								<a href="${ deleteLink }" class="btn btn-danger">Delete</a>
							</div>
							
						</div>
					</div>
				</div>
		</div>
	</div>
<!-- item details card -->

<!-- footer -->
<c:import url="../common/footer.jsp" />

</body>
</html>