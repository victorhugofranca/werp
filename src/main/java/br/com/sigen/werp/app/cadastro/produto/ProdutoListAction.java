package br.com.sigen.werp.app.cadastro.produto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.sigen.werp.app.AbstractListAction;

@Named("produtoListAction")
@Stateless
public class ProdutoListAction extends AbstractListAction<Produto> {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected List<Produto> carregarRegistrosParaExibicao(int pageIndex,
			int pageSize) {
		return entityManager
				.createQuery(
						"select produto from Produto produto order by produto.codigo",
						Produto.class).setFirstResult(pageIndex)
				.setMaxResults(pageSize).getResultList();
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
		Long count = (Long) entityManager.createQuery(
				"select count(produto) from Produto produto").getSingleResult();
		return Integer.valueOf(count.toString());
	}
}
