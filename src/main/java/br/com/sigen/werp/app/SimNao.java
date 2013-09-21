package br.com.sigen.werp.app;

public enum SimNao {

	S("Sim"), N("Nao");

	private String valor;

	SimNao(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
	
	@Override
	public String toString() {
		return getValor();
	}

}
