<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/CSS/style.css" rel = "stylesheet" >

</head>

<body>
	<div class = "container">	
		<div class = "header">
			<a href="Accueil" class = "fs-2  text-reset text-decoration-none">ENI - ENCHERES</a>
		</div>
	</div>

		Bonjour ${Utilisateur.getPrenom()}
		
		
		
			<!-- accessible si le profil est bien celui de l'utilisateur -->
			<div class="InscriptionContenu justify-content-center d-flex flex-row">
				<ul>
					<li class="ProfilListeInfo"> Pseudo :  ${Utilisateur.getPseudo()} </li> <br>
					
					<li class="ProfilListeInfo">Nom : &thinsp;	${Utilisateur.getNom()} </li>
					<li class="ProfilListeInfo">Prénom : &thinsp; 	${Utilisateur.getPrenom()}</li>
					<li class="ProfilListeInfo">Email : &thinsp;	 ${Utilisateur.getEmail()}</li>
					<li class="ProfilListeInfo">Téléphone : &thinsp;	${Utilisateur.getTelephone()}</li>
					<li class="ProfilListeInfo">Rue : &thinsp;	${Utilisateur.getRue()}</li>
					<li class="ProfilListeInfo">Code Postal : &thinsp;	${Utilisateur.getCodePostale()}</li>
					<li class="ProfilListeInfo">Ville : &thinsp;	${Utilisateur.getVille()} </li>
							
				</ul>
								
			
			</div>
					
				<!-- Creation d'un bouton modification permettant de modifer les informations de l'utilisateur -->			
			<div class = "Connection_boutonInscription justify-content-center d-flex flex-row">
				<a href="ModifProfil" ><button>Modifier</button></a>
			</div>
						
		
			
</body>
</html>