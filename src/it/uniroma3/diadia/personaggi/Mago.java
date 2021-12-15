package it.uniroma3.diadia.personaggi;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio{
	
	private Attrezzo attrezzo;
	
	public Mago(String nome, String presentazione,Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}

	
	@Override
	public String agisci(Partita partita) {
		String s;
		if(this.attrezzo != null) {
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.attrezzo);
			s = "Attrezzo donato. Controlla, si trova nella stanza";
			this.attrezzo = null;
		}
		else {
			s = "Attrezzo non disponibile perchè già donato.";
		}
		
		return s;
	}


	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo == null) return null;
		if(partita == null) return null;
		partita.getLabirinto().getStanzaCorrente().addAttrezzo(new Attrezzo(attrezzo.getNome(), attrezzo.getPeso()/2));
		
		return "Ho dimezzato il peso di questo attrezzo che mi hai regalato; l'ho posato nella stanza corrente";
	}

	
	
}
