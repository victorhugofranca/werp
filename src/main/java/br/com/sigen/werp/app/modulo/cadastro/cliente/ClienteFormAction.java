package br.com.sigen.werp.app.modulo.cadastro.cliente;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("clienteFormAction")
@RequestScoped
public class ClienteFormAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Cliente instance;

	@EJB
	private ClienteBusiness clienteBusiness;

	public ClienteFormAction() {
	}

	public String edit() {
		clienteBusiness.save(instance);
		return "clienteList";
	}

	protected Logger getLogger(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Class for logger is required.");
		}
		return Logger.getLogger(clazz.getName());
	}

	public Cliente getInstance() {
		return instance;
	}

	public void setInstance(Cliente instance) {
		this.instance = instance;
	}

}
