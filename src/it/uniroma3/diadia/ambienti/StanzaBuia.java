package it.uniroma3.diadia.ambienti;


/**
 * 
 * Classe StanzaBuia: gestisce stanze che siano buie o meno in base alla presenza dell'attrezzo lanterna.
 *
 */
public class StanzaBuia extends Stanza {
	
	private String nomeAttrPerVedere;
	
	public StanzaBuia(String nome) {
		super(nome);
		this.nomeAttrPerVedere = "lanterna";
	}
	
	
	@Override
	public String getDescrizione() {
		
		if(!this.hasAttrezzo(this.nomeAttrPerVedere))
			return "Qui c'è un buio pesto!";
		
		return super.getDescrizione();
	
	}
	
	
}
