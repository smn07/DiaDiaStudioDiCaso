package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaProtected {
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;
	
	protected String nome;
	protected Map<String,Attrezzo> attrezzi;  //Map<String,Attrezzo> //posso avere un solo attrezzo con quel nome nella stanza.
	protected int numeroAttrezzi;
	protected Map<String,Stanza> stanzeAdiacenti;  //Map<String,Stanza> //posso avere una sola stanza in quella direzione.
	protected int numeroStanzeAdiacenti;
	protected Set<String> direzioni; //SET (non posso avere due nord). Sfrutto equals e hashCode di String.
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public StanzaProtected(String nome) {
        this.nome = nome;
        this.numeroStanzeAdiacenti = 0;
        this.numeroAttrezzi = 0;
        
        this.direzioni = new HashSet<String>();
        this.stanzeAdiacenti = new HashMap<String,Stanza>();
        this.attrezzi = new HashMap<String,Attrezzo>();
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
    	if(direzione == null) return;
    	if(stanza == null) return;
    	
    	if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
    		Stanza s =this.stanzeAdiacenti.put(direzione,stanza);
    		if(s == null) this.numeroStanzeAdiacenti++;
    		
    		this.direzioni.add(direzione);
    	
    	}
    	
    	
    	
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata.
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
 
		return this.stanzeAdiacenti.get(direzione);
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public Map<String,Attrezzo> getAttrezzi() {
        return this.attrezzi;
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if(attrezzo == null) return false;
        if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
        	Attrezzo a = this.attrezzi.put(attrezzo.getNome(), attrezzo);
        	if(a == null) this.numeroAttrezzi++;
        	return true;
        }
        return false;
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	risultato.append(this.direzioni.toString());
    	risultato.append("\nAttrezzi nella stanza: ");
    	risultato.append(this.attrezzi.values().toString());
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	/**
     * Restituisce l'attrezzo con nome nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return Ritorna l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {

		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza.
	 * @param attrezzo: attrezzo da rimuovere.
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti.
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		// TODO da implementare
		if(attrezzo == null) return false;
		Attrezzo a = this.attrezzi.remove(attrezzo.getNome());
		return a != null;
	}
	
	public int getNumeroAttrezzi() {
		return this.numeroAttrezzi;
	}


	public Set<String> getDirezioni() {
		return this.direzioni;
    }
	

}
