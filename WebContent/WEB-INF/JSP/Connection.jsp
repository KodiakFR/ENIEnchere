<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/CSS/style.css" rel = "stylesheet" >
<title>ENI - Encheres</title>
</head>
<header>
	<div class = "container">	
		<a href="Accueil" class = "fs-2  text-reset text-decoration-none">ENI - ENCHERES</a>
	</div>
</header>
<body>
	<div class = "container">
		<form action="Connection" method="post">
			<p>
				<label for ="Identifiant">Indentifiant: </label>
				<input type="text" name="Identifiant" id="Identifiant" required="required">
			</p>
			<p>
				<label for ="Password">Mot de passe: </label>
				<input type="password" name="Password" id="Password" required="required">
			</p>
			<div class="Connection_goup_submit_souvenirMoi_mdpOublié">
				<input type="submit" value="Connexion">
				<div class = "Connection_group_souvenirMOi_mdpOublié">
					<div class="Connection_SouvenirMoi">
						<input type="checkbox" name="souvenirMoi" id="souvenirMoi">
						<label for = "souvenirMoi">Se souvenir de moi</label>
					</div>
					<a href="#">Mot de passe oublié</a>
				</div>
			</div>
		</form>
		<div class = "Connection_boutonInscription">
			<a href="#" ><button>Créer un compte</button></a>
		</div>
	
		
	</div>
</body>
</html>