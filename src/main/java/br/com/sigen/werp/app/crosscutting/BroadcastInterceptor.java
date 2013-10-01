package br.com.sigen.werp.app.crosscutting;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class BroadcastInterceptor {

	@Inject
	@BroadcastMessage
	Event<String> jmsEvent;

	@AroundInvoke
	public Object broadcast(InvocationContext ctx) throws Exception {
		System.out.println("Broadcasting informations about operation...");
		jmsEvent.fire("Operação disparada:" + ctx.getMethod().getName());
		return ctx.proceed();
	}
}
