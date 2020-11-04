package dev.kameshs;

import dev.kameshs.fruit.data.Fruit;
import javax.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fruit")
public class FruitController {

  @Inject
  FruitService fruitService;

  @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity addFruit(@RequestBody Fruit payload) {
    fruitService.addFruit((FruitEntity) payload);
    return ResponseEntity.accepted()
                         .build();
  }
}
