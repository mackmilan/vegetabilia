package br.com.sapientia.vegetabilia.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import br.com.sapientia.vegetabilia.dao.EntradaDAO;
import br.com.sapientia.vegetabilia.dao.EntradaHasProdutoDAO;
import br.com.sapientia.vegetabilia.dao.ProdutoDAO;
import br.com.sapientia.vegetabilia.dao.UsuarioDAO;
import br.com.sapientia.vegetabilia.models.Entrada;
import br.com.sapientia.vegetabilia.models.EntradaHasProduto;
import br.com.sapientia.vegetabilia.models.Produto;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

@SuppressWarnings({ "serial", "rawtypes" })
public class EntradaController {
	private EntradaDAO entradaDAO = new EntradaDAO();
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private EntradaHasProdutoDAO entradaHasProdutoDAO = new EntradaHasProdutoDAO();

	public Table grid;

	public FormLayout fl;
	public TwinColSelect produtos;
	public ListSelect list;
	public Button addProduto;
	public Button saveBtn;
	public TextField qnt;
	public ComboBox produto;

	private List<Produto> produtoList = new ArrayList<Produto>();

	public Table buildGrid() {
		// grid
		grid = new Table();
		grid.setImmediate(true);
		grid.setWidth("100.0%");
		grid.setHeight("100.0%");
		grid.setColumnCollapsingAllowed(true);
		grid.setSelectable(true);
		grid.setContainerDataSource(entradaDAO.listar());

		grid.setVisibleColumns(new String[] { "codEntrada", "data",
				"usuario.nome" });

		grid.setColumnHeaders(new String[] { "codigo", "data", "usuario" });

		return grid;
	}

	public FormLayout buildForm() {
		fl = new FormLayout();
		fl.setWidth("100%");

		// produtos = new TwinColSelect("Produtos:");
		// produtos.setWidth("100%");
		// fl.addComponent(produtos);

		addProduto = new Button("Adicionar produto");
		addProduto.setWidth("100%");
		addProduto.addClickListener(addProdutoLista());
		fl.addComponent(addProduto);

		list = new ListSelect("Produtos:");
		list.setWidth("100%");
		list.setNullSelectionAllowed(false);
		fl.addComponent(list);

		HorizontalLayout hl = new HorizontalLayout();
		hl.setSizeUndefined();

		saveBtn = new Button("Salvar");
		saveBtn.addClickListener(salvar());
		hl.addComponent(saveBtn);

		fl.addComponent(hl);

		return fl;
	}

	public ClickListener salvar() {
		return new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				Entrada e = new Entrada();
				e.setData(new Date());
				e.setUsuario(usuarioDAO.obter(1));

				try {
					entradaDAO.salvar(e);

					for (Iterator i = list.getItemIds().iterator(); i.hasNext();) {
						Produto p = (Produto) i.next();

						EntradaHasProduto ehp = new EntradaHasProduto();
						ehp.setProduto(p);
						ehp.setEntrada(e);

						try {
							entradaHasProdutoDAO.salvar(ehp);
						} catch (Exception e2) {
							Notification.show("Erro ao salvar!!",
									Type.ERROR_MESSAGE);
						}
					}

					Notification.show("Entrada inserida com sucesso",
							Type.HUMANIZED_MESSAGE);
				} catch (Exception e2) {
					Notification.show("Erro ao inserir entrada!!",
							Type.ERROR_MESSAGE);
				}
			}
		};
	}

	public ClickListener addProdutoLista() {
		return new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				MessageBox.RESOURCE_FACTORY.setResourceLocale(new Locale("pt",
						"BR"));

				MessageBox.showCustomized(Icon.NONE, "Adicionar produto",
						vlMessageBox(), new MessageBoxListener() {

							@Override
							public void buttonClicked(ButtonId buttonId) {
								if (buttonId.toString().equals("mb_SAVE")) {
									Produto p = (Produto) produto.getValue();

									produtoList.add((Produto) produto
											.getValue());
									list.addItem(p);
									list.setItemCaption(p, p.getNome() + " - "
											+ qnt.getValue() + " "
											+ p.getMedida().getNome() + "(s)");
								}
							}

						}, ButtonId.SAVE, ButtonId.CANCEL).setWidth("40%");
			}
		};
	}

	public VerticalLayout vlMessageBox() {
		VerticalLayout vl = new VerticalLayout();
		vl.setSizeFull();
		vl.setMargin(true);

		FormLayout fl = new FormLayout();
		fl.setWidth("100%");

		produto = new ComboBox("Produto:");
		produto.setWidth("100%");
		produto.setContainerDataSource(produtoDAO.listar());
		produto.setItemCaptionPropertyId("nome");
		produto.setNullSelectionAllowed(false);
		produto.setInputPrompt("-- Selecione um produto --");
		fl.addComponent(produto);

		qnt = new TextField("Quantidade:");
		qnt.setWidth("100%");
		fl.addComponent(qnt);

		vl.addComponent(fl);

		return vl;
	}
}
