<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link href="../../CSS/style.css">
<title>Detail Vente</title>
</head>
<body>
<header>
<h1><a href="<%=request.getContextPath()%>/Accueil">ENI-Enchères</a></h1>
<div style="text-align: center;">
<h2 class="center">Nouvelle vente</h2>
</div>
</header>

	<c:if test="${!empty articleAAfficher}">
	    <div class="container">
	 
	        <form method="POST" action="DetailVente">
		        <div class="form-group row">
		            <label for="map" class="col-sm-4 col-form-label">Nom de l'article</label>
					   				 <div class="col-sm-6">
					     				 <input type="text" name="nomArticle" value="${articleAAfficher.nomArticle}" readonly="readonly"/>
					    			</div>
		        </div>
	
		        <div class="form-group row">
		            <label for="map" class="col-sm-4 col-form-label">Description</label>
					   				 <div class="col-sm-6">
					     				 <p>${articleAAfficher.description}</p>
					    			</div>
		        </div>
	
	
		        <div class="form-group row">
		            <label for="map" class="col-sm-4 col-form-label">Meilleure offre</label>
					   				 <div class="col-sm-6">
					     				 <c:if test="${empty montantEnchere }"><p>${articleAAfficher.miseAPrix} pts</p></c:if>
					     				 <c:if test="${!empty montantEnchere }"><p>${montantEnchere.getMontantEnchere()+10} pts</p></c:if>
					    			</div>
		        </div>
		
		        <div class="form-group row">
		            <label for="map" class="col-sm-4 col-form-label">Mise à prix</label>
					   				 <div class="col-sm-6">
					     				 <p>${articleAAfficher.miseAPrix} pts</p>
					    			</div>
		        </div>
	
		        <div class="form-group row">
		            <label for="map" class="col-sm-4 col-form-label">Retrait</label>
					   				 <div class="col-sm-6">
					     				 <p>Rue 		: ${retraitArticleSelected.rue }</p>
					     				 <p>Code Postal :${retraitArticleSelected.codePostal }</p>
					     				 <p>Ville		:${retraitArticleSelected.ville }</p>
					    			</div>
		        </div>
	
		        <div class="form-group row">
		            <label for="map" class="col-sm-4 col-form-label">Vendeur</label>
		            <div class="col-sm-6">
		              <input type="text" name="pseudoVendeur" value="${vendeur}"/>
		             </div>
		        </div>
	
	        <div class="form-group row">
	            <label for="map" class="col-sm-4 col-form-label">Ma proposotion</label>
	           	<div class="col-sm-6">
	                     <input type="number" <c:if test="${empty montantEnchere }"> min="${articleAAfficher.miseAPrix+10}" value="${articleAAfficher.miseAPrix+10}"</c:if><c:if test="${!empty montantEnchere }"> min="${montantEnchere.getMontantEnchere()+10}" value="${montantEnchere.getMontantEnchere()+10}"</c:if> name="enchere"/>
	                     <input type="submit" value="Enchérir" class="btn btn-primary mb-2"/>
	                
	            </div>
	        </div>
			</form>
	    </div>
	</c:if>
</body>
</html>