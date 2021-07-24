<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/CSS/style.css" rel = "stylesheet">
<title>Accueil Bis</title>
</head>
<body>
<header>
	<div class = "header">
			<a href="AccueilBis" class = "fs-2  text-reset text-decoration-none">ENI - ENCHERES</a>
			<c:if test="${empty Utilisateur}">
				<div class = "inscrireConnect">
					<a href="Inscription">S'inscrire - </a>
					<a href="Connection">Se connecter</a>
				</div>
			</c:if>
			<c:if test="${!empty Utilisateur}">
				<div class = "inscrireConnect">
					<a href="#">Enchère</a>
					<a href="NouvelleVente"> Vendre un article</a>
					<a href="Profil?pseudo=${Utilisateur}"> Mon profil</a>
					<a href="deconnexion"> Déconnexion</a> <br>
					Bonjour ${Utilisateur}
				</div>
			</c:if>
		</div>
		
	</div>
</header>
<main>

	<div class="container">
		<c:forEach items="${ list }" var="art">
			<div class="col-12 col-lg-6 mb-2 mt-2">
				<div class="card flex-row">
					<h4 class="card-title">${ art.nomArticle }</h4>
						<div class="card-body">
						<p class="card-text">${ art.description }</p>
						<p class="card-ytext">Prix : <c:if test=""></c:if></p>
						</div>
				</div>
			</div>
		</c:forEach>
	</div>

</main>

</body>
</html>