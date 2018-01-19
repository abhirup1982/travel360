<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"	href="${contextPath}/resources/css/bootstrap.min.css">

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div style="margin-left: 10%">

					<img src="../resources/img/registersuccess.png"
						style="height: 100px; width: 100px;" />
				</div>

					<form id="logoutForm" method="POST" action="/logout">
					</form>
					<p>Welcome ${fullName} to Travel360</p>
					<p>Your User Name is: ${userName} </p>				
					<!-- <a onclick="document.forms['logoutForm'].submit()">back</a>	 	
					<a onclick="document.forms['logoutForm'].submit()">Edit</a>	-->
					<a href="http://localhost:8080/home/'${userName}'">Edit</a>

			</div>
		</div>
	</div>
</body>
</html>