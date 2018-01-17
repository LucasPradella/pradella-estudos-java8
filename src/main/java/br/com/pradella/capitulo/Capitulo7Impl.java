package br.com.pradella.capitulo;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import br.com.pradella.entidade.Usuario;

public class Capitulo7Impl {

	public static void main(String[] args) {
		
		Usuario user1 = new Usuario("Hanah", 120);
		Usuario user2 = new Usuario("Arya", 150);
		Usuario user3 = new Usuario("Stark", 120);
		Usuario user4 = new Usuario("Snow", 20);
		
		List<Usuario> usuarios = asList(user1, user2, user3, user4);
		
		
		System.out.println("============= STREAMS E COLLECTORS ================");
		
		// listar os 10 users com mais pontos e tornalo moderador
		
		usuarios.sort(Comparator.comparingInt(Usuario::getPonto)); // primeiro ordenar a lista
		usuarios.subList(0, 2)                                     // segundo subList pegando os 10 primeiros e tornando eles moderadores 
				.forEach(Usuario::tornarModerador);
		
		
		
	     System.out.println("============= Utilizando Stream ================");
		
	     // forma mais vertical
	     Stream<Usuario> stream = usuarios.stream();
	     stream.filter(u -> u.getPonto() > 100);
		
	     // forma reduzida 
	     
	     Stream<Usuario> resultNovoStream = usuarios.stream()
	     		 					.filter(u -> u.getPonto() > 100);
		
	     resultNovoStream.forEach(System.out::println);
	     
	     
	     
	     // Podemos encadear as invocações 
	     usuarios.stream()
		     		 .filter(u -> u.getPonto() > 100)
		     		 .forEach(System.out::println);
	     
	     
	     
	     
	     System.out.println("============= Utilizando Stream para tornalos moderadores ================");
	     usuarios.stream()
			 		 .filter(u -> u.getPonto() > 100)
			 		 .forEach(Usuario::tornarModerador);
	     
	     
	     
	     System.out.println("============= invocando usuarios que sao moderadores ================");
	     usuarios.stream()
	     			.filter(Usuario::isModerador)
	     			.forEach(System.out::println);
	     
	     
	     
	     System.out.println("============= Obtendo novamente a lista ================");

	     // Forma manual
	     List<Usuario> maisQue100 = new ArrayList<>();
	     
	     usuarios.stream()
	     			.filter(u -> u.getPonto() > 100)
	     			.forEach(maisQue100::add);
	     
	     
	     // utilizando COLLECTORS
	     
	     List<Usuario> collect = usuarios.stream()
	     			.filter(u -> u.getPonto() > 100)
	     			.collect(Collectors.toList());
	     
	     
	     // ou com o codigo mais limpo 
	     List<Usuario> collect2 = usuarios.stream()
	     			.filter(u -> u.getPonto() > 100)
	     			.collect(toList());
	     
	     
	     System.out.println("============= listar apenas os pontos dos usuarios com Map ================");
	     
	     List<Integer> pontos = new ArrayList<>();
	     usuarios.forEach(u -> pontos.add(u.getPonto()));
	     
	     
	     List<Integer> collectMap = usuarios.stream()
	     			.map(u -> u.getPonto())
	     			.collect(toList());
	     
	     System.out.println("============= INTSTREAM e STREAMS ================");
	     IntStream streamint = usuarios.stream()
	    		 					.mapToInt(Usuario::getPonto);
	     
	     
	     // Obter a media de pontos 
	     double pontuacaoMedia = usuarios.stream()
	    		 .mapToInt(Usuario::getPonto)
	    		 .average()
	    		 .getAsDouble();
	     
	     
	     
	     System.out.println("============= Optional ================");
	 	OptionalDouble media = usuarios.stream()
	 							.mapToInt(Usuario::getPonto)
	 							.average();
	 	
	 	double pontuacaoMediaOptional = media.orElse(0.0);
	 	
	 	// ou apenas em uma linha
	 
	 	double pontuacaoMediaOptionalSimplificado = usuarios.stream()
												 			.mapToInt(Usuario::getPonto)
												 			.average()
												 			.orElse(0.0);
	 	
	 	// caso a lista for vazia nao ocorre excption porque o retorno é opcional
	 	Optional<Usuario> max = usuarios
					 			.stream()
					 			.max(Comparator.comparingInt(Usuario::getPonto));
	 	
	 	
	 	
	}
	

}
