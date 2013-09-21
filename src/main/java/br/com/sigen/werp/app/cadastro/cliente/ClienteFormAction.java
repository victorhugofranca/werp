package br.com.sigen.werp.app.cadastro.cliente;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named("clienteFormAction")
@Stateless
public class ClienteFormAction implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente instance;

	@PersistenceContext
	private EntityManager entityManager;

	public ClienteFormAction() {
		instance = new Cliente();
	}

	public String edit() {
		entityManager.merge(instance);
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
