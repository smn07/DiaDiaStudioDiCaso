package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {

	private Partita p;
	private Stanza s;
	private ComandoPosa c;


	@Before
	public void setUp() {
		IO io = new IOConsole();
		this.p = new Partita(new Labirinto(io));
		this.s = new Stanza("stanza corrente");
		
		this.p.getLabirinto().setStanzaCorrente(s);
		
		this.c = new ComandoPosa();
		this.c.setIO(io);
	}
	
	private Partita comandoPosaPartita(int numeroAttrBorsa, int numeroAttrStanza, String nomeAttrezzo) {
		
		for(int i = 0; i < numeroAttrStanza; i++) {
			s.addAttrezzo(new Attrezzo("nomeAttrStanza"+i,i+1));
		}
		
		
		for(int i = 0; i < numeroAttrBorsa; i++) {
			p.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("nome"+i,i+1));
		}

		c.setParametro(nomeAttrezzo);
		
		c.esegui(p);
		return p;
	}
	
	
	@Test
	public void testEseguiPrendendoAttrInesistente() {
		Partita p = comandoPosaPartita(3, 1, "spada");
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("spada"));
		assertFalse(p.getLabirinto().getStanzaCorrente().hasAttrezzo("spada"));
	}
	
	
	@Test
	public void testEseguiVerificato() {
		Partita p = comandoPosaPartita(3, 1, "nome2");
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("nome2"));
		assertTrue(p.getLabirinto().getStanzaCorrente().hasAttrezzo("nome2"));
	}
	
	
	@Test
	public void testEseguiStanzaPiena() {
		Partita p = comandoPosaPartita(3, 10, "nome2");
		assertTrue(p.getGiocatore().getBorsa().hasAttrezzo("nome2"));
		assertFalse(p.getLabirinto().getStanzaCorrente().hasAttrezzo("nome2"));
	}
	
	

}
