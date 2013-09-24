package br.com.sigen.werp.app.vendas.orcamento;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "vnd_orcamento")
@SequenceGenerator(name = "sq_vnd_orcamento", sequenceName = "sq_vnd_orcamento", allocationSize = 1, catalog = "public")
public class Orcamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_orcamento")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_vnd_orcamento")
	private Integer idOrcamento;

	@Basic(optional = false)
	@Column(name = "ds_codigo", unique = true)
	private String codigo;

	public Integer getIdOrcamento() {
		return idOrcamento;
	}

	public void setIdOrcamento(Integer idOrcamento) {
		this.idOrcamento = idOrcamento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
