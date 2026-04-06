
package modele;

import java.sql.*;
import java.util.ArrayList;

import controleur.*;

public class Modele {

    private static BDD uneBdd = new BDD("root", "", "auto_ecole", "localhost");

    // =======================================================
    // EXECUTION GENERIQUE
    // =======================================================
    public static void executerRequete(String requete) {
        try {
            uneBdd.seConnecter();
            Statement st = uneBdd.getMaconnecxion().createStatement();
            st.execute(requete);
            st.close();
            uneBdd.seDeconnecter();
        } catch (SQLException e) {
            System.out.println("Erreur requete : " + requete);
        }
    }

    // =======================================================
    // USER
    // =======================================================
    public static User selectWhereUser(String email, String mdp) {

        User u = null;

        String sql = "SELECT * FROM user WHERE email='" + email + "' AND mdp='" + mdp + "';";

        try {
            uneBdd.seConnecter();
            Statement st = uneBdd.getMaconnecxion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                u = new User(
                        rs.getInt("iduser"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mdp"),
                        rs.getString("role")
                );
            }

            st.close();
            uneBdd.seDeconnecter();

        } catch (SQLException e) {
            System.out.println("Erreur user");
        }

        return u;
    }

    public static void updateUser(User u) {

        String sql = "UPDATE user SET "
                + "nom='" + u.getNom() + "', "
                + "prenom='" + u.getPrenom() + "', "
                + "email='" + u.getEmail() + "', "
                + "mdp='" + u.getMdp() + "', "
                + "role='" + u.getRole() + "' "
                + "WHERE iduser=" + u.getIduser() + ";";

        executerRequete(sql);
    }

    // =======================================================
    // VEHICULE
    // =======================================================
    public static void insertVehicule(Vehicule v) {

        String sql = "INSERT INTO vehicule (marque, modele, immatriculation, image, etat) VALUES('"
                + v.getMarque() + "','"
                + v.getModele() + "','"
                + v.getImmatriculation() + "','"
                + v.getImage() + "','"
                + v.getEtat() + "');";

        executerRequete(sql);
    }

    public static ArrayList<Vehicule> selectAllVehicules(String filtre) {

        ArrayList<Vehicule> liste = new ArrayList<>();

        String sql = filtre.isEmpty()
                ? "SELECT * FROM vehicule;"
                : "SELECT * FROM vehicule WHERE marque LIKE '%" + filtre + "%' OR modele LIKE '%" + filtre + "%';";

        try {
            uneBdd.seConnecter();
            Statement st = uneBdd.getMaconnecxion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                liste.add(new Vehicule(
                        rs.getInt("idvehicule"),
                        rs.getString("marque"),
                        rs.getString("modele"),
                        rs.getString("immatriculation"),
                        rs.getString("image"),
                        rs.getString("etat")
                ));
            }

            st.close();
            uneBdd.seDeconnecter();

        } catch (SQLException e) {
            System.out.println("Erreur vehicule");
        }

        return liste;
    }

    public static void deleteVehicule(int id) {
        executerRequete("DELETE FROM vehicule WHERE idvehicule=" + id + ";");
    }

    public static void updateVehicule(Vehicule v) {

        String sql = "UPDATE vehicule SET "
                + "marque='" + v.getMarque() + "', "
                + "modele='" + v.getModele() + "', "
                + "immatriculation='" + v.getImmatriculation() + "', "
                + "image='" + (v.getImage() == null ? "" : v.getImage()) + "', "
                + "etat='" + v.getEtat() + "' "
                + "WHERE idvehicule=" + v.getIdvehicule() + ";";

        executerRequete(sql);
    }

    // =======================================================
    // CANDIDAT
    // =======================================================
    public static void insertCandidat(Candidat c) {

        String sql = "INSERT INTO candidats (nom, prenom, email, mdp, tel, adresse, premier_connexion, est_etudiant, nom_ecole, date_prevue_code, date_prevue_permis) VALUES('"
                + c.getNom() + "','"
                + c.getPrenom() + "','"
                + c.getEmail() + "','"
                + c.getMdp() + "','"
                + c.getTel() + "','"
                + c.getAdresse() + "',1,0,NULL,NULL,NULL);";

        executerRequete(sql);
    }

    public static ArrayList<Candidat> selectAllCandidats(String filtre) {

        ArrayList<Candidat> liste = new ArrayList<>();

        String sql = filtre.isEmpty()
                ? "SELECT * FROM candidats;"
                : "SELECT * FROM candidats WHERE nom LIKE '%" + filtre + "%' OR prenom LIKE '%" + filtre + "%';";

        try {
            uneBdd.seConnecter();
            Statement st = uneBdd.getMaconnecxion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                liste.add(new Candidat(
                        rs.getInt("idcandidat"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mdp"),
                        rs.getInt("premier_connexion"),
                        rs.getString("tel"),
                        rs.getString("adresse"),
                        rs.getInt("est_etudiant"),
                        rs.getString("nom_ecole"),
                        rs.getDate("date_prevue_code"),
                        rs.getDate("date_prevue_permis")
                ));
            }

            st.close();
            uneBdd.seDeconnecter();

        } catch (SQLException e) {
            System.out.println("Erreur candidats");
        }

        return liste;
    }

    public static void deleteCandidat(int id) {
        executerRequete("DELETE FROM candidats WHERE idcandidat=" + id + ";");
    }

    public static void updateCandidat(Candidat c) {

        String sql = "UPDATE candidats SET "
                + "nom='" + c.getNom() + "', "
                + "prenom='" + c.getPrenom() + "', "
                + "email='" + c.getEmail() + "', "
                + "mdp='" + c.getMdp() + "', "
                + "tel='" + c.getTel() + "', "
                + "adresse='" + c.getAdresse() + "' "
                + "WHERE idcandidat=" + c.getIdcandidat() + ";";

        executerRequete(sql);
    }

    // =======================================================
    // MONITEUR
    // =======================================================
    public static void insertMoniteur(Moniteur m) {

        String sql = "INSERT INTO moniteur (nom, prenom, email, mdp, tel, adresse, experience, type_permis) VALUES('"
                + m.getNom() + "','"
                + m.getPrenom() + "','"
                + m.getEmail() + "','"
                + m.getMdp() + "','"
                + m.getTel() + "','"
                + m.getAdresse() + "',"
                + m.getExperience() + ",'"
                + m.getType_permis() + "');";

        executerRequete(sql);
    }

    public static ArrayList<Moniteur> selectAllMoniteurs(String filtre) {

        ArrayList<Moniteur> liste = new ArrayList<>();

        String sql = "SELECT * FROM moniteur;";

        try {
            uneBdd.seConnecter();
            Statement st = uneBdd.getMaconnecxion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                liste.add(new Moniteur(
                        rs.getInt("idmoniteur"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mdp"),
                        rs.getString("tel"),
                        rs.getString("adresse"),
                        rs.getInt("experience"),
                        rs.getString("type_permis")
                ));
            }

            st.close();
            uneBdd.seDeconnecter();

        } catch (SQLException e) {
            System.out.println("Erreur moniteur");
        }

        return liste;
    }

    public static void deleteMoniteur(int id) {
        executerRequete("DELETE FROM moniteur WHERE idmoniteur=" + id + ";");
    }

    public static void updateMoniteur(Moniteur m) {

        String sql = "UPDATE moniteur SET "
                + "nom='" + m.getNom() + "', "
                + "prenom='" + m.getPrenom() + "', "
                + "email='" + m.getEmail() + "', "
                + "tel='" + m.getTel() + "', "
                + "adresse='" + m.getAdresse() + "', "
                + "experience=" + m.getExperience() + ", "
                + "type_permis='" + m.getType_permis() + "' "
                + "WHERE idmoniteur=" + m.getIdmoniteur() + ";";

        executerRequete(sql);
    }

    // =======================================================
    // COURS
    // =======================================================
    public static void insertCours(Cours c) {

        String sql = "INSERT INTO cours (date_cours, heure_debut, heure_fin, statut, idvehicule, idmoniteur, idcandidat) VALUES('"
                + c.getDateCours() + "','"
                + c.getHeureDebut() + "','"
                + c.getHeureFin() + "','"
                + c.getStatut() + "',"
                + c.getIdvehicule() + ","
                + c.getIdmoniteur() + ","
                + c.getIdcandidat() + ");";

        executerRequete(sql);
    }

    public static ArrayList<Cours> selectAllCours(String filtre) {

        ArrayList<Cours> liste = new ArrayList<>();

        try {
            uneBdd.seConnecter();
            Statement st = uneBdd.getMaconnecxion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cours;");

            while (rs.next()) {

                liste.add(new Cours(
                        rs.getInt("idcours"),
                        rs.getDate("date_cours"),
                        rs.getTime("heure_debut"),
                        rs.getTime("heure_fin"),
                        rs.getString("statut"),
                        rs.getInt("idvehicule"),
                        rs.getInt("idmoniteur"),
                        rs.getInt("idcandidat")
                ));
            }

            st.close();
            uneBdd.seDeconnecter();

        } catch (SQLException e) {
            System.out.println("Erreur cours");
        }

        return liste;
    }

    public static void deleteCours(int id) {
        executerRequete("DELETE FROM cours WHERE idcours=" + id + ";");
    }

    public static void updateCours(Cours c) {

        String sql = "UPDATE cours SET "
                + "date_cours='" + c.getDateCours() + "', "
                + "heure_debut='" + c.getHeureDebut() + "', "
                + "heure_fin='" + c.getHeureFin() + "', "
                + "statut='" + c.getStatut() + "', "
                + "idvehicule=" + c.getIdvehicule() + ", "
                + "idmoniteur=" + c.getIdmoniteur() + ", "
                + "idcandidat=" + c.getIdcandidat() + " "
                + "WHERE idcours=" + c.getIdcours() + ";";

        executerRequete(sql);
    }
}