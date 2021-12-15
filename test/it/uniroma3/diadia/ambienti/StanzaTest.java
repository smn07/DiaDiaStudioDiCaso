package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	
	private Stanza stanzaAddAttrezzo(int numeroAttrDaAggiungere) {
		Stanza s = new Stanza("nomeacaso");

		for(int i = 0; i < numeroAttrDaAggiungere; i++) {
			s.addAttrezzo(new Attrezzo("nome"+ i,i));
		}

		return s;
	}
	
	
	/*TEST PER IMPOSTA STANZA ADIACENTE*/
	private Stanza stanzaImpostaStanzaAd(Stanza ad, String direzione) {
		Stanza s = new Stanza("nomeacaso");
		s.impostaStanzaAdiacente(direzione, ad);
		
		return s;
	}
	
	
	
	@Test 
	public void testImpostaStanzaAdConDirezioneEStanzaAdInesistenti() {
		assertNull(stanzaImpostaStanzaAd(null,null).getStanzaAdiacente(null));
	}
	
	
	@Test
	public void testImpostaStanzaAdInesistente() {
		assertNull(stanzaImpostaStanzaAd(null,"nord").getStanzaAdiacente("nord"));
	}
	
	@Test 
	public void testImpostaStanzaAdConDirezioneInesistente() {
		assertNull(stanzaImpostaStanzaAd(new Stanza("adiac"),null).getStanzaAdiacente(null));
	}
	
	
	@Test
	public void testImpostaStanzaAdEsistente() {
		Stanza adiac = new Stanza("adiac");
		assertSame(adiac, stanzaImpostaStanzaAd(adiac,"est").getStanzaAdiacente("est"));
	}
	
	@Test
	public void testStanzaAdRimpiazzata() {  //in quanto sto utilizzando le mappe.
		Stanza adiac1 = new Stanza("adiac1");
		Stanza adiac2 = new Stanza("adiac2");
		Stanza s = stanzaImpostaStanzaAd(adiac1,"est");
		assertSame(adiac2, stanzaImpostaStanzaAd(adiac2,"est").getStanzaAdiacente("est"));
		
	}
	
	@Test
	public void testStanzaAdNumeroAd() {
		Stanza adiac1 = new Stanza("adiac1");
		Stanza adiac2 = new Stanza("adiac2");
		Stanza s = stanzaImpostaStanzaAd(adiac1,"est");
		s.impostaStanzaAdiacente("est", adiac2);
		assertSame(1, s.getDirezioni().size());
	}
	
	@Test
	public void testStanzaAdNumeroAd2() {
		Stanza adiac1 = new Stanza("adiac1");
		Stanza adiac2 = new Stanza("adiac2");
		Stanza s = stanzaImpostaStanzaAd(adiac1,"est");
		s.impostaStanzaAdiacente("nord", adiac2);
		assertSame(2, s.getDirezioni().size());
	}

	
	/*TEST PER ADD ATTREZZO*/
	
	@Test
	public void testAddAttrPassandogliNull() {
		assertFalse(stanzaAddAttrezzo(3).addAttrezzo(null));
	}
	
	@Test
	public void testAddAttrConNumeroAttrezziMaxNonRaggiunto() {
		assertTrue(stanzaAddAttrezzo(3).addAttrezzo(new Attrezzo("nuovo",1)));
	}
	
	@Test
	public void testAddAttrConNumeroAttrezziMaxRaggiunto() {
		assertFalse(stanzaAddAttrezzo(10).addAttrezzo(new Attrezzo("nome",1)));
	}
	
	@Test
	public void testAddAttrezzoNumeroAttrezzi() {
		Stanza s = stanzaAddAttrezzo(3);
		s.addAttrezzo(new Attrezzo("nome1",1));
		assertEquals(3, s.getNumeroAttrezzi());
	}
	
	@Test
	public void testAddAttrezzoNumeroAttrezzi2() {
		Stanza s = stanzaAddAttrezzo(3);
		s.addAttrezzo(new Attrezzo("nuovo",1));
		assertEquals(4, s.getNumeroAttrezzi());
	}
	

	/*TEST PER HAS ATTREZZO*/
	
	@Test
	public void testHasConAttrezziTuttiNull() {
		assertFalse(stanzaAddAttrezzo(0).hasAttrezzo("spada"));
	}
	
	@Test
	public void testHasAttrPassandoNull() {
		assertFalse(stanzaAddAttrezzo(3).hasAttrezzo(null));
	}
	
	
	
	@Test
	public void testHasAttrNonVerificato() {
		assertFalse(stanzaAddAttrezzo(3).hasAttrezzo("spada"));
	}
	
	
	
	@Test
	public void testHasAttrVerificato() {
		assertTrue(stanzaAddAttrezzo(3).hasAttrezzo("nome1"));
	}
	
	/*TEST PER REMOVE ATTREZZO*/
	
	@Test
	public void testRemoveAttrConAttrezziNullPassandoNull() {
		assertFalse(stanzaAddAttrezzo(0).removeAttrezzo(null));
	}
	
	@Test
	public void testRemoveAttrConAttrezziNull() {
		assertFalse(stanzaAddAttrezzo(0).removeAttrezzo(new Attrezzo("nome",1)));
	}
	
	@Test
	public void testRemoveAttrPassandoNull() {
		assertFalse(stanzaAddAttrezzo(3).removeAttrezzo(null));
	}
	
	@Test
	public void testRemoveAttrNonVerificato() {
		assertFalse(stanzaAddAttrezzo(3).removeAttrezzo(new Attrezzo("nuovo",1)));
	}
	
	@Test
	public void testRemoveAttrVerificato() {
		Stanza s = stanzaAddAttrezzo(3);
		assertTrue(s.removeAttrezzo(s.getAttrezzo("nome1")));
	}
	

}
