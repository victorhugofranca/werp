package br.com.sigen.werp.app.modulo.cadastro.produto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

@Named("produtoSelectDialogAction")
@Stateless
public class ProdutoSelectDialogAction implements Serializable {

	private static final long serialVersionUID = 1L;

	public ProdutoSelectDialogAction() {
	}

	public void pesquisar() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);

		RequestContext.getCurrentInstance().openDialog(
				"/modulo/cadastro/produto/produtoSelectDialog", options, null);
	}

	public void select(Produto produto) {
		RequestContext.getCurrentInstance().closeDialog(produto);
	}

	protected Logger getLogger(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Class for logger is required.");
		}
		return Logger.getLogger(clazz.getName());
	}

}
