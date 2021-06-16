package fr.eni.encheres.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/NouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("Utilisateur");
		int idUtilisateur = user.getNoUtilisateur();
		System.out.println(idUtilisateur);
		
		String nomArticle = request.getParameter("nomArticle");
		System.out.println(nomArticle);
		String description = request.getParameter("description");
		System.out.println(description);
		String categorie = request.getParameter("categorie");
		System.out.println(categorie);
		LocalDate dateDebutEncheres = ((Date.valueOf(request.getParameter("dateDebutEnchere"))).toLocalDate());
		System.out.println(dateDebutEncheres);
		LocalDate dateFinEncheres = ((Date.valueOf(request.getParameter("dateFinEnchere"))).toLocalDate());
		System.out.println(dateFinEncheres);
		
	}

}
