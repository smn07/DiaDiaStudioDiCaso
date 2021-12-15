package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private boolean finita;
	private Labirinto labirinto;
	private Giocatore giocatore;
	
	public Partita(Labirinto l){
		this.labirinto = l;
		this.finita = false;
		this.giocatore = new Giocatore();
	}
	
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta.
	 * @return vero se partita vinta.
	 */
	public boolean vinta() {
		return this.labirinto.getStanzaCorrente() == this.labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita.
	 * @return vero se partita finita.
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita.
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	/**
	 *@return labirinto (labirinto del gioco).
	 * 
	 */
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	/**
	 *
	 *Restituisce giocatore.
	 *@return giocatore. 
	 */
	public Giocatore getGiocatore() {
		return this.giocatore;
	}


	public boolean giocatoreIsVivo() {
		return this.giocatore.isVivo();
	}
	
	public void setLabirinto(Labirinto l) {
		this.labirinto = l;
	}
}

