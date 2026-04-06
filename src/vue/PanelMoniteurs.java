package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Controleur;
import controleur.Moniteur;
import controleur.Tableau;

public class PanelMoniteurs extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();

    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtTel = new JTextField();

    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JButton btModifier = new JButton("Modifier");

    private JTable tableMoniteurs;
    private JScrollPane scrollMoniteurs;
    private Tableau unTableau;

    private JLabel lbNBMoniteurs = new JLabel("");

    public PanelMoniteurs(String titre) {

        super(titre);

        // ================= FILTRE =================
        JPanel panelFiltre = new JPanel(new GridLayout(1, 3, 5, 5));
        panelFiltre.setBounds(450, 80, 460, 30);

        panelFiltre.add(new JLabel("Filtrer :"));
        panelFiltre.add(txtFiltre);
        panelFiltre.add(btFiltrer);

        this.add(panelFiltre);

        // ================= FORM =================
        this.panelForm.setBounds(50, 100, 300, 220);
        this.panelForm.setBackground(Color.gray);
        this.panelForm.setLayout(new GridLayout(8, 2, 10, 10));

        this.panelForm.add(new JLabel("Nom :"));
        this.panelForm.add(this.txtNom);

        this.panelForm.add(new JLabel("Prenom :"));
        this.panelForm.add(this.txtPrenom);

        this.panelForm.add(new JLabel("Email :"));
        this.panelForm.add(this.txtEmail);

        this.panelForm.add(new JLabel("Tel :"));
        this.panelForm.add(this.txtTel);

        this.panelForm.add(btAnnuler);
        this.panelForm.add(btValider);
        this.panelForm.add(btSupprimer);
        this.panelForm.add(btModifier);

        this.add(this.panelForm);

        btSupprimer.setEnabled(false);
        btModifier.setEnabled(false);

        // ================= TABLE =================
        String entetes[] = {"ID", "Nom", "Prenom", "Email", "Tel"};

        this.unTableau = new Tableau(obtenirDonnees(""), entetes);
        this.tableMoniteurs = new JTable(this.unTableau);

        this.scrollMoniteurs = new JScrollPane(this.tableMoniteurs);
        this.scrollMoniteurs.setBounds(450, 120, 450, 240);
        this.add(this.scrollMoniteurs);

        // ================= CLICK TABLE =================
        tableMoniteurs.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int ligne = tableMoniteurs.getSelectedRow();

                txtNom.setText(unTableau.getValueAt(ligne, 1).toString());
                txtPrenom.setText(unTableau.getValueAt(ligne, 2).toString());
                txtEmail.setText(unTableau.getValueAt(ligne, 3).toString());
                txtTel.setText(unTableau.getValueAt(ligne, 4).toString());

                btModifier.setEnabled(true);
                btSupprimer.setEnabled(true);
            }
        });

        // ================= LABEL =================
        this.lbNBMoniteurs.setBounds(450, 380, 300, 20);
        this.add(this.lbNBMoniteurs);

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

        ArrayList<Moniteur> lesMoniteurs = Controleur.selectAllMoniteurs(filtre);

        Object[][] matrice = new Object[lesMoniteurs.size()][5];

        int i = 0;
        for (Moniteur m : lesMoniteurs) {

            matrice[i][0] = m.getIdmoniteur();
            matrice[i][1] = m.getNom();
            matrice[i][2] = m.getPrenom();
            matrice[i][3] = m.getEmail();
            matrice[i][4] = m.getTel();

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

            Moniteur m = new Moniteur(
                    txtNom.getText(),
                    txtPrenom.getText(),
                    txtEmail.getText(),
                    "1234",
                    txtTel.getText(),
                    "paris",
                    0,
                    "B"
            );

            Controleur.insertMoniteur(m);

            unTableau.setDonnees(obtenirDonnees(""));
            actualiserLabel();
            viderChamps();
        }

        else if (e.getSource() == btSupprimer) {

            int ligne = tableMoniteurs.getSelectedRow();

            if (ligne == -1) return;

            int id = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());

            Controleur.deleteMoniteur(id);

            unTableau.setDonnees(obtenirDonnees(""));
            actualiserLabel();
            viderChamps();
        }

        else if (e.getSource() == btModifier) {

            int ligne = tableMoniteurs.getSelectedRow();

            if (ligne == -1) return;

            int id = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());

            Moniteur m = new Moniteur(
                    id,
                    txtNom.getText(),
                    txtPrenom.getText(),
                    txtEmail.getText(),
                    "1234",
                    txtTel.getText(),
                    "paris",
                    0,
                    "B"
            );

            Controleur.updateMoniteur(m);

            unTableau.setDonnees(obtenirDonnees(""));
            actualiserLabel();
            viderChamps();
        }
    }

    // ================= RESET =================
    public void viderChamps() {

        txtNom.setText("");
        txtPrenom.setText("");
        txtEmail.setText("");
        txtTel.setText("");

        btModifier.setEnabled(false);
        btSupprimer.setEnabled(false);
    }

    // ================= LABEL =================
    public void actualiserLabel() {
        lbNBMoniteurs.setText("Nombre de moniteurs : " + unTableau.getRowCount());
    }
}