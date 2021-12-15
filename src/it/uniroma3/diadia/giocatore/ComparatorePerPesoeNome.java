package it.uniroma3.diadia.giocatore;

import java.util.Comparator;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComparatorePerPesoeNome implements Comparator<Attrezzo> {

	@Override
	public int compare(Attrezzo o1, Attrezzo o2) {
		int output = o1.getPeso() - o2.getPeso();
		if(output == 0) {
			return o1.getNome().compareTo(o2.getNome());
		}
		return output;
	}
	
}
