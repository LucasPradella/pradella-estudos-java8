package br.com.pradella.capitulo;

import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.pradella.entidade.Customer;
import br.com.pradella.entidade.Payment;
import br.com.pradella.entidade.Product;
import br.com.pradella.entidade.Subscription;

public class Capitulo11Impl extends BaseEstudos{
	
	public static void main(String[] args) {
	
		System.out.println("=============== UM MODELO DE PAGAMENTOS COM JAVA 8 ===============");
		
		Customer tonny = new Customer("Tonny Stark");
		Customer arya = new Customer("Arya Stark");
		Customer joao = new Customer("Joao Snow");
		Customer capitao = new Customer("Capitao America");
		
		
		Product vingadoresMP3 = new Product("Vingadores MP3", Paths.get("mp3/vingadores.mp3"), new BigDecimal(100));
		Product vingadoresJPG = new Product("Vingadores Imagem", Paths.get("img/vingadores.jpg"), new BigDecimal(100));
		Product vingadoresMOV = new Product("Vingadores Filme", Paths.get("mov/vingadores.mov"), new BigDecimal(100));
		Product gotMP3 = new Product("Game Of Thrones MP3", Paths.get("mp3/got.mp3"), new BigDecimal(100));
		Product gotJPG = new Product("Game Of Thrones JPG", Paths.get("img/got.jpg"), new BigDecimal(100));
		Product gotMOV = new Product("Game Of Thrones Filme", Paths.get("mov/got.mov"), new BigDecimal(100));

		
		LocalDateTime hoje = LocalDateTime.now();
		LocalDateTime ontem = hoje.minusDays(1);
		LocalDateTime ultimoMes = hoje.minusMonths(1);
	
		Payment payment1 = new Payment(asList(vingadoresJPG, gotJPG), ontem, tonny);
		Payment payment2 = new Payment(asList(vingadoresMOV, gotMOV), hoje,arya);
		Payment payment3 = new Payment(asList(vingadoresMOV), hoje, capitao);
		Payment payment4 = new Payment(asList(vingadoresJPG, gotMOV, gotJPG), ontem, joao);
		Payment payment5 = new Payment(asList(vingadoresMP3, gotMP3), ultimoMes, tonny);
		
		List<Payment> payments = asList(payment1, payment2, payment3, payment4, payment5); 
		
	
		System.out.println("=============== ORDENANDO NOSSOS PAGAMENTOS ===============");
		
		// orderjar os pagamentos por datas a imprimilos 
		payments.stream()
					.sorted(Comparator.comparing(Payment::getDate))
					.forEach(System.out::println);
		
		
		System.out.println("================== REDUZINDO BIGDECIMAL EM SOMAS ===================");
		
		// somando produtos primeiro pagamento
		payment1.getProducts()
						.stream()
						.map(Product::getPrice)
						.reduce(BigDecimal::add)
						.ifPresent(System.out::println);
		
		// mais uma opção 
		BigDecimal total = payment1.getProducts()
					.stream()
					.map(Product::getPrice)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println(total);
		
		
		// somar todos os valores de todos os pagamentos, retornando uma stream
		Stream<BigDecimal> total1 = payments.stream()
											.map(p -> p.getProducts()
														.stream()
														.map(Product::getPrice)
														.reduce(BigDecimal.ZERO, BigDecimal::add));
		System.out.println(total1);
		
		// utilizando flapMap retornando um BigInteger
		BigDecimal totalFlat = payments.stream()
											.flatMap(p -> p.getProducts()
																	.stream()
																	.map(Product::getPrice))
																	.reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println(totalFlat);
		
		
		System.out.println("==================== PRODUTOS MAIS VENDIDOS =====================");
		// verifica qual produto vendeu mais 
		Stream<Product> preferidoResultado = payments.stream()
														.map(Payment::getProducts)
														.flatMap(p -> p.stream());
		System.out.println(preferidoResultado);
	
		
		Stream<Product> preferidoResultado1 = payments.stream()
														.map(Payment::getProducts)
														.flatMap(List::stream);
		System.out.println(preferidoResultado1);

		// gerar um map de product
		Map<Product, Long> mapProduct = payments.stream()
				.flatMap(p -> p.getProducts().stream())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(mapProduct);
		
		
		// melhorando a leitura do map Product
		mapProduct.entrySet()
						.stream()
						.forEach(System.out::println);
		

		System.out.println("----------");
		// pegando o valor da lista que mais aparece
		mapProduct.entrySet()
					.stream()
					.max(Comparator.comparing(Map.Entry::getValue))
					.ifPresent(System.out::println);
	
		
		System.out.println("==================== VALORES GERADOS POR PRODUTO =================");
		
		// quntidade de vendas por produtos
		Map<Product,BigDecimal> totalValuePerProduct = payments.stream()
				.flatMap(p -> p.getProducts().stream())
				.collect(Collectors.groupingBy(Function.identity(),
								Collectors.reducing(BigDecimal.ZERO, Product::getPrice,
																			BigDecimal::add)));
		
		
					
		totalValuePerProduct.entrySet().stream()
										.sorted(Comparator.comparing(Map.Entry::getValue))
										.forEach(System.out::println);
										
		
		System.out.println("=================== QUAIS SÃO OS PRODUTOS DE CADA CLIENTE =================");
		
		
		Map<Customer, List<List<Product>>> customerToProductsList =
				payments.stream()
				.collect(Collectors.groupingBy(Payment::getCustomer,
				Collectors.mapping(
				Payment::getProducts, Collectors.toList())));
				
				
		Map<Customer, List<Product>> customerToProducts2steps = 
				customerToProductsList
						    .entrySet()
							.stream()
							.collect(Collectors
									.toMap(Map
											.Entry::getKey,e -> e.getValue()
																	.stream()
																	.flatMap(List::stream)
																	.collect(Collectors.toList())));
		
		customerToProducts2steps.entrySet()
										.stream()
										.sorted(Comparator.comparing(e -> e.getKey().getName()))
										.forEach(System.out::println);
		
		
		System.out.println("================ QUAL É NOSSO CLIENTE MAIS ESPECIAL ==================");

		Function<Payment, BigDecimal> paymentToTotal =	p -> p.getProducts()
																	.stream()
																	.map(Product::getPrice)
																	.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		Map<Customer, BigDecimal> totalValuePerCustomer = 
				payments.stream()
								.collect(Collectors.groupingBy(Payment::getCustomer,	
																Collectors.reducing(BigDecimal.ZERO,
																paymentToTotal,
																BigDecimal::add)));

		totalValuePerCustomer.entrySet().stream()
											.sorted(Comparator.comparing(Map.Entry::getValue))
											.forEach(System.out::println);
		
		
		
		
		System.out.println("====================== RELATÓRIOS COM DATAS ====================");
		
		
		Map<YearMonth, List<Payment>> paymentsPerMonth =
				payments.stream()
								.collect(Collectors.groupingBy(p -> YearMonth.from(p.getDate())));
		
		paymentsPerMonth.entrySet()
							.stream()
							.forEach(System.out::println);

		
		// valores por mes
		Map<YearMonth, BigDecimal> paymentsValuePerMonth =
				payments.stream()
						.collect(Collectors.groupingBy(p -> 
												YearMonth.from(p.getDate()), 
																Collectors
																.reducing(BigDecimal.ZERO,
						p -> p.getProducts()
							.stream()
							.map(Product::getPrice)
							.reduce(BigDecimal.ZERO, BigDecimal::add),
						BigDecimal::add)));					
		
		paymentsValuePerMonth.entrySet()
									.stream()
									.forEach(System.out::println);

		
		
		BigDecimal monthlyFee = new BigDecimal("99.90");
		
		Subscription s1 = new Subscription(monthlyFee,ontem.minusMonths(5), tonny);
		Subscription s2 = new Subscription(monthlyFee,ontem.minusMonths(8), hoje.minusMonths(1), joao);
		Subscription s3 = new Subscription(monthlyFee,ontem.minusMonths(5), hoje.minusMonths(2), arya);
		List<Subscription> subscriptions = Arrays.asList(s1, s2, s3);
		
		// quantos meses foram pagos atraves dessa assinatura 
/*		long meses = ChronoUnit.MONTHS
								.between(s1.getBegin(), LocalDateTime.now());
*/	
		long meses = ChronoUnit.MONTHS
								.between(s1.getBegin(),
								s1.getEnd().orElse(LocalDateTime.now()));
	
		System.out.println(meses);
		
		
		BigDecimal totalAssinatura = s1.getMonthlyFee()
								.multiply(new BigDecimal(ChronoUnit.MONTHS
								.between(s1.getBegin(),
										s1.getEnd().orElse(LocalDateTime.now()))));
		System.out.println(totalAssinatura);
	
	
	}
	
	

}
