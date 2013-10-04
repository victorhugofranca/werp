package br.com.sigen.werp.app.modulo.cadastro.fornecedor;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("fornecedorFormAction")
@RequestScoped
public class FornecedorFormAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Fornecedor instance;

	@EJB
	private FornecedorBusiness fornecedorBusiness;

	public FornecedorFormAction() {
	}

	public String edit() {
		fornecedorBusiness.save(instance);
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
