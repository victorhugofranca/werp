package br.com.sigen.werp.app.cadastro.produto;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sigen.werp.app.SimNao;

@Entity
@Table(name = "cad_produto")
@SequenceGenerator(name = "sq_cad_produto")
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

}
