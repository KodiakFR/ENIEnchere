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
//		String nomArticle = request.getParameter("nomArticle");
//		String pseudoVendeur = request.getParameter("pseudoVendeur");
		
		String nomArticle = "aprilia";
		String pseudoVendeur= "Kodiak";

		
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
				
				if(articleAAfficher.getEtatVente()>1)
					{
					System.out.println("Etat de vente supérieur à un");
						EnchereManager enchereManager = EnchereManager.getInstance();
						Enchere montantEnchere = enchereManager.recuperationEnchereByArticle(articleAAfficher);
						System.out.println(montantEnchere);
						request.setAttribute("montantEnchere", montantEnchere);
					}
				
			} 
		catch (BusinessException e) 
			{
				e.ajouterErreur(40002);
				e.printStackTrace();
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
//		String pseudoEncherisseur = (String) request.getSession().getAttribute("Utilisateur");
		
		String pseudoEncherisseur = "Kodiak";
		
		int montantEnchere = Integer.parseInt(request.getParameter("enchere"));
		String resultatEnchere = "nullitude";
		try
			{
				//Récupération des informations de l'article pour traitement de l'enchère
			
				ArticleVenduManager articleManager = ArticleVenduManager.getInstance();
				ArticleVendu articleAEncherir = articleManager.recupererArticleParNomArticleEtNomVendeur(nomArticle, pseudoVendeur);
				
				System.out.println(articleAEncherir);
				
				//Récupération des informations de l'enchérisseur
				UtilisateurManager userManager = UtilisateurManager.getInstance();
				Utilisateur encherisseur = userManager.recuperationUtilisateur(pseudoEncherisseur);
				
				System.out.println(encherisseur);
				
				int idEncherisseur = encherisseur.getNoUtilisateur();
				
				System.out.println(idEncherisseur);
				
				EnchereManager enchereManager = EnchereManager.getInstance();
					if(articleAEncherir.getEtatVente() == 1)
						{
							System.out.println("inexistant");
							enchereManager.startEnchere(articleAEncherir,idEncherisseur,montantEnchere);
							 resultatEnchere = "Bravo vous êtes le premier et le meilleur enchérisseur";
							request.setAttribute("resultatEnchere", resultatEnchere);
						}
					else if(articleAEncherir.getEtatVente() == 2)
						{
						System.out.println("Encours");
							 resultatEnchere = enchereManager.doNouvelleEnchere(articleAEncherir.getNoArticle(), encherisseur, montantEnchere);
							request.setAttribute("resultatEnchere", resultatEnchere);
						}
					else
						{
						System.out.println("missed");
						 resultatEnchere = "Désolé cette enchère n'est pas accessible ou est terminée";
						request.setAttribute("resultatEnchereError", resultatEnchere);
						}
					
				System.out.println(resultatEnchere);	
			}
		catch (BusinessException e)
			{
				e.printStackTrace();
				e.ajouterErreur(40004);
			}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		rd.forward(request, response);
	}
					
}
