import java.net.ConnectException;
import java.util.Scanner;

import models.Currency;
import models.Sesion;

public class App {
    public static void main(String[] args) throws Exception {
        Currency to;
        Currency from;
        Sesion sesion = new Sesion(true, Currency.getConvertible(), new Scanner(System.in));

        sesion.cleanConsole();
        System.out.println("[€$¥] Bienvenido al Conversor de Monedas [€$¥]");
        sesion.showMenu();

        while (sesion.isActive()) {
            try {
                from = (Currency) sesion.selectCurrency("Seleccione la moneda actual:");
                from.reqAmount(sesion.getScanner());

                to = (Currency) sesion.selectCurrency("Seleccione la moneda a convertir:");
                from.convertCurrency(to);

                if (to.getAmount() > 0)
                    sesion.printResult(to, from);
                sesion.showMenu();
            } catch (ConnectException e) {
                System.out.println("Verifica tu conexión a internet y vuelve a intentarlo.");
                sesion.showMenu();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("¡Hasta pronto! :)");
            }
        }
    }
}
