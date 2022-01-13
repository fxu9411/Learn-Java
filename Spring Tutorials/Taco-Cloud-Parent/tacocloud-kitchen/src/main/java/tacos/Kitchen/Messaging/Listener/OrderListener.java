package tacos.Kitchen.Messaging.Listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import tacos.Model.KitchenUi;
import tacos.Model.Order;

@Component
public class OrderListener {
    private KitchenUi ui;

    @Autowired
    public OrderListener(KitchenUi ui) {
        this.ui = ui;
    }

    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(Order order) {
        ui.displayOrder(order);
    }
}
 