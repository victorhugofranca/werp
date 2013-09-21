package br.com.sigen.werp.app.cadastro.produto;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named("produtoFormAction")
@Stateless
public class ProdutoFormAction implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto instance;

	@PersistenceContext
	private EntityManager entityManager;

	public ProdutoFormAction() {
		instance = new Produto();
	}

	public String edit() {
		entityManager.merge(instance);
		return "produtoList";
	}

	protected Logger getLogger(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Class for logger is required.");
		}
		return Logger.getLogger(clazz.getName());
	}

	public Produto getInstance() {
		return instance;
	}

	public void setInstance(Produto instance) {
		this.instance = instance;
	}

}
