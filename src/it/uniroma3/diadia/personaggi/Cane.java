package it.uniroma3.diadia.personaggi;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	
	private final String regaloPreferito = "osso";
	private Attrezzo attrezzoCane;

	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
		this.attrezzoCane = new Attrezzo("palla", 1);
	}

	@Override
	public String agisci(Partita partita) {
		
		String s = "Il cane ti ha morso, hai perso un CFU";
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
		
		return s;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		
		if(attrezzo == null) return null;
		if(partita == null) return null;
		StringBuilder ris = null;
		if(attrezzo.getNome().equals(regaloPreferito)) {
//			this.attrezzi.put("guinzaglio", new Attrezzo("guinzaglio",3));
			ris = new StringBuilder("Grazie per l'osso. ");	
			if(this.attrezzoCane != null) {
				partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.attrezzoCane);
				this.attrezzoCane = attrezzo;
				ris.append("Ho fatto cadere un attrezzo, controlla nella stanza!");
			}
		}
		else {
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
			return "Il cane ti ha morso perchè non gli hai regalato il suo cibo preferito, hai perso un CFU";
		}
		
		return ris.toString();
		
	}



}
