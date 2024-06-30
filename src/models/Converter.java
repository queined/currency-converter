package models;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.github.cdimascio.dotenv.Dotenv;

public class Converter {
    private double resultCurrency;

    private String getURL(Currency from, Currency to) {
        String urlAPI = "https://v6.exchangerate-api.com/v6/key/pair/currFrom/currTo/currAmount"
                .replace("key", getAPI())
                .replace("currFrom", from.getCurrencyCode())
                .replace("currTo", to.getCurrencyCode())
                .replace("currAmount", String.valueOf(from.getAmount()));

        return urlAPI;
    }

    private String getAPI() {
        Dotenv dotenv = Dotenv.load();
        return dotenv.get("API_KEY");
    }

    public void convertCurrency(Currency from, Currency to) throws IOException, InterruptedException {
        URI url = URI.create(getURL(from, to));

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

        if (jsonObject.get("result").getAsString().equals("error")) {
            System.out.println("[X] Error en la conversión. Digitó un código de moneda no válido.");
        }

        if (jsonObject.get("result").getAsString().equals("success")) {
            resultCurrency = jsonObject.get("conversion_result").getAsDouble();
            to.setAmount(resultCurrency);
        }
    }
}
