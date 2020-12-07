package consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import producer.Pedido;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class EstoqueCLI {

    public static void main(String[] args) {
        EstoqueCLI estoqueCLI = new EstoqueCLI();
        estoqueCLI.consumerNovoPedido();
    }

    public void consumerNovoPedido() {
        var consumer = new KafkaConsumer<String, Pedido>(getKafkaProperties());
        consumer.subscribe(Collections.singletonList("PEDIDO_NOVO_PEDIDO"));
        while (true) {
            var records = consumer.poll(Duration.ofMillis(100));
            records.forEach(record -> {
                System.out.println(record);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public Properties getKafkaProperties() {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, PedidoDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "ESTOQUE_GROUP_APP");
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
        return properties;
    }
}
