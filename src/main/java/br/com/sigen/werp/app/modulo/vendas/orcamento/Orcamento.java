package br.com.sigen.werp.app.modulo.vendas.orcamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sigen.werp.app.modulo.cadastro.cliente.Cliente;
import br.com.sigen.werp.app.modulo.cadastro.vendedor.Vendedor;

@Entity
@Table(name = "vnd_orcamento")
@SequenceGenerator(name = "sq_vnd_orcamento", sequenceName = "sq_vnd_orcamento", allocationSize = 1)
public class Orcamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_orcamento")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_vnd_orcamento")
	private Integer idOrcamento;

	@Basic(optional = false)
	@Column(name = "ds_codigo", unique = true)
	private String codigo;

	@Basic(optional = false)
	@Column(name = "vl_total", unique = true)
	private BigDecimal total;

	@Basic(optional = false)
	@Column(name = "dt_data", unique = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "id_vendedor")
	private Vendedor vendedor;

	@OneToMany(mappedBy = "orcamento", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<OrcamentoItem> orcamentoItens;

	public Orcamento() {
		this.orcamentoItens = new ArrayList<OrcamentoItem>();
		data = new Date();
		total = BigDecimal.ZERO;
	}

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<OrcamentoItem> getOrcamentoItens() {
		return orcamentoItens;
	}

	public void setOrcamentoItens(List<OrcamentoItem> orcamentoItens) {
		this.orcamentoItens = orcamentoItens;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

}
