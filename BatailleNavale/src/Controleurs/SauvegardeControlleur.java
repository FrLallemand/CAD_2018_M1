package Controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import DAO.ModeleDAO;
import DAO.ModeleDAO_XML;
import modeles.GameFactory;
import modeles.Modele;

public class SauvegardeControlleur implements ActionListener {

	JFileChooser fc;
	JButton jb;
	Modele m;
	JComboBox type;
	
	public SauvegardeControlleur(JButton jb,Modele m,JComboBox type){
		fc = new JFileChooser();
		this.jb=jb;
		this.m=m;
		this.type=type;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	    if( fc.showOpenDialog( jb ) == JFileChooser.APPROVE_OPTION )
	    {
	    	//ex : C:\Users\Guillaume\Documents\lol.test
	        String f=fc.getSelectedFile().getAbsolutePath();
	        ModeleDAO save=GameFactory.getModeleDAO(type.getSelectedItem().toString(), m);
	        save.sauvegarde(f);
	    }

	}
}
