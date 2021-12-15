package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

	
	private StanzaBloccata s;
	private Attrezzo chiave;

	@Before
	public void setUp() {
		this.s = new StanzaBloccata("bloccata", "nord");
		this.chiave = new Attrezzo("chiave",1);
		this.s.impostaStanzaAdiacente("nord", new Stanza("aula1"));
		this.s.impostaStanzaAdiacente("est", new Stanza("aula2"));
	}

	private StanzaBloccata stanzaBloccataTest(Attrezzo attrezzo) {
	
		this.s.addAttrezzo(attrezzo);
		
		return this.s;
	}
	
	/*TEST GET STANZA ADIACENTE*/
	
	@Test
	public void testgetStanzaAdiacenteDirezioneNonBloccata() {
		assertEquals("aula2",stanzaBloccataTest(this.chiave).getStanzaAdiacente("est").getNome());
	}
	
	@Test
	public void testgetStanzaAdiacenteDirezioneBloccataSenzaChiave() {
		assertEquals("bloccata",stanzaBloccataTest(new Attrezzo("spada",1)).getStanzaAdiacente("nord").getNome());
	}
	
	@Test
	public void testgetStanzaAdiacenteDirezioneBloccataConChiave() {
		assertEquals("aula1",stanzaBloccataTest(this.chiave).getStanzaAdiacente("nord").getNome());
	}
	


}
