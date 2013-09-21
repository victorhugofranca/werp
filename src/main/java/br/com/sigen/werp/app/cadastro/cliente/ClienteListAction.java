package br.com.sigen.werp.app.cadastro.cliente;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.sigen.werp.app.AbstractListAction;

@Named("clienteListAction")
@Stateless
public class ClienteListAction extends AbstractListAction<Cliente> {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected List<Cliente> carregarRegistrosParaExibicao(int pageIndex,
			int pageSize) {
		return entityManager
				.createQuery(
						"select cliente from Cliente cliente order by cliente.codigo",
						Cliente.class).setFirstResult(pageIndex)
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
				"select count(cliente) from Cliente cliente").getSingleResult();
		return Integer.valueOf(count.toString());
	}
}
