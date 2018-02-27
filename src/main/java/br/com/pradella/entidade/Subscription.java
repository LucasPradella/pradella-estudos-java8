package br.com.pradella.entidade;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class Subscription {
	
	private BigDecimal monthlyFee;
	private LocalDateTime begin;
	private Optional<LocalDateTime> end;
	private Customer customer;
	
	public Subscription(BigDecimal monthlyFee, LocalDateTime begin, Customer customer) {
			this.monthlyFee = monthlyFee;
			this.begin = begin;
			this.end = Optional.empty();
			this.customer = customer;
	}
	
	public Subscription(BigDecimal monthlyFee, LocalDateTime begin, LocalDateTime end, Customer customer) {
			this.monthlyFee = monthlyFee;
			this.begin = begin;
			this.end = Optional.of(end);
			this.customer = customer;
	}
	
	public LocalDateTime getBegin() {
		return begin;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public Optional<LocalDateTime> getEnd() {
		return end;
	}
	
	public BigDecimal getMonthlyFee() {
		return monthlyFee;
	}
	
			
}
