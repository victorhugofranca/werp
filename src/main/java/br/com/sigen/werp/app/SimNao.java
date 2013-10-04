package br.com.sigen.werp.app;

public enum SimNao {

	S("Sim"), N("Não");

	private String valor;

	SimNao(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

}
