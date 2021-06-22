package fr.eni.encheres.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet("/NouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
		
			ArticleVenduManager manager = ArticleVenduManager.getInstance();
			try {
				Set<Categorie> listeDeCategories = manager.getListCategories();
				request.setAttribute("listeDeCategories", listeDeCategories);
			} catch (BusinessException e) {
				e.ajouterErreur(40000);
				e.printStackTrace();
			}
			
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/NouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean validerAjout = false;
		request.setCharacterEncoding("UTF-8");
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("Utilisateur");
		int idUtilisateur = user.getNoUtilisateur();
		String pseudoUtilisateur = user.getPseudo();
		System.out.println(idUtilisateur);
		
		// Récupération des dates et validation avant insertion de l'article
		
		LocalDate dateDebutEncheres = ((Date.valueOf(request.getParameter("dateDebutEnchere"))).toLocalDate());
		System.out.println(dateDebutEncheres);
		LocalDate dateFinEncheres = ((Date.valueOf(request.getParameter("dateFinEnchere"))).toLocalDate());
		System.out.println(dateFinEncheres);
		Boolean validateDateDebut = validationDate(dateDebutEncheres, dateFinEncheres);
		System.out.println("Date Validate: "+validateDateDebut);
		
		//Récupération des entrées utilisateurs de l'article
		
		String nomArticle = request.getParameter("nomArticle").trim();
		String description = request.getParameter("description").trim();
		String categorie = request.getParameter("categorie");
		System.out.println(categorie);
		int miseAPrix = Integer.parseInt(request.getParameter("map"));
		String rue = request.getParameter("nomRue").trim();
		String cp = request.getParameter("codePostal").trim();
		String ville = request.getParameter("ville").trim();
	
	//Construction de l'article pour sa manipulation en dal
		
		ArticleVendu newArticle = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix);
	
	//Construction du retrait pour la manipulation en dal
		
		Retrait retrait = new Retrait(rue,cp,ville);
		
		//Dates invalides
		
		if(validateDateDebut == false)
			{
			request.setAttribute("validateDateDebut", validateDateDebut);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/NouvelleVente.jsp");
			rd.forward(request, response);
			}

		//Dates valides, traitement et ajour de l'article
		
		else
			{
	
			//Ajout de l'article à la base de donnée et récupére tous les articles
			//mis en vente par cet utilisateur
				try 
					{
						ArticleVenduManager manager = ArticleVenduManager.getInstance();
						
						manager.ajoutArticle(newArticle, pseudoUtilisateur, idUtilisateur, categorie);
					}
				catch (BusinessException e) 
					{
						e.printStackTrace();
					}
			
//				Affichage message ajout OK
			validerAjout = true;
			request.setAttribute("validerAjout", validerAjout);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
			rd.forward(request, response);
			}
	}

	
	private boolean validationDate(LocalDate datedebut, LocalDate dateFin) {
		boolean validationDate = false;
			if(datedebut.compareTo(LocalDate.now()) <1 || dateFin.compareTo(datedebut)<1)
				validationDate = false;
			else
				validationDate = true;
		return validationDate;
		
	}
	
}
