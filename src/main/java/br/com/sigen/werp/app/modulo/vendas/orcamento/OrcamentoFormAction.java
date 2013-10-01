package br.com.sigen.werp.app.modulo.vendas.orcamento;

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

@Named("orcamentoFormAction")
@ConversationScoped
public class OrcamentoFormAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private OrcamentoBusiness orcamentoBusiness;

	@Inject
	private Conversation conversation;

	private Orcamento instance;

	private List<OrcamentoItem> itens;

	private String codigoProduto;

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

	public String save() {
		orcamentoBusiness.save(instance);
		endConversation();
		return "orcamentoList";
	}

	public void addItem() {
		OrcamentoItem orcamentoItem = new OrcamentoItem();
		orcamentoItem.setIdOrcamentoItem(Integer.valueOf(codigoProduto));
		itens.add(orcamentoItem);
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
