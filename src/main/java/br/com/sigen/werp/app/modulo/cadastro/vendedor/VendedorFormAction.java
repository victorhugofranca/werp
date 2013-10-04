package br.com.sigen.werp.app.modulo.cadastro.vendedor;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("vendedorFormAction")
@RequestScoped
public class VendedorFormAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Vendedor instance;

	@EJB
	private VendedorBusiness vendedorBusiness;

	public VendedorFormAction() {
	}

	public String edit() {
		vendedorBusiness.save(instance);
		return "vendedorList";
	}

	protected Logger getLogger(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Class for logger is required.");
		}
		return Logger.getLogger(clazz.getName());
	}

	public Vendedor getInstance() {
		return instance;
	}

	public void setInstance(Vendedor instance) {
		this.instance = instance;
	}

}
