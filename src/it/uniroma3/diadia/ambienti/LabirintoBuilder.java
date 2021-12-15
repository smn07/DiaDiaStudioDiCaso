package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * 
 * Classe LabirintoBuilder:
 * Permette di creare un Labirinto per giocare.
 *
 */

public class LabirintoBuilder extends Labirinto{

	private Stanza iniziale;
	private Stanza vincente;
	private Stanza ultimaAggiunta;
	private Map<String,Stanza> mappaStanze;
	
	public LabirintoBuilder(IO io) {
		super(io);
		this.iniziale = null;
		this.vincente = null;
		this.mappaStanze = new HashMap<String,Stanza>();
	}

	/**
	 * Aggiunge la stanza iniziale al labirinto.
	 * @param iniziale
	 */
	public void addStanzaIniziale(String iniziale) {
		if(iniziale == null) return;
		Stanza inizialeS = new Stanza(iniziale);
		this.mappaStanze.put(iniziale,inizialeS);
		this.iniziale = inizialeS;
		this.ultimaAggiunta =  inizialeS;
	}
	
	/**
	 * Aggiunge la stanza vincente al labirinto.
	 * @param vincente
	 */
	public void addStanzaVincente(String vincente) {
		if(vincente == null) return;
		Stanza vincenteS = new Stanza(vincente);
		this.mappaStanze.put(vincente,vincenteS);
		this.vincente = vincenteS;
		this.ultimaAggiunta =  vincenteS;
	}
	
	/**
	 * Aggiunge attrezzo di peso "peso" e nome "nomeAttr" all'ultima stanza aggiunta nel labirinto.
	 * @param nomeAttr
	 * @param peso
	 */
	public void addAttrezzo(String nomeAttr, int peso) {
		if(nomeAttr == null) return;
		this.ultimaAggiunta.addAttrezzo(new Attrezzo(nomeAttr,peso));
	}
	
	/**
	 * Imposta stanza adiacente "stanzaAd" ad una stanza di nome "stanza" nella direzione "direzione".
	 * @param stanza
	 * @param stanzaAd
	 * @param direzione
	 */
	public void addAdiacenza(String stanza, String stanzaAd, String direzione) {
		if(stanza == null) return;
		if(stanzaAd == null) return;
		if(direzione == null) return;
		this.mappaStanze.get(stanza).impostaStanzaAdiacente(direzione,this.mappaStanze.get(stanzaAd));
	}
	
	/**
	 * Aggiunge una stanza al labirinto.
	 * @param nomeStanza
	 */
	public void addStanza(String nomeStanza) {
		if(nomeStanza == null) return;
		Stanza aggiunta = new Stanza(nomeStanza);
		this.mappaStanze.put(nomeStanza,aggiunta);
		this.ultimaAggiunta =  aggiunta;
	}
	
	
	/**
	 * Aggiunge una stanza buia al labirinto.
	 * @param nomeStanza
	 */
	public void addStanzaBuia(String nomeStanza) {
		if(nomeStanza == null) return;
		Stanza aggiunta = new StanzaBuia(nomeStanza);
		this.mappaStanze.put(nomeStanza,aggiunta);
		this.ultimaAggiunta =  aggiunta;
	}
	
	
	/**
	 * Aggiunge una stanza magica al labirinto.
	 * @param nomeStanza
	 */
	public void addStanzaMagica(String nomeStanza) {
		if(nomeStanza == null) return;
		Stanza aggiunta = new StanzaMagica(nomeStanza);
		this.mappaStanze.put(nomeStanza,aggiunta);
		this.ultimaAggiunta =  aggiunta;
	}
	
	
	/**
	 * Aggiunge una stanza bloccata al labirinto.
	 * @param nomeStanza
	 */
	
	public void addStanzaBloccata(String nomeStanza,String direzione) {
		if(nomeStanza == null) return;
		Stanza aggiunta = new StanzaBloccata(nomeStanza,direzione);
		this.mappaStanze.put(nomeStanza,aggiunta);
		this.ultimaAggiunta =  aggiunta;
	}
	
	/*METODI GET*/
	public Map<String,Stanza> getMappaStanze() {
		return this.mappaStanze;
	}
	
	
	public Stanza getUltimaStanza() {
		return this.ultimaAggiunta;
	}
	
	public Stanza getIniziale() {
		return this.iniziale;
	}
	
	public Stanza getVincente() {
		return this.vincente;
	}
	
	public Labirinto getLabirinto() {
		return this;
	}
	
}
