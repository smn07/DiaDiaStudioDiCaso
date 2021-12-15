package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;


/**
 * 
 * Comando "guarda": presenta la descrizione della stanza corrente e dello stato della partita.
 *
 */
public class ComandoGuarda extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		
		this.getIo().mostraMessaggio("Descrizione stanza corrente:");
		this.getIo().mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		this.getIo().mostraMessaggio("Descrizione stato corrente della partita:");
		this.getIo().mostraMessaggio("Numero di cfu a disposizione: " + partita.getGiocatore().getCfu());
		
	}

}
