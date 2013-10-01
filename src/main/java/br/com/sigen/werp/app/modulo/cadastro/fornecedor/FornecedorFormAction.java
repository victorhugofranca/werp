package br.com.sigen.werp.app.modulo.cadastro.fornecedor;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

@Named("fornecedorFormAction")
@Stateless
public class FornecedorFormAction implements Serializable {

	private static final long serialVersionUID = 1L;

	private Fornecedor instance;

	@EJB
	private FornecedorBusiness fornecedorBusiness;

	public FornecedorFormAction() {
		instance = new Fornecedor();
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
