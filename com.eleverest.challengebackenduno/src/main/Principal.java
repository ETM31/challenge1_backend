package main;


import traer.Monedas;
import traer.TraerMonedas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner meteB = new Scanner(System.in);
        Scanner meteS = new Scanner(System.in);
        Scanner cantidad = new Scanner(System.in);
        double canti;
        Scanner repetidor = new Scanner(System.in);


        do{
            System.out.println("introduce la moneda en la que estás: ");
            System.out.println(" Peso Argentina = ARS \n Peso Mexico = MXN \n Dolar Americano = USD \n Dolar canadiense = CAD \n" +
                    " Euro = EUR \n En caso de quere salir digite 'salir' ");
            String monedaBase = meteB.nextLine();

                System.out.println("cuál es la cantidad que desea convertir");
                canti = cantidad.nextDouble();

            if (monedaBase.equalsIgnoreCase("salir")){
                break;
            } else {
                System.out.println("introduce la moneda a la que quieres convertir: ");
                System.out.println(" Peso Argentina = ARS \n Peso Mexico = MXN \n Dolar Americano = USD \n Dolar canadiense = CAD \n" +
                        " Euro = EUR \n En caso de quere salir digite 'salir' ");
                String monedaSolicitada = meteS.nextLine();


                if (monedaSolicitada.equalsIgnoreCase("salir")){ //con este if declaramos que cuando el usuario digite "salir" parará el bucle
                    //el ".equalsIgnoreCase" hace que no importe si son mayusculas o minusculas el como escribamos el salir, de igual manera funcionará
                    break;
                }else {
                    TraerMonedas envioMoneda= new TraerMonedas(monedaBase,monedaSolicitada, canti);
                    System.out.println("------------------------------------------------------------------");
                    System.out.println(envioMoneda); //modifiqué el .tostring en TraerMonedas
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("quiere continuar con alguna operacion? \n si o no?");
                    String condicional = repetidor.nextLine();
                    if (condicional.equalsIgnoreCase("no")){
                        break;
                    }else if (condicional.equalsIgnoreCase("si")){
                        System.out.println("continuemos");
                    }
                }
            }
        }while(true);
    }
}
