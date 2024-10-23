package com.foliaco;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Productor {

    private KafkaProducer<String, String> kafkaProductor;
    private static final String TOPIC = "topic-pedidos";
    private static final Logger log = LogManager.getLogger(Productor.class);

    public Productor() {

        try{
            Properties conf = new Properties();
            conf.load(new FileReader("src/main/resources/producer.properties"));
            this.kafkaProductor = new KafkaProducer<>(conf);
        } catch (IOException ioe) {
            log.error(ioe.getMessage());
        }

    }

    public void send(String value) {
        try {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC, value);
            this.kafkaProductor.send(record);
        } catch (KafkaException e) {
            log.error(e.getMessage());
            this.kafkaProductor.close();
        }
    }

    public void flush(){
        this.kafkaProductor.flush();
    }
}
