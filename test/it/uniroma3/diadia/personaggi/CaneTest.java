package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaneTest {
	
	private Partita partita;
	private Attrezzo attrezzo;
	private Cane c;
	
	@Before
	public void setUp() {
		this.partita = new Partita(new Labirinto(new IOConsole()));
		this.partita.getLabirinto().setStanzaCorrente(new Stanza("corrente"));
		this.attrezzo = new Attrezzo("osso", 2);
		this.c = new Cane("carlino", "presentazione:test-prova");
	}

	
	
	@Test(expected=NullPointerException.class)
	public void testAgisciConCaneNull() {
		Cane c = null;
		c.agisci(this.partita);
	}
	
	@Test
	public void testAgisciConCane() {  //il cane ha morso
		this.c.agisci(this.partita);
		assertEquals(19, this.partita.getGiocatore().getCfu());
	}
	
	@Test(expected=NullPointerException.class)
	public void testRiceviRegaloCaneNull() {
		Cane c = null;
		c.riceviRegalo(null,null);
	}
	
	@Test
	public void testRiceviRegaloAttrezzoNull() {
		assertNull(this.c.riceviRegalo(null, null));
	}
	
	@Test
	public void testStringaRiceviRegaloAttrezzoOsso() {
		assertEquals("Grazie per l'osso. Ho fatto cadere un attrezzo, controlla nella stanza!",this.c.riceviRegalo(this.attrezzo, this.partita));
	}
	
	@Test
	public void testAtrezzoInStanzaRiceviRegaloAttrezzoOsso() {
		this.c.riceviRegalo(new Attrezzo("osso", 2), this.partita);
		assertNotNull(this.partita.getLabirinto().getStanzaCorrente().getAttrezzo("palla"));
	}
	
	
	@Test
	public void testAtrezzoInStanzaRiceviRegaloAttrezzoNonOsso() { //il cane ha morso
		this.c.riceviRegalo(new Attrezzo("spada", 2),this.partita);
		assertEquals(19, this.partita.getGiocatore().getCfu());
	}
	
	


}
