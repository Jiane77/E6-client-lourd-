package vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controleur.Controleur;
import controleur.Auto;
import controleur.User;

public class VueConnexion extends JFrame implements ActionListener, KeyListener {

    private JPanel panelForm = new JPanel();
    private JTextField txtEmail = new JTextField();
    private JPasswordField txtMdp = new JPasswordField();

    private JButton btAnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");

    public VueConnexion() {

        this.setTitle("AUTO ECOLE");
        this.setBounds(300, 10, 300, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.decode("#165763"));

        ImageIcon uneImage = new ImageIcon("src/images/logo.png");
        Image img = uneImage.getImage();
        Image imgScale = img.getScaledInstance(250, 200, Image.SCALE_SMOOTH);
        ImageIcon imageRedimensionnee = new ImageIcon(imgScale);

        JLabel lbLogo = new JLabel(imageRedimensionnee);
        lbLogo.setBounds(25, 20, 250, 200);
        this.add(lbLogo);

        // PANEL FORM
        this.panelForm.setBounds(25, 260, 250, 160);
        this.panelForm.setBackground(Color.decode("#165763"));
        this.panelForm.setLayout(new GridLayout(3, 2, 5, 10));

        this.panelForm.add(new JLabel("Email :"));
        this.panelForm.add(this.txtEmail);

        this.panelForm.add(new JLabel("MDP :"));
        this.panelForm.add(this.txtMdp);

        this.panelForm.add(this.btAnuler);
        this.panelForm.add(this.btValider);

        this.add(this.panelForm);

        // LISTENERS
        this.btAnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        this.txtEmail.addKeyListener(this);
        this.txtMdp.addKeyListener(this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.btAnuler) {
            viderChamps();
        }

        if (e.getSource() == this.btValider) {
            traitement();
        }
    }

    public void viderChamps() {
        this.txtEmail.setText("");
        this.txtMdp.setText("");
    }

    public void traitement() {

        String email = this.txtEmail.getText();
        String mdp = new String(this.txtMdp.getPassword());

        if (email.isEmpty() || mdp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs !");
            return;
        }

        User unUser = Controleur.selectWhereUser(email, mdp);

        if (unUser == null) {
            JOptionPane.showMessageDialog(this, "Identifiants incorrects !");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Bienvenue " + unUser.getNom() + " " + unUser.getPrenom());

            Auto.rendreVisibleVueConnexion(false);
            Auto.creerDetruireVueGenerale(true);
            Auto.setUserConnecte(unUser);

            PanelProfil.actualiserInfos();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            traitement();
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}