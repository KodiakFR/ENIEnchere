package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletProfil
 */
@WebServlet("/Profil")
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
	
			
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("Utilisateur");
			System.out.println("pseudo sauvegardé c'est bon " + utilisateur);
			
			


			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/JSP/Profil.jsp");
			rd.forward(request, response);

			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("Utilisateur");
			System.out.println("pseudo sauvegardé c'est bon " + utilisateur);
			
			String nom = utilisateur.getNom();
			String Prenom = utilisateur.getPrenom();
			String email = utilisateur.getEmail();
			String tel = utilisateur.getTelephone();
			String rue = utilisateur.getRue();
			String cp = utilisateur.getCodePostale();
			String ville = utilisateur.getVille();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
