<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../common/header.jsp"></c:import>
</head>
<body>
	<c:import url="../common/nav.jsp"></c:import>

	<!-- Form -->
	<!-- container -->
	<div class="container-md">
		<div class="row">
			<div class="col-md-6 mx-auto">
				<div class="card mb-3">
					<div class="card-header">Update Item Form</div>
					<div class="card-body">

						<c:if test="${ not empty updateOk and not updateOk }">
							<div class="alert alert-danger" role="alert">Updating item
								is failed!</div>
						</c:if>

						<form action="item" method="post">
							<input type="hidden" name="mode" value="UPDATE"> <input
								type="hidden" name="itemId" value="${ item.id }">
							<div class="mb-3">
								<label for="exampleInputEmail1" class="form-label">
									Title </label> <input type="text" class="form-control" id="title"
									name="title" value="${ item.title }" required="required">
							</div>

							<div class="mb-3">
								<label for="exampleInputEmail1" class="form-label">
									Category </label> <input type="text" class="form-control" id="category"
									name="category" value="${ item.category }" required="required">
							</div>

							<div class="mb-3">
								<label for="exampleInputEmail1" class="form-label">
									Price </label> <input type="text" class="form-control" id="price"
									name="price" value="${ item.price }" required="required">
							</div>

							<div class="mb-3">
								<label for="exampleInputEmail1" class="form-label">
									Quantity </label> <input type="number" class="form-control"
									id="quantity" name="quantity" value="${ item.quantity }"
									required="required">
							</div>

							<div class="mb-3">
								<label for="exampleInputEmail1" class="form-label">
									Image URL </label> <input type="text" class="form-control" id="image"
									name="image" value="${ item.image }" required="required">
							</div>

							<div class="mb-3">
								<label for="exampleInputEmail1" class="form-label">
									Description </label>
								<textarea rows="5" class="form-control" id="description"
									name="description" required="required"> ${ item.description } </textarea>
							</div>


							<div class="mb-3 form-check">
							
								<c:choose>
									<c:when test="${ item.essential }">
										<input type="checkbox" class="form-check-input" id="essential"
									name="essential" value="true" checked="checked"> 
									</c:when>
									<c:otherwise>
										<input type="checkbox" class="form-check-input" id="essential"
									name="essential" value="true"> 
									</c:otherwise>
								</c:choose>
							
								<label class="form-check-label" for="essential">Essential</label>
							</div>
							<button type="submit" class="btn btn-primary float-end">Update</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>



	<c:import url="../common/footer.jsp"></c:import>
</body>
</html>