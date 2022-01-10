// tag::JmsOrderReceiver_noProfile[]
package tacos.kitchen.messaging.jms;

//end::JmsOrderReceiver_noProfile[]
import org.springframework.context.annotation.Profile;
//tag::JmsOrderReceiver_noProfile[]
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import tacos.TacoOrder;
import tacos.kitchen.OrderReceiver;

//end::JmsOrderReceiver_noProfile[]
@Profile("jms-template")
//tag::JmsOrderReceiver_noProfile[]
@Component
public class JmsOrderReceiver implements OrderReceiver {

  private JmsTemplate jms;

  public JmsOrderReceiver(JmsTemplate jms) {
    this.jms = jms;
  }
  
  @Override
  public TacoOrder receiveOrder() {
    return (TacoOrder) jms.receiveAndConvert("tacocloud.order.queue");
  }
  
}
//end::JmsOrderReceiver_noProfile[]
