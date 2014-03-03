package br.com.sapientia.vegetabilia.controllers;

import br.com.sapientia.vegetabilia.dao.TipoProdutoDAO;
import br.com.sapientia.vegetabilia.models.TipoProduto;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class TipoProdutoController {
	private TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();

	public Table grid;

	public FormLayout fl;

	public TextField nome;
	public Button saveBtn;
	public Button editBtn;
	public Button deleteBtn;

	private TipoProduto tipoProduto;

	public Table buildGrid() {
		// grid
		grid = new Table();
		grid.setImmediate(true);
		grid.setWidth("100.0%");
		grid.setHeight("100.0%");
		grid.setSelectable(true);
		grid.setColumnCollapsingAllowed(true);
		grid.setContainerDataSource(tipoProdutoDAO.listar());
		grid.addValueChangeListener(selectGrid());

		grid.setVisibleColumns(new String[] { "codTipoProduto", "nome" });

		grid.setColumnHeaders(new String[] { "Codigo", "Nome" });

		return grid;
	}

	public FormLayout buildForm() {
		fl = new FormLayout();
		fl.setWidth("100%");

		nome = new TextField("Nome:");
		nome.setWidth("100%");
		nome.setNullRepresentation("");
		fl.addComponent(nome);

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
				TipoProduto t = new TipoProduto();

				t.setNome(nome.getValue());

				try {
					tipoProdutoDAO.salvar(t);

					cleanForm();

					Notification.show("Tipo de produto salvo com sucesso",
							Type.HUMANIZED_MESSAGE);
				} catch (Exception e) {
					Notification.show("Erro ao salvar tipo de produto!!",
							Type.ERROR_MESSAGE);
				}
			}
		};
	}

	public ClickListener editar() {
		return new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (tipoProduto != null) {
					tipoProduto.setNome(nome.getValue());

					try {
						tipoProdutoDAO.editar(tipoProduto);

						cleanForm();
						refreshGrid();

						Notification.show(
								"Tipo de produto atualizado com sucesso",
								Type.HUMANIZED_MESSAGE);
					} catch (Exception e) {
						Notification.show(
								"Erro ao atualizar tipo de produto!!",
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
				if (tipoProduto != null) {
					try {
						tipoProdutoDAO.excluir(tipoProduto);

						refreshGrid();
						cleanForm();

						Notification.show(
								"Tipo de produto excluido com sucesso",
								Type.HUMANIZED_MESSAGE);
					} catch (Exception e) {
						Notification.show("Erro ao excluir tipo de produto!!",
								Type.ERROR_MESSAGE);
					}
				}
			}
		};
	}

	public void cleanForm() {
		nome.setValue(null);

		tipoProduto = null;

		changeBtnState(1);
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

	public ValueChangeListener selectGrid() {
		return new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (event.getProperty().getValue() != null) {
					tipoProduto = (TipoProduto) event.getProperty().getValue();

					nome.setValue(tipoProduto.getNome());

					changeBtnState(0);
				} else {
					cleanForm();
				}
			}
		};
	}

	public void refreshGrid() {
		grid.setContainerDataSource(tipoProdutoDAO.listar());

		grid.setVisibleColumns(new String[] { "codTipoProduto", "nome" });

		grid.setColumnHeaders(new String[] { "Codigo", "Nome" });
	}
}
