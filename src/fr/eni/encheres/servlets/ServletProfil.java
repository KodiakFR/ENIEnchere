package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletProfil
 */
@WebServlet(
		urlPatterns = {"/Profil", "/ModifProfil"}
		)
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
					
		if (request.getServletPath().equals("/Profil")) {
			try {
				
				Utilisateur Utilisateur = (Utilisateur) request.getSession().getAttribute("Utilisateur");
				System.out.println("pseudo sauvegardé c'est bon " + Utilisateur);
				

				HttpSession session = request.getSession(true);
				session.setAttribute("Utilisateur", Utilisateur);
				RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Profil.jsp");
				rd.forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		
		
		if (request.getServletPath().equals("/ModifProfil")) {
			Utilisateur Utilisateur = (Utilisateur) request.getSession().getAttribute("Utilisateur");
			System.out.println("pseudo sauvegardé c'est bon " + Utilisateur);
			

			HttpSession session = request.getSession(true);
			session.setAttribute("Utilisateur", Utilisateur);
			RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/ModificationProfil.jsp");
			rd.forward(request, response);
			
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
