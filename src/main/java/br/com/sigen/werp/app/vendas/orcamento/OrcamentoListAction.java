package br.com.sigen.werp.app.vendas.orcamento;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.sigen.werp.app.AbstractListAction;

@Named("orcamentoListAction")
@Stateless
public class OrcamentoListAction extends AbstractListAction<Orcamento> {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected List<Orcamento> carregarRegistrosParaExibicao(int pageIndex,
			int pageSize) {
		return entityManager
				.createQuery(
						"select orcamento from Orcamento orcamento order by orcamento.codigo",
						Orcamento.class).setFirstResult(pageIndex)
				.setMaxResults(pageSize).getResultList();
	}

	protected Map<String, String> getColumnsMap() {
		Map<String, String> columnsMap = new LinkedHashMap<String, String>();
		columnsMap.put("codigo", "Código");
		return columnsMap;
	}

	@Override
	protected int getTotalRegistros() {
		Long count = (Long) entityManager.createQuery(
				"select count(orcamento) from Orcamento orcamento")
				.getSingleResult();
		return Integer.valueOf(count.toString());
	}
}
