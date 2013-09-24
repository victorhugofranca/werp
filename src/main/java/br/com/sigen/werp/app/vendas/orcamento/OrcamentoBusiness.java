package br.com.sigen.werp.app.vendas.orcamento;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OrcamentoBusiness {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Orcamento orcamento) {

		if (orcamento.getIdOrcamento() != null) {
			entityManager.merge(orcamento);
		} else {
			entityManager.persist(orcamento);
		}
	}
}
