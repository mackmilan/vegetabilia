package br.com.sapientia.vegetabilia.controllers;

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

import br.com.sapientia.vegetabilia.dao.MedidaDAO;
import br.com.sapientia.vegetabilia.models.Medida;

@SuppressWarnings("serial")
public class MedidaController {
	private MedidaDAO medidaDAO = new MedidaDAO();

	public Table grid;

	public FormLayout fl;

	public TextField nome;
	public TextField peso;
	public TextField unidades;
	public TextField valor;
	public Button saveBtn;
	public Button editBtn;
	public Button deleteBtn;

	private Medida medida;

	public Table buildGrid() {
		// grid
		grid = new Table();
		grid.setImmediate(true);
		grid.setSelectable(true);
		grid.setColumnCollapsingAllowed(true);
		grid.addValueChangeListener(selectGrid());
		grid.setWidth("100.0%");
		grid.setHeight("100.0%");
		grid.setContainerDataSource(medidaDAO.listar());

		grid.setVisibleColumns(new String[] { "codMedida", "nome", "peso",
				"unidades", "valor" });
		grid.setColumnHeaders(new String[] { "Codigo", "Nome", "Peso",
				"Unidades", "Valor" });

		return grid;
	}

	public FormLayout buildForm() {
		fl = new FormLayout();
		fl.setWidth("100%");

		nome = new TextField("Nome:");
		nome.setWidth("100%");
		nome.setNullRepresentation("");
		fl.addComponent(nome);

		peso = new TextField("Peso:");
		peso.setWidth("100%");
		peso.setNullRepresentation("");
		fl.addComponent(peso);

		unidades = new TextField("Unidades:");
		unidades.setWidth("100%");
		unidades.setNullRepresentation("");
		fl.addComponent(unidades);

		valor = new TextField("Valor:");
		valor.setWidth("100%");
		valor.setNullRepresentation("");
		fl.addComponent(valor);

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
				Medida m = new Medida();
				m.setNome(nome.getValue());
				m.setPeso((Double) Double.parseDouble(peso.getValue()));
				m.setUnidades((Integer) Integer.parseInt(unidades.getValue()));
				m.setValor((Double) Double.parseDouble(valor.getValue()));

				try {
					medidaDAO.salvar(m);

					cleanForm();
					refreshGrid();

					Notification.show("Medida salva com sucesso",
							Type.HUMANIZED_MESSAGE);
				} catch (Exception e) {
					Notification.show("Erro ao cadastrar medida!!",
							Type.ERROR_MESSAGE);
				}
			}
		};
	}

	public ClickListener editar() {
		return new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (medida != null) {
					medida.setNome(nome.getValue());
					medida.setPeso((Double) Double.parseDouble(peso.getValue()));
					medida.setUnidades((Integer) Integer.parseInt(unidades
							.getValue()));
					medida.setValor((Double) Double.parseDouble(valor
							.getValue()));

					try {
						medidaDAO.editar(medida);

						cleanForm();
						refreshGrid();

						Notification.show("Medida atualizada com sucesso",
								Type.HUMANIZED_MESSAGE);
					} catch (Exception e) {
						Notification
								.show("Erro ao atualizar medida!!: "
										+ e.getMessage(), Type.ERROR_MESSAGE);
					}
				}
			}
		};
	}

	public ClickListener delete() {
		return new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (medida != null) {
					try {
						medidaDAO.excluir(medida);

						cleanForm();
						refreshGrid();

						Notification.show("Medida excluida com sucesso",
								Type.HUMANIZED_MESSAGE);
					} catch (Exception e) {
						Notification.show(
								"Erro ao excluir medida!!: " + e.getMessage(),
								Type.ERROR_MESSAGE);
					}
				}
			}
		};
	}

	public void cleanForm() {
		nome.setValue(null);
		peso.setValue(null);
		unidades.setValue(null);
		valor.setValue(null);

		medida = null;

		changeBtnState(1);
	}

	public void refreshGrid() {
		grid.setContainerDataSource(medidaDAO.listar());

		grid.setVisibleColumns(new String[] { "codMedida", "nome", "peso",
				"unidades", "valor" });
		grid.setColumnHeaders(new String[] { "Codigo", "Nome", "Peso",
				"Unidades", "Valor" });
	}

	public ValueChangeListener selectGrid() {
		return new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (event.getProperty().getValue() != null) {
					medida = (Medida) event.getProperty().getValue();

					nome.setValue(medida.getNome());
					peso.setValue(medida.getPeso().toString());
					unidades.setValue(medida.getUnidades().toString());
					valor.setValue(medida.getValor().toString());

					changeBtnState(0);
				} else {
					cleanForm();
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
}
