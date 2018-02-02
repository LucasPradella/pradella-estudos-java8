package br.com.pradella.capitulo;

import static java.util.Arrays.asList;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import br.com.pradella.entidade.Usuario;

public class Capitulo8Impl {
	
	public static void main(String[] args) {
		
		Usuario user1 = new Usuario("Hanah", 120);
		Usuario user2 = new Usuario("Arya", 150);
		Usuario user3 = new Usuario("Stark", 120);
		Usuario user4 = new Usuario("Snow", 20);
		
		List<Usuario> usuarios = asList(user1, user2, user3, user4);
		
		
		System.out.println("============= ORDENANDO UM STREAM ============= ");
		
		
		// filtrar os usuarios com mais de 100 pontos, e ae sim ordenalos
		List<Usuario> resultadoNovaLista = usuarios.stream()
					.filter(u -> u.getPonto() > 100)
					.sorted(Comparator.comparing(Usuario::getNome))
					.collect(Collectors.toList());
		
		// metodo findAny pega um elemento aleatorio
		Optional<Usuario> umReultadoAleatorio = usuarios.stream()
					.filter(u -> u.getPonto() > 100)
					.findAny();
	
		System.out.println("=========== ENXERGANDO A EXECUÇÃO DO PIPELINE COM PEEK =========");
		
		usuarios.stream()
					.filter(u -> u.getPonto() > 100)
					.peek(System.out::println)
					.findAny();
					
		
		// nao imprime nada quando nao acha uma opração terminal 
		usuarios.stream()
						.filter(u -> u.getPonto() > 100)
						.peek(System.out::println);
		
		
		// mesmo metodo mas com a operação terminal 
		usuarios.stream()
				.sorted(Comparator.comparing(Usuario::getNome))
				.peek(System.out::println)
				.findAny();
		
		// operações de Redução 
		// average 
		
		double pontuacaoMedia = usuarios.stream()
										.mapToInt(Usuario::getPonto)
										.average()
										.getAsDouble();
		
		System.out.println(pontuacaoMedia);
		
		
		Optional<Usuario> max = usuarios.stream()
											.max(Comparator.comparing(Usuario::getPonto));
		
		Usuario maximaPontuacao = max.get();
		System.out.println(maximaPontuacao.getPonto());
	
		int total = usuarios.stream()
								.mapToInt(Usuario::getPonto)
								.sum();
		
		System.out.println(total);
		
		// codigo equivalente ao sum
		int valorInicial = 0;
		IntBinaryOperator operacao = (a, b) -> a + b;
		
		int totalDois = usuarios.stream()
								.mapToInt(Usuario::getPonto)
								.reduce(valorInicial, operacao);
		
		// ou 
		int totalTres = usuarios.stream()
								.mapToInt(Usuario::getPonto)
								.reduce(0, (a, b) -> a + b);
		
		int totalQuatro = usuarios.stream()
								.mapToInt(Usuario::getPonto)
								.reduce(0, Integer::sum);
		
		
		// multiplicar todos os pontos 
		int multiplicacao = usuarios.stream()
										.mapToInt(Usuario::getPonto)
										.reduce(1, (a,b) -> a * b);
		
		System.out.println(multiplicacao);
		
		
		
		
		
		System.out.println("============= PREDICATES  ============= "); 
		//Retoena um boolean assim que encontra um moderador true
		
		boolean hasModerator = usuarios.stream()
										.anyMatch(Usuario::isModerador);
	
		
		// Obter os 100 primeiros elementos, usando limit 
		Random random = new Random(0);
		IntStream stream = IntStream.generate(() -> random.nextInt());
		List<Integer> list = stream
								.limit(100)
								.boxed()
								.collect(Collectors.toList());
		

		
		
		
	}
	
	

}
