package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Test;


import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class ComandoPrendiTest {

	/*private Partita comandoPrendiPartita(int numeroAttrStanza, int pesoPerBorsa, String nomeAttrezzo) {
		
		Partita p = new Partita(new Labirinto());
		
		Stanza s = new Stanza("stanza corrente");
		for(int i = 0; i < numeroAttrStanza; i++) {
			s.addAttrezzo(new Attrezzo("nome"+i,i+1));
		}
		
		p.getLabirinto().setStanzaCorrente(s);
		p.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("nome",pesoPerBorsa));
		
		
		Comando c = new ComandoPrendi();
		c.setParametro(nomeAttrezzo);
		c.setIO(new IOConsole());
		
		c.esegui(p);
		return p;
	}*/
	
	
	private Partita comandoPrendiPartita(int numeroAttrStanza, int pesoPerBorsa, String nomeAttrezzo) {
		
		LabirintoBuilder l = new LabirintoBuilder(new IOConsole());
		Partita p = new Partita(l);

		l.addStanza("stanzaCorrente");
		for(int i = 0; i < numeroAttrStanza; i++) {
			l.addAttrezzo("nome"+i,i+1);
		}

		p.getLabirinto().setStanzaCorrente(l.getUltimaStanza());
		p.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("nome",pesoPerBorsa));


		Comando c = new ComandoPrendi();
		c.setIO(new IOConsole());
		c.setParametro(nomeAttrezzo);

		c.esegui(p);
		return p;
	}

	@Test
	public void testEseguiPrendendoAttrInesistente() {
		Partita p = comandoPrendiPartita(3, 1, "spada");
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("spada"));
		assertFalse(p.getLabirinto().getStanzaCorrente().hasAttrezzo("spada"));
	}
	
	
	@Test
	public void testEseguiVerificato() {
		Partita p = comandoPrendiPartita(3, 1, "nome2");
		assertTrue(p.getGiocatore().getBorsa().hasAttrezzo("nome2"));
		assertFalse(p.getLabirinto().getStanzaCorrente().hasAttrezzo("nome2"));
	}
	
	
	@Test
	public void testEseguiBorsaPiena() {
		Partita p = comandoPrendiPartita(3, 10, "nome2");
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("nome2"));
		assertTrue(p.getLabirinto().getStanzaCorrente().hasAttrezzo("nome2"));
	}
	
	
	@Test
	public void testEseguiBorsaQuasiPiena() {
		Partita p = comandoPrendiPartita(3, 8, "nome2");   //entrerebbe solo un attrezzo di peso 2 e non 3.
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("nome2"));
		assertTrue(p.getLabirinto().getStanzaCorrente().hasAttrezzo("nome2"));
	}
	

}
