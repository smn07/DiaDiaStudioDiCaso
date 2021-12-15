package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {

	
	private static final String CON_CHI = "Con chi dovrei interagire ?";

	@Override
	public void esegui(Partita partita) {
	
		AbstractPersonaggio p = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if(p != null) {
			this.getIo().mostraMessaggio(p.agisci(partita));
		}
		else {
			this.getIo().mostraMessaggio(CON_CHI);
		}
	}

}
