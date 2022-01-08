package rsocket;
import java.math.BigDecimal;
import java.time.Duration;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Configuration
@Slf4j
public class RSocketClientConfiguration {

	@Bean
	public ApplicationRunner sender(RSocketRequester.Builder requesterBuilder) {
		return args -> {
			//tag::sendOverChannel[]
			RSocketRequester tcp = requesterBuilder.tcp("localhost", 7000);
			
			Flux<GratuityIn> gratuityInFlux = 
					Flux.fromArray(new GratuityIn[] {
							new GratuityIn(new BigDecimal(35.50), 18),
							new GratuityIn(new BigDecimal(10.00), 15),
							new GratuityIn(new BigDecimal(23.25), 20),
							new GratuityIn(new BigDecimal(52.75), 18),
							new GratuityIn(new BigDecimal(80.00), 15)
					})
					.delayElements(Duration.ofSeconds(1));
			
					tcp
						.route("gratuity")
						.data(gratuityInFlux)
						.retrieveFlux(GratuityOut.class)
						.subscribe(out -> 
							log.info(out.getPercent() + "% gratuity on "
									+ out.getBillTotal() + " is " 
									+ out.getGratuity()));
			//end::sendOverChannel[]
		};
	}
	
}
