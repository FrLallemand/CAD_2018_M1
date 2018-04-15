package DAO;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import modeles.bateaux.Bateau;
import modeles.Flotte;
import modeles.Modele;
import modeles.Position;
import modeles.Terrain;
import modeles.Terrain.StatusCase;

public class ModeleDAO_XML implements ModeleDAO{

	Modele m;
	Document document;
	
	public ModeleDAO_XML(Modele m){
		this.m=m;
	}
	

	public void sauvegarde(String file) {
		// TODO Auto-generated method stub
		File f = new File(file);
		String fileName=f.getName();
		
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();       
			document= builder.newDocument();
		    Element racine = document.createElement("BatailleNavale");
		    document.appendChild(racine);
		    
		    //joueur 1
		    Terrain t1=m.getTerrainJ1();
		    Flotte f1=t1.getFlotte();
		    Element terrainJ1 = document.createElement("TerrainJ1");
		    racine.appendChild(terrainJ1);
		    Element epoqueJ1 = document.createElement("epoque");
		    terrainJ1.appendChild(epoqueJ1);
		    epoqueJ1.appendChild(document.createTextNode(f1.getEpoque().getNom()+""));
		    Element bateauJ1 = document.createElement("bateaux");
		    terrainJ1.appendChild(bateauJ1);
		    Element casesJ1 = document.createElement("cases");
		    terrainJ1.appendChild(casesJ1);
		    sauvegardeBateau(f1,bateauJ1);
		    sauvegardeTir(t1,casesJ1);
		    
		    
		    //joueur 2
		    Terrain t2=m.getTerrainJ1();
		    Flotte f2=t2.getFlotte();
		    Element terrainJ2 = document.createElement("TerrainJ2");
		    racine.appendChild(terrainJ2);
		    Element stratJ2 = document.createElement("strategie");
		    stratJ2.appendChild(document.createTextNode(m.getStrategieJ2().getNomStrategie()+""));
		    terrainJ2.appendChild(stratJ2);
		    Element epoqueJ2 = document.createElement("epoque");
		    epoqueJ2.appendChild(document.createTextNode(f2.getEpoque().getNom()+""));
		    terrainJ2.appendChild(epoqueJ2);
		    Element bateauJ2 = document.createElement("bateaux");
		    terrainJ2.appendChild(bateauJ2);
		    Element casesJ2 = document.createElement("cases");
		    terrainJ2.appendChild(casesJ2);
		    sauvegardeBateau(f2,bateauJ2);
		    sauvegardeTir(t2,casesJ2);
		    
		    //affichage
		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		    Transformer transformer = transformerFactory.newTransformer();
		    transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
		    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		    transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		    DOMSource source = new DOMSource(document);
		    StreamResult sortie = new StreamResult(new File(file));
		    transformer.transform(source, sortie);
		    sortie = new StreamResult(System.out);
		    transformer.transform(source, sortie);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void sauvegardeBateau(Flotte flotte,Element XMLFlotte){
	    int x=flotte.getNbBateau();
	    Bateau b;
	    Position p;
	    Element nom,taille,posX,posY,direction,bateau;
	    for(int i=0;i<x;i++){
	    	b=flotte.getBateauParID(i);
	    	p=b.getPosition();
	    	bateau=document.createElement("bateau");
	    	nom=document.createElement("nom");
	    	nom.appendChild(document.createTextNode(b.getNom()));
	    	taille=document.createElement("taille");
	    	taille.appendChild(document.createTextNode(b.getTaille()+""));	    	
	    	posX=document.createElement("posX");
	    	posX.appendChild(document.createTextNode(p.getX()+""));
	    	posY=document.createElement("posY");
	    	posY.appendChild(document.createTextNode(p.getY()+""));
	    	direction=document.createElement("direction");
	    	direction.appendChild(document.createTextNode(p.getDirection()+""));
	    	bateau.appendChild(nom);
	    	bateau.appendChild(taille);
	    	bateau.appendChild(posX);
	    	bateau.appendChild(posY);
	    	bateau.appendChild(direction);
	    	XMLFlotte.appendChild(bateau);
	    }
	}
	
	private void sauvegardeTir(Terrain t,Element XMLCases){
		StatusCase[][] cases = t.getCases();
		Element elCase,posX,posY,type;
		for(int x=0;x<cases.length;x++){
			for(int y=0;y<cases[x].length;y++){
				if(cases[x][y]!=StatusCase.EAU){
					elCase=document.createElement("case");    	
			    	posX=document.createElement("posX");
			    	posX.appendChild(document.createTextNode(x+""));
			    	posY=document.createElement("posY");
			    	posY.appendChild(document.createTextNode(y+""));
			    	type=document.createElement("type");
			    	type.appendChild(document.createTextNode(cases[x][y]+""));
			    	elCase.appendChild(posX);
			    	elCase.appendChild(posY);
			    	elCase.appendChild(type);
			    	XMLCases.appendChild(elCase);
				}
			}
		}
	}
	
	public void chargement(String file) {
		// TODO Auto-generated method stub
		File f = new File(file);
		String fileName=f.getName();
		try{
			if(!f.isFile()||f.isDirectory()){
				throw new Exception();
			}
			if(!fileName.contains(".xml")){
				throw new Exception();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	    //chargement : Document document= builder.parse(new File(file));
	}

}
