package br.com.mariojp.solid.srp.service;

import br.com.mariojp.solid.srp.model.Order;

public class ReceiptService {

	private final TaxCalculator taxCalculator;
	private final ReceiptFormatter receiptFormatter;

	public ReceiptService() {
		this.taxCalculator = new TaxCalculator();
		this.receiptFormatter = new ReceiptFormatter();
	}

	public String generate(Order order) {
		double subtotal = order.getItems().stream().mapToDouble(i -> i.getUnitPrice() * i.getQuantity()).sum();
		double tax = this.taxCalculator.calculate(subtotal);
		double total = subtotal + tax;

		return receiptFormatter.format(order, subtotal, tax, total);
	}
}
