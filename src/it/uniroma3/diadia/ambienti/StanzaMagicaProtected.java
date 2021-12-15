package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * 
 * Classe StanzaMagica: gestisce stanze magiche il cui effetto è scaturito solo se si inseriscono
 * nella stanza più attrezzi di una certa "soglia magica".
 * 
 * I campi delle variabili di istanza sono protected.
 *
 */

public class StanzaMagicaProtected extends StanzaProtected {
	
	protected int contatoreAttrezziPosati;
	protected int sogliaMagica;
	
	protected static final int SOGLIA_MAGICA_DEFAULT = 3;
	

	public StanzaMagicaProtected(String nome) {
		this(SOGLIA_MAGICA_DEFAULT,nome);
	}
	
	public StanzaMagicaProtected(int sogliaMagicaDefault,String nome) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = sogliaMagicaDefault;
	}

	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null) return null;
		
		StringBuilder s = new StringBuilder(attrezzo.getNome());
		s = s.reverse();
		
		return new Attrezzo(s.toString(), attrezzo.getPeso()*2);
		
	}
	
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		
		if(attrezzo == null) return false;
		Attrezzo a;
		
		this.contatoreAttrezziPosati++;
		if(this.contatoreAttrezziPosati > this.sogliaMagica) {
			a = this.modificaAttrezzo(attrezzo);
			return super.addAttrezzo(a);
		}
		
		return super.addAttrezzo(attrezzo);
		
	}
	
}
