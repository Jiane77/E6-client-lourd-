package controleur;

import java.util.ArrayList;
import modele.Modele;

public class Controleur {

    // ---------------- USER ----------------
    public static User selectWhereUser(String email, String mdp) {
        return Modele.selectWhereUser(email, mdp);
    }

    public static void updateUser(User unUser) {
        Modele.updateUser(unUser);
    }

    // ---------------- CANDIDAT ----------------
    public static void insertCandidat(Candidat unCandidat) {
        Modele.insertCandidat(unCandidat);
    }

    public static ArrayList<Candidat> selectAllCandidats(String filtre) {
        return Modele.selectAllCandidats(filtre);
    }

    public static void deleteCandidat(int idcandidat) {
        Modele.deleteCandidat(idcandidat);
    }

    public static void updateCandidat(Candidat unCandidat) {
        Modele.updateCandidat(unCandidat);
    }

    // ---------------- MONITEUR ----------------
    public static void insertMoniteur(Moniteur unMoniteur) {
        Modele.insertMoniteur(unMoniteur);
    }

    public static ArrayList<Moniteur> selectAllMoniteurs(String filtre) {
        return Modele.selectAllMoniteurs(filtre);
    }

    public static void deleteMoniteur(int idmoniteur) {
        Modele.deleteMoniteur(idmoniteur);
    }

    public static void updateMoniteur(Moniteur unMoniteur) {
        Modele.updateMoniteur(unMoniteur);
    }

    // ---------------- VEHICULE ----------------
    public static void insertVehicule(Vehicule unVehicule) {
        Modele.insertVehicule(unVehicule);
    }

    public static ArrayList<Vehicule> selectAllVehicules(String filtre) {
        return Modele.selectAllVehicules(filtre);
    }

    public static void deleteVehicule(int idvehicule) {
        Modele.deleteVehicule(idvehicule);
    }

    public static void updateVehicule(Vehicule unVehicule) {
        Modele.updateVehicule(unVehicule);
    }

    // ---------------- COURS ----------------
    public static void insertCours(Cours unCours) {
        Modele.insertCours(unCours);
    }

    public static ArrayList<Cours> selectAllCours(String filtre) {
        return Modele.selectAllCours(filtre);
    }

    public static void deleteCours(int idcours) {
        Modele.deleteCours(idcours);
    }

    public static void updateCours(Cours unCours) {
        Modele.updateCours(unCours);
    }
}