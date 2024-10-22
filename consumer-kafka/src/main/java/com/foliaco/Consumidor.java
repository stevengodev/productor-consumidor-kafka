package com.foliaco;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class Consumidor {

    private KafkaConsumer<String, String> kafkaConsumidor;
    private static final String TOPIC = "topic-pedidos";
    private static final Logger logger = LogManager.getLogger(Consumidor.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    public Consumidor() {
        try {
            var conf = new Properties();
            conf.load(new FileReader("src/main/resources/consumer.properties"));
            this.kafkaConsumidor = new KafkaConsumer<>(conf);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    public void consumirMensajes(){

        kafkaConsumidor.subscribe(List.of(TOPIC));

        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumidor.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record : records) {
                String jsonPedido = record.value();
                // Deserializar el JSON a un objeto Pedido
                deserializarMensajeYConsumir(jsonPedido);
            }
        }

    }

    public void close() {
        this.kafkaConsumidor.close();
    }

    public void deserializarMensajeYConsumir(String jsonMensaje) {
        try {
            Pedido pedido = objectMapper.readValue(jsonMensaje, Pedido.class);
            logger.info("Pedido recibido = {}", pedido);
            logger.info(pedido.getNombre());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}

