import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws Exception {

        // URL de la API de tasas de cambio
        String url_str = "https://v6.exchangerate-api.com/v6/be9aec41f785f8021519e3af/latest/USD";

        // Realizar solicitud GET y obtener las tasas de cambio
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();
        JsonObject rates = jsonobj.getAsJsonObject("conversion_rates");

        // Mostrar bienvenida
        System.out.println("*********************************");
        System.out.println("Bienvenido a Conversor de Monedas");

        // Mostrar opciones disponibles
        String menu = """
                --- Opciones Disponibles ---
                1. Dólares(USD) a Pesos(MXN)
                2. Pesos(MXN) a Reales Brasileños(BRL)
                3. Dólares(USD) a Euros(EUR)                
                9. Salir
                
                """;
        Scanner teclado = new Scanner(System.in);
        int opcion;
        do {
            System.out.println(menu);
            opcion = teclado.nextInt();

            switch (opcion){
                case 1:
                    // Conversión de Dólares(USD) a Pesos(MXN)
                    System.out.print("Ingrese la cantidad en Dólares(USD): ");
                    double dolares = teclado.nextDouble();
                    double tasaConversionMXN = rates.get("MXN").getAsDouble(); // Obtener tasa de conversión de USD a MXN
                    double pesos = dolares * tasaConversionMXN;
                    System.out.println(dolares + " dólares(USD) equivalen a " + pesos + " pesos(MXN)");
                    break;
                case 2:
                    // Pesos(MXN) a Reales brasileños(BRL)
                    System.out.print("Ingrese la cantidad en Pesos(MXN): ");
                    double pesosReales = teclado.nextDouble();
                    double tasaConversionBRL = rates.get("BRL").getAsDouble();
                    double reales = pesosReales * tasaConversionBRL;
                    System.out.println(pesosReales + " pesos(MXN) equivalen a " + reales + " reales(BRL)");
                    break;
                case 3:
                    // Dólares(USD) a Euros(EUR)
                    System.out.print("Ingrese la cantidad en Dólares(USD): ");
                    double dolaresEuros = teclado.nextDouble();
                    double tasaConversionEUR = rates.get("EUR").getAsDouble();
                    double euros = dolaresEuros * tasaConversionEUR;
                    System.out.println(dolaresEuros + " Dólares(USD) equivalen a " + euros + " Euros(EUR)");
                    break;

                case 9:
                    System.out.println("Conversor de Monedas agradece tu preferencia...:)");
                    break;
                default:
                    System.out.println("Haz elegido una opción que NO se encuentra en el menú de opciones.");
            }
        }while (opcion!=9);
    }
}