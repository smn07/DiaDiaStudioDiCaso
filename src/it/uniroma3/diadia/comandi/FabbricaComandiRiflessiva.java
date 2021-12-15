package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaComandiRiflessiva implements FabbricaDiComandi {

	
	@Override
	@SuppressWarnings("deprecation")
	public Comando costruisciComando(String istruzione,IO ioConsole) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); // seconda parola: eventuale parametro
		try {
		 	String nomeClasse = "it.uniroma3.diadia.comandi.Comando";
		 	nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
		 	nomeClasse += nomeComando.substring(1);
		 	comando = (Comando)Class.forName(nomeClasse).newInstance();
		 	comando.setIO(ioConsole);
		 	comando.setParametro(parametro);
		}catch (Exception e) {
			comando = new ComandoNonValido();
		 	comando.setIO(ioConsole);
		 	comando.setParametro(parametro);
		}finally {
			scannerDiParole.close();
		}
		return comando;
	}
}
