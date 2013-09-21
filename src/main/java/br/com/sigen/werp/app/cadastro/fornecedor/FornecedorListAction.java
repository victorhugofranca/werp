package br.com.sigen.werp.app.cadastro.fornecedor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.sigen.werp.app.AbstractListAction;

@Named("fornecedorListAction")
@Stateless
public class FornecedorListAction extends AbstractListAction<Fornecedor> {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected List<Fornecedor> carregarRegistrosParaExibicao(int pageIndex,
			int pageSize) {
		return entityManager
				.createQuery(
						"select fornecedor from Fornecedor fornecedor order by fornecedor.codigo",
						Fornecedor.class).setFirstResult(pageIndex)
				.setMaxResults(pageSize).getResultList();
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
		Long count = (Long) entityManager.createQuery(
				"select count(fornecedor) from Fornecedor fornecedor").getSingleResult();
		return Integer.valueOf(count.toString());
	}
}
