package br.com.sigen.werp.app.modulo.cadastro.fornecedor;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.sigen.werp.app.crosscutting.BroadcastInterceptor;
import br.com.sigen.werp.app.crosscutting.SecurityInterceptor;

@Stateless
public class FornecedorBusiness {

	@PersistenceContext
	private EntityManager entityManager;

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public void save(Fornecedor fornecedor) {
		if (fornecedor.getIdFornecedor() != null) {
			entityManager.merge(fornecedor);
		} else {
			entityManager.persist(fornecedor);
		}
	}

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public void delete(Object entity) {
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
	}

	public List<Fornecedor> find(Integer pageIndex, Integer pageSize) {
		return entityManager
				.createQuery(
						"select fornecedor from Fornecedor fornecedor order by fornecedor.codigo",
						Fornecedor.class).setFirstResult(pageIndex)
				.setMaxResults(pageSize).getResultList();
	}

	public Integer count() {
		Long count = (Long) entityManager.createQuery(
				"select count(fornecedor) from Fornecedor fornecedor")
				.getSingleResult();
		return Integer.valueOf(count.toString());
	}

}
