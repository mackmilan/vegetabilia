package br.com.sapientia.vegetabilia.controllers;

import br.com.sapientia.vegetabilia.dao.MedidaDAO;
import br.com.sapientia.vegetabilia.dao.ProdutoDAO;
import br.com.sapientia.vegetabilia.dao.TipoProdutoDAO;
import br.com.sapientia.vegetabilia.models.Medida;
import br.com.sapientia.vegetabilia.models.Produto;
import br.com.sapientia.vegetabilia.models.TipoProduto;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class ProdutoController {
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();
	private MedidaDAO medidaDAO = new MedidaDAO();

	public Table grid;

	public FormLayout fl;

	public TextField nome;
	public TextField quantidade;
	public ComboBox tipoProduto;
	public ComboBox medida;
	public Button saveBtn;
	public Button editBtn;
	public Button deleteBtn;

	private Produto produto;

	public Table buildGrid() {
		grid = new Table();
		grid.setImmediate(true);
		grid.setWidth("100.0%");
		grid.setHeight("100.0%");
		grid.setSelectable(true);
		grid.addValueChangeListener(selectGrid());
		grid.setColumnCollapsingAllowed(true);
		grid.setContainerDataSource(produtoDAO.listar());

		grid.setVisibleColumns(new String[] { "codProduto", "nome",
				"tipo.nome", "medida.nome", "quantidade" });
		grid.setColumnHeaders(new String[] { "Codigo", "Nome", "tipo",
				"medida", "quantidade" });

		return grid;
	}

	public FormLayout buildForm() {
		fl = new FormLayout();
		fl.setWidth("100%");

		nome = new TextField("Nome:");
		nome.setWidth("100%");
		nome.setNullRepresentation("");
		fl.addComponent(nome);

		quantidade = new TextField("Quantidade:");
		quantidade.setWidth("100%");
		quantidade.setNullRepresentation("");
		fl.addComponent(quantidade);

		tipoProduto = new ComboBox("Tipo de produto:", tipoProdutoDAO.listar());
		tipoProduto.setWidth("100%");
		tipoProduto.setNullSelectionAllowed(false);
		tipoProduto.setItemCaptionPropertyId("nome");
		tipoProduto.setInputPrompt("-- Selecione um tipo de produto --");
		fl.addComponent(tipoProduto);

		medida = new ComboBox("Medida:", medidaDAO.listar());
		medida.setWidth("100%");
		medida.setNullSelectionAllowed(false);
		medida.setItemCaptionPropertyId("nome");
		medida.setInputPrompt("-- Selecione uma medida --");
		fl.addComponent(medida);

		HorizontalLayout hl = new HorizontalLayout();
		hl.setSizeUndefined();

		saveBtn = new Button("Salvar");
		saveBtn.addClickListener(salvar());
		hl.addComponent(saveBtn);

		editBtn = new Button("Editar");
		editBtn.setVisible(false);
		editBtn.addClickListener(editar());
		hl.addComponent(editBtn);

		deleteBtn = new Button("Excluir");
		deleteBtn.setVisible(false);
		deleteBtn.addClickListener(delete());
		hl.addComponent(deleteBtn);

		fl.addComponent(hl);

		return fl;
	}

	public ClickListener salvar() {
		return new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				Produto p = new Produto();
				p.setMedida((Medida) medida.getValue());
				p.setNome(nome.getValue());
				p.setQuantidade((Integer) Integer.parseInt(quantidade
						.getValue()));
				p.setTipo((TipoProduto) tipoProduto.getValue());

				try {
					produtoDAO.salvar(p);

					cleanForm();

					Notification.show("Produto salvo com sucesso",
							Type.HUMANIZED_MESSAGE);
				} catch (Exception e) {
					Notification.show("Erro ao salvar produto!!",
							Type.ERROR_MESSAGE);
				}
			}
		};
	}

	public ClickListener editar() {
		return new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (produto != null) {
					produto.setMedida((Medida) medida.getValue());
					produto.setNome(nome.getValue());
					produto.setQuantidade((Integer) Integer.parseInt(quantidade
							.getValue()));
					produto.setTipo((TipoProduto) tipoProduto.getValue());

					try {
						produtoDAO.editar(produto);

						cleanForm();
						refreshGrid();
						changeBtnState(1);

						Notification.show("Produto atualizado com sucesso",
								Type.HUMANIZED_MESSAGE);
					} catch (Exception e) {
						Notification.show("Erro ao atualizar produto!!",
								Type.ERROR_MESSAGE);
					}
				}
			}
		};
	}

	public ClickListener delete() {
		return new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (produto != null) {
					try {
						produtoDAO.excluir(produto);

						refreshGrid();
						cleanForm();
						changeBtnState(1);

						Notification.show("Produto excluido com sucesso",
								Type.HUMANIZED_MESSAGE);
					} catch (Exception e) {
						Notification.show("Erro ao excluir produto!!",
								Type.ERROR_MESSAGE);
					}
				}
			}
		};
	}

	public void cleanForm() {
		nome.setValue(null);
		medida.select(null);
		quantidade.setValue(null);
		tipoProduto.select(null);

		produto = null;
	}

	public ValueChangeListener selectGrid() {
		return new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (event.getProperty().getValue() != null) {
					produto = (Produto) event.getProperty().getValue();

					nome.setValue(produto.getNome());
					quantidade.setValue(produto.getQuantidade().toString());
					tipoProduto.select(produto.getTipo());
					medida.select(produto.getMedida());

					changeBtnState(0);
				} else {
					cleanForm();
					changeBtnState(1);
				}
			}
		};
	}

	public void changeBtnState(Integer state) {
		if (state == 1) {
			saveBtn.setVisible(true);
			editBtn.setVisible(false);
			deleteBtn.setVisible(false);
		} else {
			saveBtn.setVisible(false);
			editBtn.setVisible(true);
			deleteBtn.setVisible(true);
		}
	}

	public void refreshGrid() {
		grid.setContainerDataSource(produtoDAO.listar());

		grid.setVisibleColumns(new String[] { "codProduto", "nome",
				"tipo.nome", "medida.nome", "quantidade" });
		grid.setColumnHeaders(new String[] { "Codigo", "Nome", "tipo",
				"medida", "quantidade" });
	}
}
