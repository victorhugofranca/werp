package br.com.sigen.werp.app.vendas.orcamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.primefaces.context.RequestContext;

import br.com.sigen.werp.app.cadastro.produto.Produto;

@Named("orcamentoFormAction")
@ConversationScoped
public class OrcamentoFormAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Conversation conversation;

	private Orcamento instance;

	private List<OrcamentoItem> itens;

	private String codigoProduto;

	@PersistenceContext
	private EntityManager entityManager;

	public OrcamentoFormAction() {
		instance = new Orcamento();
	}

	@PostConstruct
	public void init() {
		itens = new ArrayList<OrcamentoItem>();
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

	public String edit() {
		entityManager.merge(instance);
		return "orcamentoList";
	}

	public void pesquisarProdutos() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);

		RequestContext.getCurrentInstance().openDialog(
				"/cadastro/produto/produtoSelectDialog", options, null);
	}

	public void selectProductFromDialog(Produto produto) {
		RequestContext.getCurrentInstance().closeDialog(produto);
	}

	public void addItem() {
		OrcamentoItem orcamentoItem = new OrcamentoItem();
		orcamentoItem.setIdOrcamentoItem(Integer.valueOf(codigoProduto));
		itens.add(orcamentoItem);
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

	public List<OrcamentoItem> getItens() {
		return itens;
	}

	public void setItens(List<OrcamentoItem> itens) {
		this.itens = itens;
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

}
