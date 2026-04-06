package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Candidat;
import controleur.Controleur;
import controleur.Tableau;

public class PanelCandidats extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();

    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtMdp = new JTextField();
    private JTextField txtTel = new JTextField();
    private JTextField txtAdresse = new JTextField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JButton btModifier = new JButton("Modifier");

    private JTable tableCandidats;
    private JScrollPane scroll;
    private Tableau unTableau;

    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");

    private JLabel lbNb = new JLabel("");

    public PanelCandidats(String titre) {

        super(titre);

        // ================= FILTRE =================
        JPanel panelFiltre = new JPanel(new GridLayout(1, 3, 5, 5));
        panelFiltre.setBounds(450, 80, 460, 30);

        panelFiltre.add(new JLabel("Filtrer :"));
        panelFiltre.add(txtFiltre);
        panelFiltre.add(btFiltrer);

        this.add(panelFiltre);

        // ================= FORM (MODIF MINIMALE) =================
        panelForm.setBounds(50, 100, 300, 320);
        panelForm.setLayout(new GridLayout(11, 2, 10, 10));

        panelForm.add(new JLabel("Nom :"));
        panelForm.add(txtNom);

        panelForm.add(new JLabel("Prenom :"));
        panelForm.add(txtPrenom);

        panelForm.add(new JLabel("Email :"));
        panelForm.add(txtEmail);

        panelForm.add(new JLabel("Mot de passe :"));
        panelForm.add(txtMdp);

        panelForm.add(new JLabel("Tel :"));
        panelForm.add(txtTel);

        panelForm.add(new JLabel("Adresse :"));
        panelForm.add(txtAdresse);

        // 👉 ON GARDE EXACTEMENT TES BOUTONS ICI (IMPORTANT)
        panelForm.add(btAnnuler);
        panelForm.add(btValider);
        panelForm.add(btSupprimer);
        panelForm.add(btModifier);

        this.add(panelForm);

        btSupprimer.setEnabled(false);
        btModifier.setEnabled(false);

        // ================= TABLE =================
        String[] entetes = {"ID", "Nom", "Prenom", "Email", "Tel", "Adresse"};

        unTableau = new Tableau(obtenirDonnees(""), entetes);
        tableCandidats = new JTable(unTableau);

        scroll = new JScrollPane(tableCandidats);
        scroll.setBounds(450, 120, 500, 250);

        this.add(scroll);

        // ================= CLICK TABLE =================
        tableCandidats.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int ligne = tableCandidats.getSelectedRow();

                txtNom.setText(unTableau.getValueAt(ligne, 1).toString());
                txtPrenom.setText(unTableau.getValueAt(ligne, 2).toString());
                txtEmail.setText(unTableau.getValueAt(ligne, 3).toString());
                txtTel.setText(unTableau.getValueAt(ligne, 4).toString());
                txtAdresse.setText(unTableau.getValueAt(ligne, 5).toString());

                btModifier.setEnabled(true);
                btSupprimer.setEnabled(true);
            }
        });

        // ================= LABEL =================
        lbNb.setBounds(450, 380, 400, 30);
        this.add(lbNb);

        lbNb.setText("Nombre candidats : " + unTableau.getRowCount());

        // ================= EVENTS =================
        btAnnuler.addActionListener(this);
        btValider.addActionListener(this);
        btModifier.addActionListener(this);
        btSupprimer.addActionListener(this);
        btFiltrer.addActionListener(this);
    }

    // ================= DATA =================
    private Object[][] obtenirDonnees(String filtre) {

        ArrayList<Candidat> list = Controleur.selectAllCandidats(filtre);

        Object[][] data = new Object[list.size()][6];

        int i = 0;

        for (Candidat c : list) {

            data[i][0] = c.getIdcandidat();
            data[i][1] = c.getNom();
            data[i][2] = c.getPrenom();
            data[i][3] = c.getEmail();
            data[i][4] = c.getTel();
            data[i][5] = c.getAdresse();

            i++;
        }

        return data;
    }

    // ================= ACTIONS =================
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btAnnuler) vider();

        else if (e.getSource() == btValider) insert();

        else if (e.getSource() == btModifier) update();

        else if (e.getSource() == btSupprimer) delete();

        else if (e.getSource() == btFiltrer)
            unTableau.setDonnees(obtenirDonnees(txtFiltre.getText()));
    }

    // ================= METHODS =================
    public void vider() {

        txtNom.setText("");
        txtPrenom.setText("");
        txtEmail.setText("");
        txtMdp.setText("");
        txtTel.setText("");
        txtAdresse.setText("");

        btModifier.setEnabled(false);
        btSupprimer.setEnabled(false);
    }

    public void insert() {

        Candidat c = new Candidat(
                txtNom.getText(),
                txtPrenom.getText(),
                txtEmail.getText(),
                txtMdp.getText(),
                1,
                txtTel.getText(),
                txtAdresse.getText(),
                0,
                null, null, null
        );

        Controleur.insertCandidat(c);

        JOptionPane.showMessageDialog(this, "Ajout réussi");

        unTableau.setDonnees(obtenirDonnees(""));
        lbNb.setText("Nombre candidats : " + unTableau.getRowCount());

        vider();
    }

    public void update() {

        int ligne = tableCandidats.getSelectedRow();
        int id = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());

        Candidat c = new Candidat(
                id,
                txtNom.getText(),
                txtPrenom.getText(),
                txtEmail.getText(),
                txtMdp.getText(),
                1,
                txtTel.getText(),
                txtAdresse.getText(),
                0,
                null, null, null
        );

        Controleur.updateCandidat(c);

        JOptionPane.showMessageDialog(this, "Modifié");

        unTableau.setDonnees(obtenirDonnees(""));

        vider();
    }

    public void delete() {

        int ligne = tableCandidats.getSelectedRow();
        int id = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());

        int rep = JOptionPane.showConfirmDialog(this,
                "Supprimer ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (rep == JOptionPane.YES_OPTION) {

            Controleur.deleteCandidat(id);

            JOptionPane.showMessageDialog(this, "Supprimé");

            unTableau.setDonnees(obtenirDonnees(""));

            vider();
        }
    }
}