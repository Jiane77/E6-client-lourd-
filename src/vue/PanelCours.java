
package vue;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Controleur;
import controleur.Cours;
import controleur.Tableau;

public class PanelCours extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();

    private JTextField txtDate = new JTextField();
    private JTextField txtHeureDebut = new JTextField();
    private JTextField txtHeureFin = new JTextField();
    private JTextField txtStatut = new JTextField();

    private JTextField txtVehicule = new JTextField();
    private JTextField txtMoniteur = new JTextField();
    private JTextField txtCandidat = new JTextField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");
    private JButton btModifier = new JButton("Modifier");

    private JTable tableCours;
    private JScrollPane scroll;
    private Tableau unTableau;

    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");

    private JLabel lbNb = new JLabel("");

    public PanelCours(String titre) {

        super(titre);

        // ================= FILTRE =================
        JPanel panelFiltre = new JPanel(new GridLayout(1, 3, 5, 5));
        panelFiltre.setBounds(450, 80, 460, 30);

        panelFiltre.add(new JLabel("Filtrer :"));
        panelFiltre.add(txtFiltre);
        panelFiltre.add(btFiltrer);

        this.add(panelFiltre);

        // ================= FORM =================
        panelForm.setBounds(55, 80, 200, 250);
        panelForm.setLayout(new GridLayout(10, 2, 10, 10));

        panelForm.add(new JLabel("Date :"));
        panelForm.add(txtDate);

        panelForm.add(new JLabel("Début :"));
        panelForm.add(txtHeureDebut);

        panelForm.add(new JLabel("Fin :"));
        panelForm.add(txtHeureFin);

        panelForm.add(new JLabel("Statut :"));
        panelForm.add(txtStatut);

        panelForm.add(new JLabel("Véhicule :"));
        panelForm.add(txtVehicule);

        panelForm.add(new JLabel("Moniteur :"));
        panelForm.add(txtMoniteur);

        panelForm.add(new JLabel("Candidat :"));
        panelForm.add(txtCandidat);

        panelForm.add(btAnnuler);
        panelForm.add(btValider);
        panelForm.add(btSupprimer);
        panelForm.add(btModifier);

        this.add(panelForm);

        btSupprimer.setEnabled(false);
        btModifier.setEnabled(false);

        // ================= TABLE =================
        String[] entetes = {
                "ID", "Date", "Début", "Fin",
                "Statut", "Vehicule", "Moniteur", "Candidat"
        };

        unTableau = new Tableau(obtenirDonnees(""), entetes);
        tableCours = new JTable(unTableau);

        scroll = new JScrollPane(tableCours);
        scroll.setBounds(350, 120, 600, 200);
        this.add(scroll);

        // ================= CLICK TABLE =================
        tableCours.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int ligne = tableCours.getSelectedRow();

                txtDate.setText(unTableau.getValueAt(ligne, 1).toString());
                txtHeureDebut.setText(unTableau.getValueAt(ligne, 2).toString());
                txtHeureFin.setText(unTableau.getValueAt(ligne, 3).toString());
                txtStatut.setText(unTableau.getValueAt(ligne, 4).toString());
                txtVehicule.setText(unTableau.getValueAt(ligne, 5).toString());
                txtMoniteur.setText(unTableau.getValueAt(ligne, 6).toString());
                txtCandidat.setText(unTableau.getValueAt(ligne, 7).toString());

                btModifier.setEnabled(true);
                btSupprimer.setEnabled(true);
            }
        });

        // ================= LABEL =================
        lbNb.setBounds(450, 380, 300, 30);
        this.add(lbNb);

        lbNb.setText("Nombre cours : " + unTableau.getRowCount());

        // ================= EVENTS =================
        btAnnuler.addActionListener(this);
        btValider.addActionListener(this);
        btModifier.addActionListener(this);
        btSupprimer.addActionListener(this);
        btFiltrer.addActionListener(this);
    }

    // ================= DATA =================
    private Object[][] obtenirDonnees(String filtre) {

        ArrayList<Cours> list = Controleur.selectAllCours(filtre);

        Object[][] data = new Object[list.size()][8];

        int i = 0;

        for (Cours c : list) {

            data[i][0] = c.getIdcours();
            data[i][1] = c.getDateCours();
            data[i][2] = c.getHeureDebut();
            data[i][3] = c.getHeureFin();
            data[i][4] = c.getStatut();
            data[i][5] = c.getIdvehicule();
            data[i][6] = c.getIdmoniteur();
            data[i][7] = c.getIdcandidat();

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

        txtDate.setText("");
        txtHeureDebut.setText("");
        txtHeureFin.setText("");
        txtStatut.setText("");
        txtVehicule.setText("");
        txtMoniteur.setText("");
        txtCandidat.setText("");

        btModifier.setEnabled(false);
        btSupprimer.setEnabled(false);
    }

    public void insert() {

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

            JOptionPane.showMessageDialog(this, "Ajout réussi");

            unTableau.setDonnees(obtenirDonnees(""));
            lbNb.setText("Nombre cours : " + unTableau.getRowCount());

            vider();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur insertion !");
        }
    }

    public void update() {

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

            JOptionPane.showMessageDialog(this, "Modifié");

            unTableau.setDonnees(obtenirDonnees(""));
            vider();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur modification !");
        }
    }

    public void delete() {

        int ligne = tableCours.getSelectedRow();
        int id = Integer.parseInt(unTableau.getValueAt(ligne, 0).toString());

        int rep = JOptionPane.showConfirmDialog(this,
                "Supprimer ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (rep == JOptionPane.YES_OPTION) {

            Controleur.deleteCours(id);

            JOptionPane.showMessageDialog(this, "Supprimé");

            unTableau.setDonnees(obtenirDonnees(""));
            vider();
        }
    }
}