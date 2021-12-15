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

public class MagoTest {

	private Partita partita;
	private Attrezzo attrezzo;
	private Mago m;
	
	@Before
		public void setUp() {
		this.m = new Mago("Merlino", "presentazione:prova-test", new Attrezzo("bacchetta", 2));
		this.partita = new Partita(new Labirinto(new IOConsole()));
		this.partita.getLabirinto().setStanzaCorrente(new Stanza("corrente"));
		this.attrezzo = new Attrezzo("osso", 2);
	}

	
	@Test(expected=NullPointerException.class)
	public void testAgisciConMagoNull() {
		Mago m = null;
		m.agisci(this.partita);
	}
	
	@Test
	public void testAgisciConMagoStringa() {  //il mago ha donato.
											  //il mago possiede solo la bacchetta.
		assertEquals("Attrezzo donato. Controlla, si trova nella stanza", this.m.agisci(this.partita));
	}
	
	
	@Test
	public void testAgisciConMagoControlloStanza() {  //il mago ha donato.
		this.m.agisci(this.partita);	  //il mago possiede solo la bacchetta.
		assertNotNull(this.partita.getLabirinto().getStanzaCorrente().getAttrezzo("bacchetta"));
	}
	
	@Test
	public void testAgisciConMagoGiaDonato() {  //il mago ha già donato.
		this.m.agisci(this.partita);	  
		assertEquals("Attrezzo non disponibile perchè già donato.",this.m.agisci(this.partita));
	}
	
	
	@Test(expected=NullPointerException.class)
	public void testRiceviRegaloCaneNull() {
		Mago m = null;
		m.riceviRegalo(null,null);
	}
	
	@Test
	public void testRiceviRegaloAttrezzoNull() {
		assertNull(this.m.riceviRegalo(null, null));
	}
	
	@Test
	public void testStringaRiceviRegaloAttrezzoStringa() {
		assertEquals("Ho dimezzato il peso di questo attrezzo che mi hai regalato; l'ho posato nella stanza corrente",this.m.riceviRegalo(this.attrezzo, this.partita));
	}
	
	@Test
	public void testStringaRiceviRegaloAttrezzoInStanzaDimezzatoDiPeso() {
		this.m.riceviRegalo(this.attrezzo, this.partita);
		assertEquals(1,this.partita.getLabirinto().getStanzaCorrente().getAttrezzo("osso").getPeso());
	}

}
