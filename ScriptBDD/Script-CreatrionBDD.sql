DELETE FROM ENCHERES;
DELETE FROM RETRAITS ;
DELETE FROM ARTICLES_VENDUS ;
DELETE FROM UTILISATEURS ;

DBCC CHECKIDENT('UTILISATEURS',RESEED,0);
DBCC CHECKIDENT('ARTICLES_VENDUS',RESEED,0);

INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,ville,code_postal,mot_de_passe,credit,administrateur) 
VALUES ('Max','Maxime','Braud','maxime.braud2021@campus-eni.fr', '0606060606', '12 rue de la licorne', 'RainbowCity','10000', 'aze', 20, 0);

INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,ville,code_postal,mot_de_passe,credit,administrateur) 
VALUES ('Strawberry','Romain','Begue','romain.begue2021@campus-eni.fr', '0606060606', '5 rue des troënes', 'Plaine des Palmistes','94731', 'patatedouce', 500000, 0);

INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,ville,code_postal,mot_de_passe,credit,administrateur) 
VALUES ('DiMillepattes','Dimitri','Gouliarmis','dimitri.gouliarmis2021@campus-eni.fr', '0606060606', '10 rue du sommeil', 'Athenes','10682', 'abc', 1000, 0);

INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie,etat_vente,images)
VALUES ('PC Gamer', 'Joli PC surpuissant! Permet de lancer Oracle', '2021-06-25', '2021-06-29', 500, 500, 1, 1,1,null);

INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie,etat_vente,images)
VALUES ('Chewing gum', 'Chewing gum prémaché avec un reste de gout fraise. Occasion à saisir!', '2021-06-26', '2021-06-29', 3, 3, 2, 4,1,null);

INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie,etat_vente,images)
VALUES ('Fiat Multiplat', 'Voiture de collection, idéal pour chopper cet été. Presque neuve, peu de KM au compteur (350 000KM), amateurs passez votre chemin', '2021-06-24', '2021-06-29', 40000, 40000, 3, 4,1,null);

INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie,etat_vente,images)
VALUES ('Boxer', 'Boxer porter une seule fois. Taches apparentent authentiques mais propre', '2021-06-27', '2021-06-29', 20, 20, 1, 3,1,null);

INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie,etat_vente,images)
VALUES ('Moitié de lit double', 'Magnifique moitié de lit double après le jugement de mon divorse. Il suffit de rajouter deux pieds au côté droit', '2021-06-24', '2021-06-28', 450, 450, 2, 2,1,null);






