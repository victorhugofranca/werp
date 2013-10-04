package br.com.sigen.werp.app.modulo.cadastro.produtofabricante;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.sigen.werp.app.crosscutting.BroadcastInterceptor;
import br.com.sigen.werp.app.crosscutting.SecurityInterceptor;

@Stateless
public class FabricanteBusiness {

	@PersistenceContext
	private EntityManager entityManager;

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public Fabricante save(Fabricante fabricante) {
		if (fabricante.getIdFabricante() != null) {
			return entityManager.merge(fabricante);
		} else {
			entityManager.persist(fabricante);
			return fabricante;
		}
	}

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public void delete(Object entity) {
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
	}

	public List<Fabricante> find(Integer pageIndex, Integer pageSize) {
		return entityManager
				.createQuery(
						"select fabricante from Fabricante fabricante order by fabricante.codigo",
						Fabricante.class).setFirstResult(pageIndex)
				.setMaxResults(pageSize).getResultList();
	}

	public Integer count() {
		Long count = (Long) entityManager.createQuery(
				"select count(fabricante) from Fabricante fabricante")
				.getSingleResult();
		return Integer.valueOf(count.toString());
	}

}
