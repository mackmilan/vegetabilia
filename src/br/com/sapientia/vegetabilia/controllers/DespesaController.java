package br.com.sapientia.vegetabilia.controllers;

import java.util.Locale;

import br.com.sapientia.vegetabilia.dao.DespesaDAO;
import br.com.sapientia.vegetabilia.models.Despesa;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class DespesaController {
	private DespesaDAO despesaDAO = new DespesaDAO();

	public Table grid;
	public FormLayout fl;

	public TextField nome;
	public TextArea descricao;
	public TextField valor;
	public PopupDateField data;
	public Button saveBtn;

	public Table buildGrid() {
		grid = new Table();
		grid.setImmediate(true);
		grid.setWidth("100.0%");
		grid.setHeight("100.0%");
		grid.setColumnCollapsingAllowed(true);
		grid.setSelectable(true);
		grid.setContainerDataSource(despesaDAO.listar());

		grid.setVisibleColumns(new String[] { "codDespesa", "nome", "data",
				"valor", "descricao" });

		grid.setColumnHeaders(new String[] { "Codigo", "nome", "data", "valor",
				"descrição" });

		return grid;
	}

	public FormLayout buildForm() {
		fl = new FormLayout();
		fl.setWidth("100%");

		nome = new TextField("Nome:");
		nome.setWidth("100%");
		nome.setNullRepresentation("");
		fl.addComponent(nome);

		valor = new TextField("Valor:");
		valor.setWidth("100%");
		valor.setNullRepresentation("");
		fl.addComponent(valor);

		data = new PopupDateField("Data:");
		data.setWidth("100%");
		data.setDateFormat("dd/MM/yyyy");
		data.setLocale(new Locale("pt", "BR"));
		data.setTextFieldEnabled(false);
		fl.addComponent(data);

		descricao = new TextArea("Descrição:");
		descricao.setWidth("100%");
		descricao.setNullRepresentation("");
		fl.addComponent(descricao);

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
				Despesa d = new Despesa();
				d.setData(data.getValue());
				d.setDescricao(descricao.getValue());
				d.setNome(nome.getValue());
				d.setValor((Double) Double.parseDouble(valor.getValue()));

				try {
					despesaDAO.salvar(d);

					cleanForm();

					Notification.show("Despesa lançada com sucesso",
							Type.HUMANIZED_MESSAGE);
				} catch (Exception e) {
					Notification.show("Erro ao lançar despesa!!",
							Type.ERROR_MESSAGE);
				}
			}
		};
	}

	public void cleanForm() {
		nome.setValue(null);
		data.setValue(null);
		descricao.setValue(null);
		valor.setValue(null);
	}
}
