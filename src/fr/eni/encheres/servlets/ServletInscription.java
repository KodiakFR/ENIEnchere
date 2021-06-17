package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
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
 * Servlet implementation class ServletInscription
 */
@WebServlet("/Inscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/JSP/Inscription.jsp");
		rd.forward(request, response);
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Boolean validationMandP = false;
		Boolean validationMDP = false;
		
		try {
			UtilisateurManager utilisateur = new UtilisateurManager();
			
			// Récuperation de l'ensemble des données du formulaire
			String pseudo = request.getParameter("pseudo");
			String prenom = request.getParameter("prenom");
			String tel = request.getParameter("tel");
			String cp = request.getParameter("cp");
			String mdp = request.getParameter("mdp");
			String nom = request.getParameter("nom");
			String email = request.getParameter("email");
			String rue = request.getParameter("rue");
			String ville = request.getParameter("ville");
			String mdpConfirm = request.getParameter("confirm");
			
			// Construction utilisateur
			Utilisateur utilisateurU = new Utilisateur(pseudo, nom, prenom, email, tel, rue, cp, ville, mdp);
			System.out.println(utilisateurU.toString());	
						
			// If permettant de savoir si les deux mdps du formulaire sont identiques
			if(mdpConfirm.equals(mdp)) {
				
				// Utilisation de la méthode validation
				validationMandP = utilisateur.AjouterInscription(utilisateurU);
				if(validationMandP == false) {
					request.setAttribute("validationMandP", validationMandP);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Inscription.jsp");
					rd.forward(request, response);
				}
				
			} 
			if(!mdpConfirm.contentEquals(mdp)) {
				validationMDP = true;
				request.setAttribute("validationMDP", validationMDP);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Inscription.jsp") ;
				rd.forward(request, response);
			}
			
						
			
		} catch (NumberFormatException | BusinessException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

	
	
}
