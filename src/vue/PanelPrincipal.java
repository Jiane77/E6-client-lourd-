package vue;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class PanelPrincipal  extends JPanel
{
	//panel generique qui sert de base pour creer les autres apnels 
	//garder les caracteristiques communes 
	
public PanelPrincipal(String titre) {
	this.setBackground(Color.decode("#165763"));
	this.setBounds(10, 80, 960, 400);
	this.setLayout(null);
	JLabel lbTitre =new JLabel(titre);
	lbTitre.setBounds(240,20,400,20);
	this.add(lbTitre);
	Font unePolice=new Font ("Arial",Font.BOLD,18);
	lbTitre.setFont(unePolice);
	lbTitre.setBounds(340,10,400,20);
	
	this.setVisible(true);
}
}
