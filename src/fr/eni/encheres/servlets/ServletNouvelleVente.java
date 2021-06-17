package fr.eni.encheres.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet("/NouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/NouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
//		Utilisateur user = (Utilisateur) request.getSession().getAttribute("Utilisateur");
//		int idUtilisateur = user.getNoUtilisateur();
//		System.out.println(idUtilisateur);
		
		// Récupération des dates et valider avant insertion de l'article
		
		LocalDate dateDebutEncheres = ((Date.valueOf(request.getParameter("dateDebutEnchere"))).toLocalDate());
		System.out.println(dateDebutEncheres);
		LocalDate dateFinEncheres = ((Date.valueOf(request.getParameter("dateFinEnchere"))).toLocalDate());
		System.out.println(dateFinEncheres);
		Boolean validateDateDebut = validationDate(dateDebutEncheres, dateFinEncheres);
		System.out.println("Date Validate: "+validateDateDebut);
		
		//Dates invalides
		
		if(validateDateDebut == false)
			{
			request.setAttribute("validateDateDebut", validateDateDebut);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/NouvelleVente.jsp");
			rd.forward(request, response);
			}

		//Dates valides, traitement et ajour de l'article
		
		else
			{
			//Récupération des entrées utilisateurs de l'article
			
				String nomArticle = request.getParameter("nomArticle");
				String description = request.getParameter("description");
				String categorie = request.getParameter("categorie");
				Integer miseAPrix = Integer.parseInt(request.getParameter("map"));
				String rue = request.getParameter("nomRue");
				Integer cp = Integer.parseInt(request.getParameter("codePostal"));
				String ville = request.getParameter("ville");
			
			//Construction de l'article pour sa manipulation en dal
				
				ArticleVendu newArticle = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix);
				
			//Ajout de l'article à la base de donnée et récupére tous les articles
			//mis en vente par cet utilisateur
				try 
					{
						ArticleVenduManager manager = ArticleVenduManager.getInstance();
						
						List<ArticleVendu> listArticles = manager.ajoutArticle(newArticle, 1, categorie);
						request.getSession().setAttribute("listArticles", listArticles);
					}
				catch (BusinessException e) 
					{
						e.printStackTrace();
					}
			
				//Affichage message ajout OK
//			Boolean validerAjout = true;
//			request.setAttribute("validerAjout", validerAjout);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
			rd.forward(request, response);
			}
	}

	
	private Boolean validationDate(LocalDate datedebut, LocalDate dateFin) {
		Boolean validationDate = null;
			if(datedebut.compareTo(LocalDate.now()) <1 || dateFin.compareTo(datedebut)<1)
				validationDate = false;
			else
				validationDate = true;
		return validationDate;
		
	}
	
}
