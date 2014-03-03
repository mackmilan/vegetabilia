package br.com.sapientia.vegetabilia.views;

import br.com.sapientia.vegetabilia.controllers.ProdutoController;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class ProdutoView extends CustomComponent implements View {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private HorizontalSplitPanel hsp1;

	@AutoGenerated
	private VerticalLayout vl2;

	@AutoGenerated
	private Panel p2;

	@AutoGenerated
	private VerticalLayout vlp2;

	@AutoGenerated
	private VerticalLayout vl1;

	@AutoGenerated
	private Panel p1;

	@AutoGenerated
	private VerticalLayout vlp1;

	@AutoGenerated
	private MainMenu mainMenu_1;

	public static final String NAME = "produto";

	private ProdutoController controller = new ProdutoController();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public ProdutoView() {
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

		// mainMenu_1
		mainMenu_1 = new MainMenu();
		mainMenu_1.setImmediate(false);
		mainMenu_1.setWidth("100.0%");
		mainLayout.addComponent(mainMenu_1, "top:0.0px;left:0.0px;");

		// hsp1
		hsp1 = buildHsp1();
		mainLayout.addComponent(hsp1, "top:23.0px;left:0.0px;");

		return mainLayout;
	}

	@AutoGenerated
	private HorizontalSplitPanel buildHsp1() {
		// common part: create layout
		hsp1 = new HorizontalSplitPanel();
		hsp1.setImmediate(false);
		hsp1.setWidth("100.0%");
		hsp1.setHeight("98.0%");

		// vl1
		vl1 = buildVl1();
		hsp1.addComponent(vl1);

		// vl2
		vl2 = buildVl2();
		hsp1.addComponent(vl2);

		return hsp1;
	}

	@AutoGenerated
	private VerticalLayout buildVl1() {
		// common part: create layout
		vl1 = new VerticalLayout();
		vl1.setImmediate(false);
		vl1.setWidth("100.0%");
		vl1.setHeight("100.0%");
		vl1.setMargin(true);

		// p1
		p1 = buildP1();
		vl1.addComponent(p1);
		vl1.setExpandRatio(p1, 1.0f);

		return vl1;
	}

	@AutoGenerated
	private Panel buildP1() {
		// common part: create layout
		p1 = new Panel();
		p1.setImmediate(false);
		p1.setWidth("100.0%");
		p1.setHeight("100.0%");

		// vlp1
		vlp1 = buildVlp1();
		p1.setContent(vlp1);

		return p1;
	}

	@AutoGenerated
	private VerticalLayout buildVlp1() {
		// common part: create layout
		vlp1 = new VerticalLayout();
		vlp1.setImmediate(false);
		vlp1.setWidth("100.0%");
		vlp1.setHeight("100.0%");
		vlp1.setMargin(true);

		vlp1.addComponent(controller.buildGrid());

		return vlp1;
	}

	@AutoGenerated
	private VerticalLayout buildVl2() {
		// common part: create layout
		vl2 = new VerticalLayout();
		vl2.setImmediate(false);
		vl2.setWidth("100.0%");
		vl2.setHeight("100.0%");
		vl2.setMargin(true);

		// p2
		p2 = buildP2();
		vl2.addComponent(p2);
		vl2.setExpandRatio(p2, 1.0f);

		return vl2;
	}

	@AutoGenerated
	private Panel buildP2() {
		// common part: create layout
		p2 = new Panel();
		p2.setImmediate(false);
		p2.setWidth("100.0%");
		p2.setHeight("100.0%");

		// vlp2
		vlp2 = new VerticalLayout();
		vlp2.setImmediate(false);
		vlp2.setWidth("100.0%");
		vlp2.setHeight("100.0%");
		vlp2.setMargin(true);

		vlp2.addComponent(controller.buildForm());

		p2.setContent(vlp2);

		return p2;
	}

}
