package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 */

public class ComandoAiuto extends AbstractComando implements Comando{

	final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda","interagisci","saluta"};
	
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i < this.elencoComandi.length; i++) 
			this.getIo().mostraMessaggio(elencoComandi[i]+" ");
	}


}
