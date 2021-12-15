package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * 
 * Classe StanzaMagica: gestisce stanze magiche il cui effetto è scaturito solo se si inseriscono
 * nella stanza più attrezzi di una certa "soglia magica".
 *
 */

public class StanzaMagica extends Stanza {
	
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	
	private static final int SOGLIA_MAGICA_DEFAULT = 3;
	

	public StanzaMagica(String nome) {
		this(SOGLIA_MAGICA_DEFAULT,nome);
	}
	
	public StanzaMagica(int sogliaMagicaDefault,String nome) {
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
