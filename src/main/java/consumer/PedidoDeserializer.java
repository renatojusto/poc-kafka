package consumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Deserializer;
import producer.Pedido;

public class PedidoDeserializer implements Deserializer<Pedido> {

    private final Gson gson = new GsonBuilder().create();

    @Override
    public Pedido deserialize(String s, byte[] bytes) {
        return gson.fromJson(new String(bytes), Pedido.class);
    }
}
