package br.com.sigen.werp.app.modulo.cadastro.produtogrupo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

@Named("grupoProdutoSelectDialogAction")
@Stateless
public class GrupoProdutoSelectDialogAction implements Serializable {

	private static final long serialVersionUID = 1L;

	public GrupoProdutoSelectDialogAction() {
	}

	public void pesquisar() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);

		RequestContext.getCurrentInstance().openDialog(
				"/modulo/cadastro/grupoProduto/grupoProdutoSelectDialog",
				options, null);
	}

	public void select(GrupoProduto grupoProduto) {
		RequestContext.getCurrentInstance().closeDialog(grupoProduto);
	}

	protected Logger getLogger(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Class for logger is required.");
		}
		return Logger.getLogger(clazz.getName());
	}

}
