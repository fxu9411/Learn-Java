package tacos.messaging.jms;

import tacos.Order;

public interface OrderMessagingService {

    void sendOrder(Order order);

}
