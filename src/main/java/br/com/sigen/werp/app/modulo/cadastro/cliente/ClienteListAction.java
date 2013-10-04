package br.com.sigen.werp.app.modulo.cadastro.cliente;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.sigen.werp.app.AbstractListAction;

@Named("clienteListAction")
@Stateless
public class ClienteListAction extends AbstractListAction<Cliente> {

	private static final long serialVersionUID = 1L;

	@EJB
	private ClienteBusiness clienteBusiness;

	@Override
	public void delete(Object object) {
		clienteBusiness.delete(object);
	}

	@Override
	protected List<Cliente> carregarRegistrosParaExibicao(int pageIndex,
			int pageSize) {
		return clienteBusiness.find(pageIndex, pageSize);
	}

	protected Map<String, String> getColumnsMap() {
		Map<String, String> columnsMap = new LinkedHashMap<String, String>();
		columnsMap.put("codigo", "Código");
		columnsMap.put("razaoSocial", "Razão Social");
		columnsMap.put("ativo", "Ativo");
		return columnsMap;
	}

	@Override
	protected int getTotalRegistros() {
		return clienteBusiness.count();
	}
}
