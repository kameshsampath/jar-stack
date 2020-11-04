package dev.kameshs;

import dev.kameshs.events.FruitCreatedEvent;
import io.debezium.outbox.quarkus.ExportedEvent;
import java.time.Instant;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class FruitService {

  @Inject
  Event<ExportedEvent<?, ?>> event;

  @Inject
  FruitRepository fruitRepository;

  @Transactional
  public FruitEntity addFruit(FruitEntity fruitEntity) {
    fruitEntity = fruitRepository.save(fruitEntity);
    event.fire(new FruitCreatedEvent(Instant.now(), fruitEntity));
    return null;
  }
}
