package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Comando "Fine".
 */

public class ComandoFine extends AbstractComando {

	
	@Override
	public void esegui(Partita partita) {
		this.getIo().mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
		partita.setFinita();
	}



}
