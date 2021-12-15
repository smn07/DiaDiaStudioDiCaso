package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

public class IOSimulatorTest {

	private String messaggioBenvenuto;
	private DiaDia gioco;



	private IOSimulator impostaIoSimulator(Map<Integer,String> mapStr) {
		IOSimulator s = new IOSimulator(mapStr);
		
		this.gioco = new DiaDia(s,new Labirinto(s));  //s è sottotipo di IO. Vale il principio di sostituzione.
		gioco.gioca();
		
		this.messaggioBenvenuto = gioco.getBenvenuto();
		
		return s;
		
	}
	
	
	
	@Test
	public void testVai() {
		
		//String[] arrayStr = {"vai","fine"};
		Map<Integer,String> m = new HashMap<Integer,String>();
		m.put(0,"vai");
		m.put(1,"fine");
		
		IOSimulator ioS = impostaIoSimulator(m);
		
		assertTrue(ioS.hasNextMessaggio());
		assertEquals(this.messaggioBenvenuto,ioS.nextMessaggio());
		assertTrue(ioS.hasNextMessaggio());
		assertEquals("Dove vuoi andare ?",ioS.nextMessaggio());
		assertTrue(ioS.hasNextMessaggio());
		assertEquals("Direzione inesistente",ioS.nextMessaggio());
	}
	
	@Test
	public void testFine() {
		
		//String[] arrayStr = {"fine"};
		Map<Integer,String> m = new HashMap<Integer,String>();
		m.put(0,"fine");
		
		IOSimulator ioS = impostaIoSimulator(m);
		
		assertTrue(ioS.hasNextMessaggio());
		assertEquals(this.messaggioBenvenuto,ioS.nextMessaggio());
		assertTrue(ioS.hasNextMessaggio());
		assertEquals("Grazie di aver giocato!",ioS.nextMessaggio());
		
	}
	
	
	@Test
	public void testPrendi() {
		
		//String[] arrayStr = {"prendi","fine"};
		
		Map<Integer,String> m = new HashMap<Integer,String>();
		m.put(0,"prendi");
		m.put(1,"fine");
		
		IOSimulator ioS = impostaIoSimulator(m);
		
		assertTrue(ioS.hasNextMessaggio());
		assertEquals(this.messaggioBenvenuto,ioS.nextMessaggio());
		assertTrue(ioS.hasNextMessaggio());
		assertEquals("Quale attrezzo vuoi prendere ?",ioS.nextMessaggio());
		
		
	}
	
	@Test
	public void testAiuto() {
		
		//String[] arrayStr = {"aiuto","fine"};
		
		Map<Integer,String> m = new HashMap<Integer,String>();
		m.put(0,"aiuto");
		m.put(1,"fine");
		IOSimulator ioS = impostaIoSimulator(m);
		
		assertTrue(ioS.hasNextMessaggio());
		assertEquals(this.messaggioBenvenuto,ioS.nextMessaggio());
		assertTrue(ioS.hasNextMessaggio());
		assertEquals("vai ",ioS.nextMessaggio());
		assertEquals("aiuto ",ioS.nextMessaggio());
		assertEquals("fine ",ioS.nextMessaggio());
		assertEquals("prendi ",ioS.nextMessaggio());
		assertEquals("posa ",ioS.nextMessaggio());
		assertEquals("guarda ",ioS.nextMessaggio());
		
	}
	
	
	@Test
	public void testPrendiVaiPrendiPosaFine() {
		
		Map<Integer,String> m = new HashMap<Integer,String>();
		m.put(0,"prendi spada");
		m.put(1,"vai sud");
		m.put(2, "prendi lanterna");
		m.put(3,"posa spada");
		m.put(4,"fine");
		
		IOSimulator ioS = impostaIoSimulator(m);

		assertTrue(ioS.hasNextMessaggio());
		assertEquals(this.messaggioBenvenuto,ioS.nextMessaggio());
		assertTrue(ioS.hasNextMessaggio());
		
		assertEquals("Descrizione borsa:\nContenuto borsa (4kg/10kg): \nAttrezzi ordinati per peso e a parità di peso per nome:\n[spada (4kg)]\nAttrezzi ordinati per nome:\n[spada (4kg)]\nAttrezzi ordinati raggruppandoli per peso:\n[4=[spada (4kg)]]\nDescrizione generica:\n[spada (4kg)]", ioS.nextMessaggio());
		assertTrue(ioS.hasNextMessaggio());
		assertEquals("Descrizione stanza corrente:\nAtrio\nUscite: [nord, sud, est, ovest]\nAttrezzi nella stanza: [osso (1kg), chiave (1kg), maschera (5kg)]",ioS.nextMessaggio());
		assertTrue(ioS.hasNextMessaggio());
		assertEquals("Aula N10\nUscite: [nord, est, ovest]\nAttrezzi nella stanza: [lanterna (3kg)]",ioS.nextMessaggio());
		assertTrue(ioS.hasNextMessaggio());
		assertEquals("Descrizione borsa:\nContenuto borsa (7kg/10kg): \nAttrezzi ordinati per peso e a parità di peso per nome:\n[lanterna (3kg), spada (4kg)]\nAttrezzi ordinati per nome:\n[lanterna (3kg), spada (4kg)]\nAttrezzi ordinati raggruppandoli per peso:\n[3=[lanterna (3kg)], 4=[spada (4kg)]]\nDescrizione generica:\n[spada (4kg), lanterna (3kg)]",ioS.nextMessaggio());
		assertTrue(ioS.hasNextMessaggio());
		assertEquals("Descrizione stanza corrente:\nAula N10\nUscite: [nord, est, ovest]\nAttrezzi nella stanza: []", ioS.nextMessaggio());
		assertTrue(ioS.hasNextMessaggio());
		assertEquals("Descrizione borsa:\nContenuto borsa (3kg/10kg): \nAttrezzi ordinati per peso e a parità di peso per nome:\n[lanterna (3kg)]\nAttrezzi ordinati per nome:\n[lanterna (3kg)]\nAttrezzi ordinati raggruppandoli per peso:\n[3=[lanterna (3kg)]]\nDescrizione generica:\n[lanterna (3kg)]",ioS.nextMessaggio());
		assertTrue(ioS.hasNextMessaggio());
		assertEquals("Descrizione stanza corrente:\nAula N10\nUscite: [nord, est, ovest]\nAttrezzi nella stanza: [spada (4kg)]",ioS.nextMessaggio());
		assertTrue(ioS.hasNextMessaggio());
		assertEquals("Grazie di aver giocato!",ioS.nextMessaggio());
		assertFalse(ioS.hasNextMessaggio());
	}
	
	
}
