package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controleur.Auto;

public class VueGenerale extends JFrame implements ActionListener {

    private JPanel panelMenu = new JPanel();

    private JButton btProfil = new JButton("Profil");
    private JButton btCandidats = new JButton("Candidats");
    private JButton btMoniteurs = new JButton("Moniteurs");
    private JButton btVehicules = new JButton("Vehicules");
    private JButton btCours = new JButton("Cours");
    private JButton btQuitter = new JButton("Quitter");

    // ================= PANELS =================
    private PanelProfil unPanelProfil = new PanelProfil("Gestion du Profil");

    private PanelCandidats unPanelCandidats = new PanelCandidats("Gestion des Candidats");

    private PanelMoniteurs unPanelMoniteurs = new PanelMoniteurs("Gestion des Moniteurs");

    private PanelVehicules unPanelVehicules = new PanelVehicules("Gestion des Vehicules");

    private PanelCours unPanelCours = new PanelCours("Gestion des Cours");

    public VueGenerale() {

        this.setTitle("Auto Ecole - Gestion");
        this.setBounds(100, 10, 1000, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.gray);

        // ================= MENU =================
        this.panelMenu.setBackground(Color.gray);
        this.panelMenu.setBounds(100, 20, 800, 30);
        this.panelMenu.setLayout(new GridLayout(1, 6, 10, 10));

        this.panelMenu.add(this.btProfil);
        this.panelMenu.add(this.btCandidats);
        this.panelMenu.add(this.btMoniteurs);
        this.panelMenu.add(this.btVehicules);
        this.panelMenu.add(this.btCours);
        this.panelMenu.add(this.btQuitter);

        this.add(this.panelMenu);

        // ================= ACTIONS =================
        this.btProfil.addActionListener(this);
        this.btCandidats.addActionListener(this);
        this.btMoniteurs.addActionListener(this);
        this.btVehicules.addActionListener(this);
        this.btCours.addActionListener(this);
        this.btQuitter.addActionListener(this);

        // ================= AJOUT PANELS =================
        this.add(this.unPanelProfil);
        this.add(this.unPanelCandidats);
        this.add(this.unPanelMoniteurs);
        this.add(this.unPanelVehicules);
        this.add(this.unPanelCours);

        this.setVisible(true);

        // affichage par défaut
        this.afficherPanel(1);
    }

    // ================= AFFICHAGE =================
    public void afficherPanel(int choix) {

        this.unPanelProfil.setVisible(false);
        this.unPanelCandidats.setVisible(false);
        this.unPanelMoniteurs.setVisible(false);
        this.unPanelVehicules.setVisible(false);
        this.unPanelCours.setVisible(false);

        switch (choix) {

            case 1:
                this.unPanelProfil.setVisible(true);
                break;

            case 2:
                this.unPanelCandidats.setVisible(true);
                break;

            case 3:
                this.unPanelMoniteurs.setVisible(true);
                break;

            case 4:
                this.unPanelVehicules.setVisible(true);
                break;

            case 5:
                this.unPanelCours.setVisible(true);
                break;
        }
    }

    // ================= ACTION =================
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.btQuitter) {

            JOptionPane.showMessageDialog(this,
                    "Au revoir " + Auto.getUserConnecte().getNom());

            Auto.rendreVisibleVueConnexion(true);
            Auto.creerDetruireVueGenerale(false);
        }

        else if (e.getSource() == this.btProfil) {
            this.afficherPanel(1);
        }

        else if (e.getSource() == this.btCandidats) {
            this.afficherPanel(2);
        }

        else if (e.getSource() == this.btMoniteurs) {
            this.afficherPanel(3);
        }

        else if (e.getSource() == this.btVehicules) {
            this.afficherPanel(4);
        }

        else if (e.getSource() == this.btCours) {
            this.afficherPanel(5);
        }
    }
}