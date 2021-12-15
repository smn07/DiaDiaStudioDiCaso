package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;


/**
 * 
 * Classe Borsa modella la borsa di attrezzi del giocatore.
 * La borsa contiene attrezzi e la somma dei pesi di tali attrezzi non può superare pesoMax.
 * E' possibile aggiungere o rimuovere attrezzi o verificare che la borsa sia vuota.
 *
 */
public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String,Attrezzo> attrezzi;
	private int pesoMax;
	private int pesoAttuale;
	
	/**
	 * Costruttore borsa che crea la borsa del giocatore.
	 * Invoca il costruttore Borsa(int pesoMax) con valore di default (10).
	 */
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	/**
	 * Costruttore borsa che crea la borsa del giocatore.
	 * @param pesoMax: peso max della borsa.
	 */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<String,Attrezzo>();
		this.pesoAttuale = 0;
	}

	/**
	 * Aggiunge attrezzo alla borsa.
	 * @param attrezzo: attrezzo da aggiungere.
	 * @return true se attrezzo aggiunto alla borsa, false altrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null) return false;
		Attrezzo a = this.attrezzi.get(attrezzo.getNome());
		int appoggio = this.getPeso();
		if(a != null) {
			appoggio = appoggio - a.getPeso();
		}
		if(appoggio + attrezzo.getPeso() > this.getPesoMax())
					return false;
		this.pesoAttuale = appoggio + attrezzo.getPeso();
		this.attrezzi.put(attrezzo.getNome(), attrezzo);  //Ricordando che se già esiste un attrezzo con quel nome
		                                                  //viene sostituito il valore della coppia nella map.
		return true;
	}

	/**
	 * Ritorna il peso max della borsa.
	 * @return peso massimo che può sostenere la borsa.
	 */
	public int getPesoMax() {
		return pesoMax;
	}

	/**
	 * Ritorna attrezzo se presente. 
	 * @param nomeAttrezzo nome dell'attrezzo da ritornare.
	 * @return null se attrezzo inesistente, altrimenti ritorna attrezzo.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		
		return this.attrezzi.get(nomeAttrezzo);
	}
	
	/**
	 * Ritorna peso contenuto attualmente nella borsa.
	 * @return peso: peso attuale della borsa ossia la somma dei pesi degli attrezzi.
	 */
	public int getPeso() {
		return this.pesoAttuale;
	}
	
	/**
	 * Verifica se la borsa è vuota.
	 * @return true se la borsa è vuota, false altrimenti.
	 */
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	/**
	 * Verifica l'esistenza di un attrezzo all'interno della borsa.
	 * @param nomeAttrezzo nome dell'attrezzo.
	 * @return true se esistente, false altrimenti (se getAttrezzo restituisce null).
	 */
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}
	
	/**
	 * Rimuove un attrezzo dalla borsa (ricerca in base al nome).
	 * @param nomeAttrezzo: nome dell'attrezzo da rimuovere.
	 * @return null se non rimosso, altrimenti ritorna attrezzo.
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo appoggio = this.getAttrezzo(nomeAttrezzo);
		if(appoggio != null) {
			this.pesoAttuale -= appoggio.getPeso();
		}
		return this.attrezzi.remove(nomeAttrezzo);
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			s.append("\nAttrezzi ordinati per peso e a parità di peso per nome:\n");
			s.append(this.getContenutoOrdinatoPerPeso().toString());
			s.append("\nAttrezzi ordinati per nome:\n");
			s.append(this.getContenutoOrdinatoPerNome().toString());
			s.append("\nAttrezzi ordinati raggruppandoli per peso:\n");
			Map<Integer,Set<Attrezzo>> m = this.getContenutoRaggruppatoPerPeso();
			s.append(m.entrySet().toString());
			
			s.append("\nDescrizione generica:\n");
			s.append(this.attrezzi.values().toString());
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		
		ComparatorePerPesoeNome c = new ComparatorePerPesoeNome();
		List<Attrezzo> l = new ArrayList<Attrezzo>(this.attrezzi.values());
		
		Collections.sort(l,c);
		return l;
	}
	
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		
		SortedSet<Attrezzo> s = new TreeSet<Attrezzo>(this.attrezzi.values());
		
		return s;
	}
	
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		
		Map<Integer,Set<Attrezzo>> m = new HashMap<Integer,Set<Attrezzo>>();
		List<Attrezzo> l = new ArrayList<Attrezzo>(this.attrezzi.values());
		
		for(Attrezzo a : l) {
			if(m.get(a.getPeso()) == null) {
				Set<Attrezzo> s = new TreeSet<Attrezzo>();
				s.add(a);
				m.put(a.getPeso(),s);
			}
			else {
				m.get(a.getPeso()).add(a);
			}
		}
		
		return m;
	}
	
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		
		ComparatorePerPesoeNome c = new ComparatorePerPesoeNome();
		SortedSet<Attrezzo> s = new TreeSet<Attrezzo>(c);
		
		s.addAll(this.attrezzi.values());
		
		return s;
	}
	
	
	
	
	
}