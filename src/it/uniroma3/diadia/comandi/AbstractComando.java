package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando{

	private String parametro;
	private IO io;
	
	
	public void setParametro(String parametro) {
		this.parametro = parametro;

	}
	
	@Override
	public String getParametro() {
		return this.parametro;
		
	}
	
	public IO getIo() {
		return this.io;
	}

	@Override
	public String getNome() {
		
		return this.getClass().getCanonicalName().substring(27).toLowerCase();
				//it.uniroma3.diadia.comandi.Comando...
	}
	
	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
	public abstract void esegui(Partita partita);
	
}
