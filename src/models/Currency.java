package models;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import interfaces.Visible;

public class Currency implements Visible {
    private double amount;
    private String currencyCode;
    private String currencyName;
    private static ArrayList<Visible> list = new ArrayList<>();

    public Currency(String currencyCode, String currencyName) {
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.amount = 0;
    }

    public Currency(String currencyCode) {
        this.currencyCode = currencyCode;
        this.amount = 0;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void reqAmount(Scanner scanner) {
        System.out.println("Ingrese la cifra en " + showCurrency() + ": ");
        try {
            amount = Double.parseDouble(scanner.nextLine());
            if (amount < 0 || amount == 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un número válido.");
            reqAmount(scanner);
        }
    }

    public void convertCurrency(Currency to) throws IOException, InterruptedException {
        Converter converter = new Converter();
        converter.convertCurrency(this, to);
    }

    public String showCurrency() {
        if (currencyName == null || currencyName.isEmpty()) {
            return currencyCode;
        } else {
            return currencyCode + " - " + currencyName;
        }
    }

    public static ArrayList<Visible> getConvertible() {
        list = new ArrayList<>(Arrays.asList(
                new Currency("USD", "Dólar Estadounidense"),
                new Currency("EUR", "Euro"),
                new Currency("COP", "Peso Colombiano"),
                new Currency("BRL", "Real Brasileño"),
                new Currency("ARS", "Peso Argentino"),
                new Currency("JPY", "Yen Japonés"),
                new Currency("CAD", "Dólar Canadiense"),
                new Currency("GBP", "Libra Esterlina")));
        return list;
    }

    @Override
    public String toString() {
        NumberFormat formatter = NumberFormat.getInstance();
        return formatter.format(amount) + " " + currencyCode;
    }
}
