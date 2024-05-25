package traer;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.Principal;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class TraerMonedas{
    private static String moneda;
    private static String monedaDeseada;
    private static Monedas monedicas;
    private static double cantidad;
    //conviene no definir la variable y dejarla así, de esta manera no tendrás problemas de que te de otros valores

    //los contructores como este son setters bonitos que nos permiten modificar varias variables, o asignarle valores a varias variables de la clase
    public TraerMonedas(String moneda, String monedaDeseada, double cantidad) {
        this.moneda = moneda;
        this.monedaDeseada = monedaDeseada;
        this.cantidad = cantidad;
    }

    //el getter nos sirve para poder enviar a otras clases el valor de la variable que nosotros le digamos, NO SIRVE PARA EL MISMO ARCHIVO, PARA ESTE ARCHIVO USA LA VARIABLE DE LA QUE HICISTE EL GETTER
    public String getMoneda() {
        return moneda;
    }

    public String getMonedaDeseada() {
        return monedaDeseada;
    }

    @Override
    public String toString() {
        //acá llamamos al gson para que podamos usar la api
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();

        //aquí creamos la variable de la direccion (pudiste hacerlo en una funcion y por eso al final lo convertimos en funcion, sólo para hacer más sencillo el mantenimiento de código)
        String direccionBase = "https://v6.exchangerate-api.com/v6/11a35f72361e66b2b1441046/pair/"+moneda+"/"+monedaDeseada+"/"+cantidad;

        HttpClient client = HttpClient.newHttpClient(); //acá declaramos que somos un cliente
        HttpRequest requestBase = HttpRequest.newBuilder().uri(URI.create(direccionBase)).build(); //acá declaramos que queremos pedirle que construya algo

        try{
            HttpResponse<String> responseBase = client.send(requestBase, HttpResponse.BodyHandlers.ofString());

            String datosApi = responseBase.body(); //acá guardamos los datos de la api en la variable
            //System.out.println(datosApi); //acá mostramos los datos que trajimos de la API

            monedicas = gson.fromJson(datosApi, Monedas.class);
            //System.out.println(monedicas); //aquí se ven los datos de la api

            //esta parte nos permite crear un archivo en el que vamos a guardar las variables que precisamos en nuestro código, y los datos que iremos sacando saldrán de la API
            FileWriter escritura = new FileWriter("monedas.txt"); //hacemos el archivo y lo llamamos
            escritura.write(monedicas.toString());//guardamos las variables que nos interesan de OMDb que le solicitamos en miTitulo
            escritura.close(); //cerramos el writer para hacer que no sea lento el código

        }catch (NumberFormatException e) {
            System.out.println("ocurrió un error: "+e.getMessage());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "La conversion fue de "+moneda+" a "+monedaDeseada+" entonces "+cantidad+" es igual a: "+monedicas.conversion_result();
    }
}





