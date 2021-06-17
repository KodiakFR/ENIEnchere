package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.MaximeUtilisateurManager;
import fr.eni.encheres.bll.UtilisateurManager;

/**
 * Servlet implementation class ServletMotPasseOublie
 */
@WebServlet("/MotDePasseOublie")
public class ServletMotPasseOublie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/MotDePasseOublie.jsp");
			rd.forward(request, response);
			
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Boolean verifmail = false;
		
		try {
			UtilisateurManager Utilisateur = UtilisateurManager.getInstance();
			//test avec le fichier de Maxime
			MaximeUtilisateurManager MaximeUtilisateur = new MaximeUtilisateurManager();
			
			//récuperation de l'adresse email
			String email = request.getParameter("email");
			
			//verification de l'existance du mail
			
			verifmail = Utilisateur.verifmail(email);
			System.out.println(verifmail);
			
			if (verifmail == false)
			{
				request.setAttribute("verifmail", verifmail);
				RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/MotDePasseOublie.jsp");
				rd.forward(request, response);
			}
			
			if (verifmail == true)
			{
				
				Utilisateur.sentmail(email);
				
				request.setAttribute("verifmail", verifmail);
				RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/MotDePasseOublie.jsp");
				rd.forward(request, response);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
