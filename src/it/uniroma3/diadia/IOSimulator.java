package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * Classe IOSimulator: permette di gestire I/O attraverso collezioni (Mappe). 
 *
 */
public class IOSimulator implements IO {
	
	private Map<Integer,String> mapLeggiRiga;
	int counterLeggiRiga;
	
	private Map<Integer,String> mapMostra;
	int counterMostra;
	
	int counterPerIlNext;
	
	private Map<String, List<String>> mapMostraConRigaLetta;
	
	public IOSimulator(Map<Integer,String> daLeggere) {
		this.mapLeggiRiga = daLeggere;
		this.mapMostra = new HashMap<Integer,String>();
		this.counterLeggiRiga = 0;
		this.counterMostra = 0;
		this.counterPerIlNext = 0;
		
		this.mapMostraConRigaLetta = new HashMap<String,List<String>>();
	}
	
	
	@Override
	public void mostraMessaggio(String messaggio) {
		this.mapMostra.put(this.counterMostra, messaggio);
		this.counterMostra++;
		
		if(messaggio == ""+  //Perchè avendo come chiave null farebbe saltare tutto. quindi nella mappa in
							 //in cui raggruppo i messaggi in base a riga letta non lo considero.
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.") return; 
		
		/*DA QUI IN GIU' MI PERMETTE DI TENERE CONTO DI MESSAGGI PRODOTTI IN BASE A RIGA LETTA*/
		                             
		List<String> l = this.mapMostraConRigaLetta.get(this.mapLeggiRiga.get(this.counterLeggiRiga-1));
		if(l != null) {
			l.add(messaggio);
		}
		else {
			l = new ArrayList<String>();
			l.add(messaggio);
			this.mapMostraConRigaLetta.put(this.mapLeggiRiga.get(this.counterLeggiRiga-1), l);
		}
		

	}

	@Override
	public String leggiRiga() {
		String a = this.mapLeggiRiga.get(this.counterLeggiRiga);
		this.counterLeggiRiga++;
				
		return a;
	}
	
	public String nextMessaggio() {
		String next = this.mapMostra.get(this.counterPerIlNext);
		this.counterPerIlNext++;
		return next;
	}
	
	public boolean hasNextMessaggio() {
		return this.counterPerIlNext < this.counterMostra;
	}
	
	public Map<Integer,String> getMostra() {
		return this.mapMostra;
	}

	
	/**
	 * 
	 * @return mappa con messaggi prodotti in base a riga letta.
	 */
	public Map<String, List<String>> getMappaConRiferimentoAlleRigheLette() {
		return this.mapMostraConRigaLetta;
	}

}