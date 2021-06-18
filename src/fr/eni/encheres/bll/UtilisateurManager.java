package fr.eni.encheres.bll;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	private UtilisateurDAO utilisateurDAO;

	
	
	// Méthode de BLL pour inserer une nouvelle inscription

	private static UtilisateurManager instance = null;
	
	
	private UtilisateurManager() throws BusinessException {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public static synchronized UtilisateurManager getInstance() throws BusinessException {
		if(instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	// Méthode de BLL pour inserer une nouvelle inscription	
	public boolean AjouterInscription(Utilisateur utilisateur) throws BusinessException {
		
		boolean inscriValideP = false;
		boolean inscriValideE = false;
		
		inscriValideP = validerPseudo(utilisateur.getPseudo());
		inscriValideE = validerMail(utilisateur.getEmail());
		
		
		if(inscriValideP == false & inscriValideE == false) {
			this.utilisateurDAO.insertInscription(utilisateur);
			return true;
		}
		else {
			return false;
		}
	}
	
	

	// Méthode qui vérifie que le pseudo n'est pas vide ou trop long
			private boolean validerPseudo(String nomPseudo) {
				boolean StatusValidation = false;
				if(nomPseudo == null | nomPseudo.trim().length() > 30) {
					StatusValidation = true;
				}
				
					List<String> listepseudo;
					try {
						listepseudo = this.utilisateurDAO.selectPseudo();
						if(listepseudo.contains(nomPseudo)) {
							StatusValidation = true;
						}
					} catch (BusinessException e) {
						e.printStackTrace();
					}
					return StatusValidation;
			}
	
	
	
	
	// Méthode qui vérifie si le mail n'est pas vide ou trop long
	private boolean validerMail(String nomMail) {
		boolean StatusValidation = false;
		if(nomMail == null | nomMail.trim().length() > 60) {
			StatusValidation = true;
		}
		List<String> listeEmail;
		try {
			listeEmail = this.utilisateurDAO.selectEmail();
			if(listeEmail.contains(nomMail)) {
				StatusValidation = true;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
			return StatusValidation;
	}
	
	//Méthode de connection
			public boolean connection(Utilisateur utilisateur) throws BusinessException{
				Utilisateur u;
				boolean testConnection = false;
				System.out.println("je suis dans ma bll avant d'aller dans la DAL");
				testConnection = validerPseudo(utilisateur.getPseudo());
				System.out.println(testConnection);
				if (testConnection == true)
				{
					u = this.utilisateurDAO.SelectUser(utilisateur);
					if(utilisateur.getMotDePasse().equals(u.getMotDePasse())) {
						testConnection = true;
					}
					else {
						testConnection = false;
					}
				}
				return testConnection;
				
			}
			
	//méthode de récuperation d'un utilisateur
			
		public Utilisateur recuperationUtilisateur(Utilisateur utilisateur) throws BusinessException {
			Utilisateur u = null;
			u = this.utilisateurDAO.SelectUser(utilisateur);
			return u;
		}
		
	//méthode vérification de mail
		
		public boolean verifmail(String email) throws BusinessException 
		{
			return validerMail(email);
		}
		
	//méthode pour envoyer un mail oublie mot de passe
		
		public void sentmail(String userEmail) throws Exception{
			System.out.println("message en cours d'envoie");
			Properties properties = new Properties();

			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			
			
			final String monEmail = "enienchere@gmail.com";
			final String password = "G&EtN5tT3KBNDjox";
			
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(monEmail, password);
				}
			});
			
			//Message message = prepareMessage(session,monEmail, userEmail);
			
			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(monEmail));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
				message.setSubject("Réinitialisation de votre mot de passe");
				message.setText("Bonjour, \n cliquez sur le lien ci-dessous pour changer votre mot de passe. \n http://localhost:8080/ENIEncheres/Reinitialisation?userEmail="+userEmail);
				
				Transport.send(message);
				
				System.out.println("message envoyé");
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}			
		}
		
	

	
}

	
