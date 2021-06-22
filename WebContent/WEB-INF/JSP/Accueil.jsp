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
					<a href="Profil?pseudo=${Utilisateur}"> Mon profil</a>
					<a href="deconnexion"> Déconnexion</a> <br>
					Bonjour ${Utilisateur}
				</div>
			</c:if>
		</div>
		
	</div>
</header>

<body>
	<div class = "container">	
	
		<h1 class = "text-center">Liste des enchères</h1>
		
	<!-- Affichage du formulaire.  -->
	
	<h3>Filtres:</h3>	
	
	<form action="" method="post">
		<p>
			<input type="text" placeholder="Le nom de l'article contient" class="col-4">
		</p>
		<p>
			<label for="categorie">Catégories</label>
			<select name="categorie" id="categorie">
				<option value="Informatique">Informatique </option>
				<option value="Ameublement">Ameublement </option>
				<option value="Vêtement">Vêtement </option>
				<option value="Sport&Loisirs">Sport&Loisirs </option>
			</select>
		</p>
		<div class="row">
			<div class="col-4">
					<input type="radio" name="status" value="Achats" id="Achats" checked="checked">
					<label for="Achats">Achats</label> <br>
						<div class="ms-4">	
						
							<input type="checkbox" name="ouvertes" checked="checked">
							<label for="ouvertes">enchères ouvertes</label> <br>
							<input type="checkbox" name="encours">
							<label for="encours">mes enchères en cours</label> <br>
							<input type="checkbox" name="remportees">
							<label for="remportees">mes enchères remportées</label> <br>
						</div>
			</div>
			<div class="col-4">
					<input type="radio" name="status" value="Ventes" id="Ventes">
					<label for="Ventes">Mes Ventes</label><br>	
					<div class="ms-4">
						<input type="checkbox" name="ventesEnCours">
						<label for="ventesEnCours">enchères ouvertes</label> <br>
						<input type="checkbox" name="ventesNonDebutees">
						<label for="ventesNonDebutees">mes enchères en cours</label> <br>
						<input type="checkbox" name="ventesTerminées">
						<label for="ventesTerminées">mes enchères remportées</label> <br>	
					</div>
			</div>
			<div class="col-4">
					<input type="submit" value="Rechercher" class="btn btn-primary btn-lg">
			</div>	
		</div>

	</form>
	

	
	<!-- 		Affichage bandeau vert ajout article reussi  \\ depuis servlet NouvelleVente -->
			<c:if test="${validerAjout == true}">
				<div class="container">
					<div class="alert alert-success alert-dismissible fade show" role="alert">
					  <strong>Holy guacamole!</strong> L'article a bien été ajouté à votre liste d'enchères.
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
				</div>
			</c:if>	
	
	<!-- Affichage des articles en mode carte test -->		
	
	
	<div class = "row">
		<c:forEach var="A" items="${listeEnchereEnCours}">
	
				<div class = "col-12 col-lg-6 mb-2 mt-2" >
					<div class = "card flex-row">
						<img alt="image article" src="#">
						<div class="card-body">
						 ${A.getNomArticle()} <br>
						 prix : ${A.getPrixVente()} points <br>
						 Fin d'enchère: ${A.getDateFinEncheres()} <br>
						 Vendeur : <a href="Profil?pseudo=${A.getPseudoUtilisateur()}">${A.getPseudoUtilisateur()}</a>
						</div>
					</div>
				</div>		
		</c:forEach>
	</div>	
	</div>	
</body>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

</html>