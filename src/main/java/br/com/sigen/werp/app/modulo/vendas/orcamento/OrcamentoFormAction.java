package br.com.sigen.werp.app.modulo.vendas.orcamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.sigen.werp.app.MathOp;
import br.com.sigen.werp.app.modulo.cadastro.cliente.Cliente;
import br.com.sigen.werp.app.modulo.cadastro.produto.Produto;
import br.com.sigen.werp.app.modulo.cadastro.vendedor.Vendedor;

@Named("orcamentoFormAction")
@ConversationScoped
public class OrcamentoFormAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private OrcamentoBusiness orcamentoBusiness;

	@Inject
	private Conversation conversation;

	@Inject
	private Orcamento instance;

	@Inject
	private Produto produtoTemp;
	private BigDecimal precoItemTemp;
	private BigDecimal qtdItemTemp;

	private List<OrcamentoItem> selectedItens;

	public OrcamentoFormAction() {
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
		orcamentoBusiness.save(instance);
		endConversation();
		return "orcamentoList";
	}

	public void addItem() {
		OrcamentoItem orcamentoItem = buildNewOrcamentoItem();
		orcamentoBusiness.addItem(getInstance(), orcamentoItem);
	}

	public void deleteItem(OrcamentoItem orcamentoItem) {
		orcamentoBusiness.removeItem(getInstance(), orcamentoItem);
	}

	public void onProductChosen(SelectEvent event) {
		Produto produto = (Produto) event.getObject();
		setPrecoItemTemp(produto.getPreco());
		setProdutoTemp(produto);
	}

	public void onClientChosen(SelectEvent event) {
		Cliente cliente = (Cliente) event.getObject();
		instance.setCliente(cliente);
	}

	public void onVendedorChosen(SelectEvent event) {
		Vendedor vendedor = (Vendedor) event.getObject();
		instance.setVendedor(vendedor);
	}

	private OrcamentoItem buildNewOrcamentoItem() {
		OrcamentoItem orcamentoItem = new OrcamentoItem();
		orcamentoItem.setProduto(produtoTemp);
		orcamentoItem.setPreco(precoItemTemp);
		orcamentoItem.setQuantidade(qtdItemTemp);
		orcamentoItem.setTotal(MathOp.multiply(precoItemTemp, qtdItemTemp));
		orcamentoItem.setOrcamento(getInstance());
		return orcamentoItem;
	}

	protected Logger getLogger(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Class for logger is required.");
		}
		return Logger.getLogger(clazz.getName());
	}

	public Orcamento getInstance() {
		return instance;
	}

	public void setInstance(Orcamento instance) {
		this.instance = instance;
	}

	public Produto getProdutoTemp() {
		return produtoTemp;
	}

	public void setProdutoTemp(Produto produtoTemp) {
		this.produtoTemp = produtoTemp;
	}

	public BigDecimal getPrecoItemTemp() {
		return precoItemTemp;
	}

	public void setPrecoItemTemp(BigDecimal precoItemTemp) {
		this.precoItemTemp = precoItemTemp;
	}

	public BigDecimal getQtdItemTemp() {
		return qtdItemTemp;
	}

	public void setQtdItemTemp(BigDecimal qtdItemTemp) {
		this.qtdItemTemp = qtdItemTemp;
	}

	public List<OrcamentoItem> getSelectedItens() {
		return selectedItens;
	}

	public void setSelectedItens(List<OrcamentoItem> selectedItens) {
		this.selectedItens = selectedItens;
	}
}
