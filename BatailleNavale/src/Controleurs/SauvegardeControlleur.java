package Controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import DAO.ModeleDAO;
import DAO.ModeleDAO_XML;
import modeles.Modele;

public class SauvegardeControlleur implements ActionListener {

	JFileChooser fc;
	JButton jb;
	Modele m;
	
	public SauvegardeControlleur(JButton jb,Modele m){
		fc = new JFileChooser();
		this.jb=jb;
		this.m=m;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	    if( fc.showOpenDialog( jb ) == JFileChooser.APPROVE_OPTION )
	    {
	    	//ex : C:\Users\Guillaume\Documents\lol.test
	        String f=fc.getSelectedFile().getAbsolutePath();
	        ModeleDAO_XML save=new ModeleDAO_XML(m);
	        save.sauvegarde(f);
	    }

	}
}
