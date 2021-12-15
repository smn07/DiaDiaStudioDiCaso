package it.uniroma3.diadia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {
	
	private Partita p;
	private Stanza stanza1;
	private Stanza stanza2;

	@Before
	public void setUp() {
		this.p = new Partita(new Labirinto(new IOConsole()));
		this.stanza1 = new Stanza("stanza1");
		this.stanza2 = new Stanza("stanza2");
	}
	
	private Partita partita(Stanza corrente,Stanza vincente,boolean finita,int cfu) {
		if(corrente !=  null && vincente != null) {
			this.p.getLabirinto().setStanzaCorrente(corrente);
			this.p.getLabirinto().setStanzaVincente(vincente);
		}
		
		if(finita == true) { p.setFinita(); }
		this.p.getGiocatore().setCfu(cfu);
		
		return this.p;
	}
	
	
	
	/*TEST DEL METODO VINTA*/
	
	@Test
	public void testNonVittoria() {
		assertFalse(partita(this.stanza1, this.stanza2,false,20).vinta());
	}
	
	@Test
	public void testVittoria() {
		assertTrue(partita(this.stanza1, this.stanza1,false,20).vinta());
	}
	
	@Test
	public void testVittoriaConZeroCfu() {
		assertTrue(partita(this.stanza1, this.stanza1,false,0).vinta());
	}
	
	
	/*TEST METODO IS FINITA*/
	
	@Test
	public void testIsFinitaPrimaCondizioneVerificata() {
		  /*le altre due condizioni di "isFinita" non sono verificate.
		    cfu = 20,stanzacorrente != stanzaVinc.*/
		assertTrue(partita(null,null,true,20).isFinita());
	}
	
	@Test
	public void testIsFinitaSecondaCondizioneVerificata() {
              /*le altre due condizioni di "isFinita" non sono verificate.
		        cfu = 20, finita = false;*/
		assertTrue(partita(this.stanza1,this.stanza1,false,20).isFinita());
	}
	
	@Test
	public void testIsFinitaTerzaCondizioneVerificata() {
		/*le altre due condizioni di "isFinita" non sono verificate.
		  stanzacorrente != stanzaVinc, finita = false;*/
		assertTrue(partita(null,null,false,0).isFinita());
	}
	
	@Test
	public void testIsFinitaTutteVerificate() {
		assertTrue(partita(this.stanza1,this.stanza1,true,0).isFinita());
	}
	
	@Test
	public void testIsFinitaNessunaVerificata() {
		assertFalse(new Partita(new Labirinto(new IOConsole())).isFinita());
	}
	
	
	
	
	
	
	
	
	
}
