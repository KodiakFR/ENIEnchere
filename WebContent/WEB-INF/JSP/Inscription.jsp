<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Page inscription</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/CSS/style.css" rel = "stylesheet" >

</head>
	<div class = "container">	
	
		<div class = "header">
			<a href="Accueil" class = "fs-2  text-reset text-decoration-none">ENI - ENCHERES</a>
		</div>
	</div>
	
	<h1 class=" InscriptionTitre text-center">Mon profil</h1>


	<form name="FormInscription" action="/Inscritpion" method="POST">
		<fieldset>
		
			<div class="InscriptionContenu justify-content-center d-flex flex-row">
				<div class="InscritpionDivGauche d-flex flex-column">
					<p> <label for="pseudo">Pseudo : </label>
					<input type="text" name="pseudo" id="pseudo" autofocus required="required"></p>
					
					<p> <label for="prenom">Prénom : </label> 
					<input type="text" name="prenom" id="prenom" required required="required"> </p>
					
					<p> <label for="tel">Téléphone : </label> 
					<input type="text" name="tel" id="tel" required="required"> </p>
					
					<p> <label for="cp">Code Postal : </label> 
					<input type="text" name="cp" id="cp" required="required"> </p>
					
					<p> <label for="mdp">Mot de passe : </label> 
					<input type="password" name="mdp" id="mdp" required="required"> </p>
					
				</div>
				<div class="InscritpionDivDroite d-flex flex-column">
					<p> <label for="nom">Nom : </label>
					<input type="text" name="nom" id="nom" autofocus required="required"></p>
					
					<p> <label for="email">Email : </label> 
					<input type="email" name="email" id="email" required required="required"> </p>
					
					<p> <label for="rue">Rue : </label> 
					<input type="text" name="rue" id="rue" required="required"> </p>
					
					<p> <label for="ville">Ville : </label> 
					<input type="text" name="ville" id="ville" required="required"> </p>
					
					<p> <label for="confirm">Confirmation : </label> 
					<input type="password" name="confirm" id="confirm" required="required"> </p>
				</div>
			</div>
			
			<div class= "InscriptionBouttons">
				<div class="Inscriptiondivbtna">
					<a class="InscriptionBouttonCreer" href="#">Créer</a>
				</div>
				
				<div class="Inscriptiondivbtnb">
					<a class="InscriptionBouttonAnnuler" href="Accueil">Annuler</a>
				</div>
			</div>		
		</fieldset>
	</form>
			
			

<body>

</body>
</html>