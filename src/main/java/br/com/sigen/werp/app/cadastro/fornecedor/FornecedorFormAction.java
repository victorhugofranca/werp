package br.com.sigen.werp.app.cadastro.fornecedor;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named("fornecedorFormAction")
@Stateless
public class FornecedorFormAction implements Serializable {

	private static final long serialVersionUID = 1L;

	private Fornecedor instance;

	@PersistenceContext
	private EntityManager entityManager;

	public FornecedorFormAction() {
		instance = new Fornecedor();
	}

	public String edit() {
		entityManager.merge(instance);
		return "fornecedorList";
	}

	protected Logger getLogger(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Class for logger is required.");
		}
		return Logger.getLogger(clazz.getName());
	}

	public Fornecedor getInstance() {
		return instance;
	}

	public void setInstance(Fornecedor instance) {
		this.instance = instance;
	}

}
