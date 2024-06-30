import java.util.Scanner;

import models.Currency;
import models.Sesion;

public class App {
    public static void main(String[] args) throws Exception {
        Currency to;
        Currency from;
        Sesion sesion = new Sesion(true,Currency.getConvertible(), new Scanner(System.in));
       
        sesion.cleanConsole();
        System.out.println("Bienvenido al Conversor de Monedas :)");
        sesion.menu();
        
        while (sesion.isActive()) {
            try {
                from = (Currency) sesion.selectCurrency("Seleccione la moneda actual: ");
                from.reqAmount(sesion.getScanner());

                to = (Currency) sesion.selectCurrency("Seleccione la moneda a convertir");
                from.convertCurrency(to);

                if (to.getAmount()>0) System.out.println("Resultado: " + from + " -> " + to);
                sesion.menu();

            } catch (IndexOutOfBoundsException e) {
                System.out.println("¡Hasta pronto! :)");
            }     
        }
    }
}
