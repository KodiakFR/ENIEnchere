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
 * Servlet implementation class ServletProfil
 */
@WebServlet(
		urlPatterns = {"/Profil", "/ModifProfil",}
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
				boolean valideP = false;
				UtilisateurManager utilisateur = UtilisateurManager.getInstance();
				String utilisateurGeneral = (String) request.getSession().getAttribute("Utilisateur");
				System.out.println("utilisateurGeneral= " + utilisateurGeneral);
	
				
				// Récupération du pseudo lorsqu'on clique sur le nom du vendeur. A finir lorsque la page accueil sera présente.	
				
				String pseudoRecup = request.getParameter("pseudo");
				//String pseudoRecup = "zizou";
				System.out.println(pseudoRecup + "ceci est le pseudo recup");
				

				// Si le pseudo récupéré n'est pas egal la session
				
				System.out.println("je suis dans le if 1er");	
				Utilisateur utilisateurInconnu = utilisateur.recuperationUtilisateur(utilisateurGeneral);
				request.setAttribute("utilisateurInconnu", utilisateurInconnu);
				
				if(pseudoRecup.equals(utilisateurGeneral)) {
				valideP = true;	
				request.setAttribute("valideP", valideP);
				}
				request.setAttribute("valideP", valideP);
				RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Profil.jsp");
				rd.forward(request, response);
				
			

				
			} catch (NumberFormatException | BusinessException e) {
				e.printStackTrace();
			}

		}
		

		if (request.getServletPath().equals("/ModifProfil")) {
			
			try {
				UtilisateurManager utilisateur = UtilisateurManager.getInstance();
				String utilisateurGener = (String) request.getSession().getAttribute("Utilisateur");
				Utilisateur utilisateurProfil = utilisateur.recuperationUtilisateur(utilisateurGener);
				System.out.println("pseudo sauvegardé c'est bon " + utilisateurGener);

				request.setAttribute("utilisateurProfil", utilisateurProfil);
				RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/ModificationProfil.jsp");
				rd.forward(request, response);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean validationMdp = false;
		boolean validationMdpAc = false;
		boolean verifMdp = false;
		
		boolean verifPseudo = false;
		boolean verifMail = false;
		try {
			
		
			UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
			String utilisateurGener = (String) request.getSession().getAttribute("Utilisateur");
			Utilisateur utilisateurProfil = utilisateurManager.recuperationUtilisateur(utilisateurGener);
			
			// Récupération des nouvelles données
			String pseudo = request.getParameter("pseudo");
			String prenom = request.getParameter("prenom");
			String tel = request.getParameter("tel");
			String cp = request.getParameter("cp");
			String nom = request.getParameter("nom");
			String email = request.getParameter("email");
			String rue = request.getParameter("rue");
			String ville = request.getParameter("ville");
			String mdpAc = request.getParameter("mdp");
			String newMdp = request.getParameter("newmdp");
			String confirmMdp = request.getParameter("confirm");
			
			// Vérification du mot de passe : résultat en boolean
			verifMdp = utilisateurManager.validerMDP(mdpAc);
			System.out.println(verifMdp + " reponse");
			
			//if verifie si mdp bon
			if(verifMdp == true) {
				verifMail = utilisateurManager.validerMailModifProfil(email);
				verifPseudo = utilisateurManager.validerPseudoModifProfil(pseudo);

				// Methode verifie que mail et pseudo ne sont pas déjà existant
				if(verifMail == false && verifPseudo == false) {
					//méthode verifie si le nouveau mdp ne sont pas : null
					if(newMdp.length()>0 & confirmMdp.length()>0) {
						//methode verifie si les deux nouveaux mdp sont egaux
						if(newMdp.equals(confirmMdp)) {
							Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, tel, rue, cp, ville, newMdp);
							utilisateurManager.modificationProfil(utilisateur);
							System.out.println("je passe 1");
						}
					}
					// Vérifie si les deux new mdps sont : null
					if(newMdp.length()==0 & confirmMdp.length()==0) {
						Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, tel, rue, cp, ville, mdpAc);
						utilisateurManager.modificationProfil(utilisateur);	
						System.out.println("je passe 2");
					}
					// si mdp les deux mdp ne sont pas egaux
					else if(!newMdp.equals(confirmMdp)) {
						validationMdp = true;
						request.setAttribute("validationMdp", validationMdp);
						request.setAttribute("utilisateurProfil", utilisateurProfil);
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ModificationProfil.jsp") ;
						rd.forward(request, response);
						System.out.println("je passe 3");
					}
				}
				//si mail et pseudo existe déjà renvoie message
				else if(verifMail == true || verifPseudo == true) {
					verifMail = true;
					request.setAttribute("verifMail", verifMail);
					request.setAttribute("utilisateurProfil", utilisateurProfil);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ModificationProfil.jsp") ;
					rd.forward(request, response);
				}
			}
			
			// methode envoie erreur si le mdp saisie n'est pas bon  pas egaux
			 if(verifMdp == false) {
				validationMdpAc = true;
				request.setAttribute("validationMdpAc", validationMdpAc);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ModificationProfil.jsp") ;
				rd.forward(request, response);
				System.out.println("je passe 4");
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp") ;
			rd.forward(request, response);
			
		} catch (NumberFormatException | BusinessException e) {
			e.printStackTrace();
		}
		
		
	}

}
