package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Comando posa: il giocatore prende un attrezzo dalla borsa e lo deposita
 * nella stanza corrente se possibile.
 */

public class ComandoPosa extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		if(this.getParametro() == null)
			this.getIo().mostraMessaggio("Quale attrezzo vuoi posare ?");
		else {
			Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
			if(attrezzo == null) {
				this.getIo().mostraMessaggio("\nAttrezzo non trovato nella borsa.\n");
			}else {
				boolean appoggio = partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzo);
				if(appoggio != false) {
					partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
				}else {
					this.getIo().mostraMessaggio("Nella stanza non possono esserci altri attrezzi.");
				}
				this.getIo().mostraMessaggio("Descrizione borsa:\n" + partita.getGiocatore().getBorsa().toString());
				this.getIo().mostraMessaggio("Descrizione stanza corrente:\n" + partita.getLabirinto().getStanzaCorrente().toString());
			}
		}

	}

}
