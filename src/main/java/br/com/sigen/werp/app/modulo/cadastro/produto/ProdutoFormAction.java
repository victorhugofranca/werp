package br.com.sigen.werp.app.modulo.cadastro.produto;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("produtoFormAction")
@RequestScoped
public class ProdutoFormAction implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto instance;

	@EJB
	private ProdutoBusiness produtoBusiness;

	public ProdutoFormAction() {
		instance = new Produto();
	}

	public String edit() {
		produtoBusiness.save(instance);
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
