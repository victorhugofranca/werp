package br.com.sigen.werp.app.modulo.cadastro.vendedor;

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
@Table(name = "cad_vendedor")
@SequenceGenerator(name = "sq_cad_vendedor", allocationSize = 1)
public class Vendedor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_cad_vendedor")
	@Basic(optional = false)
	@Column(name = "id_vendedor")
	private Integer idVendedor;

	@Basic(optional = false)
	@Column(name = "ds_codigo")
	private String codigo;

	@Basic(optional = false)
	@Column(name = "ds_nome")
	private String nome;

	@Basic(optional = false)
	@Column(name = "sn_ativo")
	@Enumerated(EnumType.STRING)
	private SimNao ativo;

	public Vendedor() {
		ativo = SimNao.S;
	}

	public Integer getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(Integer idVendedor) {
		this.idVendedor = idVendedor;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public SimNao getAtivo() {
		return ativo;
	}

	public void setAtivo(SimNao ativo) {
		this.ativo = ativo;
	}

}
