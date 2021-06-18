package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;

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
		
		Cookie[] cookies = request.getCookies();
		
		System.out.println("j'ai crée mon tableau de cookies");
		
		if(cookies == null)
		{
			RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
			rd.forward(request, response);
		}
		
		else
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
						Utilisateur utilisateur = new Utilisateur(userPseudo, null);
						System.out.println(utilisateur);
						utilisateur = utilisateurManager.recuperationUtilisateur(utilisateur);
						HttpSession session = request.getSession(true);
						session.setAttribute("Utilisateur", utilisateur);
					}
				}
				
				RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
				rd.forward(request, response);	
				
			}catch (NumberFormatException | BusinessException e) {
				e.printStackTrace();
				} 
			
		} 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
