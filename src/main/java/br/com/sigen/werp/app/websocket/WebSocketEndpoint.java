package br.com.sigen.werp.app.websocket;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import br.com.sigen.werp.app.crosscutting.BroadcastMessage;

@ServerEndpoint("/websocket")
@Singleton
public class WebSocketEndpoint implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger
			.getLogger(WebSocketEndpoint.class.getName());

	private List<Session> sessions;

	private List<String> mensagens;

	@PostConstruct
	public void setup() {
		mensagens = new ArrayList<String>();
		sessions = new ArrayList<Session>();
	}

	@OnOpen
	public void onOpen(Session session) {
		LOGGER.log(Level.INFO, "New connection with client: {0}",
				session.getId());

		if (!sessions.contains(session)) {
			sessions.add(session);
		}

		for (Iterator<String> iterator = mensagens.iterator(); iterator
				.hasNext();) {
			String msg = iterator.next();
			try {
				session.getBasicRemote().sendText(
						"<a href='url'>" + msg + "</a> ");
			} catch (IOException ex) {
				Logger.getLogger(WebSocketEndpoint.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}

	}

	@OnMessage
	public void onMessage(String message, Session session) {
		LOGGER.log(Level.INFO, "New message from Client [{0}]: {1}",
				new Object[] { session.getId(), message });

		if (message.equals("atualizar")) {
			for (Iterator<String> iterator = mensagens.iterator(); iterator
					.hasNext();) {
				String msg = iterator.next();
				try {
					session.getBasicRemote().sendText(
							"<a href='url'>" + msg + "</a> ");
				} catch (IOException ex) {
					Logger.getLogger(WebSocketEndpoint.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		}

	}

	@OnClose
	public void onClose(Session session) {
		LOGGER.log(Level.INFO, "Close connection for client: {0}",
				session.getId());
		sessions.remove(session);
	}

	@OnError
	public void onError(Throwable exception, Session session) {
		LOGGER.log(Level.INFO, "Error for client: {0}", session.getId());
	}

	public void onBroadcastMessage(@Observes @BroadcastMessage String msg) {
		try {
			mensagens.add(msg + mensagens.size());

			for (Iterator<Session> iterator = sessions.iterator(); iterator
					.hasNext();) {
				Session session = iterator.next();
				session.getBasicRemote().sendText(
						"<a href='produtoForm.faces'>" + msg
								+ (mensagens.size() - 1) + "</a> ");
			}

		} catch (IOException ex) {
			Logger.getLogger(WebSocketEndpoint.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}
}
