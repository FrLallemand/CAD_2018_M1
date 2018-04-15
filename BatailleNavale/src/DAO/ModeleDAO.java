package DAO;

import modeles.Modele;

public interface ModeleDAO {

	public void sauvegarde(String file);
	
	public void chargement(String file);
}
