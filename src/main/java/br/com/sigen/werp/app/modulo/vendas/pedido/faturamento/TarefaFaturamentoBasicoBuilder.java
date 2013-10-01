package br.com.sigen.werp.app.modulo.vendas.pedido.faturamento;

public class TarefaFaturamentoBasicoBuilder implements TarefaFaturamentoBuilder {

	@Override
	public TarefaFaturamento build() {
		TarefaFaturamento tarefaFaturamento = new TarefaFaturamentoEstoque();
		tarefaFaturamento.setNext(new TarefaFaturamentoFinanceiro());
		return tarefaFaturamento;
	}

}
