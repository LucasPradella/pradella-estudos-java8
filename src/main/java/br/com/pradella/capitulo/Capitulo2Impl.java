package br.com.pradella.capitulo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import br.com.pradella.entidade.Usuario;

public class Capitulo2Impl {

	
	public static void main(String[] args) {

		Usuario usuario1 = new Usuario("Arya", 100);
		Usuario usuario2 = new Usuario("Tyrion", 500);
		Usuario usuario3 = new Usuario("Dany", 10);
		
		List<Usuario> usuarios = Arrays.asList(usuario1, usuario2, usuario3);
		
		System.out.println("========== Forma antiga - forEach ==========");
		for (Usuario usuario : usuarios) {
			System.out.println(usuario.getNome());
		}
		
		
		System.out.println("========== Forma j8 - novo metodo forEach ==========");
		System.out.println("========== mostrador1 ==========");
		
		class Mostrador implements Consumer<Usuario> {
			public void accept(Usuario u) {
					System.out.println(u.getNome());
			}
		}
		
		Mostrador mostrador = new Mostrador();
		usuarios.forEach(mostrador);
	
		
		
		System.out.println("========== mostrador2 ==========");
		Consumer<Usuario> mostrador2 = new Consumer<Usuario>() {
			public void accept(Usuario u) {
				System.out.println(u.getNome());
			}
		};

		usuarios.forEach(mostrador2);
		
		
		System.out.println("========== mostrador3 ==========");
		usuarios.forEach(new Consumer<Usuario>() {
			public void accept(Usuario u) {
			System.out.println(u.getNome());
			}
		});
		
		
		System.out.println("========== Forma j8 - Lambda ==========");
		Consumer<Usuario> mostrador4 = (Usuario u) -> {System.out.println(u.getNome());};
		
		// ou simplesmente 
		
		usuarios.forEach(u -> System.out.println(u.getNome()));
			
		
		System.out.println("========== Forma j8 - Lambda - tornar todos moderadores ==========");
		usuarios.forEach(u -> u.tornarModerador() );
		usuarios.forEach(u -> System.out.println(u.isModerador()));
	}
	
	
}
