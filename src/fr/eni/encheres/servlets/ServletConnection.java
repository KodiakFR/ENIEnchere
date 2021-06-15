package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletConnection
 */
@WebServlet("/Connection")
public class ServletConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Connection.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		try {
			UtilisateurManager Utilisateur = new UtilisateurManager();
			
			//récupération des données
			
			String identifiant = request.getParameter("Identifiant");
			String password	= request.getParameter("Password");
			System.out.println("j'ai récupérer l'identifiant et le mpd");
			
			//construction de l'objet Utilisateur
			
			Utilisateur utilisateur = new Utilisateur(identifiant, password);
			
			//Appel de la méthode de connection
			
			Utilisateur.connection(utilisateur);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			}
		
		
		// Affichage de la page 
		RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		rd.forward(request, response);
	}

	
	
	
}
