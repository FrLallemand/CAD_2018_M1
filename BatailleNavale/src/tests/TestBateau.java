package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;
import modeles.Position;
import modeles.Position.Direction;
import modeles.bateaux.Bateau;

public class TestBateau {

	@Test
	public void testTirManqueHorizontal() {
		Bateau b = new Bateau(5, "testManqueHorizontal");
		b.setPosition(new Position(1,1, Direction.HORIZONTAL));
		
		boolean result = b.testerTir(new Position(2, 2));
		
		assertTrue(!result);
	}
	
	@Test
	public void testTirManqueVertical() {
		Bateau b = new Bateau(5, "testManqueHorizontal");
		b.setPosition(new Position(1,1, Direction.VERTICAL));
		
		boolean result = b.testerTir(new Position(2, 2));
		
		assertTrue(!result);
	}
	
	@Test
	public void testTirHorizontal() {
		Bateau b = new Bateau(5, "testHorizontal");
		b.setPosition(new Position(1,1, Direction.HORIZONTAL));
		
		boolean result = b.testerTir(new Position(1, 2));
		
		assertTrue(!result);
	}
	
	@Test
	public void testTirVertical() {
		Bateau b = new Bateau(5, "testVertical");
		b.setPosition(new Position(1,1, Direction.VERTICAL));
		
		boolean result = b.testerTir(new Position(2, 1));
		
		assertTrue(!result);
	}
	
	@Test
	public void testBateauIntact() {
		Bateau b = new Bateau(1, "testIntact");
		b.setPosition(new Position(1,1));
		
		assertTrue(!b.estDetruit());
	}
	
	@Test
	public void testBateauTouche() {
		Bateau b = new Bateau(2,  "testTouche");
		b.setPosition(new Position(1, 1));
		
		b.effectuerTir(new Position(1, 1));
		
		assertTrue(!b.estDetruit());
	}
	
	@Test
	public void testBateauCoule() {
		Bateau b = new Bateau(1, "testCoule");
		b.setPosition(new Position(1,1));
		
		b.effectuerTir(new Position(1, 1));
		
		assertTrue(b.estDetruit());
	}
}
