package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

/**
 * 
 * Classe Labirinto: modella il labirinto del gioco.
 * E' costituito da stanze collegate e pone come stanza di partenza l'atrio e come stanza
 * vincente la biblioteca.
 *
 */

public class Labirinto {
    
	private static final String PRESENTAZIONE_CANE = null;
	private static final String PRESENTAZIONE_STREGA = null;
	private static final String PRESENTAZIONE_MAGO = null;
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	private IO io;
	
	public Labirinto(IO io) {
		this.io = io;
		creaStanze();
	}
	
	public void setStanzaVincente(Stanza vincente) {  //utile soltanto per i test.
		this.stanzaVincente = vincente;
	}
	
	/**
	 * 
	 * @return stanzaVincente (Biblioteca).
	 */
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	/**
	 * Imposta stanza corrente.
	 * @param stanzaCorrente (stanza da impostare come corrente).
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	/**
	 * 
	 * @return stanzaCorrente (stanza in cui si trova il giocatore).
	 */
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
     * Crea tutte le stanze e le porte di collegamento
     */
    private void creaStanze() {
    	
		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		
		/*attrezzi aggiunti per prove*/
		Attrezzo spada = new Attrezzo("spada",4);
		Attrezzo maschera = new Attrezzo("maschera",5);
		Attrezzo chiave  = new Attrezzo("chiave", 1);
		
    	
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		aulaN11.setPersonaggio(new Strega("Strega",PRESENTAZIONE_STREGA));
		Stanza aulaN10 = new Stanza("Aula N10");
		aulaN10.setPersonaggio(new Mago("Mago",PRESENTAZIONE_MAGO,new Attrezzo("bacchetta magica",2)));
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		laboratorio.setPersonaggio(new Cane("Carlino", PRESENTAZIONE_CANE));
		Stanza biblioteca = new Stanza("Biblioteca");
		StanzaMagica stanzaMagica= new StanzaMagica(0,"Stanza Magica");
		StanzaBloccata stanzaBloccata = new StanzaBloccata("Stanza Bloccata","sud");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		
		aulaN11.impostaStanzaAdiacente("sud", stanzaMagica);
		
		stanzaMagica.impostaStanzaAdiacente("nord", aulaN11);
		stanzaMagica.impostaStanzaAdiacente("sud", biblioteca); //utilizzo di stanza magica nel gioco.
		
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		
		laboratorio.impostaStanzaAdiacente("nord", stanzaBloccata);
		
		stanzaBloccata.impostaStanzaAdiacente("sud", laboratorio);
		stanzaBloccata.impostaStanzaAdiacente("est", biblioteca);
		
		biblioteca.impostaStanzaAdiacente("sud", atrio);
		biblioteca.impostaStanzaAdiacente("nord", stanzaMagica);
		biblioteca.impostaStanzaAdiacente("ovest", stanzaBloccata);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		
		atrio.addAttrezzo(spada);
		atrio.addAttrezzo(maschera);
		
		atrio.addAttrezzo(chiave);

		// il gioco comincia nell'atrio
		
        //stanzaCorrente = atrio;
		this.setStanzaCorrente(atrio);
		stanzaVincente = biblioteca;
    }
}
