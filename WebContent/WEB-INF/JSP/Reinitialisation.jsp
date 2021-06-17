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
		
			<c:if test="${statusPassword == false}">
			<div 	class="alert alert-danger" role="alert">
				Vos mots de passe ne sont pas identique, veuillez réessayer.
			</div>
		</c:if>
		<c:if test="${statusPassword == true}">
			<div 	class="alert alert-success" role="alert">
				Votre mot de passe a bien été modifié, vous pouvez vous connecter
			</div>
		</c:if>
		
		
		<form action="Reinitialisation" method="post">
			<p>	
				<input type="email" name="userEmail" id="userEmail" value="${userEmail}" style="visibility: hidden" >
			</p>
			<p>
				<label for="password">Nouveau mot de passe</label>
				<input type="password" name="password" id="password" required="required">
			</p>
			<p>
				<label for="verifPassword">Confirmation du nouveau mot de passe</label>
				<input type="password" name="verifPassword" id="verifPassword" required="required">
			</p>	
			<input type="submit" value="Envoyer">	
		</form>
		
</div>

</body>
</html>