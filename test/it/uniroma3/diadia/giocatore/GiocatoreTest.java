package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

public class GiocatoreTest {
	
	private Giocatore giocatore(int cfu) {
		Giocatore g = new Giocatore();
		if(cfu != 0) {
			g.setCfu(cfu);
		}
		return g;
	}
	
	
	@Test
	public void testGetCfuVerificato() {
		assertEquals(8,giocatore(8).getCfu());
	}
	
	@Test
	public void testGetCfuVerificatoCondizioneIniziale() {
		assertEquals(20,giocatore(0).getCfu());
	}
	
	
	/*TEST PER SET CFU*/
	@Test
	public void testSetCfuCondizioneIniziale() {
		/*non effettuo alcun set*/   //allora vi sono i cfu iniziali (20).
		assertEquals(20,giocatore(0).getCfu());
	}
	@Test
	public void testSetCfuVerificato() {
		assertEquals(10,giocatore(10).getCfu());
	}
}
