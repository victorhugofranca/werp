package br.com.sigen.werp.app.menu;

import static br.com.sigen.werp.app.AttributeLabelConstantes.MENU_ITEM;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.Submenu;

@ManagedBean
@SessionScoped
public class MenuController implements Serializable {

	private static final long serialVersionUID = 1L;

	private MenuModel menuModel;

	public MenuController() {
		menuModel = new DefaultMenuModel();

		DefaultSubMenu submenuControleAcesso = gerarSubmenuControleAcesso();
		menuModel.addElement(submenuControleAcesso);

		Submenu submenuCadastros = gerarSubmenuCadastros();
		menuModel.addElement(submenuCadastros);
	}

	private DefaultSubMenu gerarSubmenuControleAcesso() {
		DefaultSubMenu submenuControleAcesso = new DefaultSubMenu(
				"Controle de Acesso");
		submenuControleAcesso.setId(MENU_ITEM + "ControleAcessoId");

		DefaultMenuItem mi = new DefaultMenuItem("Usuários");
		mi.setUrl("/usuario/usuarioList.faces");
		mi.setId(MENU_ITEM + "UsuariosId");
		submenuControleAcesso.addElement(mi);
		return submenuControleAcesso;
	}

	private Submenu gerarSubmenuCadastros() {
		DefaultSubMenu submenuCadastros = new DefaultSubMenu("Cadastros");
		submenuCadastros.setId(MENU_ITEM + "CadastrosId");

		submenuCadastros.addElement(createMenuItem("ProdutoId", "Produtos",
				"/cadastro/produto/produtoList.faces"));

		submenuCadastros.addElement(createMenuItem("ClienteId", "Clientes",
				"/cadastro/cliente/clienteList.faces"));

		submenuCadastros.addElement(createMenuItem("FornecedorId",
				"Fornecedores", "/cadastro/fornecedor/fornecedorList.faces"));

		submenuCadastros.addElement(createMenuItem("OrcamentoId", "Orçamentos",
				"/vendas/orcamento/orcamentoList.faces"));

		return submenuCadastros;
	}

	private MenuItem createMenuItem(String id, String value, String url) {
		DefaultMenuItem mi = new DefaultMenuItem(value);
		mi.setUrl(url);
		mi.setId(MENU_ITEM + id);
		return mi;
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

}
