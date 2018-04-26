package DAO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import modeles.bateaux.Bateau;
import modeles.epoques.Epoque;
import modeles.Flotte;
import modeles.GameFactory;
import modeles.Modele;
import modeles.Position;
import modeles.Strategie;
import modeles.StrategieChargement;
import modeles.Terrain;
import modeles.Terrain.StatusCase;

public class ModeleDAO_XML implements ModeleDAO{

	private Modele m;
	private Document document;

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
			Element tirsJ1 = document.createElement("tirs");
			terrainJ1.appendChild(tirsJ1);
			sauvegardeBateau(f1,bateauJ1);
			sauvegardeTir(t1,tirsJ1);


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
			Element tirsJ2 = document.createElement("tirs");
			terrainJ2.appendChild(tirsJ2);
			sauvegardeBateau(f2,bateauJ2);
			sauvegardeTir(t2,tirsJ2);

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
			/* sortie terminal :
		    sortie = new StreamResult(System.out);
		    transformer.transform(source, sortie);*/
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void sauvegardeBateau(Flotte flotte,Element XMLFlotte){
		int x=flotte.getNbBateau();
		Bateau b;
		Position p;
		Element posX,posY,direction,bateau;
		for(int i=0;i<x;i++){
			b=flotte.getBateauParID(i);
			p=b.getPosition();
			bateau=document.createElement("bateau");   	
			posX=document.createElement("posX");
			posX.appendChild(document.createTextNode(p.getX()+""));
			posY=document.createElement("posY");
			posY.appendChild(document.createTextNode(p.getY()+""));
			direction=document.createElement("direction");
			direction.appendChild(document.createTextNode(p.getDirection()+""));
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
					elCase=document.createElement("tir");    	
					posX=document.createElement("posX");
					posX.appendChild(document.createTextNode(x+""));
					posY=document.createElement("posY");
					posY.appendChild(document.createTextNode(y+""));
					elCase.appendChild(posX);
					elCase.appendChild(posY);
					XMLCases.appendChild(elCase);
				}
			}
		}
	}

	public void chargement(String file) {
		// TODO Auto-generated method stub
		File f = new File(file);
		String fileName=f.getName();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			if(!f.isFile()||f.isDirectory()){
				throw new Exception();
			}
			if(!fileName.contains(".xml")){
				throw new Exception();
			}
			DocumentBuilder builder = factory.newDocumentBuilder();
			document= builder.parse(new File(file));
			Element batailleNavale = document.getDocumentElement();//BatailleNavale
			NodeList terrains = batailleNavale.getChildNodes();//terrainJ1+J2
			//recuperation info J1
			Element terrainJ1=(Element)batailleNavale.getElementsByTagName("TerrainJ1").item(0);
			Element epoqueJ1=(Element)terrainJ1.getElementsByTagName("epoque").item(0);
			Element bateauxJ1=(Element)terrainJ1.getElementsByTagName("bateaux").item(0);
			NodeList listbateauxJ1=bateauxJ1.getElementsByTagName("bateau");
			Element casesJ1=(Element)terrainJ1.getElementsByTagName("tirs").item(0);
			NodeList listcasesJ1=casesJ1.getElementsByTagName("tir");
			//recuperation info J2
			Element terrainJ2=(Element)batailleNavale.getElementsByTagName("TerrainJ2").item(0);
			Element epoqueJ2=(Element)terrainJ2.getElementsByTagName("epoque").item(0);
			Element strategieJ2=(Element)terrainJ2.getElementsByTagName("strategie").item(0);
			Element bateauxJ2=(Element)terrainJ2.getElementsByTagName("bateaux").item(0);
			NodeList listbateauxJ2=bateauxJ2.getElementsByTagName("bateau");
			Element casesJ2=(Element)terrainJ2.getElementsByTagName("tirs").item(0);
			NodeList listcasesJ2=casesJ2.getElementsByTagName("tir");
			//MAJ modele
			ArrayList<Position> fb1=chargementBateaux(listbateauxJ1);
			ArrayList<Position> fb2=chargementBateaux(listbateauxJ2);
			Flotte fj1=new Flotte(new ArrayList<Bateau>(),getEpoque(epoqueJ1.getTextContent()));
			Flotte fj2=new Flotte(new ArrayList<Bateau>(),getEpoque(epoqueJ2.getTextContent()));
			m.getTerrainJ1().setFlotte(fj1);
			m.getTerrainJ2().setFlotte(fj2);
			ArrayList<Position> tirsJ2=chargementTir(listcasesJ1);
			ArrayList<Position> tirsJ1=chargementTir(listcasesJ2);
			m.setStrategieJ2(new StrategieChargement(tirsJ2,fb2));
			//partie bateaux
			m.run();
			//partie tirs
			for(int x=0;x<tirsJ1.size();x++){
				m.effectuerTir(tirsJ1.get(x));				
			}
			m.setStrategieJ2(getStrategie(strategieJ2.getTextContent(),m.getTerrainJ2()));
		}catch(Exception e){
			e.printStackTrace();
		}
		//chargement : Document document= builder.parse(new File(file));
	}


	private ArrayList<Position> chargementBateaux(NodeList bateaux){
		ArrayList<Position> p=new ArrayList<Position>();
		String direction;
		int posx,posy;
		Position.Direction d;
		for(int x=0;x<bateaux.getLength();x++){
			Element bateau=(Element)bateaux.item(x);
			posx=Integer.parseInt(bateau.getElementsByTagName("posX").item(0).getTextContent());
			posy=Integer.parseInt(bateau.getElementsByTagName("posY").item(0).getTextContent());
			direction=bateau.getElementsByTagName("direction").item(0).getTextContent();
			if(Position.Direction.valueOf(direction)==Position.Direction.HORIZONTAL){
				d=Position.Direction.HORIZONTAL;
			}else{
				d=Position.Direction.VERTICAL;
			}
			p.add(new Position(posx,posy,d));
		}
		return p;
	}
	
	private ArrayList<Position> chargementTir(NodeList tirs){
		int posx,posy;
		ArrayList<Position> posTir=new ArrayList<Position>();
		for(int x=0;x<tirs.getLength();x++){
			Element tir=(Element)tirs.item(x);
			posx=Integer.parseInt(tir.getElementsByTagName("posX").item(0).getTextContent());
			posy=Integer.parseInt(tir.getElementsByTagName("posY").item(0).getTextContent());
			posTir.add(new Position(posx,posy));
		}
		return posTir;
	}
	
	private Strategie getStrategie(String stratergie,Terrain tj2){
		return GameFactory.getStrategie(stratergie, tj2);
	}
	
	private Epoque getEpoque(String epoque){
		return GameFactory.getEpoque(epoque);
	}
}
