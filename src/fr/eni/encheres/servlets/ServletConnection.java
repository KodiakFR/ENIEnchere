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

/**
 * Servlet implementation class ServletConnection
 */
@WebServlet(
		urlPatterns = {"/Connection", "/deconnexion"}
				)
public class ServletConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if (request.getServletPath().equals("/deconnexion"))
		{
			
			if (request.getSession() != null)
			{
				Cookie[] cookies = request.getCookies();
				for(Cookie cookieConnection : cookies)
					if(cookieConnection.getName().equals("userPseudo")||cookieConnection.getName().equals("JSESSIONID"))
					{
						{
							cookieConnection.setMaxAge(0);
							response.addCookie(cookieConnection);
						}
					}
				
				request.getSession().invalidate();
				
				RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
				rd.forward(request, response);
			}
			
		}
		
		if (request.getServletPath().equals("/Connection"))
		{
			RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Connection.jsp");
			rd.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Boolean testConnection = false;

			try {
				UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
			
				//récupération des données
				
				String identifiant = request.getParameter("Identifiant");
				String password	= request.getParameter("Password");
				String souvenirMoi = request.getParameter("souvenirMoi");
				System.out.println("récupération des infos " + identifiant + " " + password + " " + souvenirMoi );
				
				//construction de l'objet Utilisateur
				
				Utilisateur utilisateur = new Utilisateur(identifiant, password);
				System.out.println("création de l'objet utilisateur avec un id et mdp");
				System.out.println(utilisateur.toString());
				
				//Appel de la méthode de connection
				
				System.out.println(utilisateur.toString());
				testConnection = utilisateurManager.connection(utilisateur);
				System.out.println(testConnection);
				
				if (testConnection == false)
				{
					request.setAttribute("testConnection", testConnection);
					RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Connection.jsp");
					rd.forward(request, response);

				}
				
				if (testConnection == true)
				{
					utilisateur = utilisateurManager.recuperationUtilisateur(utilisateur);
					
					HttpSession session = request.getSession(true);
					session.setAttribute("Utilisateur", utilisateur);
					if(souvenirMoi != null)
					{
						//création d'un cookie de connection pour une semaine
						Cookie cookieConnection	= new Cookie ("userPseudo", utilisateur.getPseudo());
						cookieConnection.setMaxAge(7*24*3600);
						response.addCookie(cookieConnection);
						System.out.println(cookieConnection.getName() + " " + cookieConnection.getValue());
						
					}
					
					RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
					rd.forward(request, response);
				}

				
			} catch (NumberFormatException | BusinessException e) {
				e.printStackTrace();
				} 
		

	}

}
