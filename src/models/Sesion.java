package models;

import java.util.ArrayList;
import java.util.Scanner;

import interfaces.Convertible;

public class Sesion {
    private boolean isActive;
    private ArrayList<Convertible> list;
    private Scanner scanner;

    public Sesion(boolean isActive, ArrayList<Convertible> list, Scanner scanner) {
        this.isActive = isActive;
        this.list = list;
        this.scanner = scanner;
    }

    public boolean isActive(){
        return isActive;
    }

    public void cleanConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void menu(){
        System.out.println("-".repeat(42));
        System.out.println("Seleccione una opción");
        System.out.println("1. Iniciar");
        System.out.println("2. Salir");
        
        Integer input = isValid(String.valueOf(scanner.nextLine()));
        if (input != null){
        if (input == 1) {
            cleanConsole();
        } else if(input == 2) {
            System.out.println("¡Vuelve pronto! :)");
            isActive = false;
        } else {
            cleanConsole();
            System.out.println("Digite una opción válida");
            menu();
        }} else {
            cleanConsole();
            System.out.println("Digite una opción válida");
            menu();
        }
    }

    public Scanner getScanner(){
        return scanner;
    }

    private void printCurrencies(){
        Convertible currency;
        for (int i = 0; i < list.size(); i++) {
            currency = list.get(i);
            System.out.println(i+1 + ". " + currency.showCurrency());}
        System.out.println("9. Otra moneda");
        System.out.println("0. Ingrese 0 para salir");
    }

    private Integer isValid(String input) {
        if (input.matches("[0-9]+")) {
            return Integer.parseInt(input);
        }
        return null;
    }

    private Currency anotherCurrency(Scanner scanner){
        System.out.println("Digite el código de la moneda en formato ISO 4217: ");
        String currencyCode = String.valueOf(scanner.nextLine());

        if (currencyCode.matches("^[a-zA-Z]{3}$")){
            return new Currency(currencyCode.toUpperCase());
        }
        cleanConsole();
        System.out.println("Código de moneda no válido.");
        return anotherCurrency(scanner);
    }

    public Convertible selectCurrency(String text){
        Integer item;
        String input;
       
        cleanConsole();
        System.out.println(text);
        System.out.println("-".repeat(42));
        printCurrencies();

        input = scanner.nextLine();
        item = isValid(input);

        if (item != null) {
            if (item == 0) {
                isActive = false;
                throw new IndexOutOfBoundsException();
            } else if (item >= 1 && item <= list.size()) {
                Convertible currency = list.get(item - 1);
                cleanConsole();
                return currency;
            } else if (item == 9) {
                cleanConsole();
                Convertible currency = anotherCurrency(scanner);
                return currency;
            }
        }
        return selectCurrency("Por favor, digite una opción válida.");
    }
    }

