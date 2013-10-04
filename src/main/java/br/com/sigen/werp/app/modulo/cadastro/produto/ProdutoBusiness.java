package br.com.sigen.werp.app.modulo.cadastro.produto;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.sigen.werp.app.crosscutting.BroadcastInterceptor;
import br.com.sigen.werp.app.crosscutting.SecurityInterceptor;

@Stateless
public class ProdutoBusiness {

	@PersistenceContext
	private EntityManager entityManager;

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public Produto save(Produto produto) {
		if (produto.getIdProduto() != null) {
			return entityManager.merge(produto);
		} else {
			entityManager.persist(produto);
			return produto;
		}
	}

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public void delete(Object entity) {
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
	}

	public List<Produto> find(Integer pageIndex, Integer pageSize) {
		return entityManager
				.createQuery(
						"select produto from Produto produto order by produto.codigo",
						Produto.class).setFirstResult(pageIndex)
				.setMaxResults(pageSize).getResultList();
	}

	public Integer count() {
		Long count = (Long) entityManager.createQuery(
				"select count(produto) from Produto produto").getSingleResult();
		return Integer.valueOf(count.toString());
	}

}
