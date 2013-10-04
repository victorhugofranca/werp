package br.com.sigen.werp.app.modulo.cadastro.produtogrupo;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.sigen.werp.app.crosscutting.BroadcastInterceptor;
import br.com.sigen.werp.app.crosscutting.SecurityInterceptor;

@Stateless
public class GrupoProdutoBusiness {

	@PersistenceContext
	private EntityManager entityManager;

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public GrupoProduto save(GrupoProduto grupoProduto) {
		if (grupoProduto.getIdGrupoProduto() != null) {
			return entityManager.merge(grupoProduto);
		} else {
			entityManager.persist(grupoProduto);
			return grupoProduto;
		}
	}

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public void delete(Object entity) {
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
	}

	public List<GrupoProduto> find(Integer pageIndex, Integer pageSize) {
		return entityManager
				.createQuery(
						"select grupoProduto from GrupoProduto grupoProduto order by grupoProduto.codigo",
						GrupoProduto.class).setFirstResult(pageIndex)
				.setMaxResults(pageSize).getResultList();
	}

	public Integer count() {
		Long count = (Long) entityManager.createQuery(
				"select count(grupoProduto) from GrupoProduto grupoProduto")
				.getSingleResult();
		return Integer.valueOf(count.toString());
	}

}
