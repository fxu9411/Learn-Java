package tacos.Messaging;


import javax.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import tacos.Model.Order;
import tacos.OrderReceiver;

@Component
public class JmsOrderReceiver implements OrderReceiver {

    private JmsTemplate jmsTemplate;
    private MessageConverter converter;

    @Autowired
    public JmsOrderReceiver(JmsTemplate jmsTemplate, MessageConverter converter) {
        this.jmsTemplate = jmsTemplate;
        this.converter = converter;
    }

    @Override
    public Order receiveOrder() {
        Message message = jmsTemplate.receive("tacocloud.order.queue");
        return (Order) converter.fromMessage(message);
    }
}
