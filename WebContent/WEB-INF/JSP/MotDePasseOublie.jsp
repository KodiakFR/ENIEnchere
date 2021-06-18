<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/CSS/style.css" rel = "stylesheet" >
<title>ENI - Encheres</title>
</head>
<body>
	<div class = "container">	
		<div class = "header">
			<a href="Accueil" class = "fs-2  text-reset text-decoration-none">ENI - ENCHERES</a>
		</div>
		
		<c:if test="${verifmail == false}">
			<div 	class="alert alert-danger" role="alert">
				Votre e-mail n'est pas associé a un compte, veuillez saisir un autre e-mail ou inscrivez-vous
			</div>
		</c:if>
		<c:if test="${verifmail == true}">
			<div 	class="alert alert-success" role="alert">
				Un e-mail vous a été envoyé pour réinitialiser votre mot de passe.
			</div>
		</c:if>
		
		<form action="MotDePasseOublie" method="post">
			<p>
				<label for="email">Votre Email</label>
				<input type="email" name="email" id="email" required="required" size="50px">
			</p>	
			<input type="submit" value="Envoyer">	
		</form>
	</div>
	
</body>
</html>