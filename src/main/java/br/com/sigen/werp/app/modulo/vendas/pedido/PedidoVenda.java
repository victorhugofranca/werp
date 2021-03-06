package br.com.sigen.werp.app.modulo.vendas.pedido;

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
@Table(name = "vnd_pedido")
@SequenceGenerator(name = "sq_vnd_pedido", sequenceName = "sq_vnd_pedido", allocationSize = 1)
public class PedidoVenda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_pedido")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_vnd_pedido")
	private Integer idPedido;

	@Basic(optional = false)
	@Column(name = "ds_codigo", unique = true)
	private String codigo;

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
