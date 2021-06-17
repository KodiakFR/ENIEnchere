package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bll.MaximeUtilisateurManager;
import fr.eni.encheres.bll.UtilisateurManager;

/**
 * Servlet implementation class ServletMotPasseOublie
 */
@WebServlet(
		urlPatterns = {"/MotDePasseOublie", "/Reinitialisation"}
)
public class ServletMotPasseOublie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getServletPath().equals("/MotDePasseOublie"))
		{
			RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/MotDePasseOublie.jsp");
			rd.forward(request, response);
			
		}
		
		if (request.getServletPath().equals("/Reinitialisation"))
		{
			//récuparation du mail
			
			String userEmail = request.getParameter("userEmail");
			request.setAttribute("userEmail", userEmail);
			RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Reinitialisation.jsp");
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Boolean verifmail = false;
		if (request.getServletPath().equals("/MotDePasseOublie"))
		{
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
				e.printStackTrace();
			}
				
		}
		
		if (request.getServletPath().equals("/Reinitialisation"))
		{
			try {
				UtilisateurManager Utilisateur = UtilisateurManager.getInstance();
				//test avec le fichier de Maxime
				MaximeUtilisateurManager MaximeUtilisateur = new MaximeUtilisateurManager();
				
				//récuperation de l'adresse email et le mot de passe 
				String password = request.getParameter("password");
				String verifPassword = request.getParameter("verifPassword");
				String usermail = request.getParameter("userEmail");
				
				//vérification du mot des mots de passe
				
				Boolean statusPassword = false;
				if (password.equals(verifPassword))
				{
					statusPassword = true;
				}

				//envoi d'un message d'erreur si les mots de passe ne sont pas identique 
				
				if (statusPassword == false)
				{
					request.setAttribute("statusPassword", statusPassword);
					RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Reinitialisation.jsp");
					rd.forward(request, response);
					
				}
				
				if(statusPassword == true)
					
				{
					//utilisationd de la méthode pour changer le mot de passe
					
					MaximeUtilisateur.UpdatePassword(password, verifPassword, usermail);
					
					//affichage d'un message de validation
					
					request.setAttribute("statusPassword", statusPassword);
					RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Reinitialisation.jsp");
					rd.forward(request, response);
				}
				
				
				
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		}
	}

}
