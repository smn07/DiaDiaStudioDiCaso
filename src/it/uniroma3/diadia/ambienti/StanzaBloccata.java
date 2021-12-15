package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * 
 * Classe StanzaBloccata: gestisce stanze che hanno una direzione bloccata; è possibile sbloccarla solo
 * se è presente una chiave.
 *
 */

public class StanzaBloccata extends Stanza {

	private String nomeDirezioneBloccata;
	private String nomeAttrezzoSbloccoDirezione;
	
	public StanzaBloccata(String nome, String direzione) {
		super(nome);
		this.nomeDirezioneBloccata = direzione;
		this.nomeAttrezzoSbloccoDirezione = "chiave";
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		
		if((direzione == this.nomeDirezioneBloccata) && (!this.hasAttrezzo(this.nomeAttrezzoSbloccoDirezione)))
			return this;
		
		return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		return super.getDescrizione() + "NOTA BENE: la direzione " + this.nomeDirezioneBloccata + "è bloccata; "
				+ "si può sbloccare solo se c'è una chiave.";
	}
	

}
