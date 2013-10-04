package br.com.sigen.werp.app.modulo.financeiro.titulo.receber;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.sigen.werp.app.crosscutting.BroadcastInterceptor;
import br.com.sigen.werp.app.crosscutting.SecurityInterceptor;

@Stateless
public class TituloReceberBusiness {

	@PersistenceContext
	private EntityManager entityManager;

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public TituloReceber save(TituloReceber titulo) {
		if (titulo.getIdTituloReceber() != null) {
			return entityManager.merge(titulo);
		} else {
			entityManager.persist(titulo);
			return titulo;
		}
	}

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public void delete(Object entity) {
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
	}

	public List<TituloReceber> find(Integer pageIndex, Integer pageSize) {
		return entityManager
				.createQuery(
						"select titulo from TituloReceber titulo order by titulo.idTituloReceber",
						TituloReceber.class).setFirstResult(pageIndex)
				.setMaxResults(pageSize).getResultList();
	}

	public Integer count() {
		Long count = (Long) entityManager.createQuery(
				"select count(titulo) from TituloReceber titulo")
				.getSingleResult();
		return Integer.valueOf(count.toString());
	}

}
