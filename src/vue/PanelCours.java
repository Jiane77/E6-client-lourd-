package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Controleur;
import controleur.Cours;
import controleur.Tableau;

public class PanelCours extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();

    private JTextField txtDate = new JTextField();        // format: 2026-02-20
    private JTextField txtHeureDebut = new JTextField();  // format: 10:00:00
    private JTextField txtHeureFin = new JTextField();    // format: 11:00:00
    private JTextField txtStatut = new JTextField();

    private JTextField txtVehicule = new JTextField();
    private JTextField txtMoniteur = new JTextField();
    private JTextField txtCandidat = new JTextField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JButton btModifier = new JButton("Modifier");

    private JTable tableCours;
    private JScrollPane scrollCours;
    private Tableau unTableau;

    private JLabel lbNBCours = new JLabel("");

    public PanelCours(String titre) {
        super(titre);

        this.panelForm.setBounds(50, 100, 320, 300);
        this.panelForm.setBackground(Color.gray);
        this.panelForm.setLayout(new GridLayout(8, 2, 10, 10));

        this.panelForm.add(new JLabel("Date (YYYY-MM-DD) :"));
        this.panelForm.add(this.txtDate);

        this.panelForm.add(new JLabel("Heure début :"));
        this.panelForm.add(this.txtHeureDebut);

        this.panelForm.add(new JLabel("Heure fin :"));
        this.panelForm.add(this.txtHeureFin);

        this.panelForm.add(new JLabel("Statut :"));
        this.panelForm.add(this.txtStatut);

        this.panelForm.add(new JLabel("ID Véhicule :"));
        this.panelForm.add(this.txtVehicule);

        this.panelForm.add(new JLabel("ID Moniteur :"));
        this.panelForm.add(this.txtMoniteur);

        this.panelForm.add(new JLabel("ID Candidat :"));
        this.panelForm.add(this.txtCandidat);

        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        this.panelForm.add(this.btSupprimer);
        this.panelForm.add(this.btModifier);

        this.add(this.panelForm);

        btAnnuler.addActionListener(this);
        btValider.addActionListener(this);
        btModifier.addActionListener(this);
        btSupprimer.addActionListener(this);

        String entetes[] = {"ID", "Date", "Début", "Fin", "Statut", "Vehicule", "Moniteur", "Candidat"};

        this.unTableau = new Tableau(obtenirDonnees(), entetes);
        this.tableCours = new JTable(this.unTableau);

        this.scrollCours = new JScrollPane(this.tableCours);
        this.scrollCours.setBounds(420, 120, 600, 250);
        this.add(this.scrollCours);

        this.lbNBCours.setBounds(420, 380, 300, 20);
        this.add(this.lbNBCours);

        actualiserLabel();
    }

    private Object[][] obtenirDonnees() {

        ArrayList<Cours> lesCours = Controleur.selectAllCours("");

        Object[][] matrice = new Object[lesCours.size()][8];

        int i = 0;
        for (Cours c : lesCours) {

            matrice[i][0] = c.getIdcours();
            matrice[i][1] = c.getDateCours();
            matrice[i][2] = c.getHeureDebut();
            matrice[i][3] = c.getHeureFin();
            matrice[i][4] = c.getStatut();
            matrice[i][5] = c.getIdvehicule();
            matrice[i][6] = c.getIdmoniteur();
            matrice[i][7] = c.getIdcandidat();

            i++;
        }

        return matrice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btAnnuler) {
            viderChamps();
        }

        else if (e.getSource() == btValider) {

            try {
                Cours c = new Cours(
                        Date.valueOf(txtDate.getText()),
                        Time.valueOf(txtHeureDebut.getText()),
                        Time.valueOf(txtHeureFin.getText()),
                        txtStatut.getText(),
                        Integer.parseInt(txtVehicule.getText()),
                        Integer.parseInt(txtMoniteur.getText()),
                        Integer.parseInt(txtCandidat.getText())
                );

                Controleur.insertCours(c);
                unTableau.setDonnees(obtenirDonnees());
                actualiserLabel();
                viderChamps();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur de saisie !");
            }
        }

        else if (e.getSource() == btSupprimer) {

            int ligne = tableCours.getSelectedRow();
            int id = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());

            Controleur.deleteCours(id);

            unTableau.setDonnees(obtenirDonnees());
            actualiserLabel();
        }

        else if (e.getSource() == btModifier) {

            int ligne = tableCours.getSelectedRow();
            int id = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());

            try {
                Cours c = new Cours(
                        id,
                        Date.valueOf(txtDate.getText()),
                        Time.valueOf(txtHeureDebut.getText()),
                        Time.valueOf(txtHeureFin.getText()),
                        txtStatut.getText(),
                        Integer.parseInt(txtVehicule.getText()),
                        Integer.parseInt(txtMoniteur.getText()),
                        Integer.parseInt(txtCandidat.getText())
                );

                Controleur.updateCours(c);

                unTableau.setDonnees(obtenirDonnees());
                actualiserLabel();
                viderChamps();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur modification !");
            }
        }
    }

    public void viderChamps() {
        txtDate.setText("");
        txtHeureDebut.setText("");
        txtHeureFin.setText("");
        txtStatut.setText("");
        txtVehicule.setText("");
        txtMoniteur.setText("");
        txtCandidat.setText("");
    }

    public void actualiserLabel() {
        lbNBCours.setText("Nombre de cours : " + unTableau.getRowCount());
    }
}