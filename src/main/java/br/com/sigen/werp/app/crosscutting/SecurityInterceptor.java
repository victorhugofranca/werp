package br.com.sigen.werp.app.crosscutting;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class SecurityInterceptor {

	@Resource
	private SessionContext sessionContext;

	@AroundInvoke
	public Object verifyAuthrization(InvocationContext ctx) throws Exception {
		System.out.println("Authorization checking for "
				+ sessionContext.getCallerPrincipal().getName() + "...");

		return ctx.proceed();
	}
}
