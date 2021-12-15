package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando{
	
	@Override
	public void esegui(Partita partita) {
		
		AbstractPersonaggio p = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if(p != null) {
			this.getIo().mostraMessaggio("Ciao.  (Ho salutatato il personaggio.)");
			this.getIo().mostraMessaggio(p.saluta());;
		}
		else {
			this.getIo().mostraMessaggio("Chi dovrei salutare? Non c'è nessun personaggio!");
		}
		
	}

}
