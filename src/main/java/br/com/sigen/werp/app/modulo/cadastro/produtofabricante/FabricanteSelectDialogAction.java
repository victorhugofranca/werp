package br.com.sigen.werp.app.modulo.cadastro.produtofabricante;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

@Named("fabricanteSelectDialogAction")
@Stateless
public class FabricanteSelectDialogAction implements Serializable {

	private static final long serialVersionUID = 1L;

	public FabricanteSelectDialogAction() {
	}

	public void pesquisar() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);

		RequestContext.getCurrentInstance().openDialog(
				"/modulo/cadastro/fabricante/fabricanteSelectDialog", options,
				null);
	}

	public void select(Fabricante fabricante) {
		RequestContext.getCurrentInstance().closeDialog(fabricante);
	}

	protected Logger getLogger(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Class for logger is required.");
		}
		return Logger.getLogger(clazz.getName());
	}

}
