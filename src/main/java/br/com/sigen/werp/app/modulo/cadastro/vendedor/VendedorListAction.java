package br.com.sigen.werp.app.modulo.cadastro.vendedor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.sigen.werp.app.AbstractListAction;

@Named("vendedorListAction")
@Stateless
public class VendedorListAction extends AbstractListAction<Vendedor> {

	private static final long serialVersionUID = 1L;

	@EJB
	private VendedorBusiness vendedorBusiness;

	@Override
	public void delete(Object object) {
		vendedorBusiness.delete(object);
	}

	@Override
	protected List<Vendedor> carregarRegistrosParaExibicao(int pageIndex,
			int pageSize) {
		return vendedorBusiness.find(pageIndex, pageSize);
	}

	protected Map<String, String> getColumnsMap() {
		Map<String, String> columnsMap = new LinkedHashMap<String, String>();
		columnsMap.put("codigo", "Código");
		columnsMap.put("nome", "Nome");
		columnsMap.put("ativo", "Ativo");
		return columnsMap;
	}

	@Override
	protected int getTotalRegistros() {
		return vendedorBusiness.count();
	}
}
