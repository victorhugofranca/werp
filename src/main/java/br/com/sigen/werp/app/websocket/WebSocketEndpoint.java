package br.com.sigen.werp.app.websocket;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@Named
@ServerEndpoint("/websocket")
public class WebSocketEndpoint implements Serializable {

	private static final long serialVersionUID = 1L;

	// this object will hold all WebSocket sessions connected to this WebSocket
	// server endpoint (per JVM)
	private static final Set<Session> sessions = Collections
			.synchronizedSet(new HashSet<Session>());

	private QueueSenderSessionBean senderBean;

	@Inject
	public WebSocketEndpoint(QueueSenderSessionBean sb) {
		this.senderBean = sb;
	}

	@OnOpen
	public void onOpen(final Session session) {
		sessions.add(session);
	}

	@OnMessage
	public void onMessage(final String message, final Session client) {
		senderBean.sendMessage(message);
	}

	@OnClose
	public void onClose(final Session session) {
		sessions.remove(session);
	}

	public void onJMSMessage(@Observes @WSJMSMessage Message msg) {
		try {
			for (Session s : sessions) {
				s.getBasicRemote().sendText(
						"message from JMS: " + msg.getBody(String.class));
			}
		} catch (IOException | JMSException ex) {
			Logger.getLogger(WebSocketEndpoint.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}

}
