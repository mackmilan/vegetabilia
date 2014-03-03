package br.com.sapientia.vegetabilia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import br.com.sapientia.vegetabilia.views.ClienteView;
import br.com.sapientia.vegetabilia.views.DespesaView;
import br.com.sapientia.vegetabilia.views.EntradaView;
import br.com.sapientia.vegetabilia.views.EstoqueView;
import br.com.sapientia.vegetabilia.views.FuncionarioView;
import br.com.sapientia.vegetabilia.views.HomeView;
import br.com.sapientia.vegetabilia.views.InternetView;
import br.com.sapientia.vegetabilia.views.MedidaView;
import br.com.sapientia.vegetabilia.views.ProdutoView;
import br.com.sapientia.vegetabilia.views.TipoProdutoView;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("vegetabilia")
public class VegetabiliaUI extends UI {

	Navigator nav = new Navigator(this, this);

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = VegetabiliaUI.class)
	public static class Servlet extends VaadinServlet {

		@Override
		protected void servletInitialized() throws ServletException {
			// TODO Auto-generated method stub
			super.servletInitialized();

			getService().addSessionInitListener(new SessionInitListener() {

				@Override
				public void sessionInit(SessionInitEvent event)
						throws ServiceException {
					event.getSession().addBootstrapListener(
							new BootstrapListener() {

								@Override
								public void modifyBootstrapPage(
										BootstrapPageResponse response) {
									response.getDocument()
											.head()
											.append("<script type='text/javascript' src='./VAADIN/js/jquery-2.0.3.min.js'></script>");
									response.getDocument()
											.head()
											.append("<script type='text/javascript' src='./VAADIN/js/jquery.maskedinput.min.js'></script>");
									response.getDocument()
											.head()
											.append("<script type='text/javascript' src='./VAADIN/js/functions.js'></script>");
								}

								@Override
								public void modifyBootstrapFragment(
										BootstrapFragmentResponse response) {
									// TODO Auto-generated method stub

								}
							});
				}
			});
		}

	}

	@Override
	protected void init(VaadinRequest request) {
		setStyleName("app");

		nav.addView("", HomeView.class);
		nav.addView(HomeView.NAME, HomeView.class);
		nav.addView(ClienteView.NAME, ClienteView.class);
		nav.addView(InternetView.NAME, InternetView.class);
		nav.addView(TipoProdutoView.NAME, TipoProdutoView.class);
		nav.addView(ProdutoView.NAME, ProdutoView.class);
		nav.addView(MedidaView.NAME, MedidaView.class);
		nav.addView(FuncionarioView.NAME, FuncionarioView.class);
		nav.addView(EstoqueView.NAME, EstoqueView.class);
		nav.addView(DespesaView.NAME, DespesaView.class);
		nav.addView(EntradaView.NAME, EntradaView.class);
	}

}