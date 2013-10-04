package br.com.sigen.werp.app.modulo.vendas.orcamento;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.sigen.werp.app.MathOp;
import br.com.sigen.werp.app.crosscutting.BroadcastInterceptor;
import br.com.sigen.werp.app.crosscutting.SecurityInterceptor;

@Stateless
public class OrcamentoBusiness {

	@PersistenceContext
	private EntityManager entityManager;

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public void save(Orcamento orcamento) {
		if (orcamento.getIdOrcamento() != null) {
			entityManager.merge(orcamento);
		} else {
			entityManager.persist(orcamento);
		}
	}

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public void delete(Object entity) {
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
	}

	public List<Orcamento> find(Integer pageIndex, Integer pageSize) {
		return entityManager
				.createQuery(
						"select orcamento from Orcamento orcamento order by orcamento.codigo",
						Orcamento.class).setFirstResult(pageIndex)
				.setMaxResults(pageSize).getResultList();
	}

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public void addItem(Orcamento orcamento, OrcamentoItem orcamentoItem) {
		orcamento.setTotal(MathOp.sum(orcamento.getTotal(),
				orcamentoItem.getTotal()));

		orcamento.getOrcamentoItens().add(orcamentoItem);
	}

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public void removeItem(Orcamento orcamento, OrcamentoItem orcamentoItem) {
		orcamento.setTotal(MathOp.subtract(orcamento.getTotal(),
				orcamentoItem.getTotal()));
		orcamento.getOrcamentoItens().remove(orcamentoItem);
	}

	public Integer count() {
		Long count = (Long) entityManager.createQuery(
				"select count(orcamento) from Orcamento orcamento")
				.getSingleResult();
		return Integer.valueOf(count.toString());
	}

}
