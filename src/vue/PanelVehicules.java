package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Controleur;
import controleur.Vehicule;
import controleur.Tableau;

public class PanelVehicules extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();

    private JTextField txtMarque = new JTextField();
    private JTextField txtModele = new JTextField();
    private JTextField txtImmatriculation = new JTextField();
    private JTextField txtImage = new JTextField();
    private JTextField txtEtat = new JTextField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JButton btModifier = new JButton("Modifier");

    private JTable tableVehicules;
    private JScrollPane scrollVehicules;
    private Tableau unTableau;

    private JLabel lbNBVehicules = new JLabel("");

    public PanelVehicules(String titre) {
        super(titre);

        this.panelForm.setBounds(50, 100, 300, 250);
        this.panelForm.setBackground(Color.gray);
        this.panelForm.setLayout(new GridLayout(6, 2, 10, 10));

        this.panelForm.add(new JLabel("Marque :"));
        this.panelForm.add(this.txtMarque);

        this.panelForm.add(new JLabel("Modele :"));
        this.panelForm.add(this.txtModele);

        this.panelForm.add(new JLabel("Immatriculation :"));
        this.panelForm.add(this.txtImmatriculation);

        this.panelForm.add(new JLabel("Image :"));
        this.panelForm.add(this.txtImage);

        this.panelForm.add(new JLabel("Etat :"));
        this.panelForm.add(this.txtEtat);

        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        this.panelForm.add(this.btSupprimer);
        this.panelForm.add(this.btModifier);

        this.add(this.panelForm);

        btAnnuler.addActionListener(this);
        btValider.addActionListener(this);
        btModifier.addActionListener(this);
        btSupprimer.addActionListener(this);

        String entetes[] = {"ID", "Marque", "Modele", "Immatriculation", "Etat"};

        this.unTableau = new Tableau(obtenirDonnees(), entetes);
        this.tableVehicules = new JTable(this.unTableau);

        this.scrollVehicules = new JScrollPane(this.tableVehicules);
        this.scrollVehicules.setBounds(450, 120, 500, 250);
        this.add(this.scrollVehicules);

        this.lbNBVehicules.setBounds(450, 380, 300, 20);
        this.add(this.lbNBVehicules);

        actualiserLabel();
    }

    private Object[][] obtenirDonnees() {

        ArrayList<Vehicule> lesVehicules = Controleur.selectAllVehicules("");

        Object[][] matrice = new Object[lesVehicules.size()][5];

        int i = 0;
        for (Vehicule v : lesVehicules) {

            matrice[i][0] = v.getIdvehicule();
            matrice[i][1] = v.getMarque();
            matrice[i][2] = v.getModele();
            matrice[i][3] = v.getImmatriculation();
            matrice[i][4] = v.getEtat();

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

            Vehicule v = new Vehicule(
                    txtMarque.getText(),
                    txtModele.getText(),
                    txtImmatriculation.getText(),
                    txtImage.getText(),
                    txtEtat.getText()
            );

            Controleur.insertVehicule(v);
            unTableau.setDonnees(obtenirDonnees());
            actualiserLabel();
            viderChamps();
        }

        else if (e.getSource() == btSupprimer) {

            int ligne = tableVehicules.getSelectedRow();

            if (ligne >= 0) {
                int id = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());

                Controleur.deleteVehicule(id);

                unTableau.setDonnees(obtenirDonnees());
                actualiserLabel();
                viderChamps();
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un véhicule !");
            }
        }

        else if (e.getSource() == btModifier) {

            int ligne = tableVehicules.getSelectedRow();

            if (ligne >= 0) {
                int id = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());

                Vehicule v = new Vehicule(
                        id,
                        txtMarque.getText(),
                        txtModele.getText(),
                        txtImmatriculation.getText(),
                        txtImage.getText(),
                        txtEtat.getText()
                );

                Controleur.updateVehicule(v);

                unTableau.setDonnees(obtenirDonnees());
                actualiserLabel();
                viderChamps();
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un véhicule !");
            }
        }
    }

    public void viderChamps() {
        txtMarque.setText("");
        txtModele.setText("");
        txtImmatriculation.setText("");
        txtImage.setText("");
        txtEtat.setText("");
    }

    public void actualiserLabel() {
        lbNBVehicules.setText("Nombre de véhicules : " + unTableau.getRowCount());
    }
}