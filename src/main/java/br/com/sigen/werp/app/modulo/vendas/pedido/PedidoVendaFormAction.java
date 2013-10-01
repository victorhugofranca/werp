package br.com.sigen.werp.app.modulo.vendas.pedido;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.sigen.werp.app.modulo.cadastro.produto.Produto;

@Named("pedidoFormAction")
@ConversationScoped
public class PedidoVendaFormAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private PedidoVendaBusiness pedidoBusiness;

	@Inject
	private Conversation conversation;

	private PedidoVenda instance;

	private List<PedidoVendaItem> itens;

	private String codigoProduto;

	public PedidoVendaFormAction() {
		instance = new PedidoVenda();
	}

	@PostConstruct
	public void init() {
		itens = new ArrayList<PedidoVendaItem>();
	}

	public void beginConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}

	public void endConversation() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	public String save() {
		pedidoBusiness.save(instance);
		endConversation();
		return "pedidoList";
	}

	public void addItem() {
		PedidoVendaItem pedidoItem = new PedidoVendaItem();
		pedidoItem.setIdPedidoItem(Integer.valueOf(codigoProduto));
		itens.add(pedidoItem);
	}

	public void onProductChosen(SelectEvent event) {
		Produto produto = (Produto) event.getObject();
		setCodigoProduto(produto.getCodigo());
	}

	protected Logger getLogger(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Class for logger is required.");
		}
		return Logger.getLogger(clazz.getName());
	}

	public PedidoVenda getInstance() {
		return instance;
	}

	public void setInstance(PedidoVenda instance) {
		this.instance = instance;
	}

	public List<PedidoVendaItem> getItens() {
		return itens;
	}

	public void setItens(List<PedidoVendaItem> itens) {
		this.itens = itens;
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

}
