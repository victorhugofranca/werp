package br.com.sigen.werp.app.cadastro.cliente;

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
@Table(name = "cad_cliente")
@SequenceGenerator(name = "sq_cad_cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_cad_cliente")
	@Basic(optional = false)
	@Column(name = "id_cliente")
	private Integer idCliente;

	@Basic(optional = false)
	@Column(name = "ds_codigo")
	private String codigo;

	@Basic(optional = false)
	@Column(name = "ds_razao_social")
	private String razaoSocial;

	@Basic(optional = false)
	@Column(name = "sn_ativo")
	@Enumerated(EnumType.STRING)
	private SimNao ativo;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public SimNao getAtivo() {
		return ativo;
	}

	public void setAtivo(SimNao ativo) {
		this.ativo = ativo;
	}

}
