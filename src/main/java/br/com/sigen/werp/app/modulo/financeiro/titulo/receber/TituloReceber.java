package br.com.sigen.werp.app.modulo.financeiro.titulo.receber;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "fin_titulo_receber")
@SequenceGenerator(name = "sq_fin_titulo_receber", allocationSize = 1)
public class TituloReceber implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_fin_titulo_receber")
	@Basic(optional = false)
	@Column(name = "id_titulo_receber")
	private Integer idTituloReceber;

	@Column(name = "vl_valor_original", nullable = false)
	private BigDecimal vlOriginal;

	public Integer getIdTituloReceber() {
		return idTituloReceber;
	}

	public void setIdTituloReceber(Integer idTituloReceber) {
		this.idTituloReceber = idTituloReceber;
	}

	public BigDecimal getVlOriginal() {
		return vlOriginal;
	}

	public void setVlOriginal(BigDecimal vlOriginal) {
		this.vlOriginal = vlOriginal;
	}

}
