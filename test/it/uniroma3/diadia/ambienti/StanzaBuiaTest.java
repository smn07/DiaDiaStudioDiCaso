package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	
	private StanzaBuia s;
	private Attrezzo spada;
	private Attrezzo lanterna;


	@Before
	public void setUp() {
		this.s = new StanzaBuia("stanza buia");
		this.spada = new Attrezzo("spada", 5);
		this.lanterna = new Attrezzo("lanterna", 4);
	}

	private StanzaBuia stanzaBuiaTest(Attrezzo attrezzo1, Attrezzo attrezzo2) {
		
		this.s.addAttrezzo(attrezzo1);
		this.s.addAttrezzo(attrezzo2);
		
		return this.s;
	}
	
	/*TEST GET DESCRIZIONE*/
	
	@Test
	public void testGetDescrizioneSenzaLanterna() {
		assertEquals("Qui c'è un buio pesto!", stanzaBuiaTest(this.spada,null).getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneConLanterna() {
		assertNotEquals("Qui c'è un buio pesto!", stanzaBuiaTest(this.lanterna,null).getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneConPiuAttrConLanterna() {
		assertNotEquals("Qui c'è un buio pesto!", stanzaBuiaTest(this.lanterna, this.spada).getDescrizione());
	}
	
	
	@Test
	public void testGetDescrizioneConPiuAttrSenzaLanterna() {
		assertEquals("Qui c'è un buio pesto!", stanzaBuiaTest(new Attrezzo("osso", 4), this.spada).getDescrizione());
	}
	
}