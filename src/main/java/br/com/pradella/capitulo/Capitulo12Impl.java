package br.com.pradella.capitulo;

import java.util.Comparator;

import br.com.pradella.entidade.Usuario;

public class Capitulo12Impl extends BaseEstudos{

	
	public static void main(String[] args) {
		
		System.out.println("LIMITAÇÕES DA INFERÊNCIA NO LAMBDA");
		
		usuarios.sort(Comparator.comparingInt((Usuario u) -> u.getPonto())
                .thenComparing(u -> u.getNome()));

		usuarios.sort(Comparator.<Usuario, Integer>comparing(u -> u.getPonto()).reversed());	
	}
}
