package fr.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;

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
 * Servlet implementation class ServletModifEnchere
 */
@WebServlet(
		urlPatterns = {"/ModifEnchere", "/Delete"}
				)
public class ServletModifEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		switch (request.getServletPath()) {
		case "/ModifEnchere":
				String pseudoVendeur = request.getParameter("pseudoVendeur");
				String nomArticleAModifier = request.getParameter("nomArticle");
				
			try 
				{
					ArticleVenduManager articleManager = ArticleVenduManager.getInstance();
					ArticleVendu articleAModifier = articleManager.recupererArticleParNomArticleEtNomVendeur(nomArticleAModifier, pseudoVendeur);
					LocalDate debutEnchere = articleAModifier.getDateDebutEncheres();
					
					//Verification du droit à la modification selon la date du jour
					if(LocalDate.now().compareTo(debutEnchere)<0)
						{
							request.setAttribute("articleAModifier", articleAModifier);
							
							RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ModifEnchere.jsp");
							rd.forward(request, response);
						}
					//Si pas le droit redirection accueil avec bandeau signalant la raison
					else
						{
							String nonDroitModif = "Désolé, l'enchère ayant débutée vous ne pouvez plus modifier ou supprimer l'article";
							request.setAttribute("nonDroitModif", nonDroitModif);
							
							RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ModifEnchere.jsp");
							rd.forward(request, response);
						}
				} 
			catch (BusinessException e) 
				{
					e.ajouterErreur(40005);
					e.printStackTrace();
				}
				
				
			break;
		
		case "/Delete":
				
			break;
		
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
