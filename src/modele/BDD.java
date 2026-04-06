package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDD {

    private String user, mdp, bdd, serveur;
    private Connection maConnexion;

    public BDD(String user, String mdp, String bdd, String serveur) {
        this.user = user;
        this.mdp = mdp;
        this.bdd = bdd;
        this.serveur = serveur;
        this.maConnexion = null;
    }

    public void chargerPilote() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException exp) {
            System.out.println("Absence du pilote JDBC");
            exp.printStackTrace();
        }
    }

    public void seConnecter() {

        String url = "jdbc:mysql://" + this.serveur + ":3306/" + this.bdd;

        this.chargerPilote();

        try {
            this.maConnexion = DriverManager.getConnection(url, this.user, this.mdp);
            System.out.println("Connexion OK");
        } catch (SQLException exp) {
            System.out.println("Impossible de se connecter : " + url);
            exp.printStackTrace();
            this.maConnexion = null;
        }
    }

    public void seDeconnecter() {
        try {
            if (this.maConnexion != null) {
                this.maConnexion.close();
            }
        } catch (SQLException exp) {
            System.out.println("Erreur déconnexion serveur");
        }
    }

    public Connection getMaconnecxion() {
        return this.maConnexion;
    }
}