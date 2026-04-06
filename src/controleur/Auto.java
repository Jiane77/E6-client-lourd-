package controleur;

import vue.VueConnexion;
import vue.VueGenerale;

public class Auto {
	
	private static VueConnexion uneVueConnexion ; 
	private static VueGenerale uneVueGenerale;
	
	private static User userConnecte;
	public static void main(String[] args) {
		uneVueConnexion = new VueConnexion();
	}
	public static User getUserConnecte () {
		return userConnecte;
	}
	public static void setUserConnecte(User unUser) {
		userConnecte =unUser;
	}
	public static void rendreVisibleVueConnexion(boolean action) {
		uneVueConnexion.setVisible(action);
	}
public static void creerDetruireVueGenerale(boolean action) {
	if(action==true) {
		uneVueGenerale =new VueGenerale();
	}
	else {
		uneVueGenerale.dispose();
	}
}
 

}
