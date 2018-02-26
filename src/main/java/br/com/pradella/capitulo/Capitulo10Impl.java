package br.com.pradella.capitulo;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Locale;

public class Capitulo10Impl extends BaseEstudos{
	
	public static void main(String[] args) {
		
		System.out.println("=============== TRABALHANDO COM DATAS DE FORMA FLUENTE ===============");
		
		// somando um mes
		LocalDate mesQueVem = LocalDate.now().plusMonths(1);
		System.out.println(mesQueVem);
		
		// subtrair um ano
		LocalDate anoPassado = LocalDate.now().minusYears(1);
		System.out.println(anoPassado);
		
		//data + horario 
		LocalDateTime agora = LocalDateTime.now();
		System.out.println(agora);
		
		// apenas as horas
		LocalTime agoraHoras = LocalTime.now();
		System.out.println(agoraHoras);
		
		// determinando horario especifico
		LocalDateTime hojeAoMeioDia = LocalDate.now().atTime(12,0);
		System.out.println(hojeAoMeioDia);
		
		// combinando classes localDate com LocalTime
		LocalTime agora1 = LocalTime.now();
		LocalDate hoje = LocalDate.now();
		LocalDateTime dataEhora = hoje.atTime(agora1);
		System.out.println(dataEhora);
		
		// definir e capturar uma data de um time zone especifico 
		ZonedDateTime dataComHoraETimezone = dataEhora.atZone(ZoneId.of("America/Sao_Paulo"));
		System.out.println(dataComHoraETimezone);
		
		// convertendo informação utilizando o "to"
		LocalDateTime semTimeZone =	dataComHoraETimezone.toLocalDateTime();
		System.out.println(semTimeZone);
	
		// metodos estaticos "of"
		LocalDate date = LocalDate.of(2018, 12, 25);
		LocalDateTime dateTime = LocalDateTime.of(2018, 12, 25, 10, 30);
		System.out.println(date);
		System.out.println(dateTime);
	
		// setar o ano e identificar utilizando o get identificar o ano
		LocalDate dataDoPassado = LocalDate.now().withYear(1988);
		System.out.println(dataDoPassado.getYear());
		
		
		// verificar se medida de tempo acontece antes, depois ou ao mesmo tempo que outra
		LocalDate hoje1 = LocalDate.now();
		LocalDate amanha = LocalDate.now().plusDays(1);
		System.out.println(hoje1.isBefore(amanha));
		System.out.println(hoje1.isAfter(amanha));
		System.out.println(hoje1.isEqual(amanha));
		
		// compara um time zone com outro, utilizando isEqual
		ZonedDateTime tokyo = ZonedDateTime.of(2018, 5, 2, 10, 30, 0, 0, ZoneId.of("Asia/Tokyo"));
		ZonedDateTime saoPaulo = ZonedDateTime.of(2018, 5, 2, 10, 30, 0, 0, ZoneId.of("America/Sao_Paulo"));
		System.out.println(tokyo.isEqual(saoPaulo));
		
		// adicionando 12 horas no tome de tokyo, para resultado ser verdadeiro
		ZonedDateTime tokyo1 = ZonedDateTime.of(2018, 5, 2, 10, 30, 0, 0, ZoneId.of("Asia/Tokyo"));
		ZonedDateTime saoPaulo1 = ZonedDateTime.of(2018, 5, 2, 10, 30, 0, 0, ZoneId.of("America/Sao_Paulo"));
		tokyo1 = tokyo1.plusHours(12);
		System.out.println(tokyo1.isEqual(saoPaulo1));
		
		// obtendo o dia do mes
		System.out.println("hoje é dia: " + MonthDay.now().getDayOfMonth());

		// pegando mes e ano de uma data
		YearMonth ym = YearMonth.from(LocalDate.now());
		System.out.println(ym.getMonth() + " " + ym.getYear());
		
		
		
		
		System.out.println("=============== ENUMS NO LUGAR DE CONSTANTES ===============");
		
		// no lugar de usar o valor 1, pode usar JANUARY
		System.out.println(LocalDate.of(2018, 1, 25));
		System.out.println(LocalDate.of(2018, Month.JANUARY, 25));

		// incrementando/retirando meses  
		System.out.println(Month.DECEMBER.firstMonthOfQuarter());
		System.out.println(Month.DECEMBER.plus(2));
		System.out.println(Month.DECEMBER.minus(1));
		
		
		// Adicionando mes em portugues
		Locale pt = new Locale("pt");
		System.out.println(Month.DECEMBER.getDisplayName(TextStyle.FULL, pt));		
		System.out.println(Month.DECEMBER.getDisplayName(TextStyle.SHORT, pt));
		
		
		System.out.println("=============== FORMATANDO COM A NOVA API DE DATAS ===============");
		
		// formatando horas 
		LocalDateTime agoraFormat = LocalDateTime.now();
		String resultado = agoraFormat.format(DateTimeFormatter.ISO_LOCAL_TIME);
		System.out.println(resultado);
		
		// definindo outro tipo de padrão
		LocalDateTime agoraFormat1 = LocalDateTime.now();
		String resultado1 = agoraFormat1.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.println(resultado1);
		
		System.out.println("=============== DATAS INVÁLIDAS ===============");
		// mesmo essa data nao existindo não retorna uma exceção, pois a propria api trata isso de forma transparente 
		Calendar instante = Calendar.getInstance();
		instante.set(2014, Calendar.FEBRUARY, 30);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		System.out.println(dateFormat.format(instante.getTime()));
		
		System.out.println("=============== DURAÇÃO E PERÍODO ===============");
		
		// comparando datas 
		LocalDate agora2 = LocalDate.now();
		LocalDate outraData = LocalDate.of(1989, Month.JANUARY, 25);
		long dias = ChronoUnit.DAYS.between(outraData, agora2);
		System.out.println(dias);
		
		// calcular dias meses e anos 
		long diasChrono = ChronoUnit.DAYS.between(outraData, agora);
		long mesesChrono = ChronoUnit.MONTHS.between(outraData, agora);
		long anosChrono = ChronoUnit.YEARS.between(outraData, agora);
		System.out.printf("%s dias, %s meses e %s anos", diasChrono, mesesChrono, anosChrono);
		
		System.out.println();
		
		// usando Period + between
		LocalDate agora3 = LocalDate.now();
		LocalDate outraData3 = LocalDate.of(1989, Month.JANUARY, 25);
		Period periodo = Period.between(outraData3, agora3);
		System.out.printf("%s dias, %s meses e %s anos", periodo.getDays(), periodo.getMonths(),
																							periodo.getYears());
	
		System.out.println();
		// usando Period com saida negativa
		LocalDate agora4 = LocalDate.now();
		LocalDate outraData4 = LocalDate.of(2019, Month.JANUARY, 25);
		Period periodo2 = Period.between(outraData4, agora4);
		System.out.printf("%s dias, %s meses e %s anos", periodo2.getDays(), periodo2.getMonths(), 
																						periodo2.getYears());
		
		
		

		System.out.println();
		// usando isNegated, valores são convertidos para positivos
		Period periodo3 = Period.between(outraData4, agora4);
	
		if (periodo3.isNegative()) {
			periodo3 = periodo3.negated();
		}
		
		System.out.printf("%s dias, %s meses e %s anos", periodo3.getDays(), periodo3.getMonths(),
																						periodo3.getYears());
	
		System.out.println();
		// duration trata valores com m 
		LocalDateTime agora5 = LocalDateTime.now();
		LocalDateTime daquiAUmaHora = LocalDateTime.now().plusHours(1);
		Duration duration = Duration.between(agora5, daquiAUmaHora);
		if (duration.isNegative()) {
			duration = duration.negated();
		}
		System.out.printf("%s horas, %s minutos e %s segundos", duration.toHours(), duration.toMinutes(),
																							duration.getSeconds());
	
	
	
	}
	
	

}
