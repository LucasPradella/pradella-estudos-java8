package br.com.pradella.capitulo;

import static java.util.Arrays.asList;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.ToIntBiFunction;

import br.com.pradella.entidade.Usuario;

public class Capitulo6Impl {
	
	
	public static void main(String[] args) {
		
		Usuario user3 = new Usuario("Hanah", 120);
		Usuario user1 = new Usuario("Arya", 150);
		Usuario user2 = new Usuario("Snow", 120);
		
		List<Usuario> usuarios = asList(user1, user2, user3);
		
		System.out.println("============ Method Reference ============");
		
		System.out.println("======== Tornando todos usuarios moderadores : primeira maneira ========");
		usuarios.forEach(u -> u.tornarModerador());
		
		System.out.println("======== Tornando todos usuarios moderadores : segunda maneira ========");
		usuarios.forEach(Usuario::tornarModerador);
		
		
		
		System.out.println("============ method reference :: Comparando de forma enxuta ============");
		usuarios.sort(Comparator.comparing(Usuario::getNome));
		usuarios.forEach( u -> System.out.println(u.getNome()));
		
		
		// outra forma, mesmo resultado 
		Function<Usuario, String> byName = Usuario::getNome;
		usuarios.sort(Comparator.comparing(byName));
	
		
		System.out.println("============ method reference :: Compondo comparators ============");
		usuarios.sort(Comparator.comparingInt(Usuario::getPonto));
		usuarios.forEach( u -> System.out.println(u.getNome()));
		
		// em caso de empate, podemos definir um outro criterio usando thenComparing
		
		usuarios.sort(Comparator.comparingInt(Usuario::getPonto).thenComparing(Usuario::getNome));
		usuarios.forEach( u -> System.out.println(u.getNome()));

		
		
		// nullsLast() :: caso exista algum valor null eles são posicionados no final da lista
		usuarios.sort(Comparator.nullsLast(Comparator.comparing(Usuario::getNome)));

		
		// reversed() :: exemplo de ordenar por pontos, mas de forma reversa
		System.out.println("===== reversed() :: exemplo de ordenar por pontos, mas de forma reversa ====");
		
		usuarios.sort(Comparator.comparingInt(Usuario::getPonto).reversed());
		usuarios.forEach(u -> System.out.println(u.getNome()));
		
		
		System.out.println("============== REFERENCIANDO MÉTODOS DE INSTÂNCIA =============");
		
		Usuario lucas = new Usuario("Lucas Pradella", 50);
		Runnable bloco = lucas::tornarModerador; // poderia ser dessa forma      Runnable bloco2 = () -> rodrigo.tornaModerador();
		bloco.run();
		
		
		
		// outra forma utilizando o consumer
		Usuario lucas2 = new Usuario("Lucas Dois", 50);
		Consumer<Usuario> consumer = Usuario::tornarModerador;
		consumer.accept(lucas2);
		
		
		System.out.println("Metodos que recebem argumentos");
		usuarios.forEach(System.out::println);
		
		
		
		
		System.out.println("============== REFERENCIANDO CONSTRUTORES =============");
		
		Function<String, Usuario> criadorDeUsuarios = Usuario::new;
		Usuario duke = criadorDeUsuarios.apply("Duke");
		
		
		
		BiFunction<Integer, Integer, Integer> max = Math::max;
		ToIntBiFunction<Integer, Integer> max2 = Math::max;
		IntBinaryOperator max3 = Math::max;
		
		
	}
	

}
