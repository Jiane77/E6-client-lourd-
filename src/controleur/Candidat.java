package controleur;

import java.sql.Date;

public class Candidat {

    private int idcandidat;
    private String nom, prenom, email, mdp, tel, adresse, nom_ecole;
    private int premier_connexion, est_etudiant;
    private Date date_prevue_code, date_prevue_permis;

    // CONSTRUCTEUR COMPLET
    public Candidat(int idcandidat, String nom, String prenom, String email,
                    String mdp, int premier_connexion,
                    String tel, String adresse,
                    int est_etudiant, String nom_ecole,
                    Date date_prevue_code, Date date_prevue_permis) {

        this.idcandidat = idcandidat;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.premier_connexion = premier_connexion;
        this.tel = tel;
        this.adresse = adresse;
        this.est_etudiant = est_etudiant;
        this.nom_ecole = nom_ecole;
        this.date_prevue_code = date_prevue_code;
        this.date_prevue_permis = date_prevue_permis;
    }

    // CONSTRUCTEUR SANS ID
    public Candidat(String nom, String prenom, String email,
                    String mdp, int premier_connexion,
                    String tel, String adresse,
                    int est_etudiant, String nom_ecole,
                    Date date_prevue_code, Date date_prevue_permis) {

        this.idcandidat = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.premier_connexion = premier_connexion;
        this.tel = tel;
        this.adresse = adresse;
        this.est_etudiant = est_etudiant;
        this.nom_ecole = nom_ecole;
        this.date_prevue_code = date_prevue_code;
        this.date_prevue_permis = date_prevue_permis;
    }

    // ---------------- GETTERS ----------------

    public int getIdcandidat() { return idcandidat; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getMdp() { return mdp; }
    public int getPremier_connexion() { return premier_connexion; }
    public String getTel() { return tel; }
    public String getAdresse() { return adresse; }
    public int getEst_etudiant() { return est_etudiant; }
    public String getNom_ecole() { return nom_ecole; }
    public Date getDate_prevue_code() { return date_prevue_code; }
    public Date getDate_prevue_permis() { return date_prevue_permis; }

    // ---------------- SETTERS ----------------

    public void setIdcandidat(int idcandidat) { this.idcandidat = idcandidat; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setMdp(String mdp) { this.mdp = mdp; }
    public void setPremier_connexion(int premier_connexion) { this.premier_connexion = premier_connexion; }
    public void setTel(String tel) { this.tel = tel; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public void setEst_etudiant(int est_etudiant) { this.est_etudiant = est_etudiant; }
    public void setNom_ecole(String nom_ecole) { this.nom_ecole = nom_ecole; }
    public void setDate_prevue_code(Date date_prevue_code) { this.date_prevue_code = date_prevue_code; }
    public void setDate_prevue_permis(Date date_prevue_permis) { this.date_prevue_permis = date_prevue_permis; }
}