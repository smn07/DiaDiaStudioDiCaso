package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVaiTest {

	private Stanza s1;
	private Stanza s2;

	/*TEST PER IL METODO ESEGUI DELLA CLASSE ComandoVAI.*/
	
	private Partita comandoVaiPartita(String direzioneStanza,String direzioneVai,Stanza s2) {
		
		Partita p = new Partita(new Labirinto(new IOConsole()));  
		this.s1 = new Stanza("s1");
		this.s2 = s2;
		
		s1.impostaStanzaAdiacente(direzioneStanza, s2);
		
		p.getLabirinto().setStanzaCorrente(s1);
		
		Comando c = new ComandoVai();
		c.setIO(new IOConsole());
		c.setParametro(direzioneVai);
		c.esegui(p);
		
		return p;
	}
	
	
	@Test
	public void testEseguiConDirezioneNull() {
		Partita p = comandoVaiPartita(null,null,new Stanza("s2"));
		assertSame(this.s1,p.getLabirinto().getStanzaCorrente());
	}
	
	@Test
	public void testEseguiConStanzaAdiacNull() {
		Partita p = comandoVaiPartita("nord","nord",null);
		assertSame(this.s1,p.getLabirinto().getStanzaCorrente());
	}
	
	@Test
	public void testEseguiVerificato() {
		Partita p = comandoVaiPartita("nord","nord",new Stanza("s2"));
		assertSame(this.s2,p.getLabirinto().getStanzaCorrente());
	}
	
	
	@Test
	public void testEseguiNONVerificato() {
		Partita p = comandoVaiPartita("nord","est",new Stanza("s2"));
		assertNotSame(this.s2,p.getLabirinto().getStanzaCorrente());
	}
	
	

}
