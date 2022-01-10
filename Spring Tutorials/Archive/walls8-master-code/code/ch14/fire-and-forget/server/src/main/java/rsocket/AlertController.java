package rsocket;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class AlertController {

	@MessageMapping("alert")
	public Mono<Void> setAlert(Mono<Alert> alertMono) {
		return alertMono
			.doOnNext(alert -> {
				log.info(alert.getLevel() + " alert"
						+ " ordered by " + alert.getOrderedBy()
						+ " at " + alert.getOrderedAt());
			})
			.thenEmpty(Mono.empty());
	}
	
}
