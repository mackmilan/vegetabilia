package br.com.sapientia.vegetabilia.views;

import br.com.sapientia.vegetabilia.controllers.MenuController;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

public class MainMenu extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private MenuBar menu;

	private MenuController controller = new MenuController();

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public MainMenu() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// menu
		menu = new MenuBar();
		menu.setImmediate(true);
		menu.setWidth("100.0%");
		menu.setHeight("-1px");

		MenuBar.MenuItem app = menu.addItem("Aplicativo", new ThemeResource(
				"resources/icons/cog.png"), null);
		app.addItem("Atualizar", new ThemeResource(
				"resources/icons/arrow_refresh.png"), controller.refresh());
		app.addItem("Internet", new ThemeResource("resources/icons/world.png"),
				controller.internet());

		MenuBar.MenuItem cliente = menu.addItem("Cliente", new ThemeResource(
				"resources/icons/group.png"), null);
		cliente.addItem("Cadastro", new ThemeResource(
				"resources/icons/group_go.png"), controller.clienteCadastro());

		MenuBar.MenuItem estoque = menu.addItem("Estoque", new ThemeResource(
				"resources/icons/lorry.png"), null);
		estoque.addItem("Entrada", new ThemeResource(
				"resources/icons/lorry_go.png"), controller.entrada());
		estoque.addItem("Saída", new ThemeResource("resources/icons/box.png"),
				controller.saida());
		estoque.addItem("Devolução", new ThemeResource(
				"resources/icons/arrow_undo.png"), null);
		estoque.addItem("Desperdício", new ThemeResource(
				"resources/icons/lorry_delete.png"), null);
		estoque.addItem("Visualizar", new ThemeResource(
				"resources/icons/eye.png"), controller.estoque());

		MenuItem produto = estoque.addItem("Produto", new ThemeResource(
				"resources/icons/basket.png"), null);
		produto.addItem("Cadastro", new ThemeResource(
				"resources/icons/basket_put.png"), controller.produto());
		produto.addItem("Tipo de produto", new ThemeResource(
				"resources/icons/basket_go.png"), controller.tipoProduto());
		produto.addItem("Medida",
				new ThemeResource("resources/icons/ruby.png"),
				controller.medida());

		MenuBar.MenuItem funcionario = menu.addItem("Funcionario",
				new ThemeResource("resources/icons/user_suit.png"), null);
		funcionario.addItem("Cadastro", new ThemeResource(
				"resources/icons/user_go.png"), controller.funcionario());
		funcionario.addItem("Despesa", new ThemeResource(
				"resources/icons/coins.png"), controller.despesa());

		MenuBar.MenuItem usuario = menu.addItem("Usuario", new ThemeResource(
				"resources/icons/user_gray.png"), null);
		usuario.addItem("Cadastro", new ThemeResource(
				"resources/icons/user_edit.png"), null);

		mainLayout.addComponent(menu, "top:0.0px;left:0.0px;");

		return mainLayout;
	}

}
