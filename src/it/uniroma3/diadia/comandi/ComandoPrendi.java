package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Comando prendi: il giocatore prende un attrezzo dalla stanza e lo deposita
 * nella borsa se non è piena.
 */

public class ComandoPrendi extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		if(this.getParametro() == null)
			this.getIo().mostraMessaggio("Quale attrezzo vuoi prendere ?");
		else {
			Attrezzo attrezzo = partita.getLabirinto().getStanzaCorrente().getAttrezzo(this.getParametro());
			if(attrezzo == null) {
				this.getIo().mostraMessaggio("\nAttrezzo non trovato nella stanza corrente.\n");
			}else {
				boolean appoggio = partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
				if(appoggio != false) {
					partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzo);
				}else {
					this.getIo().mostraMessaggio("Borsa piena o quasi piena!");
				}
				this.getIo().mostraMessaggio("Descrizione borsa:\n" + partita.getGiocatore().getBorsa().toString());
				this.getIo().mostraMessaggio("Descrizione stanza corrente:\n" + partita.getLabirinto().getStanzaCorrente().toString());
			}
		}

	}

}
