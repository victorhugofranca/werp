package br.com.sigen.werp.app.modulo.vendas.orcamento;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.sigen.werp.app.AbstractListAction;

@Named("orcamentoListAction")
@Stateless
public class OrcamentoListAction extends AbstractListAction<Orcamento> {

	private static final long serialVersionUID = 1L;

	@EJB
	private OrcamentoBusiness orcamentoBusiness;

	@Override
	protected List<Orcamento> carregarRegistrosParaExibicao(int pageIndex,
			int pageSize) {
		return orcamentoBusiness.find(pageIndex, pageSize);
	}

	protected Map<String, String> getColumnsMap() {
		Map<String, String> columnsMap = new LinkedHashMap<String, String>();
		columnsMap.put("codigo", "Código");
		return columnsMap;
	}

	@Override
	protected int getTotalRegistros() {
		return orcamentoBusiness.count();
	}
}
