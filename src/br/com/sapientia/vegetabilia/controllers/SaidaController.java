package br.com.sapientia.vegetabilia.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import br.com.sapientia.vegetabilia.dao.ClienteDAO;
import br.com.sapientia.vegetabilia.dao.FuncionarioDAO;
import br.com.sapientia.vegetabilia.dao.ProdutoDAO;
import br.com.sapientia.vegetabilia.dao.SaidaDAO;
import br.com.sapientia.vegetabilia.dao.SaidaHasFuncionarioDAO;
import br.com.sapientia.vegetabilia.dao.SaidaHasProdutoDAO;
import br.com.sapientia.vegetabilia.dao.UsuarioDAO;
import br.com.sapientia.vegetabilia.models.Cliente;
import br.com.sapientia.vegetabilia.models.Funcionario;
import br.com.sapientia.vegetabilia.models.Produto;
import br.com.sapientia.vegetabilia.models.Saida;
import br.com.sapientia.vegetabilia.models.SaidaHasFuncionario;
import br.com.sapientia.vegetabilia.models.SaidaHasProduto;

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
import com.vaadin.ui.VerticalLayout;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

@SuppressWarnings({ "serial", "rawtypes" })
public class SaidaController {
	private SaidaDAO saidaDAO = new SaidaDAO();
	private ClienteDAO clienteDAO = new ClienteDAO();
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private SaidaHasProdutoDAO saidaHasProdutoDAO = new SaidaHasProdutoDAO();
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private SaidaHasFuncionarioDAO saidaHasFuncionarioDAO = new SaidaHasFuncionarioDAO();

	public Table grid;

	public FormLayout fl;

	public ComboBox cliente;
	public ListSelect produtosList;
	public ListSelect funcionariosList;
	public Button addProduto;
	public Button saveBtn;
	public ComboBox produto;
	public ComboBox funcionarios;
	public TextField qnt;
	public Button addFuncionario;

	private HashMap<Integer, Integer> qntList = new HashMap<Integer, Integer>();

	public Table buildGrid() {
		grid = new Table();
		grid.setImmediate(true);
		grid.setWidth("100.0%");
		grid.setHeight("100.0%");
		grid.setSelectable(true);
		grid.setContainerDataSource(saidaDAO.listar());

		grid.setVisibleColumns(new String[] { "codSaida", "data",
				"cliente.nome", "usuario.nome", "valor" });
		grid.setColumnHeaders(new String[] { "codigo", "data", "cliente",
				"usuario", "valor" });

		return grid;
	}

	public FormLayout buildForm() {
		fl = new FormLayout();
		fl.setWidth("100%");

		cliente = new ComboBox("Cliente:", clienteDAO.listar());
		cliente.setWidth("100%");
		cliente.setNullSelectionAllowed(false);
		cliente.setItemCaptionPropertyId("nome");
		cliente.setInputPrompt("-- Selecione um cliente --");
		fl.addComponent(cliente);

		produtosList = new ListSelect("Produtos:");
		produtosList.setWidth("100%");
		produtosList.setNullSelectionAllowed(false);
		fl.addComponent(produtosList);

		addProduto = new Button("Adicionar produto");
		addProduto.setWidth("100%");
		addProduto.addClickListener(adicionarProdutoLista());
		fl.addComponent(addProduto);

		funcionariosList = new ListSelect("Funcionarios:");
		funcionariosList.setNullSelectionAllowed(false);
		funcionariosList.setWidth("100%");
		fl.addComponent(funcionariosList);

		addFuncionario = new Button("Adicionar funcionario");
		addFuncionario.setWidth("100%");
		addFuncionario.addClickListener(adicionarFuncionarioLista());
		fl.addComponent(addFuncionario);

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
				Double valor = 0.0;

				Saida s = new Saida();
				s.setData(new Date());
				s.setUsuario(usuarioDAO.obter(1));
				s.setCliente((Cliente) cliente.getValue());

				for (Iterator i = produtosList.getItemIds().iterator(); i
						.hasNext();) {
					Produto p = (Produto) i.next();

					valor += (p.getMedida().getValor() * qntList.get(p
							.getCodProduto()));
				}

				s.setValor(valor);

				try {
					saidaDAO.salvar(s);

					for (Iterator i = produtosList.getItemIds().iterator(); i
							.hasNext();) {
						Produto p = (Produto) i.next();

						SaidaHasProduto shp = new SaidaHasProduto();
						shp.setProduto(p);
						shp.setQnt(qntList.get(p.getCodProduto()));
						shp.setSaida(s);

						try {
							saidaHasProdutoDAO.salvar(shp);

							p.setQuantidade(p.getQuantidade()
									- qntList.get(p.getCodProduto()));

							produtoDAO.editar(p);
						} catch (Exception e) {
							Notification.show("Erro", Type.ERROR_MESSAGE);
						}
					}

					for (Iterator i = funcionariosList.getItemIds().iterator(); i
							.hasNext();) {
						Funcionario f = (Funcionario) i.next();

						SaidaHasFuncionario shf = new SaidaHasFuncionario();
						shf.setFuncionario(f);
						shf.setSaida(s);

						try {
							saidaHasFuncionarioDAO.salvar(shf);
						} catch (Exception e) {
							Notification.show("Erro", Type.ERROR_MESSAGE);
						}
					}

					Notification.show("Saida registrada com sucesso",
							Type.HUMANIZED_MESSAGE);
				} catch (Exception e) {
					Notification.show("Erro ao salvar saida!!",
							Type.ERROR_MESSAGE);
				}
			}
		};
	}

	public ClickListener adicionarProdutoLista() {
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

									qntList.put(p.getCodProduto(),
											Integer.parseInt(qnt.getValue()));
									produtosList.addItem(p);
									produtosList.setItemCaption(p, p.getNome()
											+ " - " + qnt.getValue() + " "
											+ p.getMedida().getNome() + "(s)");
								}
							}
						}, ButtonId.SAVE, ButtonId.CANCEL).setWidth("40%");
			}
		};
	}

	public ClickListener adicionarFuncionarioLista() {
		return new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				MessageBox.RESOURCE_FACTORY.setResourceLocale(new Locale("pt",
						"BR"));

				MessageBox.showCustomized(Icon.NONE, "Adicionar funcionario",
						vlMessageBox2(), new MessageBoxListener() {

							@Override
							public void buttonClicked(ButtonId buttonId) {
								if (buttonId.toString().equals("mb_SAVE")) {
									Funcionario f = (Funcionario) funcionarios
											.getValue();

									funcionariosList.addItem(f);
									funcionariosList.setItemCaption(f,
											f.getNome());
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

		produto = new ComboBox("Produto:", produtoDAO.listar());
		produto.setWidth("100%");
		produto.setNullSelectionAllowed(false);
		produto.setInputPrompt("-- Selecione um produto --");
		produto.setItemCaptionPropertyId("nome");
		fl.addComponent(produto);

		qnt = new TextField("Quantidade:");
		qnt.setWidth("100%");
		fl.addComponent(qnt);

		vl.addComponent(fl);

		return vl;
	}

	public VerticalLayout vlMessageBox2() {
		VerticalLayout vl = new VerticalLayout();
		vl.setSizeFull();
		vl.setMargin(true);

		FormLayout fl = new FormLayout();
		fl.setWidth("100%");

		funcionarios = new ComboBox("Funcionario:", funcionarioDAO.listar());
		funcionarios.setWidth("100%");
		funcionarios.setNullSelectionAllowed(false);
		funcionarios.setInputPrompt("-- Selecione um funcionario --");
		funcionarios.setItemCaptionPropertyId("nome");
		fl.addComponent(funcionarios);

		vl.addComponent(fl);

		return vl;
	}
}
