package br.com.sigen.werp.app.websocket;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.Message;
import javax.jms.MessageListener;

@Named
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/test") })
public class WebSocketMDB implements MessageListener {

	@Inject
	@WSJMSMessage
	private Event<Message> jmsEvent;

	@Override
	public void onMessage(Message msg) {
		jmsEvent.fire(msg);
	}
}
