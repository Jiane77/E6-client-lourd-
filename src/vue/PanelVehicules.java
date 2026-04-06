package vue;

import java.awt.*;
import java.awt.event.*;
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

    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JButton btModifier = new JButton("Modifier");

    private JTable tableVehicules;
    private JScrollPane scroll;
    private Tableau unTableau;

    private JLabel lbNBVehicules = new JLabel("");

    public PanelVehicules(String titre) {

        super(titre);

        // ================= FILTRE =================
        JPanel panelFiltre = new JPanel(new GridLayout(1, 3, 5, 5));
        panelFiltre.setBounds(450, 80, 460, 30);

        panelFiltre.add(new JLabel("Filtrer :"));
        panelFiltre.add(txtFiltre);
        panelFiltre.add(btFiltrer);

        this.add(panelFiltre);

        // ================= FORM =================
        panelForm.setBounds(50, 100, 300, 260);
        panelForm.setBackground(Color.gray);
        panelForm.setLayout(new GridLayout(10, 2, 10, 10));

        panelForm.add(new JLabel("Marque :"));
        panelForm.add(txtMarque);

        panelForm.add(new JLabel("Modele :"));
        panelForm.add(txtModele);

        panelForm.add(new JLabel("Immatriculation :"));
        panelForm.add(txtImmatriculation);

        panelForm.add(new JLabel("Image :"));
        panelForm.add(txtImage);

        panelForm.add(new JLabel("Etat :"));
        panelForm.add(txtEtat);

        panelForm.add(btAnnuler);
        panelForm.add(btValider);
        panelForm.add(btSupprimer);
        panelForm.add(btModifier);

        this.add(panelForm);

        btSupprimer.setEnabled(false);
        btModifier.setEnabled(false);

        // ================= TABLE =================
        String[] entetes = {"ID", "Marque", "Modele", "Immatriculation", "Etat"};

        unTableau = new Tableau(obtenirDonnees(""), entetes);
        tableVehicules = new JTable(unTableau);

        scroll = new JScrollPane(tableVehicules);
        scroll.setBounds(450, 120, 500, 250);
        this.add(scroll);

        // ================= CLICK TABLE =================
        tableVehicules.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int ligne = tableVehicules.getSelectedRow();

                txtMarque.setText(unTableau.getValueAt(ligne, 1).toString());
                txtModele.setText(unTableau.getValueAt(ligne, 2).toString());
                txtImmatriculation.setText(unTableau.getValueAt(ligne, 3).toString());
                txtEtat.setText(unTableau.getValueAt(ligne, 4).toString());

                btModifier.setEnabled(true);
                btSupprimer.setEnabled(true);
            }
        });

        // ================= LABEL =================
        lbNBVehicules.setBounds(450, 380, 300, 20);
        this.add(lbNBVehicules);

        actualiserLabel();

        // ================= EVENTS =================
        btAnnuler.addActionListener(this);
        btValider.addActionListener(this);
        btModifier.addActionListener(this);
        btSupprimer.addActionListener(this);
        btFiltrer.addActionListener(this);
    }

    // ================= DATA =================
    private Object[][] obtenirDonnees(String filtre) {

        ArrayList<Vehicule> list = Controleur.selectAllVehicules(filtre);

        Object[][] matrice = new Object[list.size()][5];

        int i = 0;

        for (Vehicule v : list) {

            matrice[i][0] = v.getIdvehicule();
            matrice[i][1] = v.getMarque();
            matrice[i][2] = v.getModele();
            matrice[i][3] = v.getImmatriculation();
            matrice[i][4] = v.getEtat();

            i++;
        }

        return matrice;
    }

    // ================= ACTION =================
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btAnnuler) {
            viderChamps();
        }

        else if (e.getSource() == btFiltrer) {
            unTableau.setDonnees(obtenirDonnees(txtFiltre.getText()));
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

            unTableau.setDonnees(obtenirDonnees(""));
            actualiserLabel();
            viderChamps();
        }

        else if (e.getSource() == btSupprimer) {

            int ligne = tableVehicules.getSelectedRow();

            if (ligne == -1) return;

            int id = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());

            Controleur.deleteVehicule(id);

            unTableau.setDonnees(obtenirDonnees(""));
            actualiserLabel();
            viderChamps();
        }

        else if (e.getSource() == btModifier) {

            int ligne = tableVehicules.getSelectedRow();

            if (ligne == -1) return;

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

            unTableau.setDonnees(obtenirDonnees(""));
            actualiserLabel();
            viderChamps();
        }
    }

    // ================= RESET =================
    public void viderChamps() {

        txtMarque.setText("");
        txtModele.setText("");
        txtImmatriculation.setText("");
        txtImage.setText("");
        txtEtat.setText("");

        btModifier.setEnabled(false);
        btSupprimer.setEnabled(false);
    }

    // ================= LABEL =================
    public void actualiserLabel() {
        lbNBVehicules.setText("Nombre de véhicules : " + unTableau.getRowCount());
    }
}