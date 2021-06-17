package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
				UtilisateurManager Utilisateur = new UtilisateurManager();
			
				//récupération des données
				
				String identifiant = request.getParameter("Identifiant");
				String password	= request.getParameter("Password");
				System.out.println("j'ai récupérer l'identifiant et le mpd");
				
				//construction de l'objet Utilisateur
				
				Utilisateur utilisateur = new Utilisateur(identifiant, password);
				System.out.println("création de l'objet utilisateur avec un id et mdp");
				System.out.println(utilisateur.toString());
				
				//Appel de la méthode de connection
				
				System.out.println(utilisateur.toString());
				testConnection = Utilisateur.connection(utilisateur);
				System.out.println(testConnection);
				
				if (testConnection == false)
				{
					request.setAttribute("testConnection", testConnection);
					RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Connection.jsp");
					rd.forward(request, response);

				}
				
				if (testConnection == true)
				{
					utilisateur = Utilisateur.recuperationUtilisateur(utilisateur);
					
					HttpSession session = request.getSession(true);
					session.setAttribute("Utilisateur", utilisateur);
					RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
					rd.forward(request, response);
				}

				
			} catch (NumberFormatException | BusinessException e) {
				e.printStackTrace();
				} 
		

	}

}
