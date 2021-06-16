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
			<div class="InscritpionDivGauche">
			<p> <label for="pseudo">Pseudo : </label>
			<input type="text" name="pseudo" id="pseudo" autofocus required></p>
			<p> <label for="prenom">Prénom : </label> 
			<input type="text" name="prenom" id="prenom" autofocus required> </p>
			<p> <label for="tel">Téléphone : </label> 
			<input type="text" name="tel" id="tel" autofocus required> </p>
			<p> <label for="cp">Code Postal : </label> 
			<input type="text" name="cp" id="cp" autofocus required> </p>
			<p> <label for="mdp">Mot de passe : </label> 
			<input type="text" name="mdp" id="mdp" autofocus required> </p>
			</div>
			
			
			
		
		
		</fieldset>
	</form>


<body>

</body>
</html>