package com.foliaco;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Scanner;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);
    private static Productor productor = new Productor();
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long opcion = 1;

        do {
            System.out.println("Introduce la información del pedido:");

            System.out.print("Nombre del pedido: ");
            String nombrePedido = scanner.nextLine();

            System.out.print("Precio del pedido: ");
            double precioPedido = Double.parseDouble(scanner.nextLine());

            if (nombrePedido != null && precioPedido != 0) {
                Pedido pedido = new Pedido(nombrePedido, precioPedido);
                serializarYEnviarMensaje(pedido); // Serializar el objeto pedido a JSON y enviar mensaje
            }else {
                log.error("Error en la entrada de datos");
            }

            System.out.print("Para continuar presione 1, de caso contrario, presione otra tecla numerica diferente: ");
            opcion = Long.parseLong(scanner.nextLine());

        } while (opcion == 1);

        scanner.close();
    }

    public static void serializarYEnviarMensaje(Pedido pedido){

        try {
            String jsonPedido = objectMapper.writeValueAsString(pedido);
            productor.send(jsonPedido);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }


}