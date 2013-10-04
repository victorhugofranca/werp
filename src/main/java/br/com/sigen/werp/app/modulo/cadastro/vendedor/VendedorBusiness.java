package br.com.sigen.werp.app.modulo.cadastro.vendedor;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.sigen.werp.app.crosscutting.BroadcastInterceptor;
import br.com.sigen.werp.app.crosscutting.SecurityInterceptor;

@Stateless
public class VendedorBusiness {

	@PersistenceContext
	private EntityManager entityManager;

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public void save(Vendedor vendedor) {
		if (vendedor.getIdVendedor() != null) {
			entityManager.merge(vendedor);
		} else {
			entityManager.persist(vendedor);
		}
	}

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public void delete(Object entity) {
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
	}

	public List<Vendedor> find(Integer pageIndex, Integer pageSize) {
		return entityManager
				.createQuery(
						"select vendedor from Vendedor vendedor order by vendedor.codigo",
						Vendedor.class).setFirstResult(pageIndex)
				.setMaxResults(pageSize).getResultList();
	}

	public Integer count() {
		Long count = (Long) entityManager.createQuery(
				"select count(vendedor) from Vendedor vendedor")
				.getSingleResult();
		return Integer.valueOf(count.toString());
	}

}
