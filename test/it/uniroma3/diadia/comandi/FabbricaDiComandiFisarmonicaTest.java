package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;

public class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica f;

	@Before
	public void setUp() {
		this.f = new FabbricaDiComandiFisarmonica();
	}

	
	@Test
	public void testComandoNonValidoConComandoInesistente() {
		assertEquals("comandononvalido", this.f.costruisciComando("osserva spada", new IOConsole()).getNome());
	}
	
	@Test
	public void testComandoVai() {
		assertEquals("comandovai", this.f.costruisciComando("vai", new IOConsole()).getNome());
	}
	
	@Test
	public void testComandoVaiConParametro() {
		assertEquals("nord", this.f.costruisciComando("vai nord", new IOConsole()).getParametro());
	}
	
	@Test
	public void testComandoPrendi() {
		assertEquals("comandoprendi", this.f.costruisciComando("prendi", new IOConsole()).getNome());
	}
	
	@Test
	public void testComandoPrendiConParametro() {
		assertEquals("spada", this.f.costruisciComando("prendi spada", new IOConsole()).getParametro());
	}
	
	@Test
	public void testComandoPosa() {
		assertEquals("comandoposa", this.f.costruisciComando("posa", new IOConsole()).getNome());
	}
	
	@Test
	public void testComandoPosaConParametro() {
		assertEquals("spada", this.f.costruisciComando("posa spada", new IOConsole()).getParametro());
	}
	
	@Test
	public void testComandoAiuto() {
		assertEquals("comandoaiuto", this.f.costruisciComando("aiuto", new IOConsole()).getNome());
	}
	
	@Test
	public void testComandoFine() {
		assertEquals("comandofine", this.f.costruisciComando("fine", new IOConsole()).getNome());
	}
	
	@Test
	public void testComandoGuarda() {
		assertEquals("comandoguarda", this.f.costruisciComando("guarda", new IOConsole()).getNome());
	}

	
	
	
	
	
	
	
	
	
	
}
