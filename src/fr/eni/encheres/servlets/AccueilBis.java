package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;

/**
 * Servlet implementation class AccueilBis
 */
@WebServlet("/AccueilBis")
public class AccueilBis extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArticleVenduManager articleManager = ArticleVenduManager.getInstance();
		Cookie[] cookies = request.getCookies();
		List<ArticleVendu> listeArticlesTot;
		try {
			listeArticlesTot = articleManager.getAllArticlesAccueil();
			request.setAttribute("listeArticlesTot", listeArticlesTot);
			
			if(cookies != null)
				{
						String userPseudo=null;
						for(Cookie cookieConnection : cookies)
							{
								if(cookieConnection.getName().equals("userPseudo"))
									{
										userPseudo = cookieConnection.getValue();
										HttpSession session = request.getSession(true);
										session.setAttribute("Utilisateur", userPseudo);
									}
							}
					}
		} catch (BusinessException e) {
			e.ajouterErreur(CodeResultatServlets.RECUPERATION_LISTE_TOTALE_ARTICLES_ECHEC);
		}
		
		EnchereManager enchereManager = EnchereManager.getInstance();
		List<Enchere> listEnchereTot;
		try 
			{
				listEnchereTot = enchereManager.getAllEncheresAccueil();
				request.setAttribute("listEnchereTot", listEnchereTot);
			} 
		catch(BusinessException be) 
			{
				be.ajouterErreur(CodeResultatServlets.RECUPERATION_LISTE_TOTALE_ENCHERE_ECHEC);
			}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/AccueilBis.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
