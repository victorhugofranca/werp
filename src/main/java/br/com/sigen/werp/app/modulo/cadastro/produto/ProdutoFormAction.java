package br.com.sigen.werp.app.modulo.cadastro.produto;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.sigen.werp.app.modulo.cadastro.produtofabricante.Fabricante;
import br.com.sigen.werp.app.modulo.cadastro.produtogrupo.GrupoProduto;

@Named("produtoFormAction")
@ConversationScoped
public class ProdutoFormAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Produto instance;

	@Inject
	private Conversation conversation;

	@EJB
	private ProdutoBusiness produtoBusiness;

	public ProdutoFormAction() {
	}

	@PostConstruct
	private void init() {
	}

	public String edit() {
		produtoBusiness.save(instance);
		endConversation();
		return "produtoList";
	}

	public void onGrupoProdutoChosen(SelectEvent event) {
		GrupoProduto grupoProduto = (GrupoProduto) event.getObject();
		getInstance().setGrupoProduto(grupoProduto);
	}

	public void onFabricanteChosen(SelectEvent event) {
		Fabricante fabricante = (Fabricante) event.getObject();
		getInstance().setFabricante(fabricante);
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

	protected Logger getLogger(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Class for logger is required.");
		}
		return Logger.getLogger(clazz.getName());
	}

	public Produto getInstance() {
		return instance;
	}

	public void setInstance(Produto instance) {
		this.instance = instance;
	}

}
