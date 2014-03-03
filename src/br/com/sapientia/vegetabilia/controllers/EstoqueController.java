package br.com.sapientia.vegetabilia.controllers;

import java.util.List;

import br.com.sapientia.vegetabilia.dao.ProdutoDAO;
import br.com.sapientia.vegetabilia.models.Produto;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class EstoqueController {

	private ProdutoDAO produtoDAO = new ProdutoDAO();

	public GridLayout gl;
	public List<Produto> lista = null;

	public GridLayout buildEstoque() {
		gl = new GridLayout(5, produtoDAO.listar().size());
		gl.setSizeFull();
		gl.setMargin(true);
		gl.setSpacing(true);

		lista = produtoDAO.listarEstoque();

		for (Produto p : lista) {
			gl.addComponent(buildPanel(p));
		}

		return gl;
	}

	public Panel buildPanel(Produto p) {
		Panel pan = new Panel();
		pan.setImmediate(true);
		pan.setWidth("100%");
		pan.setHeight("100%");
		pan.setCaption(p.getNome());

		VerticalLayout vl = new VerticalLayout();
		vl.setSizeUndefined();
		vl.setStyleName("vlProdutoEstoque");

		Label l1 = new Label("Tipo: " + p.getTipo().getNome());
		l1.setWidth("100%");
		vl.addComponent(l1);

		Label l2 = new Label("Qnt: " + p.getQuantidade() + " "
				+ p.getMedida().getNome() + "(s)");
		l2.setWidth("100%");
		vl.addComponent(l2);

		Label l3 = new Label("Peso: "
				+ (p.getMedida().getPeso() * p.getQuantidade()) + " Kg");
		l3.setWidth("100%");
		vl.addComponent(l3);

		Label l4 = new Label("Valor: R$ "
				+ (p.getMedida().getValor() * p.getQuantidade()));
		l4.setWidth("100%");
		vl.addComponent(l4);

		pan.setContent(vl);

		return pan;
	}
}
