package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;

public class LabirintoBuilderTest {

	private LabirintoBuilder l;

	@Before
	public void setUp() {
		this.l = new LabirintoBuilder(new IOConsole());
	}
	
	private LabirintoBuilder labirintoBuilder(String stanza1,String stanza2,String attrezzo,int peso) {
		
		this.l.addStanza(stanza1);
		this.l.addStanza(stanza2);
		this.l.addAttrezzo(attrezzo,peso);
		
		return this.l;
	}
	
	
	
	@Test
	public void testaddStringInizialeNull() {
		this.l.addStanzaIniziale(null);
		 
		 assertNull(this.l.getIniziale());
		 assertNull(this.l.getMappaStanze().get("iniziale"));
		 
	}
	
	
	@Test
	public void testaddStringIniziale() {
		this.l.addStanzaIniziale("iniziale");
		 
		 assertEquals("iniziale",this.l.getIniziale().getNome());
		 assertEquals("iniziale",this.l.getMappaStanze().get("iniziale").getNome());
		 
	}
	
	
	@Test
	public void testaddStringVincenteNull() {
		this.l.addStanzaVincente(null);
		 
		 assertNull(this.l.getVincente());
		 assertNull(this.l.getMappaStanze().get("Vincente"));
		 
	}
	
	
	@Test
	public void testaddStringVincente() {
		 this.l.addStanzaVincente("Vincente");
		 
		 assertEquals("Vincente",this.l.getVincente().getNome());
		 assertEquals("Vincente",this.l.getMappaStanze().get("Vincente").getNome());
		 
	}
	
	
	@Test
	public void testaddStanza() {
		assertEquals("ultima",labirintoBuilder("prima","ultima",null,0).getUltimaStanza().getNome());
	}
	
	
	
	@Test
	public void testaddAttrezzoAttrezzoNull() {
		LabirintoBuilder l = labirintoBuilder("prima","ultima",null,0);
		assertEquals(0,l.getUltimaStanza().getAttrezzi().size());
	}
	
	
	@Test
	public void testaddAttrezzoVerificato() {
		LabirintoBuilder l = labirintoBuilder("prima","ultima","spada",4);
		assertEquals("spada",l.getUltimaStanza().getAttrezzo("spada").getNome());
	}
	
	
	@Test
	public void testaddAdiacenzaNonVerificato() {
		
		LabirintoBuilder l = labirintoBuilder("prima","ultima",null,0);
		l.addAdiacenza("prima", "ultima", "nord");
		assertNull("ultima",l.getMappaStanze().get("prima").getStanzaAdiacente("est"));
	}
	
	
	@Test
	public void testaddAdiacenzaVerificato() {
		
		LabirintoBuilder l = labirintoBuilder("prima","ultima",null,0);
		l.addAdiacenza("prima", "ultima", "nord");
		assertEquals("ultima",l.getMappaStanze().get("prima").getStanzaAdiacente("nord").getNome());
	}
	
	/*TEST NON MINIMALI*/
	
	@Test
	public void testLabirintoBuilderMonolocale() {
		this.l.addStanza("stanza");
		this.l.addAttrezzo("spada", 4);
		
		assertEquals("spada",this.l.getMappaStanze().get("stanza").getAttrezzo("spada").getNome());
		assertNull(this.l.getMappaStanze().get("stanza").getStanzaAdiacente("nord"));
		assertNull(this.l.getMappaStanze().get("stanza").getStanzaAdiacente("sud"));
		assertNull(this.l.getMappaStanze().get("stanza").getStanzaAdiacente("est"));
		assertNull(this.l.getMappaStanze().get("stanza").getStanzaAdiacente("ovest"));
		
	}
	
	@Test
	public void testLabirintoBuilderBilocale() {
		this.l.addStanza("stanza");
		this.l.addAttrezzo("osso", 1);
		this.l.addStanza("stanza2");
		this.l.addAttrezzo("spada", 4);
		this.l.addAdiacenza("stanza", "stanza2", "nord");
		this.l.addAdiacenza("stanza2", "stanza", "sud");
		
		assertEquals("osso",this.l.getMappaStanze().get("stanza").getAttrezzo("osso").getNome());
		assertEquals("spada",this.l.getMappaStanze().get("stanza2").getAttrezzo("spada").getNome());
		assertNull("spada",this.l.getMappaStanze().get("stanza").getAttrezzo("spada"));
		assertNull("osso",this.l.getMappaStanze().get("stanza2").getAttrezzo("osso"));
		
		
		assertEquals("stanza2",this.l.getMappaStanze().get("stanza").getStanzaAdiacente("nord").getNome());
		assertNull(this.l.getMappaStanze().get("stanza").getStanzaAdiacente("sud"));
		assertNull(this.l.getMappaStanze().get("stanza").getStanzaAdiacente("est"));
		assertNull(this.l.getMappaStanze().get("stanza").getStanzaAdiacente("ovest"));
		
		assertEquals("stanza",l.getMappaStanze().get("stanza2").getStanzaAdiacente("sud").getNome());
		assertNull(this.l.getMappaStanze().get("stanza2").getStanzaAdiacente("nord"));
		assertNull(this.l.getMappaStanze().get("stanza2").getStanzaAdiacente("est"));
		assertNull(this.l.getMappaStanze().get("stanza2").getStanzaAdiacente("ovest"));		
	
	}
	

}
