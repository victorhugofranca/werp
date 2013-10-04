package br.com.sigen.werp.app.modulo.vendas.pedido.faturamento;

import br.com.sigen.werp.app.modulo.vendas.pedido.PedidoVenda;

public class TarefaFaturamentoEstoque extends TarefaFaturamento {

	@Override
	protected void execute(PedidoVenda pedidoVenda) {
		System.out.println("Atualizando estoque...");
	}

}
