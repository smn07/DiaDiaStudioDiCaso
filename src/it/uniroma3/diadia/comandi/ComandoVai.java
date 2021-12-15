package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {
	
	/**
	 * esecuzione del comando
	*/
	
	
	@Override
	public void esegui(Partita partita) {
		// qui il codice per cambiare stanza …
		if(this.getParametro() == null)
			this.getIo().mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(this.getParametro());
		if (prossimaStanza == null)
			this.getIo().mostraMessaggio("Direzione inesistente");
		else {
			partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			int cfu = partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(cfu-1); 
		}
			this.getIo().mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}

	
}