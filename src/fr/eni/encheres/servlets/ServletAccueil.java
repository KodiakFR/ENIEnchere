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
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;

/**
 * Servlet implementation class ServletAcceuil
 */
@WebServlet("/Accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
				
			ArticleVenduManager manager = ArticleVenduManager.getInstance();
			
			
			Cookie[] cookies = request.getCookies();
			
			System.out.println("j'ai crée mon tableau de cookies");
			
			if(cookies != null)
			{
				try {
					UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
					String userPseudo=null;
					for(Cookie cookieConnection : cookies)
						
					{
						System.out.println(cookieConnection.getValue());
						if(cookieConnection.getName().equals("userPseudo"))
						{
							userPseudo = cookieConnection.getValue();
							HttpSession session = request.getSession(true);
							session.setAttribute("Utilisateur", userPseudo);
						}
					}
					
					
					
				}catch (NumberFormatException | BusinessException e) {
					e.printStackTrace();
					} 
				
			} 
			
			//récupérer la liste des articles en status en cours .
			
			
			String motcle = "%%";
			int etatVente = 2;
			int categorie = 1;
			
			
			List<ArticleVendu> listeEnchereEnCours = manager.getListeEtatVente(motcle, etatVente, null, null, categorie);
			System.out.println(listeEnchereEnCours.toString());
			request.setAttribute("listeEnchereEnCours", listeEnchereEnCours);
						
			//afficher la page accueil

			
			
			RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
			rd.forward(request, response);	
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ArticleVenduManager manager = ArticleVenduManager.getInstance();
			
			//récupere mes infos
			
			String motcle = "%"+request.getParameter("motcle")+"%";
			
			int categories = Integer.parseInt(request.getParameter("categorie"));
			
			Integer ouvertes;
			Integer encours;
			Integer terminees;
			if ((request.getParameter("ouvertes")) != null) {
				
				ouvertes = Integer.parseInt(request.getParameter("ouvertes"));
			}
			else {
				ouvertes = null;
			}
			if ((request.getParameter("encours")) != null) {
				
				 encours = Integer.parseInt(request.getParameter("encours"));
			}
			else {
				 encours = null;
			}
			
			if ((request.getParameter("remportees")) != null) {
				
				terminees = Integer.parseInt(request.getParameter("remportees"));
			}
			else {
				terminees = null;
			}
			
			System.out.println(motcle + " " + ouvertes + " " + encours + " " + terminees + " " + categories);
			
			//application de la méthode pour retourner la liste des articles
			
			List<ArticleVendu> listeEnchereEnCours = manager.getListeEtatVente(motcle, ouvertes, encours, terminees, categories);
			request.setAttribute("listeEnchereEnCours", listeEnchereEnCours);
			
		} catch (NumberFormatException | BusinessException e) {
			e.printStackTrace();
		}

		
		// afficher la page
		
		RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		rd.forward(request, response);	
		
	}

}
