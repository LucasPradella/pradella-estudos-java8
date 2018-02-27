package br.com.pradella.entidade;

import java.math.BigDecimal;
import java.nio.file.Path;

public class Product {

	private String name;
	private Path file;
	private BigDecimal price;
	
	
	public Product(String name, Path file, BigDecimal price) {
		this.name = name;
		this.file = file;
		this.price = price;
	}
	

	
	public String getName() {
		return name;
	}
	
	public Path getFile() {
		return file;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	
	
	
	public String toString() {
	  return this.name;
	}
	
}
