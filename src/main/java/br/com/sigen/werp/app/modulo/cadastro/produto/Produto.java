package br.com.sigen.werp.app.modulo.cadastro.produto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sigen.werp.app.SimNao;
import br.com.sigen.werp.app.modulo.cadastro.produtofabricante.Fabricante;
import br.com.sigen.werp.app.modulo.cadastro.produtogrupo.GrupoProduto;

@Entity
@Table(name = "cad_produto")
@SequenceGenerator(name = "sq_cad_produto", allocationSize = 1)
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_cad_produto")
	@Basic(optional = false)
	@Column(name = "id_produto")
	private Integer idProduto;

	@Basic(optional = false)
	@Column(name = "ds_codigo")
	private String codigo;

	@Basic(optional = false)
	@Column(name = "ds_descricao")
	private String descricao;

	@Basic(optional = false)
	@Column(name = "sn_ativo")
	@Enumerated(EnumType.STRING)
	private SimNao ativo;

	@Basic(optional = false)
	@Column(name = "vl_preco")
	private BigDecimal preco;

	@ManyToOne
	@JoinColumn(name = "id_grupo_produto")
	private GrupoProduto grupoProduto;

	@ManyToOne
	@JoinColumn(name = "id_fabricante")
	private Fabricante fabricante;

	public Produto() {
		ativo = SimNao.S;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public SimNao getAtivo() {
		return ativo;
	}

	public void setAtivo(SimNao ativo) {
		this.ativo = ativo;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public GrupoProduto getGrupoProduto() {
		return grupoProduto;
	}

	public void setGrupoProduto(GrupoProduto grupoProduto) {
		this.grupoProduto = grupoProduto;
	}

}
