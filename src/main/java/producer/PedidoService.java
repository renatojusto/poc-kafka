package producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import utils.GsonSerializer;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.UUID;

public class PedidoService {

    public void novoPedido() throws Exception {
        var producer = createProducer();
        var record = createRecord(createPedido());

        producer.send(record, createCallback()).get();
    }

    private Pedido createPedido() {
        Pedido pedido = new Pedido();
        pedido.setId(UUID.randomUUID().toString());
        pedido.setUsuarioId(UUID.randomUUID().toString());
        pedido.setValorTotal(BigDecimal.TEN);
        return pedido;
    }

    private Callback createCallback() {
        return (recordMetadata, e) ->
            System.out.println("Partition: " + recordMetadata.partition()
                                       + " - Offset: " + recordMetadata.offset()
                                       + " - Timestamp: " + recordMetadata.timestamp());
    }

    private ProducerRecord<String, Pedido> createRecord(Pedido pedido) {
        return new ProducerRecord<>("PEDIDO_NOVO_PEDIDO", pedido.getId(), pedido);
    }

    private KafkaProducer<String, Pedido> createProducer() {
        return new KafkaProducer<>(getKafkaProperties());
    }

    public Properties getKafkaProperties() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());
        return properties;
    }
}
