package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	
	private Borsa borsa(int numeroAttrDaAggiungere) {
		Borsa b = new Borsa();

		for(int i = 0; i < numeroAttrDaAggiungere; i++) {
			b.addAttrezzo(new Attrezzo("nome"+ i,i));
		}
		return b;
	}
	
	
	
	/*TEST PER ADD ATTREZZO*/
	
	private Borsa borsaAddAttr(Attrezzo attrezzo) {
		Borsa b = new Borsa();
		if(attrezzo != null) 
			b.addAttrezzo(attrezzo);
		return b;
	}
	
	
	@Test
	public void testAddAttrBorsaVuota() {
		assertTrue(borsaAddAttr(null).addAttrezzo(new Attrezzo("nuovo",3)));
	}
	
	@Test
	public void testAddAttPassandoNUll() {
		Attrezzo attrezzo = new Attrezzo("nome",3);
		assertFalse(borsaAddAttr(attrezzo).addAttrezzo(null));
	}
	
	@Test
	public void testAddAttNonVerificatoPercheBorsaQuasiPienaENonEntraUnNuovoAttr() {
		Attrezzo attrezzo = new Attrezzo("nome",9);   //entrerebbe solo un altro attrezzo di peso 1;
		assertFalse(borsaAddAttr(attrezzo).addAttrezzo(new Attrezzo("nuovo",3)));
	}
	
	@Test
	public void testAddAttVerificato() {
		Attrezzo attrezzo = new Attrezzo("nome",1);
		assertTrue(borsaAddAttr(attrezzo).addAttrezzo(new Attrezzo("nuovo",3)));
	}
	
	@Test
	public void testAddAttConBorsaPiena() {
		Attrezzo attrezzo = new Attrezzo("nome",10); //10 = pesoMax;
		assertFalse(borsaAddAttr(attrezzo).addAttrezzo(new Attrezzo("nuovo",3)));
	}
	
	
	/*TEST PER HAS ATTREZZO*/
	
	@Test 
	public void testHasAttrPassandoNull() {
		assertFalse(borsa(3).hasAttrezzo(null));
	}
	
	@Test 
	public void testHasAttrBorsaVuota() {
		assertFalse(borsa(0).hasAttrezzo("maschera"));
	}
	
	
	@Test 
	public void testHasAttrNonVerificato() {		
		assertFalse(borsa(3).hasAttrezzo("maschera"));
	}
	
	
	@Test 
	public void testHasAttrVerificato() {
		assertTrue(borsa(3).hasAttrezzo("nome2"));
	}
	
	
	/*TEST PER REMOVE ATTREZZO*/
	
	@Test
	public void testRemoveAttrPassandoNull() {
		assertNull(borsa(3).removeAttrezzo(null));
	}
	
	@Test
	public void testRemoveAttrSenzaAttrezziInBorsa() {
		assertNull(borsa(0).removeAttrezzo("maschera"));
	}
	
	@Test
	public void testRemoveAttrNonVerificato() {
		assertNull(borsa(3).removeAttrezzo("maschera"));
	}
	
	
	@Test
	public void testRemoveAttrVerificato() {
		assertEquals("nome2",borsa(3).removeAttrezzo("nome2").getNome());
	}
	
	
	/*TEST PER METODI DI ORDINAMENTO DEGLI ATTREZZI NELLA BORSA.*/
	
	@Test
	public void testgetContenutoOrdinatoPerPesoBorsaVuota() {
		assertTrue(borsa(0).getContenutoOrdinatoPerPeso().isEmpty());
	}
	
	/*nuovo factory method per questi test:*/
	
	private Borsa borsaSortAttr(String nome1, String nome2, int peso1, int peso2) {
		Borsa b = new Borsa();
		
		b.addAttrezzo(new Attrezzo(nome1,peso1));
		b.addAttrezzo(new Attrezzo(nome2,peso2));
		
		return b;
		
	}
	
	@Test
	public void testgetContenutoOrdinatoPerPesoBorsaElemPesoUguale() {
		Borsa b = borsaSortAttr("nome2","nome1",1,1);
		assertEquals("nome1",b.getContenutoOrdinatoPerPeso().toArray(new Attrezzo[0])[0].getNome());
		assertEquals("nome2",b.getContenutoOrdinatoPerPeso().toArray(new Attrezzo[0])[1].getNome());
	}
	
	@Test
	public void testgetContenutoOrdinatoPerPesoBorsaElemPesoDiverso() {
		Borsa b = borsaSortAttr("nome2","nome1",1,2);
		assertEquals(1,b.getContenutoOrdinatoPerPeso().toArray(new Attrezzo[0])[0].getPeso());
		assertEquals(2,b.getContenutoOrdinatoPerPeso().toArray(new Attrezzo[0])[1].getPeso());
	}
	
	
	@Test
	public void testgetContenutoOrdinatoPerPesoBorsaElemPesoDiversoControlloNomi() {
		Borsa b = borsaSortAttr("nome2","nome1",1,2);
		assertEquals("nome2",b.getContenutoOrdinatoPerPeso().toArray(new Attrezzo[0])[0].getNome());
		assertEquals("nome1",b.getContenutoOrdinatoPerPeso().toArray(new Attrezzo[0])[1].getNome());
	}
	
	
	@Test
	public void testgetContenutoOrdinatoPerNomeBorsaElemNomeDiverso() {
		Borsa b = borsaSortAttr("nome2","nome1",1,1);
		assertEquals("nome1",b.getContenutoOrdinatoPerNome().toArray(new Attrezzo[0])[0].getNome());
		assertEquals("nome2",b.getContenutoOrdinatoPerNome().toArray(new Attrezzo[0])[1].getNome());
	}
	
	
	@Test
	public void testgetContenutoOrdinatoPerNomeBorsaElemNomeDiverso2ConPesoDiverso() {
		Borsa b = borsaSortAttr("nome2","chiave",3,1);
		assertEquals("chiave",b.getContenutoOrdinatoPerNome().toArray(new Attrezzo[0])[0].getNome());
		assertEquals("nome2",b.getContenutoOrdinatoPerNome().toArray(new Attrezzo[0])[1].getNome());
	}
	
	
	/*RICORDA CHE RESTITUISCE UNA MAP CON VALUES CHE SONO DEI SET DI ATTREZZO COMPARABILI RISPETTO AL NOME*/
	@Test
	public void testgetContenutoRaggruppatoPerPesoElemPesoUguale() {
		Borsa b = borsaSortAttr("nome1","chiave",1,1);
		assertEquals("{1=[chiave (1kg), nome1 (1kg)]}",b.getContenutoRaggruppatoPerPeso().toString());
	}
	
	@Test
	public void testgetContenutoRaggruppatoPerPesoElemPesoDiverso() {
		Borsa b = borsaSortAttr("nome1","chiave",3,1);
		assertEquals("{1=[chiave (1kg)], 3=[nome1 (3kg)]}",b.getContenutoRaggruppatoPerPeso().toString());
	}
	
	
	@Test  //Ricorda che nel set non vengono ammessi duplicati di attrezzi con lo stesso nome.
	       //Inoltre ho utilizzato una map per la gestione di attrezzi in borsa; quindi un attrezzo è rimpiazzato da quello inserito con lo stesso nome.
	public void testgetContenutoRaggruppatoPerPesoElemPesoDiversoStessoNome() {
		Borsa b = borsaSortAttr("nome1","nome1",3,1);
		assertEquals("{1=[nome1 (1kg)]}",b.getContenutoRaggruppatoPerPeso().toString());
	}

	
	@Test
	public void testgetSortedSetOrdinatoPerPesoUguale() {
		Borsa b = borsaSortAttr("nome2","nome1",1,1);
		assertEquals(1,b.getSortedSetOrdinatoPerPeso().toArray(new Attrezzo[0])[0].getPeso());
		assertEquals(1,b.getSortedSetOrdinatoPerPeso().toArray(new Attrezzo[0])[1].getPeso());
	}
	
	@Test
	public void testgetSortedSetOrdinatoPerPesoUgualeControlloNome() {
		Borsa b = borsaSortAttr("nome2","nome1",1,1);
		assertEquals("nome1",b.getSortedSetOrdinatoPerPeso().toArray(new Attrezzo[0])[0].getNome());
		assertEquals("nome2",b.getSortedSetOrdinatoPerPeso().toArray(new Attrezzo[0])[1].getNome());
	}
	
	@Test
	public void testgetSortedSetOrdinatoPerPesoDiverso() {
		Borsa b = borsaSortAttr("nome2","nome1",3,1);
		assertEquals(1,b.getSortedSetOrdinatoPerPeso().toArray(new Attrezzo[0])[0].getPeso());
		assertEquals(3,b.getSortedSetOrdinatoPerPeso().toArray(new Attrezzo[0])[1].getPeso());
	}
	
	
	/*TEST NON MINIMALI*/
	@Test
	public void testgetContenutoOrdinatoPerPesoNonMinimale() {
		Borsa b = new Borsa();
		b.addAttrezzo(new Attrezzo("nome1",3));
		b.addAttrezzo(new Attrezzo("nome2",1));
		b.addAttrezzo(new Attrezzo("nome6",2));
		b.addAttrezzo(new Attrezzo("nome4",0));
		b.addAttrezzo(new Attrezzo("nome5",4));
		
		Attrezzo[] a = b.getContenutoOrdinatoPerPeso().toArray(new Attrezzo[0]);
		boolean appoggio = true; //pesi in ordine
		for(int i = 0; i < a.length-1; i++) {
			if(a[i].getPeso() > a[i+1].getPeso())
				appoggio = false;
		}
		assertTrue(appoggio);
	}
	
	
	
	@Test
	public void testgetContenutoOrdinatoPerNomeNonMinimale() {
		Borsa b = new Borsa();
		b.addAttrezzo(new Attrezzo("nome1",3));
		b.addAttrezzo(new Attrezzo("nome2",1));
		b.addAttrezzo(new Attrezzo("nome6",2));
		b.addAttrezzo(new Attrezzo("nome4",0));
		b.addAttrezzo(new Attrezzo("nome5",4));
		
		Attrezzo[] a = b.getContenutoOrdinatoPerNome().toArray(new Attrezzo[0]);
		boolean appoggio = true; //pesi in ordine
		for(int i = 0; i < a.length-1; i++) {
			if(a[i].getNome().compareTo(a[i+1].getNome()) > 0 )
				appoggio = false;
		}
		assertTrue(appoggio);
	}
	
	
	@Test
	public void testgetSortedSetOrdinatoPerPesoNonMinimale() {
		Borsa b = new Borsa();
		b.addAttrezzo(new Attrezzo("nome1",3));
		b.addAttrezzo(new Attrezzo("nome2",1));
		b.addAttrezzo(new Attrezzo("nome6",1));
		b.addAttrezzo(new Attrezzo("nome4",0));
		b.addAttrezzo(new Attrezzo("nome5",4));
		
		Attrezzo[] a = b.getSortedSetOrdinatoPerPeso().toArray(new Attrezzo[0]);
		boolean appoggio = true; //pesi in ordine
		for(int i = 0; i < a.length-1; i++) {
			if(a[i].getPeso() > a[i+1].getPeso())
				appoggio = false;
		}
		
		boolean appoggio2 = true;
		
		if(a[1].getNome().compareTo(a[2].getNome()) > 0) appoggio2 = false;
		
		assertTrue(appoggio);
		assertTrue(appoggio2);
	}
	
	
	/*MANCA TEST NON MINIMALE PER MAP*/
	
	
	@Test
	public void testgetContenutoRaggruppatoPerPeso() {
		Borsa b = new Borsa();
		b.addAttrezzo(new Attrezzo("nome1",3));
		b.addAttrezzo(new Attrezzo("nome2",1));
		b.addAttrezzo(new Attrezzo("nome6",1));
		b.addAttrezzo(new Attrezzo("nome4",2));
		b.addAttrezzo(new Attrezzo("nome5",2));
		
		Map<Integer,Set<Attrezzo>> m = b.getContenutoRaggruppatoPerPeso();
		
		assertEquals("nome2",m.get(1).toArray(new Attrezzo[0])[0].getNome());
		assertEquals("nome6",m.get(1).toArray(new Attrezzo[0])[1].getNome());
		
		assertEquals("nome1",m.get(3).toArray(new Attrezzo[0])[0].getNome());
		
		assertEquals("nome4",m.get(2).toArray(new Attrezzo[0])[0].getNome());
		assertEquals("nome5",m.get(2).toArray(new Attrezzo[0])[1].getNome()); 
	}
	
	
	
	
	
	
	
}
