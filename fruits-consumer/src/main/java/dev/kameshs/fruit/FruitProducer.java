package dev.kameshs.fruit;

import dev.kameshs.fruit.data.Fruit;
import io.apicurio.registry.utils.serde.AbstractKafkaSerDe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FruitProducer {

  private final KafkaTemplate<String, Fruit> kafkaTemplate;

  public void send(Fruit payload) {
    log.info("Sending Payload {}", payload);
    try {
      kafkaTemplate.send("fruits", payload);
    } catch (Exception e) {
      log.error("Error",e);
    }
  }
}
