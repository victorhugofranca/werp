package br.com.sigen.werp.app.modulo.cadastro.cliente;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.sigen.werp.app.crosscutting.BroadcastInterceptor;
import br.com.sigen.werp.app.crosscutting.SecurityInterceptor;

@Stateless
public class ClienteBusiness {

	@PersistenceContext
	private EntityManager entityManager;

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public void save(Cliente cliente) {
		if (cliente.getIdCliente() != null) {
			entityManager.merge(cliente);
		} else {
			entityManager.persist(cliente);
		}
	}

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public void delete(Object entity) {
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
	}

	public List<Cliente> find(Integer pageIndex, Integer pageSize) {
		return entityManager
				.createQuery(
						"select cliente from Cliente cliente order by cliente.codigo",
						Cliente.class).setFirstResult(pageIndex)
				.setMaxResults(pageSize).getResultList();
	}

	public Integer count() {
		Long count = (Long) entityManager.createQuery(
				"select count(cliente) from Cliente cliente").getSingleResult();
		return Integer.valueOf(count.toString());
	}
}
