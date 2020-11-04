package dev.kameshs.fruit;

import dev.kameshs.fruit.data.Fruit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FruitConsumer {

  @KafkaListener(topics ="fruits",groupId ="${spring.kafka.consumer.group-id}" )
  public void send(Fruit message) {
    log.info("Received Payload {} on topic {} ", message,"fruits");
  }
}
