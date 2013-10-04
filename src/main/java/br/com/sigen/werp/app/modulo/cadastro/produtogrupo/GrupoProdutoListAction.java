package br.com.sigen.werp.app.modulo.cadastro.produtogrupo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.sigen.werp.app.AbstractListAction;

@Named("grupoProdutoListAction")
@Stateless
public class GrupoProdutoListAction extends AbstractListAction<GrupoProduto> {

	private static final long serialVersionUID = 1L;

	@EJB
	private GrupoProdutoBusiness grupoProdutoBusiness;

	@Override
	public void delete(Object object) {
		grupoProdutoBusiness.delete(object);
	}

	@Override
	protected List<GrupoProduto> carregarRegistrosParaExibicao(int pageIndex,
			int pageSize) {
		return grupoProdutoBusiness.find(pageIndex, pageSize);
	}

	protected Map<String, String> getColumnsMap() {
		Map<String, String> columnsMap = new LinkedHashMap<String, String>();
		columnsMap.put("codigo", "Código");
		columnsMap.put("descricao", "Descrição");
		columnsMap.put("ativo", "Ativo");
		return columnsMap;
	}

	@Override
	protected int getTotalRegistros() {
		return grupoProdutoBusiness.count();
	}
}
