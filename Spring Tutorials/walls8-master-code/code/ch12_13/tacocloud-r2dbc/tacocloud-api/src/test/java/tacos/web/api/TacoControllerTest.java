package tacos.web.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.data.TacoRepository;

@Disabled("TODO: For now...")
public class TacoControllerTest {

  @Test
  public void shouldReturnRecentTacos() {
    Taco[] tacos = {
        testTaco(1L), testTaco(2L),
        testTaco(3L), testTaco(4L),
        testTaco(5L), testTaco(6L),
        testTaco(7L), testTaco(8L),
        testTaco(9L), testTaco(10L),
        testTaco(11L), testTaco(12L),
        testTaco(13L), testTaco(14L),
        testTaco(15L), testTaco(16L)};
    Flux<Taco> tacoFlux = Flux.just(tacos);

    TacoRepository tacoRepo = Mockito.mock(TacoRepository.class);
    when(tacoRepo.findAll()).thenReturn(tacoFlux);

    WebTestClient testClient = WebTestClient.bindToController(
        new TacoController(tacoRepo, null))
        .build();

    testClient.get().uri("/api/tacos?recent")
      .exchange()
      .expectStatus().isOk()
      .expectBody()
        .jsonPath("$").isArray()
        .jsonPath("$").isNotEmpty()
        .jsonPath("$[0].id").isEqualTo(tacos[0].getId().toString())
        .jsonPath("$[0].name").isEqualTo("Taco 1")
        .jsonPath("$[1].id").isEqualTo(tacos[1].getId().toString())
        .jsonPath("$[1].name").isEqualTo("Taco 2")
        .jsonPath("$[11].id").isEqualTo(tacos[11].getId().toString())
        .jsonPath("$[11].name").isEqualTo("Taco 12")
        .jsonPath("$[12]").doesNotExist();
  }

  @Test
  public void shouldSaveATaco() {
    TacoRepository tacoRepo = Mockito.mock(
                TacoRepository.class);
    Mono<Taco> unsavedTacoMono = Mono.just(testTaco(null));
    Taco savedTaco = testTaco(null);
    Mono<Taco> savedTacoMono = Mono.just(savedTaco);

    when(tacoRepo.save(any())).thenReturn(savedTacoMono);

    WebTestClient testClient = WebTestClient.bindToController(
        new TacoController(tacoRepo, null)).build();

    testClient.post()
        .uri("/api/tacos")
        .contentType(MediaType.APPLICATION_JSON)
        .body(unsavedTacoMono, Taco.class)
      .exchange()
      .expectStatus().isCreated()
      .expectBody(Taco.class)
        .isEqualTo(savedTaco);
  }

  // tag::testTaco[]
  private Taco testTaco(Long number) {
    Taco taco = new Taco();
    taco.setId(number);
    taco.setName("Taco " + number);
    Set<Ingredient> ingredients = new HashSet<>();
    ingredients.add(
        new Ingredient("INGA", "Ingredient A", Type.WRAP));
    ingredients.add(
        new Ingredient("INGB", "Ingredient B", Type.PROTEIN));
    taco.addIngredient(new Ingredient("INGA", "Ingredient A", Type.WRAP));
    taco.addIngredient(new Ingredient("INGB", "Ingredient B", Type.PROTEIN));
    return taco;
  }
  // end::testTaco[]
}
