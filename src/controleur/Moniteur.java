package controleur;

public class Moniteur {

    private int idmoniteur;
    private String nom, prenom, email, mdp, tel, adresse, type_permis;
    private int experience;

    public Moniteur(int idmoniteur, String nom, String prenom, String email,
                    String mdp, String tel, String adresse,
                    int experience, String type_permis) {

        this.idmoniteur = idmoniteur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.tel = tel;
        this.adresse = adresse;
        this.experience = experience;
        this.type_permis = type_permis;
    }

    public Moniteur(String nom, String prenom, String email,
                    String mdp, String tel, String adresse,
                    int experience, String type_permis) {

        this.idmoniteur = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.tel = tel;
        this.adresse = adresse;
        this.experience = experience;
        this.type_permis = type_permis;
    }

    // GETTERS
    public int getIdmoniteur() { return idmoniteur; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getMdp() { return mdp; }
    public String getTel() { return tel; }
    public String getAdresse() { return adresse; }
    public int getExperience() { return experience; }
    public String getType_permis() { return type_permis; }

    // SETTERS
    public void setIdmoniteur(int idmoniteur) { this.idmoniteur = idmoniteur; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setMdp(String mdp) { this.mdp = mdp; }
    public void setTel(String tel) { this.tel = tel; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public void setExperience(int experience) { this.experience = experience; }
    public void setType_permis(String type_permis) { this.type_permis = type_permis; }
}