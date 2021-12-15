package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StregaTest {

	private Strega s;
	private Partita partita;
	private Stanza stanzaCorrente;
	private Stanza adiacente1;
	private Stanza adiacente2;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;

	@Before
	public void setUp() {
		this.s = new Strega("strega","presentazione:prova-test");
		this.partita = new Partita(new Labirinto(new IOConsole()));
		this.attrezzo1 = new Attrezzo("attr1",1);
		this.attrezzo2 = new Attrezzo("attr2",2);
		this.attrezzo3 = new Attrezzo("attr3",3);
		this.stanzaCorrente = new Stanza("corrente");
		this.stanzaCorrente.addAttrezzo(this.attrezzo1);
		this.stanzaCorrente.setPersonaggio(s);
		
		
		this.partita.getLabirinto().setStanzaCorrente(stanzaCorrente);
		this.adiacente1 = new Stanza("adiacente1");
		this.adiacente2 = new Stanza("adiacente2");
	}
	
	private void factoryMethod(Stanza ad1,Stanza ad2,int numeroAttrezziDaAggiungereAd1,int numeroAttrezziDaAggiungereAd2,
			                          boolean saluta) {
		
		if(ad1 != null) {
			if(numeroAttrezziDaAggiungereAd1 == 1) ad1.addAttrezzo(this.attrezzo1);
			if(numeroAttrezziDaAggiungereAd1 == 2) {ad1.addAttrezzo(this.attrezzo1); ad1.addAttrezzo(this.attrezzo2);}
			if(numeroAttrezziDaAggiungereAd1 == 3) {ad1.addAttrezzo(this.attrezzo1); ad1.addAttrezzo(this.attrezzo2); ad1.addAttrezzo(this.attrezzo3);}
		}
		if(ad2 != null) {
			if(numeroAttrezziDaAggiungereAd2 == 1) ad2.addAttrezzo(this.attrezzo1);
			if(numeroAttrezziDaAggiungereAd2 == 2) {ad2.addAttrezzo(this.attrezzo1); ad2.addAttrezzo(this.attrezzo2);}
			if(numeroAttrezziDaAggiungereAd2 == 3) {ad2.addAttrezzo(this.attrezzo1); ad2.addAttrezzo(this.attrezzo2); ad2.addAttrezzo(this.attrezzo3);}
		}
		
		if(ad1 != null) this.stanzaCorrente.impostaStanzaAdiacente("nord", ad1);
		if(ad2 != null) this.stanzaCorrente.impostaStanzaAdiacente("est", ad2);
		
		if(saluta) this.s.saluta();
	}
	

	
	@Test(expected=NullPointerException.class)
	public void testAgisciConStregaNull() {
		Strega s = null;
		s.agisci(this.partita);
	}
	
	@Test
	public void testAgisciSenzaStanzeAdStringa() {
		assertEquals("La stanza corrente non contiene stanze adiacenti oppure hanno lo stesso numero di attrezzi della stanza corrente, non ti sposterò da nessuna parte!", this.s.agisci(this.partita));
	}
	
	@Test
	public void testAgisciSenzaStanzeAdStanzaCorrenteInvariata() {
		this.s.agisci(this.partita);
		assertSame(this.stanzaCorrente, this.partita.getLabirinto().getStanzaCorrente());
	}
	
	@Test
	public void testAgisciUnaStanzaAdStanzaCorrenteVariataSaluto() { 
		factoryMethod(this.adiacente1, null, 2, 0, true);
		this.s.agisci(this.partita);
		assertSame(this.adiacente1, this.partita.getLabirinto().getStanzaCorrente());
	}
	
	@Test
	public void testAgisciUnaStanzaAdStanzaCorrenteNONVariataNoSaluto() { 
		factoryMethod(this.adiacente1, null, 2, 0, false);
		this.s.agisci(this.partita);
		assertSame(this.stanzaCorrente, this.partita.getLabirinto().getStanzaCorrente());
	}
	
	@Test
	public void testAgisciUnaStanzaAdStessoNumeroAttrSaluto() { 
		factoryMethod(this.adiacente1, null, 1, 0, true);
		this.s.agisci(this.partita);
		assertSame(this.stanzaCorrente, this.partita.getLabirinto().getStanzaCorrente());
	}
	
	
	@Test
	public void testAgisciDueStanzeAdConNumeroAttrMinoreOUgualeStanzaCorrenteSaluto() { 
		factoryMethod(this.adiacente1, this.adiacente2, 0, 0, true);
		this.s.agisci(this.partita);
		assertSame(this.stanzaCorrente, this.partita.getLabirinto().getStanzaCorrente());
	}
	
	@Test
	public void testAgisciDueStanzeAdConNumeroAttrAd2MaggioreSaluto() { 
		factoryMethod(this.adiacente1, this.adiacente2, 1, 2, true);
		this.s.agisci(this.partita);
		assertSame(this.adiacente2, this.partita.getLabirinto().getStanzaCorrente());
	}

	
	
	@Test
	public void testAgisciUnaStanzaAdStanzaCorrenteVariataNoSaluto() { 
		factoryMethod(this.adiacente1, null, 0, 0, false);
		this.s.agisci(this.partita);
		assertSame(this.adiacente1, this.partita.getLabirinto().getStanzaCorrente());
	}
	
	@Test
	public void testAgisciUnaStanzaAdStessoNumeroAttrNoSaluto() { 
		factoryMethod(this.adiacente1, null, 1, 0, false);
		this.s.agisci(this.partita);
		assertSame(this.stanzaCorrente, this.partita.getLabirinto().getStanzaCorrente());
	}
	
	
	@Test
	public void testAgisciDueStanzeAdConNumeroAttrMaggioreOUgualeStanzaCorrenteNoSaluto() { 
		factoryMethod(this.adiacente1, this.adiacente2, 3, 2, false);
		this.s.agisci(this.partita);
		assertSame(this.stanzaCorrente, this.partita.getLabirinto().getStanzaCorrente());
	}
	
	@Test
	public void testAgisciDueStanzeAdConNumeroAttrAd2MinoreNoSaluto() { 
		factoryMethod(this.adiacente1, this.adiacente2, 2, 1, false);
		this.s.agisci(this.partita);
		assertSame(this.stanzaCorrente, this.partita.getLabirinto().getStanzaCorrente());
	}
	
	
	
	
	
	
	
	
	
	

}
