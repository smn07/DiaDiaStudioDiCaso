package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		
		if(partita.getLabirinto().getStanzaCorrente().getPersonaggio() == null) {
			this.getIo().mostraMessaggio("Non c'è alcun personaggio in questa stanza al quale poter regalare qualcosa");
			return;
		}
		
		if(this.getParametro() != null) {
			
			Attrezzo daRimuovere = partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
			if(daRimuovere != null) {
				
				this.getIo().mostraMessaggio("Parametro regalato al personaggio!");
				this.getIo().mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getPersonaggio().riceviRegalo(daRimuovere, partita));
				
			}
			else {
				this.getIo().mostraMessaggio("Attrezzo non trovato nella borsa!");
			}
		}
		else {
			this.getIo().mostraMessaggio("Cosa vuoi regalare?");
		}
	}

}
