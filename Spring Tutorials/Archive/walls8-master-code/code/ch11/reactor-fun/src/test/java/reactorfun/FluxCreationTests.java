package reactorfun;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxCreationTests {

  //tag::createAFlux_just_1[]
  @Test
  public void createAFlux_just() {
    Flux<String> fruitFlux = Flux
        .just("Apple", "Orange", "Grape", "Banana", "Strawberry");
    //end::createAFlux_just_1[]
    //tag::createAFlux_just_2[]
    StepVerifier.create(fruitFlux)
        .expectNext("Apple")
        .expectNext("Orange")
        .expectNext("Grape")
        .expectNext("Banana")
        .expectNext("Strawberry")
        .verifyComplete();
    //end::createAFlux_just_2[]
    //tag::createAFlux_just_1[]
  }
  //end::createAFlux_just_1[]

  //tag::createAFlux_fromArray[]
  @Test
  public void createAFlux_fromArray() {
    String[] fruits = new String[] {
        "Apple", "Orange", "Grape", "Banana", "Strawberry" };
    
    Flux<String> fruitFlux = Flux.fromArray(fruits);
    
    StepVerifier.create(fruitFlux)
        .expectNext("Apple")
        .expectNext("Orange")
        .expectNext("Grape")
        .expectNext("Banana")
        .expectNext("Strawberry")
        .verifyComplete();
  }
  //end::createAFlux_fromArray[]
  
  //tag::createAFlux_fromIterable[]
  @Test
  public void createAFlux_fromIterable() {
    List<String> fruitList = new ArrayList<>();
    fruitList.add("Apple");
    fruitList.add("Orange");
    fruitList.add("Grape");
    fruitList.add("Banana");
    fruitList.add("Strawberry");
    
    Flux<String> fruitFlux = Flux.fromIterable(fruitList);
    
    StepVerifier.create(fruitFlux)
        .expectNext("Apple")
        .expectNext("Orange")
        .expectNext("Grape")
        .expectNext("Banana")
        .expectNext("Strawberry")
        .verifyComplete();
  }
  //end::createAFlux_fromIterable[]

  //tag::createAFlux_fromStream[]
  @Test
   public void createAFlux_fromStream() {
     Stream<String> fruitStream = 
          Stream.of("Apple", "Orange", "Grape", "Banana", "Strawberry");
      
     Flux<String> fruitFlux = Flux.fromStream(fruitStream);
      
     StepVerifier.create(fruitFlux)
         .expectNext("Apple")
         .expectNext("Orange")
         .expectNext("Grape")
         .expectNext("Banana")
         .expectNext("Strawberry")
         .verifyComplete();
   }
  //end::createAFlux_fromStream[]

   
  //tag::createAFlux_interval[]
  @Test
  public void createAFlux_interval() {
    Flux<Long> intervalFlux = 
        Flux.interval(Duration.ofSeconds(1))
            .take(5);
    
    StepVerifier.create(intervalFlux)
       .expectNext(0L)
        .expectNext(1L)
        .expectNext(2L)
        .expectNext(3L)
        .expectNext(4L)
        .verifyComplete();
  }
  //end::createAFlux_interval[]

  //tag::createAFlux_range[]
  @Test
  public void createAFlux_range() {
    Flux<Integer> intervalFlux = 
        Flux.range(1, 5);
    
    StepVerifier.create(intervalFlux)
        .expectNext(1)
        .expectNext(2)
        .expectNext(3)
        .expectNext(4)
        .expectNext(5)
        .verifyComplete();
  }
  //end::createAFlux_range[]
  
}
