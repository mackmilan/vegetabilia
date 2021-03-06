package br.com.sapientia.vegetabilia.views;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class InternetView extends CustomComponent implements View {

	public static final String NAME = "internet";

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private VerticalLayout vl;
	@AutoGenerated
	private Panel p1;
	@AutoGenerated
	private VerticalLayout vl1;
	@AutoGenerated
	private Embedded embedded;
	@AutoGenerated
	private MainMenu menu;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public InternetView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// menu
		menu = new MainMenu();
		menu.setImmediate(false);
		menu.setWidth("100.0%");
		mainLayout.addComponent(menu, "top:0.0px;left:0.0px;");

		// vl
		vl = buildVl();
		mainLayout.addComponent(vl, "top:28.0px;left:0.0px;");

		return mainLayout;
	}

	@AutoGenerated
	private VerticalLayout buildVl() {
		// common part: create layout
		vl = new VerticalLayout();
		vl.setImmediate(false);
		vl.setWidth("100.0%");
		vl.setHeight("100.0%");
		vl.setMargin(true);

		// p1
		p1 = buildP1();
		vl.addComponent(p1);
		vl.setExpandRatio(p1, 1.0f);

		return vl;
	}

	@AutoGenerated
	private Panel buildP1() {
		// common part: create layout
		p1 = new Panel();
		p1.setImmediate(false);
		p1.setWidth("100.0%");
		p1.setHeight("100.0%");

		// vl1
		vl1 = buildVl1();
		p1.setContent(vl1);

		return p1;
	}

	@AutoGenerated
	private VerticalLayout buildVl1() {
		// common part: create layout
		vl1 = new VerticalLayout();
		vl1.setImmediate(false);
		vl1.setWidth("100.0%");
		vl1.setHeight("100.0%");
		vl1.setMargin(true);

		// embedded
		embedded = new Embedded("Internet", new ExternalResource(
				"http://google.com"));
		embedded.setImmediate(true);
		embedded.setWidth("100.0%");
		embedded.setHeight("100.0%");
		embedded.setType(Embedded.TYPE_BROWSER);
		vl1.addComponent(embedded);
		vl1.setExpandRatio(embedded, 1.0f);

		return vl1;
	}

}
