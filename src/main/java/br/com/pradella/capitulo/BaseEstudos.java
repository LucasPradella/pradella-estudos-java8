package br.com.pradella.capitulo;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import br.com.pradella.entidade.Usuario;

public class BaseEstudos {

	
	protected static Usuario user1 = new Usuario("Hanah", 120 );
	protected static Usuario user2 = new Usuario("Arya", 150);
	protected static Usuario user3 = new Usuario("Stark", 120);
	protected static Usuario user4 = new Usuario("Snow", 20, true);
	protected static Usuario user5 = new Usuario("Ragnar", 200, true);
	
	protected static List<Usuario> usuarios = asList(user1, user2, user3, user4, user5);
	
	
	protected static Stream<String> lines(Path p) {
		try {
			return Files.lines(p);
		} catch(IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
