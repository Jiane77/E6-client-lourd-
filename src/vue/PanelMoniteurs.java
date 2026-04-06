package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        this.panelForm.setBounds(50, 100, 300, 200);
        this.panelForm.setBackground(Color.gray);
        this.panelForm.setLayout(new GridLayout(5, 2, 10, 10));

        this.panelForm.add(new JLabel("Nom :"));
        this.panelForm.add(this.txtNom);

        this.panelForm.add(new JLabel("Prenom :"));
        this.panelForm.add(this.txtPrenom);

        this.panelForm.add(new JLabel("Email :"));
        this.panelForm.add(this.txtEmail);

        this.panelForm.add(new JLabel("Tel :"));
        this.panelForm.add(this.txtTel);

        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        this.panelForm.add(this.btSupprimer);
        this.panelForm.add(this.btModifier);

        this.add(this.panelForm);

        btAnnuler.addActionListener(this);
        btValider.addActionListener(this);
        btModifier.addActionListener(this);
        btSupprimer.addActionListener(this);

        String entetes[] = {"ID", "Nom", "Prenom", "Email", "Tel"};

        this.unTableau = new Tableau(obtenirDonnees(), entetes);
        this.tableMoniteurs = new JTable(this.unTableau);

        this.scrollMoniteurs = new JScrollPane(this.tableMoniteurs);
        this.scrollMoniteurs.setBounds(450, 120, 450, 240);
        this.add(this.scrollMoniteurs);

        this.lbNBMoniteurs.setBounds(450, 380, 300, 20);
        this.add(this.lbNBMoniteurs);

        actualiserLabel();
    }

    private Object[][] obtenirDonnees() {

        ArrayList<Moniteur> lesMoniteurs = Controleur.selectAllMoniteurs("");

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

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btAnnuler) {
            viderChamps();
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
            unTableau.setDonnees(obtenirDonnees());
            actualiserLabel();
            viderChamps();
        }

        else if (e.getSource() == btSupprimer) {

            int ligne = tableMoniteurs.getSelectedRow();
            int id = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());

            Controleur.deleteMoniteur(id);

            unTableau.setDonnees(obtenirDonnees());
            actualiserLabel();
            viderChamps();
        }

        else if (e.getSource() == btModifier) {

            int ligne = tableMoniteurs.getSelectedRow();
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

            unTableau.setDonnees(obtenirDonnees());
            actualiserLabel();
            viderChamps();
        }
    }

    public void viderChamps() {
        txtNom.setText("");
        txtPrenom.setText("");
        txtEmail.setText("");
        txtTel.setText("");
    }

    public void actualiserLabel() {
        lbNBMoniteurs.setText("Nombre de moniteurs : " + unTableau.getRowCount());
    }
}