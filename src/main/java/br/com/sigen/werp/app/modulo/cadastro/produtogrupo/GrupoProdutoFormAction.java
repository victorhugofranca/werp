package br.com.sigen.werp.app.modulo.cadastro.produtogrupo;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("grupoProdutoFormAction")
@RequestScoped
public class GrupoProdutoFormAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GrupoProduto instance;

	@EJB
	private GrupoProdutoBusiness grupoProdutoBusiness;

	public GrupoProdutoFormAction() {
	}

	public String edit() {
		grupoProdutoBusiness.save(instance);
		return "grupoProdutoList";
	}

	protected Logger getLogger(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Class for logger is required.");
		}
		return Logger.getLogger(clazz.getName());
	}

	public GrupoProduto getInstance() {
		return instance;
	}

	public void setInstance(GrupoProduto instance) {
		this.instance = instance;
	}

}
