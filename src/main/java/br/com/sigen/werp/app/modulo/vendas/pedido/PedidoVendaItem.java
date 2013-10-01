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
@Table(name = "vnd_pedido_item")
@SequenceGenerator(name = "sq_vnd_pedido_item")
public class PedidoVendaItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_vnd_pedido_item")
	@Basic(optional = false)
	@Column(name = "id_pedido_item")
	private Integer idPedidoItem;

	public Integer getIdPedidoItem() {
		return idPedidoItem;
	}

	public void setIdPedidoItem(Integer idPedidoItem) {
		this.idPedidoItem = idPedidoItem;
	}

}
