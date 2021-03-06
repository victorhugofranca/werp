package br.com.sigen.werp.app.modulo.vendas.pedido.faturamento;

import br.com.sigen.werp.app.modulo.vendas.pedido.PedidoVenda;

public abstract class TarefaFaturamento {

	protected TarefaFaturamento next;

	abstract protected void execute(PedidoVenda pedidoVenda);

	public void handleRequest(PedidoVenda pedidoVenda) {
		execute(pedidoVenda);
		if (this.next != null)
			next.handleRequest(pedidoVenda);
	}

	protected void setNext(TarefaFaturamento next) {
		this.next = next;
	}

	protected TarefaFaturamento next() {
		return next;
	}

}
