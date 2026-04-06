package controleur;

import java.sql.Date;
import java.sql.Time;

public class Cours {

    private int idcours;
    private Date dateCours;
    private Time heureDebut;
    private Time heureFin;
    private String statut;

    private int idvehicule;
    private int idmoniteur;
    private int idcandidat;

    // Constructeur complet
    public Cours(int idcours, Date dateCours, Time heureDebut, Time heureFin,
                 String statut, int idvehicule, int idmoniteur, int idcandidat) {
        this.idcours = idcours;
        this.dateCours = dateCours;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.statut = statut;
        this.idvehicule = idvehicule;
        this.idmoniteur = idmoniteur;
        this.idcandidat = idcandidat;
    }

    // Constructeur sans ID
    public Cours(Date dateCours, Time heureDebut, Time heureFin,
                 String statut, int idvehicule, int idmoniteur, int idcandidat) {
        this.dateCours = dateCours;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.statut = statut;
        this.idvehicule = idvehicule;
        this.idmoniteur = idmoniteur;
        this.idcandidat = idcandidat;
    }

    // Getters / Setters
    public int getIdcours() { return idcours; }
    public void setIdcours(int idcours) { this.idcours = idcours; }

    public Date getDateCours() { return dateCours; }
    public void setDateCours(Date dateCours) { this.dateCours = dateCours; }

    public Time getHeureDebut() { return heureDebut; }
    public void setHeureDebut(Time heureDebut) { this.heureDebut = heureDebut; }

    public Time getHeureFin() { return heureFin; }
    public void setHeureFin(Time heureFin) { this.heureFin = heureFin; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public int getIdvehicule() { return idvehicule; }
    public void setIdvehicule(int idvehicule) { this.idvehicule = idvehicule; }

    public int getIdmoniteur() { return idmoniteur; }
    public void setIdmoniteur(int idmoniteur) { this.idmoniteur = idmoniteur; }

    public int getIdcandidat() { return idcandidat; }
    public void setIdcandidat(int idcandidat) { this.idcandidat = idcandidat; }
}