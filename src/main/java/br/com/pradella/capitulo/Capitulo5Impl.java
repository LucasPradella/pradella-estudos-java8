package br.com.pradella.capitulo;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.pradella.entidade.Usuario;

public class Capitulo5Impl {

	public static void main(String[] args) {

		Usuario user1 = new Usuario("Arya", 150);
		Usuario user2 = new Usuario("Snow", 120);
		Usuario user3 = new Usuario("Hanah", 190);
		List<Usuario> usuarios = asList(user1, user2, user3);

		System.out.println("========== COMPARATORS LAMBDA  ===========");
		Comparator<Usuario> comparator = new Comparator<Usuario>() {

			public int compare(Usuario u1, Usuario u2) {
				return u1.getNome().compareTo(u2.getNome());
			}
		};

		// ou dessa forma com lambda
		Collections.sort(usuarios, comparator);
		usuarios.sort(comparator);
		Comparator<Usuario> comparator1 = (u1, u2) -> u1.getNome().compareTo(u2.getNome());
		Collections.sort(usuarios, comparator1);
		
		// ou dessa forma em uma linha apenas
		
		Collections.sort(usuarios, (u1, u2) -> u1.getNome().compareTo(u2.getNome()));
		
		usuarios.forEach(u -> System.out.println(u.getNome()));
		
		
		
		
		
		System.out.println("=========== MÉTODO LIST.SORT =============");
		
		// outra forma de ordenas a lista por nomes 
		usuarios.sort((u1, u2) -> u1.getNome().compareTo(u2.getNome()));
		
		
		System.out.println("=========== MÉTODOS ESTÁTICOS NA INTERFACE COMPARATOR  =============");
	
		Comparator<Usuario> comparator2 = Comparator.comparing(u -> u.getNome());
		usuarios.sort(comparator2);
		
		// adicionando tudo em uma linha
		usuarios.sort(Comparator.comparing(u -> u.getNome()));
		
		
		// utilizanod natural Order para resolver um List<String>
		List<String> palavras =	Arrays.asList("Casa do Código", "Alura", "Caelum");
		
		System.out.println("============ natualOrder =============");
		palavras.sort(Comparator.naturalOrder());
		palavras.forEach(p -> System.out.println(p));
		
		System.out.println("============ reverseOrder =============");
		palavras.sort(Comparator.reverseOrder());
		palavras.forEach(p -> System.out.println(p));
		
		
		

		
		
	}

}
