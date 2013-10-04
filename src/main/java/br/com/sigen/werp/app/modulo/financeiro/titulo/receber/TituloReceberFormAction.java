package br.com.sigen.werp.app.modulo.financeiro.titulo.receber;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("tituloReceberFormAction")
@RequestScoped
public class TituloReceberFormAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TituloReceber instance;

	@EJB
	private TituloReceberBusiness tituloReceberBusiness;

	public TituloReceberFormAction() {
	}

	public String edit() {
		tituloReceberBusiness.save(instance);
		return "tituloReceberList";
	}

	protected Logger getLogger(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Class for logger is required.");
		}
		return Logger.getLogger(clazz.getName());
	}

	public TituloReceber getInstance() {
		return instance;
	}

	public void setInstance(TituloReceber instance) {
		this.instance = instance;
	}

}
