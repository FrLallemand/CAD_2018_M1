package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import modeles.Flotte;
import modeles.Position;
import modeles.Position.Direction;
import modeles.ResultatTir;
import modeles.epoques.Epoque;
import modeles.epoques.EpoqueModerne;
import modeles.epoques.EpoqueXVII;

public class TestFlotte {
	@Test
	public void testBateauSuivantEpoqueModerne() {
		Epoque e = new EpoqueModerne();
		e.setBateauxEpoque();
		Flotte f = new Flotte(new ArrayList<>(), e);
		assertTrue(f.bateauSuivantAPlacer().getNom() == "Porte-avions");
		
		f.placerBateauSuivant(new Position(0,0));
		
		assertTrue(f.bateauSuivantAPlacer().getNom() == "Croiseur");
	}
	
	
	@Test
	public void testBateauSuivantEpoqueXVII() {
		Epoque e = new EpoqueXVII();
		e.setBateauxEpoque();
		Flotte f = new Flotte(new ArrayList<>(), e);
		assertTrue(f.bateauSuivantAPlacer().getNom() == "Gallion");
		
		f.placerBateauSuivant(new Position(0,0));
		
		assertTrue(f.bateauSuivantAPlacer().getNom() == "Frégate");
	}
	
	@Test
	public void testPlacementFiniModerne() {
		Epoque e = new EpoqueModerne();
		e.setBateauxEpoque();
		
		Flotte f = new Flotte(new ArrayList<>(), e);
		
		for(int i = 0; i < 5; i++) {
			f.placerBateauSuivant(new Position(0,0));
		}
		
		assertTrue(f.placementFini());
	}
	
	@Test
	public void testPlacementFiniXVII() {
		Epoque e = new EpoqueXVII();
		e.setBateauxEpoque();
		
		Flotte f = new Flotte(new ArrayList<>(), e);
		
		for(int i = 0; i < 4; i++) {
			f.placerBateauSuivant(new Position(0,0));
		}
		
		assertTrue(f.placementFini());
	}
	
	@Test
	public void testTirEau() {
		Epoque epoque = new EpoqueModerne();
		epoque.setBateauxEpoque();
		
		Flotte f = new Flotte(new ArrayList<>(), epoque);
		
		f.placerBateauSuivant(new Position(0, 0, Direction.HORIZONTAL));
		ResultatTir r = f.effectuerTir(new Position(1,1));
		
		assertTrue(r == ResultatTir.EAU);
	}
	
	@Test
	public void testTirTouche() {
		Epoque epoque = new EpoqueModerne();
		epoque.setBateauxEpoque();
		
		Flotte f = new Flotte(new ArrayList<>(), epoque);
		
		f.placerBateauSuivant(new Position(0, 0, Direction.HORIZONTAL));
		ResultatTir r = f.effectuerTir(new Position(0,0));
		
		assertTrue(r == ResultatTir.TOUCHE);
	}
	
	@Test
	public void testTirCoule() {
		Epoque epoque = new EpoqueModerne();
		epoque.setBateauxEpoque();
		
		Flotte f = new Flotte(new ArrayList<>(), epoque);
		
		f.placerBateauSuivant(new Position(0, 0, Direction.VERTICAL));
		
		ResultatTir r = null;
		
		for(int i = 0; i < 5; i++) {
			r = f.effectuerTir(new Position(0,i));
		}
		
		assertTrue(r == ResultatTir.COULE);
	}
}
