<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/CSS/style.css" rel = "stylesheet">
<title>ENI - Encheres</title>
</head>

<header>
	<div class = "container">	
	
		<div class = "header">
	
			<a href="Accueil" class = "fs-2  text-reset text-decoration-none">ENI - ENCHERES</a>
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
					<a href="Profil"> Mon profil</a>
					<a href="deconnexion"> Déconnexion</a> <br>
					Bonjour ${Utilisateur.getPrenom()}
				</div>
			</c:if>
		</div>
		
	</div>
</header>

<body>
	<div class = "container">	
	
		<h1 class = "text-center">Liste des enchères</h1>
	
	</div>
	
	<!-- 		Affichage bandeau vert ajout article reussi  \\ depuis servlet NouvelleVente -->
			<c:if test="${validerAjout == true}">
				<div class="container">
					<div class="alert alert-success alert-dismissible fade show" role="alert">
					  <strong>Holy guacamole!</strong> L'article a bien été ajouté à votre liste d'enchères.
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
				</div>
			</c:if>	
</body>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

</html>