//tag::allButDestinationVariable[]
//tag::allButActualSending[]
package rsocket;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
//end::allButActualSending[]
import lombok.extern.slf4j.Slf4j;
//tag::allButActualSending[]

@Configuration
@Slf4j
public class RSocketClientConfiguration {

  @Bean
  public ApplicationRunner sender(RSocketRequester.Builder requesterBuilder) {
    return args -> {
      //tag::sendGreeting[]
      RSocketRequester tcp = requesterBuilder.tcp("localhost", 7000);

      // ... send messages with RSocketRequester ...
      //end::allButActualSending[]
      tcp
        .route("greeting")
        .data("Hello RSocket!")
        .retrieveMono(String.class)
        .subscribe(response -> log.info("Got a response: " + response));
      //end::sendGreeting[]

      //end::allButDestinationVariable[]
      //tag::destinationVariable[]
      String who = "Craig";
      tcp
        .route("greeting/{name}", who)
        .data("Hello RSocket!")
        .retrieveMono(String.class)
        .subscribe(response -> log.info("Got a response: " + response));
      //end::destinationVariable[]
      //tag::allButDestinationVariable[]
      //tag::allButActualSending[]

    };
  }

}
//end::allButActualSending[]
//end::allButDestinationVariable[]
