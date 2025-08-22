package br.com.mariojp.solid.srp.service;

public class TaxCalculator {

    public double calculate(double subtotal){
        String taxRateProperty = System.getProperty("tax.rate", "0.0");
        double taxRate;
        try {
            taxRate = Double.parseDouble(taxRateProperty);
        } catch (NumberFormatException e){
            System.err.println("Formato inválido para tax.rate. Usando 0% como valor padrão.");
            taxRate = 0.0;
        }
        return subtotal * taxRate;
    }
}
