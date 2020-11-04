package dev.kameshs.events;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.POJONode;
import dev.kameshs.FruitEntity;
import io.debezium.outbox.quarkus.ExportedEvent;
import java.time.Instant;

public class FruitCreatedEvent implements ExportedEvent<String, JsonNode> {

  private static final String TYPE = "Fruit";
  private static final String EVENT_TYPE = "FruitCreated";

  private final long fruitId;
  private final JsonNode jsonNode;
  private final Instant timestamp;

  public FruitCreatedEvent(Instant timestamp, FruitEntity fruitEntity) {
    this.fruitId = fruitEntity.id;
    this.jsonNode = convertToJson(fruitEntity);
    this.timestamp = timestamp;
  }

  @Override
  public String getAggregateId() {
    return String.valueOf(fruitId);
  }

  @Override
  public String getAggregateType() {
    return TYPE;
  }

  @Override
  public String getType() {
    return EVENT_TYPE;
  }

  @Override
  public Instant getTimestamp() {
    return timestamp;
  }

  @Override
  public JsonNode getPayload() {
    return jsonNode;
  }

  private JsonNode convertToJson(FruitEntity fruitEntity) {
    return new POJONode(fruitEntity);
  }
}
