package br.com.sigen.werp.app.modulo.cadastro.produtofabricante;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("fabricanteFormAction")
@RequestScoped
public class FabricanteFormAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Fabricante instance;

	@EJB
	private FabricanteBusiness fabricanteBusiness;

	public FabricanteFormAction() {
	}

	public String edit() {
		fabricanteBusiness.save(instance);
		return "fabricanteList";
	}

	protected Logger getLogger(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Class for logger is required.");
		}
		return Logger.getLogger(clazz.getName());
	}

	public Fabricante getInstance() {
		return instance;
	}

	public void setInstance(Fabricante instance) {
		this.instance = instance;
	}

}
