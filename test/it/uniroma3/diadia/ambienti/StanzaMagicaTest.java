package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {

	private Attrezzo spada;

	@Before
	public void setUp() {
		this.spada = new Attrezzo("spada",3);
	}
	
	private StanzaMagica nuovaStanzaMagica(int numeroMaxAttrAggiunti,Attrezzo attr) {
		StanzaMagica s = new StanzaMagica(numeroMaxAttrAggiunti, "nome");
		s.addAttrezzo(attr);
		
		return s;
	}
	
	/*TEST PER ModificaAttrezzo, ma poichè modifica attrezzo è privato (come viene specificato dalle slides) allora lo commento*/
	
	/*@Test 
	public void testModificaAttrezzo() {
		Attrezzo p = new Attrezzo("spada",3);
		assertEquals("adaps", new StanzaMagica(1,"nome").modificaAttrezzo(p).getNome());
		assertEquals(6, nuovaStanzaMagica().modificaAttrezzo(p).getPeso());
	}*/
	

	
	
	@Test
	public void testAddAttrNoMagiaNome() {
		assertTrue(nuovaStanzaMagica(10,this.spada).hasAttrezzo("spada"));
	}
	
	@Test
	public void testAddAttrNoMagiaPeso() {
		assertEquals(3,nuovaStanzaMagica(10,this.spada).getAttrezzo("spada").getPeso());
	}
	
	@Test
	public void testAddAttrMagiaNome() {
		assertTrue(nuovaStanzaMagica(0,this.spada).hasAttrezzo("adaps"));
	}
	
	@Test
	public void testAddAttrMagiaPeso() {
		assertEquals(6,nuovaStanzaMagica(0,this.spada).getAttrezzo("adaps").getPeso());
	}
	
	
	
	
	
}
