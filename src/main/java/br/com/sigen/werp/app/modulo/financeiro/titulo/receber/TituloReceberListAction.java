package br.com.sigen.werp.app.modulo.financeiro.titulo.receber;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.sigen.werp.app.AbstractListAction;

@Named("tituloReceberListAction")
@Stateless
public class TituloReceberListAction extends AbstractListAction<TituloReceber> {

	private static final long serialVersionUID = 1L;

	@EJB
	private TituloReceberBusiness tituloReceberBusiness;

	@Override
	protected List<TituloReceber> carregarRegistrosParaExibicao(int pageIndex,
			int pageSize) {
		return tituloReceberBusiness.find(pageIndex, pageSize);
	}

	protected Map<String, String> getColumnsMap() {
		Map<String, String> columnsMap = new LinkedHashMap<String, String>();
		columnsMap.put("vlOriginal", "Valor");
		return columnsMap;
	}

	@Override
	protected int getTotalRegistros() {
		return tituloReceberBusiness.count();
	}
}
