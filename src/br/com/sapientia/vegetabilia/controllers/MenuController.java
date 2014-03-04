package br.com.sapientia.vegetabilia.controllers;

import br.com.sapientia.vegetabilia.VegetabiliaUI;
import br.com.sapientia.vegetabilia.views.ClienteView;
import br.com.sapientia.vegetabilia.views.DespesaView;
import br.com.sapientia.vegetabilia.views.EntradaView;
import br.com.sapientia.vegetabilia.views.EstoqueView;
import br.com.sapientia.vegetabilia.views.FuncionarioView;
import br.com.sapientia.vegetabilia.views.HomeView;
import br.com.sapientia.vegetabilia.views.InternetView;
import br.com.sapientia.vegetabilia.views.MedidaView;
import br.com.sapientia.vegetabilia.views.ProdutoView;
import br.com.sapientia.vegetabilia.views.SaidaView;
import br.com.sapientia.vegetabilia.views.TipoProdutoView;

import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

@SuppressWarnings("serial")
public class MenuController {

	public Command refresh() {
		return new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				VegetabiliaUI.getCurrent().getNavigator()
						.navigateTo(HomeView.NAME);
			}
		};
	}

	public Command internet() {
		return new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				VegetabiliaUI.getCurrent().getNavigator()
						.navigateTo(InternetView.NAME);
			}
		};
	}

	public Command clienteCadastro() {
		return new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				VegetabiliaUI.getCurrent().getNavigator()
						.navigateTo(ClienteView.NAME);
			}
		};
	}

	public Command produto() {
		return new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				VegetabiliaUI.getCurrent().getNavigator()
						.navigateTo(ProdutoView.NAME);
			}
		};
	}

	public Command tipoProduto() {
		return new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				VegetabiliaUI.getCurrent().getNavigator()
						.navigateTo(TipoProdutoView.NAME);
			}
		};
	}

	public Command medida() {
		return new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				VegetabiliaUI.getCurrent().getNavigator()
						.navigateTo(MedidaView.NAME);
			}
		};
	}

	public Command funcionario() {
		return new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				VegetabiliaUI.getCurrent().getNavigator()
						.navigateTo(FuncionarioView.NAME);
			}
		};
	}

	public Command estoque() {
		return new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				VegetabiliaUI.getCurrent().getNavigator()
						.navigateTo(EstoqueView.NAME);
			}
		};
	}

	public Command despesa() {
		return new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				VegetabiliaUI.getCurrent().getNavigator()
						.navigateTo(DespesaView.NAME);
			}
		};
	}

	public Command entrada() {
		return new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				VegetabiliaUI.getCurrent().getNavigator()
						.navigateTo(EntradaView.NAME);
			}
		};
	}

	public Command saida() {
		return new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				VegetabiliaUI.getCurrent().getNavigator()
						.navigateTo(SaidaView.NAME);
			}
		};
	}
}
