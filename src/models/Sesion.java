package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import interfaces.Visible;

public class Sesion {
    private boolean isActive;
    private ArrayList<Visible> currencyList;
    private Scanner scanner;
    private ArrayList<History> historyList;

    public Sesion(boolean isActive, ArrayList<Visible> currencyList, Scanner scanner) {
        this.isActive = isActive;
        this.currencyList = currencyList;
        this.scanner = scanner;
        this.historyList = new ArrayList<>();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public boolean isActive() {
        return isActive;
    }

    private void exitSesion() {
        isActive = false;
    }

    private void lineConsole() {
        System.out.println("-".repeat(50));
    }

    public void cleanConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private Integer isValid(String input) {
        if (input.matches("[0-9]+")) {
            return Integer.parseInt(input);
        }
        return null;
    }

    private void printHistory() {
        if (historyList.size() > 0) {
            historyList.forEach(System.out::println);
        } else {
            System.out.println("No hay conversiones realizadas.");
        }
    }

    public void printResult(Currency to, Currency from) {
        cleanConsole();
        historyList.add(new History(to, from));
        System.out.println("[✓] Resultado: " + from + " -> " + to);
    }

    private void printOptions() {
        ArrayList<String> option = new ArrayList<>(Arrays.asList(
                "[1] Iniciar conversión",
                "[2] Ver historial",
                "[3] Salir de la app"));
        lineConsole();
        option.forEach(System.out::println);
        lineConsole();
    }

    public void showMenu() {
        boolean continueMenu = true;

        while (continueMenu) {
            printOptions();
            Integer input = isValid(String.valueOf(scanner.nextLine()));
            cleanConsole();

            if (input == null || (input < 1 || input > 3)) {
                System.out.println("Digite una opción válida.");
                continue;
            }

            switch (input) {
                case 1:
                    continueMenu = false;
                    break;
                case 2:
                    System.out.println("Historial de conversiones");
                    lineConsole();
                    printHistory();
                    break;
                case 3:
                    System.out.println("¡Vuelve pronto! :)");
                    continueMenu = false;
                    exitSesion();
                    return;
            }
        }
    }

    private void printCurrencies() {
        Visible currency;
        for (int i = 0; i < currencyList.size(); i++) {
            currency = currencyList.get(i);
            System.out.println("[" + (i + 1) + "] " + currency.showCurrency());
        }
        System.out.println("[9] Otra moneda");
        System.out.println("[0] Ingrese 0 para salir");
        lineConsole();
    }

    private Currency anotherCurrency(Scanner scanner) {
        System.out.println("Digite el código de la moneda en formato ISO 4217: ");
        String currencyCode = String.valueOf(scanner.nextLine());

        if (currencyCode.matches("^[a-zA-Z]{3}$")) {
            return new Currency(currencyCode.toUpperCase());
        }
        cleanConsole();
        System.out.println("Código de moneda no válido.");
        return anotherCurrency(scanner);
    }

    public Visible selectCurrency(String text) {
        Integer item;
        String input;

        cleanConsole();
        System.out.println(text);
        lineConsole();

        printCurrencies();

        input = scanner.nextLine();
        item = isValid(input);

        if (item != null) {
            if (item == 0) {
                exitSesion();
                throw new IndexOutOfBoundsException();
            } else if (item >= 1 && item <= currencyList.size()) {
                Visible currency = currencyList.get(item - 1);
                cleanConsole();
                return currency;
            } else if (item == 9) {
                cleanConsole();
                Visible currency = anotherCurrency(scanner);
                return currency;
            }
        }
        return selectCurrency("Por favor, digite una opción válida.");
    }
}