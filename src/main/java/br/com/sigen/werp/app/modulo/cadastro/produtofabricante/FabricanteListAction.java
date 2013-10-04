package br.com.sigen.werp.app.modulo.cadastro.produtofabricante;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.sigen.werp.app.AbstractListAction;

@Named("fabricanteListAction")
@Stateless
public class FabricanteListAction extends AbstractListAction<Fabricante> {

	private static final long serialVersionUID = 1L;

	@EJB
	private FabricanteBusiness fabricanteBusiness;

	@Override
	public void delete(Object object) {
		fabricanteBusiness.delete(object);
	}

	@Override
	protected List<Fabricante> carregarRegistrosParaExibicao(int pageIndex,
			int pageSize) {
		return fabricanteBusiness.find(pageIndex, pageSize);
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
		return fabricanteBusiness.count();
	}
}
