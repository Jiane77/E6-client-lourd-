package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controleur.Controleur;
import controleur.Auto;
import controleur.User;

public class PanelProfil extends PanelPrincipal implements ActionListener {

    private static JTextArea txtInfos = new JTextArea();

    private JButton btModifier = new JButton("Modifier Profil");

    private JPanel panelForm = new JPanel();

    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JPasswordField txtMdp = new JPasswordField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");

    public PanelProfil(String titre) {

        super(titre);

        // INFOS
        txtInfos.setBounds(40, 100, 300, 200);
        txtInfos.setBackground(Color.decode("#C1DFE6"));
        this.add(txtInfos);

        this.btModifier.setBounds(60, 320, 200, 20);
        this.add(this.btModifier);

        // ================= FORM =================
        this.panelForm.setBounds(450, 100, 350, 250);
        this.panelForm.setBackground(Color.gray);
        this.panelForm.setLayout(new GridLayout(5, 2, 10, 10));

        this.panelForm.add(new JLabel("Nom User : "));
        this.panelForm.add(this.txtNom);

        this.panelForm.add(new JLabel("Prenom User : "));
        this.panelForm.add(this.txtPrenom);

        this.panelForm.add(new JLabel("Email User : "));
        this.panelForm.add(this.txtEmail);

        this.panelForm.add(new JLabel("MDP User : "));
        this.panelForm.add(this.txtMdp);

        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);

        this.add(this.panelForm);

        this.panelForm.setVisible(false);

        // ================= EVENTS =================
        this.btModifier.addActionListener(this);
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        actualiserInfos();
    }

    // ================= INFOS USER =================
    public static void actualiserInfos() {

        if (Auto.getUserConnecte() != null) {

            txtInfos.setText(
                    "\n Nom User : " + Auto.getUserConnecte().getNom()
                    + "\n\n Prenom User : " + Auto.getUserConnecte().getPrenom()
                    + "\n\n Email : " + Auto.getUserConnecte().getEmail()
            );
        }
    }

    // ================= ACTIONS =================
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.btModifier) {

            this.panelForm.setVisible(true);

            this.txtNom.setText(Auto.getUserConnecte().getNom());
            this.txtPrenom.setText(Auto.getUserConnecte().getPrenom());
            this.txtEmail.setText(Auto.getUserConnecte().getEmail());
            this.txtMdp.setText(Auto.getUserConnecte().getMdp());
        }

        else if (e.getSource() == this.btAnnuler) {
            viderChamps();
        }

        else if (e.getSource() == this.btValider) {

            if (Auto.getUserConnecte() != null) {

                User unUser = new User(
                        Auto.getUserConnecte().getIduser(),
                        this.txtNom.getText(),
                        this.txtPrenom.getText(),
                        this.txtEmail.getText(),
                        new String(this.txtMdp.getPassword()),
                        Auto.getUserConnecte().getRole()
                );

                Controleur.updateUser(unUser);

                Auto.setUserConnecte(unUser);

                actualiserInfos();

                viderChamps();

                this.panelForm.setVisible(false);
            }
        }
    }

    // ================= RESET =================
    public void viderChamps() {

        this.txtNom.setText("");
        this.txtPrenom.setText("");
        this.txtEmail.setText("");
        this.txtMdp.setText("");

        this.panelForm.setVisible(false);
    }
}