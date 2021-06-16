<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvelle Vente</title>
</head>
<body>
<header>
<h1>ENI-Enchères</h1>
<h2 class="center">Nouvelle vente</h2>
</header>

	<form action="NouvelleVente" method="post">
		
			Article: <input type="text" name="nomArticle" autofocus="autofocus"><br><br>
			Description: <input type="text" name="description" maxlength="300"><br><br>
			Catégorie: 	<select name="categorie">
							<option>Autre...
						</select><br><br>
			Photo de l'article: <button>UPOLOADER</button><br><br>
			Début de l'enchère: <input type="date" name="dateDebutEnchere" min="${Date.now}"><br><br>
			Fin de l'enchère: <input type="date" name="dateFinEnchere"><br><br>
			<fieldset>
			<legend>Retrait</legend>
				Rue:				<input type="text" value="Rue des licornes"><br><br>
				Code Postal:		<input type="text" value="99999"><br><br>
				Ville:				<input type="text" value="CloudCity"><br><br>
				
			</fieldset>
		<input type="submit" value="Enregistrer">
		<a href="<%=request.getContextPath()%>/Accueil">
   			<input type="button" value="Annuler" />
		</a>
	</form>
	
</body>
</html>