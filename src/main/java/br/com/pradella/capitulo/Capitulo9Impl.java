package br.com.pradella.capitulo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import br.com.pradella.entidade.Usuario;

public class Capitulo9Impl extends BaseEstudos{
	
	public static void main(String[] args) throws IOException {
		
		
		System.out.println("=========== MAPEANDO, PARTICIONANDO, AGRUPANDO E PARALELIZANDO ===========");
		
		//COLETORES GERANDO MAPAS
		Stream<String> strings =
				Files.list(Paths.get("./src/main/resources/java8"))
				.filter(p -> p.toString().endsWith(".txt"))
				.flatMap(p -> lines(p));
		
		
		LongStream lines =
				Files.list(Paths.get("./src/main/resources/java8"))
				.filter(p -> p.toString().endsWith(".txt"))
				.mapToLong(p -> lines(p).count());
		
		
		System.out.println(lines);
		
		List<Long> lines2 =
				Files.list(Paths.get("./src/main/resources/java8"))
				.filter(p -> p.toString().endsWith(".txt"))
				.map(p -> lines(p).count())
				.collect(Collectors.toList());
		
		System.out.println(lines2);
		
		Map<Path, Long> linesPerFile = new HashMap<>();
		Files.list(Paths.get("./src/main/resources/java8"))
							.filter(p -> p.toString().endsWith(".txt"))
							.forEach(p -> linesPerFile.put(p, lines(p).count()));
		System.out.println(linesPerFile);
		
		
		Map<Path, Long> lines3 =
				Files.list(Paths.get("./src/main/resources/java8"))
				.filter(p -> p.toString().endsWith(".txt"))
				.collect(Collectors.toMap(
				p -> p,
				p -> lines(p).count()));
				System.out.println(lines3);
				
		Map<Path, List<String>> content = Files.
											list(Paths.get("./src/main/resources/java8"))
											.filter(p -> p.toString().endsWith(".txt"))
											.collect(Collectors.toMap(Function.identity(),
														p -> lines(p)
																.collect(Collectors.toList())));
		
		System.out.println(content);
		
		// Mapear todos os usuários utilizando seu nome como chave
		Map<String, Usuario> nameToUser = usuarios.stream()
														.collect(Collectors
														.toMap(Usuario::getNome, Function.identity()));
		System.out.println(nameToUser);

	
		// GROUPINGBY E PARTITIONINGBY
		
		Map<Integer, List<Usuario>> pontuacao = usuarios
				.stream()
				.collect(Collectors.groupingBy(Usuario::getPonto));
		
		System.out.println(pontuacao);
		
		Map<Boolean, List<Usuario>> moderadores = usuarios
				.stream()
				.collect(
				Collectors.partitioningBy(Usuario::isModerador));
    	System.out.println(moderadores);


    	Map<Integer, List<Usuario>> pontuacao2 = new HashMap<>();
    	for(Usuario u: usuarios) {
						    	pontuacao2
						    	.computeIfAbsent(u.getPonto(),user -> new ArrayList<>())	
						    	.add(u); }
    	System.out.println(pontuacao2);
    	
    	Map<Integer, List<Usuario>> pontuacao3 = usuarios
    			.stream()
    			.collect(Collectors.groupingBy(Usuario::getPonto));
    	System.out.println(pontuacao3);

    	
    	
    	Map<Boolean, List<Usuario>> moderadores1 = usuarios
										    			.stream()
										    			.collect(Collectors.partitioningBy(Usuario::isModerador));
    	System.out.println(moderadores1);
    	
    	
    	
    	// dividir nome por moderadores e nao moderadores 
    	
    	Map<Boolean, List<String>> nomesPorTipo = usuarios
									    			.stream()
									    			.collect(Collectors.partitioningBy(
									    							Usuario::isModerador,Collectors.mapping(
									    									Usuario::getNome,Collectors.toList())));
    	System.out.println(nomesPorTipo);
    	
    	
    	
    	// somatoria de todos os pontos dividida por moderador ou nao moderador
		Map<Boolean, Integer> pontuacaoPorTipo = usuarios.stream()
														.collect(Collectors.partitioningBy(
																		Usuario::isModerador, 
																		Collectors.summingInt(Usuario::getPonto)));
		System.out.println(pontuacaoPorTipo);
		
		
		// concatenar todos usuarios e dividir por virgula
		String nomes = usuarios
				.stream()
				.map(Usuario::getNome)
				.collect(Collectors.joining(", "));
		System.out.println(nomes);
		
		
		System.out.println("============ EXECUTANDO O PIPELINE EM PARALELO ================");
		
		// filtrar usuarios com mais de 100 pontos. mas processo rolando na mesma thread 
		List<Usuario> filtradosOrdenados = usuarios.stream()
				.filter(u -> u.getPonto() > 100)
				.sorted(Comparator.comparing(Usuario::getNome))
				.collect(Collectors.toList());
		System.out.println(filtradosOrdenados);
		
		
		// filtrar usuarios com mais de 100 pontos. usando paralleStream ele identifica a necessidade de processar em mais de uma thread
				List<Usuario> filtradosOrdenados1 = usuarios.parallelStream()
															.filter(u -> u.getPonto() > 100)
															.sorted(Comparator.comparing(Usuario::getNome))
															.collect(Collectors.toList());
				System.out.println(filtradosOrdenados1);
		
				
				long sum =
						LongStream.range(0, 1_000_000_000)
					//	.parallel()
						.filter(x -> x % 2 == 0)
						.sum();
				System.out.println(sum);
	}
	
}
