package br.com.sigen.werp.app.modulo.vendas.orcamento;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sigen.werp.app.modulo.cadastro.produto.Produto;

@Entity
@Table(name = "vnd_orcamento_item")
@SequenceGenerator(name = "sq_vnd_orcamento_item", allocationSize = 1)
public class OrcamentoItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_vnd_orcamento_item")
	@Basic(optional = false)
	@Column(name = "id_orcamento_item")
	private Integer idOrcamentoItem;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	@Basic(optional = false)
	@Column(name = "vl_preco")
	private BigDecimal preco;

	@Basic(optional = false)
	@Column(name = "vl_quantidade")
	private BigDecimal quantidade;

	@Basic(optional = false)
	@Column(name = "vl_total")
	private BigDecimal total;

	@ManyToOne
	@JoinColumn(name = "id_orcamento")
	private Orcamento orcamento;

	public Integer getIdOrcamentoItem() {
		return idOrcamentoItem;
	}

	public void setIdOrcamentoItem(Integer idOrcamentoItem) {
		this.idOrcamentoItem = idOrcamentoItem;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
