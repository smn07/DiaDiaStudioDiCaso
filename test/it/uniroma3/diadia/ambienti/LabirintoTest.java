package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;

public class LabirintoTest {
	
	
	private Labirinto l;
	private Stanza vincente;
	private Stanza corrente;

	@Before
	public void setUp() {
		this.l = new Labirinto(new IOConsole());
		this.vincente = new Stanza("vincente");
		this.corrente = new Stanza("corrente");
	}
	
	private Labirinto labirintoStanzaCorrente(Stanza stanza) {
		this.l.setStanzaCorrente(stanza);
		return this.l;
	}
	
	private Labirinto labirintoStanzaVincente(Stanza stanza) {
		this.l.setStanzaVincente(stanza);
		return this.l;
	}
	
	
	
	@Test
	public void testGetStanzaVincenteConNull() {
		assertSame(null,labirintoStanzaVincente(null).getStanzaVincente());
	}
	
	@Test
	public void testGetStanzaVincenteVerificato() {
		assertSame(this.vincente,labirintoStanzaVincente(this.vincente).getStanzaVincente());
	}
	
	@Test
	public void testGetStanzaCorrenteConNull() {
		assertSame(null,labirintoStanzaCorrente(null).getStanzaCorrente());
	}
	
	@Test
	public void testGetStanzaCorrenteVerificato() {
		assertSame(this.corrente,labirintoStanzaCorrente(this.corrente).getStanzaCorrente());
	}
	

}
