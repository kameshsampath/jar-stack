package dev.kameshs.fruit;

import dev.kameshs.fruit.data.Fruit;
import io.apicurio.registry.utils.serde.AbstractKafkaSerDe;
import io.apicurio.registry.utils.serde.AbstractKafkaSerializer;
import io.apicurio.registry.utils.serde.strategy.GetOrCreateIdStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FruitController {

  private final FruitProducer producer;

  @PostMapping("/publish")
  public void sendMessage(@RequestBody  Fruit fruit) {
    log.info("Sending message to fruits topic");
    producer.send(fruit);
  }

}
