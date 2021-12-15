package it.uniroma3.diadia.giocatore;

/*
 * Questa classe ha la responsabilità di gestire i CFU di un giocatore.
 */

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa;
	
	/**
	 * Costruttore Giocatore: serve a creare un oggetto istanza di tale classe. (ossia un giocatore).
	 * 
	 */
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}
	
	/**
	 * Restituisce numero cfu del giocatore (vita del giocatore).
	 * @return cfu (numero di cfu del giocatore).
	 */
	public int getCfu() {
		return this.cfu;
	}

	/**
	 * Imposta il numero di cfu del giocatore.
	 * @param cfu (numero cfu del giocatore).
	 */
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	
	/**
	 *Restituisce borsa del giocatore.
	 * @return borsa (borsa del giocatore).
	 */
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public boolean isVivo() {
		return this.cfu > 0;
	}
}
