package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletDetailVente
 */
@WebServlet("/DetailVente")
public class ServletDetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String nomArticle = request.getParameter("nomArticle");
		String pseudoVendeur = request.getParameter("pseudoVendeur");

		
		ArticleVenduManager articleManager = ArticleVenduManager.getInstance();
		RetraitManager retraitManager = RetraitManager.getInstance();

		try 
			{
				ArticleVendu articleAAfficher = articleManager.recupererArticleParNomArticleEtNomVendeur(nomArticle, pseudoVendeur);
			int idArticle = articleAAfficher.getNoArticle();
				request.setAttribute("articleAAfficher", articleAAfficher);
				request.setAttribute("vendeur", pseudoVendeur);
				
				Retrait retraitArticleSelected = retraitManager.recupererRetraitByID(idArticle);
				
				request.setAttribute("retraitArticleSelected", retraitArticleSelected);
				
				if(articleAAfficher.getEtatVente() > 1)
					{
						EnchereManager enchereManager = EnchereManager.getInstance();
						Enchere montantEnchere = enchereManager.recuperationEnchereByArticle(articleAAfficher);
						request.setAttribute("montantEnchere", montantEnchere);
					}
				
			} 
		catch (BusinessException e) 
			{
				e.ajouterErreur(40002);
			}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/DetailVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		String nomArticle = request.getParameter("nomArticle");
		String pseudoVendeur = request.getParameter("pseudoVendeur");
		String pseudoEncherisseur = (String) request.getSession().getAttribute("Utilisateur");
		
		int montantEnchere = Integer.parseInt(request.getParameter("enchere"));
		String resultatEnchere = "nullitude";
		try
			{
				//Récupération des informations de l'article pour traitement de l'enchère
			
				ArticleVenduManager articleManager = ArticleVenduManager.getInstance();
				ArticleVendu articleAEncherir = articleManager.recupererArticleParNomArticleEtNomVendeur(nomArticle, pseudoVendeur);
				
				//Récupération des informations de l'enchérisseur
				UtilisateurManager userManager = UtilisateurManager.getInstance();
				Utilisateur encherisseur = userManager.recuperationUtilisateur(pseudoEncherisseur);
				
				int idEncherisseur = encherisseur.getNoUtilisateur();
				
				EnchereManager enchereManager = EnchereManager.getInstance();
				int etatVenteEnchere = articleAEncherir.getEtatVente();
					switch (etatVenteEnchere) {
					case 1:	
						System.out.println("inexistant");
						boolean execute = enchereManager.startEnchere(articleAEncherir,idEncherisseur,montantEnchere);
					if(execute==true)
						 {
							String resultat = "Bravo vous êtes le premier et le meilleur enchérisseur";
							request.setAttribute("resultatEnchere", resultat);
						 }
					else
						 {
								String resultat = "L'enchère n'a pas encore commencée";
						 		request.setAttribute("resultatEnchereError", resultat);
						 }
						
						break;
					case 2:	
						System.out.println("Encours");
						 resultatEnchere = enchereManager.doNouvelleEnchere(articleAEncherir.getNoArticle(), encherisseur, montantEnchere);
						request.setAttribute("resultatEnchere", resultatEnchere);
						
						
						break;

					default:

						System.out.println("missed");
						 resultatEnchere = "Désolé cette enchère n'est pas accessible ou est terminée";
						request.setAttribute("resultatEnchereError", resultatEnchere);
						break;
					}	
			}
		catch (BusinessException e)
			{
				e.ajouterErreur(40004);
			}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		rd.forward(request, response);
	}
					
}
