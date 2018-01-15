package br.com.pradella.capitulo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import br.com.pradella.entidade.Usuario;

public class Capitulo4Impl {

	public static void main(String[] args) {

		System.out.println("======== INTERFACE CONSUMER - usando default metodo ========");

		Usuario user1 = new Usuario("Arya", 150);
		Usuario user2 = new Usuario("Hanah", 120);
		Usuario user3 = new Usuario("Snow", 190);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

		Consumer<Usuario> mostraMensagem = u -> System.out.println("antes de imprimir os nomes");
		Consumer<Usuario> imprimeNome = u -> System.out.println(u.getNome());

		usuarios.forEach(mostraMensagem.andThen(imprimeNome));

		
		
		
		System.out.println("======== COLLECTION: REMOVEIF ========");

		List<Usuario> usuarios1 = new ArrayList<>();
		usuarios1.add(user1);
		usuarios1.add(user2);
		usuarios1.add(user3); // para usar o removeIF tem que ser uma lista mutavel, por isso nao utilizei 0 usuarios(asList). 
		

		System.out.println("======== Utilizando RemoveIF sem lambda ========");
		// remover todos usuarios com mais de 160 pontos
		Predicate<Usuario> predicado = new Predicate<Usuario>() {
			public boolean test(Usuario u) {
				return u.getPonto() > 160;

			}
		};
		
		usuarios1.removeIf(predicado); // add predicate (regra)
		usuarios1.forEach(u -> System.out.println(u.getNome())); // imprimir lista que sobrou 
		
		
		
		System.out.println("======== Utilizando RemoveIF com lambda ========");
		usuarios1.removeIf(u -> u.getPonto() > 160);
		usuarios1.forEach(u -> System.out.println(u.getNome()));
	}

	
	
	
}
