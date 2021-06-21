<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link href="../../CSS/style.css">
<title>Detail Vente</title>
</head>
<body>
<div style="text-align: center;">
<h1>ENI-Enchères</h1>
<h2 class="center">Nouvelle vente</h2>
</div>
    <div class="container">
 
        
        <div class="form-group row">
            <label for="map" class="col-sm-4 col-form-label">Nom de l'article</label>
			   				 <div class="col-sm-6">
			     				 <p>PC portable pour travailler</p>
			    			</div>
        </div>

        <div class="form-group row">
            <label for="map" class="col-sm-4 col-form-label">Description</label>
			   				 <div class="col-sm-6">
			     				 <p>Une description d'un pc portable lambda</p>
			    			</div>
        </div>


        <div class="form-group row">
            <label for="map" class="col-sm-4 col-form-label">Meilleure offre</label>
			   				 <div class="col-sm-6">
			     				 <p>210 pts</p>
			    			</div>
        </div>

        <div class="form-group row">
            <label for="map" class="col-sm-4 col-form-label">Mise à prix</label>
			   				 <div class="col-sm-6">
			     				 <p>185 pts</p>
			    			</div>
        </div>

        <div class="form-group row">
            <label for="map" class="col-sm-4 col-form-label">Retrait</label>
			   				 <div class="col-sm-6">
			     				 <p>Adresse de retrait de l'article</p>
			    			</div>
        </div>

        <div class="form-group row">
            <label for="map" class="col-sm-4 col-form-label">Vendeur</label>
            <div class="col-sm-6">
              <p>jojo44</p>
             </div>
        </div>

        <div class="form-group row">
            <label for="map" class="col-sm-4 col-form-label">Ma proposotion</label>
            <div class="col-sm-6">
                 <form method="POST" action="DetailVente">
                     <input type="number" min="210+10" value="220" name="enchere"/>
                     <input type="button" value="Enchérir" class="btn btn-primary mb-2"/>
                </form>
             </div>
        </div>

    </div>
</body>
</html>