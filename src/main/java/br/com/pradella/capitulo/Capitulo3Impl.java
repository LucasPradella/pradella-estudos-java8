package br.com.pradella.capitulo;

public class Capitulo3Impl {

	public static void main(String[] args) {
		
		System.out.println("============ Runnable antes do j8 ============");
		
		Runnable r = new Runnable() {
			public void run() {
				for (int i = 0; i <= 1000; i++) {
					System.out.println(i);
				}
			}
		};

		new Thread(r).start();

	
		System.out.println("============ Runnable j8 - expressao lambd ============");
		
		Runnable r8 = () -> {
			for (int i = 1000; i >= 0; i--) {
				System.out.println(i);
			}
		};
	
		new Thread(r8).start();
		
		
		new Thread(() -> {
			for (int i = 100; i <= 999; i++) {
				System.out.println(i);
			}
		}).start();
		
		
		System.out.println("============ Interface Funcional - Antes do j8 ============");
		Validador<String> validadorCEP = new Validador<String>() {
			public boolean valida(String valor) {
			return valor.matches("[0-9]{5}-[0-9]{3}");
			}
		};
		
		System.out.println("CEP" + validadorCEP.valida("teste"));
		
		
		System.out.println("============ Interface Funcional -  j8 ============");
		Validador<String> validadorCEPLambda = valor -> {
			return valor.matches("[0-9]{5}-[0-9]{3}");
			
		};
		
		System.out.println("============ Interface Funcional -  j8 ============");
		Validador<String> validadorCEPLambdaReduzido = valor ->  valor.matches("[0-9]{5}-[0-9]{3}") ;
		
		
		System.out.println("CEP : " + validadorCEP.valida("teste"));
		System.out.println("CEP Lambda : " + validadorCEPLambda.valida("15600-000"));
		System.out.println("CEP Lambda Reduzido : " + validadorCEPLambda.valida("04345-000"));
		
		
		Runnable o = () -> {
			System.out.println("O que sou eu? Que lambda?");
		};
		
		System.out.println(o);
		System.out.println(o.getClass());
		
		
		
		System.out.println("============ Captura de variáveis locais -  j8 ============"); 
		int numero = 5;
		new Thread(() -> {
			System.out.println("Numero = " + numero); // não compila
		}).start();
	//	numero = 10; // numero tem que ser final , caso descomente essa linha, o codigo acima nao funciona!
		
		
		
	}
	
	
	
	@FunctionalInterface
	interface Validador<T> {
		boolean valida(T t);
	//	boolean outroMetodo(T t); a anotaçao @FuncionaInterface, permite apenas ter um metodo.
 	}
	
	
	
	
	
	
	
	

}
