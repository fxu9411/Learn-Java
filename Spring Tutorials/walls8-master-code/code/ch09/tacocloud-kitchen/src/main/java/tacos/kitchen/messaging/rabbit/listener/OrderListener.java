//tag::RabbitOrderListener_noProfile[]
package tacos.kitchen.messaging.rabbit.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
//end::RabbitOrderListener_noProfile[]
import org.springframework.context.annotation.Profile;
//tag::RabbitOrderListener_noProfile[]
import org.springframework.stereotype.Component;

import tacos.TacoOrder;
import tacos.kitchen.KitchenUI;

//end::RabbitOrderListener_noProfile[]
@Profile("rabbitmq-listener")
//tag::RabbitOrderListener_noProfile[]
@Component
public class OrderListener {
  
  private KitchenUI ui;

  @Autowired
  public OrderListener(KitchenUI ui) {
    this.ui = ui;
  }

  @RabbitListener(queues = "tacocloud.order.queue")
  public void receiveOrder(TacoOrder order) {
    ui.displayOrder(order);
  }
  
}
//end::RabbitOrderListener_noProfile[]
