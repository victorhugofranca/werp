package br.com.sigen.werp.app.modulo.vendas.pedido;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.sigen.werp.app.crosscutting.BroadcastInterceptor;
import br.com.sigen.werp.app.crosscutting.SecurityInterceptor;
import br.com.sigen.werp.app.modulo.vendas.pedido.faturamento.TarefaFaturamento;
import br.com.sigen.werp.app.modulo.vendas.pedido.faturamento.TarefaFaturamentoBasicoBuilder;
import br.com.sigen.werp.app.modulo.vendas.pedido.faturamento.TarefaFaturamentoBuilder;

@Stateless
public class PedidoVendaBusiness {

	@PersistenceContext
	private EntityManager entityManager;

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public void save(PedidoVenda pedido) {
		if (pedido.getIdPedido() != null) {
			entityManager.merge(pedido);
		} else {
			entityManager.persist(pedido);
		}
	}

	@Interceptors({ SecurityInterceptor.class, BroadcastInterceptor.class })
	public void faturar(PedidoVenda pedidoVenda) {
		TarefaFaturamentoBuilder tarefaFaturamentoBuilder = new TarefaFaturamentoBasicoBuilder();
		TarefaFaturamento tarefaFaturamento = tarefaFaturamentoBuilder.build();
		tarefaFaturamento.handleRequest(pedidoVenda);
	}

	public List<PedidoVenda> find(Integer pageIndex, Integer pageSize) {
		return entityManager
				.createQuery(
						"select pedido from PedidoVenda pedido order by pedido.codigo",
						PedidoVenda.class).setFirstResult(pageIndex)
				.setMaxResults(pageSize).getResultList();
	}

	public Integer count() {
		Long count = (Long) entityManager.createQuery(
				"select count(pedido) from PedidoVenda pedido")
				.getSingleResult();
		return Integer.valueOf(count.toString());
	}

}
