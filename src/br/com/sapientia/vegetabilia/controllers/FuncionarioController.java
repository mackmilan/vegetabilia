package br.com.sapientia.vegetabilia.controllers;

import br.com.sapientia.vegetabilia.dao.CidadeDAO;
import br.com.sapientia.vegetabilia.dao.EstadoDAO;
import br.com.sapientia.vegetabilia.dao.FuncionarioDAO;
import br.com.sapientia.vegetabilia.models.Cidade;
import br.com.sapientia.vegetabilia.models.Estado;
import br.com.sapientia.vegetabilia.models.Funcionario;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class FuncionarioController {

	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	private EstadoDAO estadoDAO = new EstadoDAO();
	private CidadeDAO cidadeDAO = new CidadeDAO();

	public Table grid;

	public FormLayout fl;

	public TextField nome;
	public TextField rg;
	public TextField cpf;
	public TextField fone;
	public TextField endereco;
	public TextField numero;
	public TextField bairro;
	public TextField cep;
	public ComboBox estado;
	public ComboBox cidade;
	public Button saveBtn;
	public Button editBtn;
	public Button deleteBtn;

	private Funcionario funcionario;

	public Table buildGrid() {
		// grid
		grid = new Table();
		grid.setImmediate(true);
		grid.setWidth("100.0%");
		grid.setHeight("100.0%");
		grid.setSelectable(true);
		grid.addValueChangeListener(selectGrid());
		grid.setColumnCollapsingAllowed(true);
		grid.setContainerDataSource(funcionarioDAO.listar());

		grid.setVisibleColumns(new String[] { "codFuncionario", "nome", "rg",
				"cpf", "fone", "endereco", "numero", "bairro", "cep",
				"cidade.nome", "cidade.estado.nome" });

		grid.setColumnHeaders(new String[] { "Codigo", "nome", "rg", "cpf",
				"telefone", "endereço", "numero", "bairro", "cep", "cidade",
				"estado" });

		return grid;
	}

	public FormLayout buildForm() {
		fl = new FormLayout();
		fl.setWidth("100%");

		nome = new TextField("Nome:");
		nome.setWidth("100%");
		nome.setNullRepresentation("");
		fl.addComponent(nome);

		rg = new TextField("RG:");
		rg.setWidth("100%");
		rg.setNullRepresentation("");
		fl.addComponent(rg);

		cpf = new TextField("CPF:");
		cpf.setWidth("100%");
		cpf.setNullRepresentation("");
		cpf.setStyleName("cpf");
		fl.addComponent(cpf);

		fone = new TextField("Telefone:");
		fone.setWidth("100%");
		fone.setNullRepresentation("");
		fone.setStyleName("fone");
		fl.addComponent(fone);

		endereco = new TextField("Endereço:");
		endereco.setWidth("100%");
		endereco.setNullRepresentation("");
		fl.addComponent(endereco);

		numero = new TextField("Numero:");
		numero.setWidth("100%");
		numero.setNullRepresentation("");
		fl.addComponent(numero);

		bairro = new TextField("Bairro:");
		bairro.setWidth("100%");
		bairro.setNullRepresentation("");
		fl.addComponent(bairro);

		cep = new TextField("CEP:");
		cep.setWidth("100%");
		cep.setNullRepresentation("");
		cep.setStyleName("cep");
		fl.addComponent(cep);

		estado = new ComboBox("Estado:", estadoDAO.listar());
		estado.setWidth("100%");
		estado.setNullSelectionAllowed(false);
		estado.setItemCaptionPropertyId("nome");
		estado.setInputPrompt("-- Selecione um estado --");
		estado.addValueChangeListener(obterCidade());
		fl.addComponent(estado);

		cidade = new ComboBox("Cidade:");
		cidade.setWidth("100%");
		cidade.setNullSelectionAllowed(false);
		cidade.setItemCaptionPropertyId("nome");
		cidade.setInputPrompt("-- Selecione um estado --");
		fl.addComponent(cidade);

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

	public ValueChangeListener obterCidade() {
		return new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (event.getProperty().getValue() != null) {
					Estado e = (Estado) event.getProperty().getValue();

					cidade.setContainerDataSource(cidadeDAO.listar(e
							.getCodEstado()));
					cidade.setInputPrompt("-- Selecione uma cidade --");
				}
			}
		};
	}

	public ClickListener salvar() {
		return new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				funcionario = new Funcionario();
				funcionario.setBairro(bairro.getValue());
				funcionario.setCep(cep.getValue());
				funcionario.setCidade((Cidade) cidade.getValue());
				funcionario.setCpf(cpf.getValue());
				funcionario.setEndereco(endereco.getValue());
				funcionario.setFone(fone.getValue());
				funcionario.setNome(nome.getValue());
				funcionario.setNumero(numero.getValue());
				funcionario.setRg(rg.getValue());

				try {
					funcionarioDAO.salvar(funcionario);

					cleanForm();
					refreshGrid();

					Notification.show("Funcionario salvo com sucesso",
							Type.HUMANIZED_MESSAGE);
				} catch (Exception e) {
					Notification.show("Erro ao salvar funcionário",
							Type.ERROR_MESSAGE);
				}
			}
		};
	}

	public ClickListener editar() {
		return new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (funcionario != null) {
					funcionario.setBairro(bairro.getValue());
					funcionario.setCep(cep.getValue());
					funcionario.setCidade((Cidade) cidade.getValue());
					funcionario.setCpf(cpf.getValue());
					funcionario.setEndereco(endereco.getValue());
					funcionario.setFone(fone.getValue());
					funcionario.setNome(nome.getValue());
					funcionario.setNumero(numero.getValue());
					funcionario.setRg(rg.getValue());

					try {
						funcionarioDAO.editar(funcionario);

						cleanForm();
						refreshGrid();
						changeBtnState(1);

						Notification.show("Funcionário atualizado com sucesso",
								Type.HUMANIZED_MESSAGE);
					} catch (Exception e) {
						Notification.show("Erro ao atualizar funcionário!!",
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
				if (funcionario != null) {
					try {
						funcionarioDAO.excluir(funcionario);

						refreshGrid();
						cleanForm();
						changeBtnState(1);

						Notification.show("Funcionario excluido com sucesso",
								Type.HUMANIZED_MESSAGE);
					} catch (Exception e) {
						Notification.show("Erro ao excluir cliente!!",
								Type.ERROR_MESSAGE);
					}
				}
			}
		};
	}

	public void cleanForm() {
		nome.setValue(null);
		rg.setValue(null);
		cpf.setValue(null);
		fone.setValue(null);
		endereco.setValue(null);
		numero.setValue(null);
		bairro.setValue(null);
		cep.setValue(null);
		estado.select(null);
		cidade.setContainerDataSource(null);
		cidade.setInputPrompt("-- Selecione um estado --");
	}

	public void refreshGrid() {
		grid.setContainerDataSource(funcionarioDAO.listar());

		grid.setVisibleColumns(new String[] { "codFuncionario", "nome", "rg",
				"cpf", "fone", "endereco", "numero", "bairro", "cep",
				"cidade.nome", "cidade.estado.nome" });

		grid.setColumnHeaders(new String[] { "Codigo", "nome", "rg", "cpf",
				"telefone", "endereço", "numero", "bairro", "cep", "cidade",
				"estado" });
	}

	public ValueChangeListener selectGrid() {
		return new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (event.getProperty().getValue() != null) {
					funcionario = (Funcionario) event.getProperty().getValue();

					nome.setValue(funcionario.getNome());
					rg.setValue(funcionario.getRg());
					cpf.setValue(funcionario.getCpf());
					fone.setValue(funcionario.getFone());
					endereco.setValue(funcionario.getEndereco());
					numero.setValue(funcionario.getNumero());
					bairro.setValue(funcionario.getBairro());
					cep.setValue(funcionario.getCep());
					estado.select(funcionario.getCidade().getEstado());
					cidade.select(funcionario.getCidade());

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

	public void initJs() {
		JavaScript.getCurrent().execute("masks()");
	}
}
