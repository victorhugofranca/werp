package br.com.sigen.werp.app.modulo.cadastro.produto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.sigen.werp.app.AbstractListAction;

@Named("produtoListAction")
@Stateless
public class ProdutoListAction extends AbstractListAction<Produto> {

	private static final long serialVersionUID = 1L;

	@EJB
	private ProdutoBusiness produtoBusiness;
	
	@Override
	public void delete(Object object) {
		produtoBusiness.delete(object);
	}

	@Override
	protected List<Produto> carregarRegistrosParaExibicao(int pageIndex,
			int pageSize) {
		return produtoBusiness.find(pageIndex, pageSize);
	}

	protected Map<String, String> getColumnsMap() {
		Map<String, String> columnsMap = new LinkedHashMap<String, String>();
		columnsMap.put("codigo", "Código");
		columnsMap.put("descricao", "Descrição");
		columnsMap.put("preco", "Preço");
		columnsMap.put("ativo", "Ativo");
		return columnsMap;
	}

	@Override
	protected int getTotalRegistros() {
		return produtoBusiness.count();
	}
}
