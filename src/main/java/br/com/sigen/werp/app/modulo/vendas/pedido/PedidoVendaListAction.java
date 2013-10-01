package br.com.sigen.werp.app.modulo.vendas.pedido;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.sigen.werp.app.AbstractListAction;

@Named("pedidoListAction")
@Stateless
public class PedidoVendaListAction extends AbstractListAction<PedidoVenda> {

	private static final long serialVersionUID = 1L;

	@EJB
	private PedidoVendaBusiness pedidoBusiness;

	public void faturar(PedidoVenda pedidoVenda) {
		pedidoBusiness.faturar(pedidoVenda);
	}

	@Override
	protected List<PedidoVenda> carregarRegistrosParaExibicao(int pageIndex,
			int pageSize) {
		return pedidoBusiness.find(pageIndex, pageSize);
	}

	protected Map<String, String> getColumnsMap() {
		Map<String, String> columnsMap = new LinkedHashMap<String, String>();
		columnsMap.put("codigo", "Código");
		return columnsMap;
	}

	@Override
	protected int getTotalRegistros() {
		return pedidoBusiness.count();
	}
}
