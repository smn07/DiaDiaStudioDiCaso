package it.uniroma3.diadia;
import java.util.Scanner;

/**
 * 
 * Classe IOConsole: gestisce System.out e System.in.
 *
 */
public class IOConsole implements IO {
	
	/**
	 * Stampa una stringa.
	 * @param msg (messaggio da stampare).
	 */
	
	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	/**
	 * Gestisce  la lettura da linea di comando.
	 * @return riga (istruzione inserita in input).
	 */
	
	@Override
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		return riga;
	}

}