package br.com.sigen.werp.app.modulo.cadastro.fornecedor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.sigen.werp.app.AbstractListAction;

@Named("fornecedorListAction")
@Stateless
public class FornecedorListAction extends AbstractListAction<Fornecedor> {

	private static final long serialVersionUID = 1L;

	@EJB
	private FornecedorBusiness fornecedorBusiness;

	@Override
	protected List<Fornecedor> carregarRegistrosParaExibicao(int pageIndex,
			int pageSize) {
		return fornecedorBusiness.find(pageIndex, pageSize);
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
		return fornecedorBusiness.count();
	}
}
