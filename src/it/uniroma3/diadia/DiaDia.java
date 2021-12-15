package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaComandiRiflessiva;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	
	
	private Partita partita;
	private IO ioConsole;

	
	
	public DiaDia(IO ioConsole,Labirinto l) {
		this.partita = new Partita(l);
		this.ioConsole = ioConsole;
	}

	public void gioca() {
		String istruzione; 

		this.ioConsole.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = this.ioConsole.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaComandiRiflessiva();
				comandoDaEseguire = factory.costruisciComando(istruzione,this.ioConsole);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			this.ioConsole.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			this.ioConsole.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}
	
	
	public String getBenvenuto() {   /*utile per i test dell'IoSimulator*/
		
		return this.MESSAGGIO_BENVENUTO;
	}
	
	public Partita getPartita() {
		return this.partita;
	}
	

	
	public static void main(String[] argc) {
		
		IO io = new IOConsole();
		
		Labirinto labirinto = new Labirinto(io);
		
		DiaDia gioco = new DiaDia(io,labirinto);
		gioco.gioca();
	}
}