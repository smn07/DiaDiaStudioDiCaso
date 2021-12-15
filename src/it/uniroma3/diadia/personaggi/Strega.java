package it.uniroma3.diadia.personaggi;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{

	private Map<String,Attrezzo> attrezziStrega;
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
		this.attrezziStrega = new HashMap<String,Attrezzo>();
	}

	
	@Override  //Se la stanza attuale non ha adiacenti oppure le adiacenti contengono un numero uguale a quella corrente,
				//la strega non sposta da nessuna parte.
	public String agisci(Partita partita) {
		String s = "La stanza corrente non contiene stanze adiacenti oppure hanno lo stesso numero di attrezzi della stanza corrente, non ti sposterò da nessuna parte!";
		Stanza diArrivo;
		if(this.haSalutato()) {
			Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
			diArrivo = stanzaCorrente.getStanzaAdiacenteConPiuAttrezzi();
			if(diArrivo != stanzaCorrente)
				s = "Poichè hai salutato, ti sposterò nella stanza con più attrezzi!";
		}
		else {
			Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
			diArrivo = stanzaCorrente.getStanzaAdiacenteConMenoAttrezzi();
			if(diArrivo != stanzaCorrente)
				s = "Poichè non hai salutato, ti sposterò nella stanza con meno attrezzi!";
		}
		
		partita.getLabirinto().setStanzaCorrente(diArrivo);
		return s;
	}


	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo == null) return null;
		
		this.attrezziStrega.put(attrezzo.getNome(), attrezzo);
		return "AHAHAHAHAH e che regalo sarebbe?";
		
	}


}
