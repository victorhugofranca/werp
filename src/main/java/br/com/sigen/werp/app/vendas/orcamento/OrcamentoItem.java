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
@Table(name = "vnd_orcamento_item")
@SequenceGenerator(name = "sq_vnd_orcamento_item")
public class OrcamentoItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_vnd_orcamento_item")
	@Basic(optional = false)
	@Column(name = "id_orcamento_item")
	private Integer idOrcamentoItem;

	public Integer getIdOrcamentoItem() {
		return idOrcamentoItem;
	}

	public void setIdOrcamentoItem(Integer idOrcamentoItem) {
		this.idOrcamentoItem = idOrcamentoItem;
	}

}
