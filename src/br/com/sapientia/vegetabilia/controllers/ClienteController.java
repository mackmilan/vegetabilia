package br.com.sapientia.vegetabilia.controllers;

import br.com.sapientia.vegetabilia.dao.CidadeDAO;
import br.com.sapientia.vegetabilia.dao.ClienteDAO;
import br.com.sapientia.vegetabilia.dao.EstadoDAO;
import br.com.sapientia.vegetabilia.models.Cidade;
import br.com.sapientia.vegetabilia.models.Cliente;
import br.com.sapientia.vegetabilia.models.Estado;

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
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ClienteController {
	public EstadoDAO estadoDAO = new EstadoDAO();
	public CidadeDAO cidadeDAO = new CidadeDAO();
	public ClienteDAO clienteDAO = new ClienteDAO();

	public FormLayout fl;

	public Table grid;

	public ComboBox cidade;
	public TextField nome;
	public TextField cpf_cnpj;
	public TextField fone;
	public TextField responsavel;
	public TextField foneResponsavel;
	public TextField endereco;
	public TextField numero;
	public TextField cep;
	public TextField bairro;
	public ComboBox estado;
	public Button saveBtn;
	public Button editBtn;
	public Button deleteBtn;

	private Cliente cliente;

	public Table buildGrid() {
		// grid
		grid = new Table();
		grid.setSelectable(true);
		grid.setColumnCollapsingAllowed(true);
		grid.setImmediate(true);
		grid.setWidth("100.0%");
		grid.setHeight("100.0%");
		grid.setContainerDataSource(clienteDAO.listar());
		grid.addValueChangeListener(selectGrid());

		grid.setVisibleColumns(new String[] { "codCliente", "nome", "cpf_cnpj",
				"fone", "responsavel", "foneResponsavel", "endereco", "numero",
				"cep", "bairro", "cidade.nome", "cidade.estado.nome" });
		grid.setColumnHeaders(new String[] { "Codigo", "Nome", "CPF/CNPJ",
				"Telefone", "Responsável", "Telefone Responsável", "Endereço",
				"Numero", "CEP", "Bairro", "Cidade", "Estado" });

		return grid;
	}

	public VerticalLayout buildForm() {
		// vlp2
		VerticalLayout vlp2 = new VerticalLayout();
		vlp2.setImmediate(false);
		vlp2.setWidth("100.0%");
		vlp2.setHeight("100.0%");
		vlp2.setMargin(true);

		fl = new FormLayout();
		fl.setWidth("100%");
		fl.setMargin(true);

		nome = new TextField("Nome:");
		nome.setNullRepresentation("");
		nome.setRequired(true);
		nome.setImmediate(true);
		nome.setRequiredError("Nome obrigatório!");
		nome.setWidth("100%");
		fl.addComponent(nome);

		cpf_cnpj = new TextField("CPF/CNPJ:");
		cpf_cnpj.setNullRepresentation("");
		cpf_cnpj.setRequired(true);
		cpf_cnpj.setImmediate(true);
		cpf_cnpj.setRequiredError("CPF/CNPJ obrigatório!");
		cpf_cnpj.setWidth("100%");
		fl.addComponent(cpf_cnpj);

		fone = new TextField("Telefone:");
		fone.setNullRepresentation("");
		fone.setRequired(true);
		fone.setImmediate(true);
		fone.setRequiredError("Telefone obrigatório!");
		fone.setWidth("100%");
		fone.setStyleName("fone");
		fl.addComponent(fone);

		responsavel = new TextField("Responsável:");
		responsavel.setNullRepresentation("");
		responsavel.setRequired(true);
		responsavel.setImmediate(true);
		responsavel.setRequiredError("Responsável obrigatório!");
		responsavel.setWidth("100%");
		fl.addComponent(responsavel);

		foneResponsavel = new TextField("Telefone do responsável:");
		foneResponsavel.setNullRepresentation("");
		foneResponsavel.setRequired(true);
		foneResponsavel.setImmediate(true);
		foneResponsavel
				.setRequiredError("Telefone do responsável obrigatório!");
		foneResponsavel.setStyleName("fone");
		foneResponsavel.setWidth("100%");
		fl.addComponent(foneResponsavel);

		endereco = new TextField("Endereço:");
		endereco.setNullRepresentation("");
		endereco.setRequired(true);
		endereco.setImmediate(true);
		endereco.setRequiredError("Endereço obrigatório!");
		endereco.setWidth("100%");
		fl.addComponent(endereco);

		numero = new TextField("Numero:");
		numero.setNullRepresentation("");
		numero.setRequired(true);
		numero.setImmediate(true);
		numero.setRequiredError("Numero obrigatório!");
		numero.setWidth("100%");
		fl.addComponent(numero);

		cep = new TextField("CEP:");
		cep.setNullRepresentation("");
		cep.setRequired(true);
		cep.setImmediate(true);
		cep.setRequiredError("Numero obrigatório!");
		cep.setWidth("100%");
		cep.setStyleName("cep");
		fl.addComponent(cep);

		bairro = new TextField("Bairro:");
		bairro.setNullRepresentation("");
		bairro.setRequired(true);
		bairro.setImmediate(true);
		bairro.setRequiredError("Bairro obrigatório!");
		bairro.setWidth("100%");
		fl.addComponent(bairro);

		estado = new ComboBox("Estado:", estadoDAO.listar());
		estado.setWidth("100%");
		estado.setRequiredError("Estado Obrigatório");
		estado.setRequired(true);
		estado.setImmediate(true);
		estado.setNullSelectionAllowed(false);
		estado.setItemCaptionPropertyId("nome");
		estado.setInputPrompt("-- Selecione um estado --");
		estado.addValueChangeListener(obterCidade());
		fl.addComponent(estado);

		cidade = new ComboBox("Cidade:");
		cidade.setWidth("100%");
		cidade.setRequiredError("Cidade Obrigatória");
		cidade.setRequired(true);
		cidade.setNullSelectionAllowed(false);
		cidade.setInputPrompt("-- Selecione um estado --");
		fl.addComponent(cidade);

		HorizontalLayout hl = new HorizontalLayout();
		hl.setSizeUndefined();

		saveBtn = new Button("Salvar");
		saveBtn.addClickListener(salvar());
		hl.addComponent(saveBtn);

		editBtn = new Button("Editar");
		editBtn.addClickListener(editar());
		editBtn.setVisible(false);
		hl.addComponent(editBtn);

		deleteBtn = new Button("Excluir");
		deleteBtn.addClickListener(delete());
		deleteBtn.setVisible(false);
		hl.addComponent(deleteBtn);

		fl.addComponent(hl);

		vlp2.addComponent(fl);

		return vlp2;
	}

	public ValueChangeListener obterCidade() {
		return new ValueChangeListener() {

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				if (event.getProperty().getValue() != null) {
					Estado estado = (Estado) event.getProperty().getValue();

					cidade.setContainerDataSource(cidadeDAO.listar(estado
							.getCodEstado()));
					cidade.setItemCaptionPropertyId("nome");
					cidade.setInputPrompt("-- Selecione uma cidade --");
				}
			}
		};
	}

	public ClickListener salvar() {
		return new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				Cliente c = new Cliente();
				c.setBairro(bairro.getValue());
				c.setCep(cep.getValue());
				c.setCidade((Cidade) cidade.getValue());
				c.setCpf_cnpj(cpf_cnpj.getValue());
				c.setEndereco(endereco.getValue());
				c.setFone(fone.getValue());
				c.setFoneResponsavel(foneResponsavel.getValue());
				c.setNome(nome.getValue());
				c.setNumero(numero.getValue());
				c.setResponsavel(responsavel.getValue());

				try {
					clienteDAO.salvar(c);

					cleanForm();
					refreshGrid();

					Notification.show("Cliente salvo com sucesso",
							Type.HUMANIZED_MESSAGE);
				} catch (Exception e) {
					Notification.show("Erro ao salvar cliente!!",
							Type.ERROR_MESSAGE);
				}
			}
		};
	}

	public ClickListener editar() {
		return new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (cliente != null) {
					cliente.setBairro(bairro.getValue());
					cliente.setCep(cep.getValue());
					cliente.setCidade((Cidade) cidade.getValue());
					cliente.setCpf_cnpj(cpf_cnpj.getValue());
					cliente.setEndereco(endereco.getValue());
					cliente.setFone(fone.getValue());
					cliente.setFoneResponsavel(foneResponsavel.getValue());
					cliente.setNome(nome.getValue());
					cliente.setNumero(numero.getValue());
					cliente.setResponsavel(responsavel.getValue());

					try {
						clienteDAO.editar(cliente);

						cleanForm();
						refreshGrid();

						changeBtnState(1);

						Notification.show("Cliente atualizado com sucesso",
								Type.HUMANIZED_MESSAGE);
					} catch (Exception e) {
						Notification.show("Erro ao atualizar cliente!!",
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
				if (cliente != null) {
					try {
						clienteDAO.excluir(cliente);

						refreshGrid();
						cleanForm();
						changeBtnState(1);

						Notification.show("Cliente excluido com sucesso",
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
		cpf_cnpj.setValue(null);
		fone.setValue(null);
		responsavel.setValue(null);
		foneResponsavel.setValue(null);
		endereco.setValue(null);
		numero.setValue(null);
		cep.setValue(null);
		bairro.setValue(null);
		estado.select(null);
		cidade.setContainerDataSource(null);

		cidade.setInputPrompt("-- Selecione um estado --");
	}

	public ValueChangeListener selectGrid() {
		return new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (event.getProperty().getValue() != null) {
					changeBtnState(0);

					cliente = (Cliente) event.getProperty().getValue();

					nome.setValue(cliente.getNome());
					cpf_cnpj.setValue(cliente.getCpf_cnpj());
					fone.setValue(cliente.getFone());
					responsavel.setValue(cliente.getResponsavel());
					foneResponsavel.setValue(cliente.getFoneResponsavel());
					endereco.setValue(cliente.getEndereco());
					numero.setValue(cliente.getNumero());
					cep.setValue(cliente.getCep());
					bairro.setValue(cliente.getBairro());
					estado.select(cliente.getCidade().getEstado());
					cidade.select(cliente.getCidade());
				} else {
					changeBtnState(1);

					cliente = null;

					cleanForm();
				}
			}
		};
	}

	public void refreshGrid() {
		grid.setContainerDataSource(clienteDAO.listar());

		grid.setVisibleColumns(new String[] { "codCliente", "nome", "cpf_cnpj",
				"fone", "responsavel", "foneResponsavel", "endereco", "numero",
				"cep", "bairro", "cidade.nome", "cidade.estado.nome" });
		grid.setColumnHeaders(new String[] { "Codigo", "Nome", "CPF/CNPJ",
				"Telefone", "Responsável", "Telefone Responsável", "Endereço",
				"Numero", "CEP", "Bairro", "Cidade", "Estado" });
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
