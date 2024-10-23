package com.foliaco;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static Productor productor = new Productor();
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {

        int contador = 0;

        for (int i = 0; i < 1_000_000; i++) {
            String nombrePedido = "Pedido_" + i;
            double precioPedido = Math.random() * 1000;
            Pedido pedido = new Pedido(nombrePedido, precioPedido);

            try {
                String jsonPedido = objectMapper.writeValueAsString(pedido);
                productor.send(jsonPedido);
                contador++;

            } catch (JsonProcessingException e) {
                logger.error(e.getMessage());
            }

        }

        productor.flush();

        if (contador % 10000 == 0) {
            System.out.println("Enviados " + contador + " mensajes.");
        }
        

    }

}