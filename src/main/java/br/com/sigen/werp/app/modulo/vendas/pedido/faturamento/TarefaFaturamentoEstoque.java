package br.com.sigen.werp.app.modulo.vendas.pedido.faturamento;

public class TarefaFaturamentoEstoque extends TarefaFaturamento {

	@Override
	protected void execute() {
		System.out.println("Atualizando estoque...");
	}

}