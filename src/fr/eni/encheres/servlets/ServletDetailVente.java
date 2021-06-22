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
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;

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
		String nomArticle = request.getParameter("nomArticle");
		String pseudoVendeur = request.getParameter("pseudoVendeur");

		
		ArticleVenduManager articleManager = ArticleVenduManager.getInstance();
		RetraitManager retraitManager = RetraitManager.getInstance();

		try 
			{
				ArticleVendu articleAAfficher = articleManager.recupererArticleParNomArticleEtNomVendeur(nomArticle, pseudoVendeur);
			System.out.println(articleAAfficher);
			int idArticle = articleAAfficher.getNoArticle();
			System.out.println(idArticle);
				request.setAttribute("articleAAfficher", articleAAfficher);
				request.setAttribute("vendeur", pseudoVendeur);
				
				Retrait retraitArticleSelected = retraitManager.recupererRetraitByID(idArticle);
				
				System.out.println(retraitArticleSelected);
				
				request.setAttribute("retraitArticleSelected", retraitArticleSelected);
				
				if(articleAAfficher.getEtatVente()>1)
					{
						EnchereManager enchereManager = EnchereManager.getInstance();
						int montantEnchere = enchereManager.recupMontantEnchere(idArticle);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
