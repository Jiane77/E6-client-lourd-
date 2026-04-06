package controleur;

public class Vehicule {

    private int idvehicule;
    private String marque;
    private String modele;
    private String immatriculation;
    private String image;
    private String etat;

    // =======================================================
    // CONSTRUCTEUR COMPLET
    // =======================================================
    public Vehicule(int idvehicule, String marque, String modele,
                     String immatriculation, String image, String etat) {

        this.idvehicule = idvehicule;
        this.marque = marque;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.image = image;
        this.etat = etat;
    }

    // =======================================================
    // CONSTRUCTEUR SANS ID (INSERT)
    // =======================================================
    public Vehicule(String marque, String modele,
                    String immatriculation, String image, String etat) {

        this.idvehicule = 0;
        this.marque = marque;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.image = image;
        this.etat = etat;
    }

    // =======================================================
    // GETTERS
    // =======================================================
    public int getIdvehicule() {
        return idvehicule;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public String getImage() {
        return image;
    }

    public String getEtat() {
        return etat;
    }

    // =======================================================
    // SETTERS
    // =======================================================
    public void setIdvehicule(int idvehicule) {
        this.idvehicule = idvehicule;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}