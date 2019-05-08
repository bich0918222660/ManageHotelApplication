package ui.pnl;

import java.awt.BorderLayout;
import java.text.DecimalFormat;

import javax.swing.Box;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import dao.CategoryDAO;
import entity.Account;
import entity.StatisticalCategory;
import ui.component.BoxComponent;

public class Pnl_Statistical extends JPanel {
	
	private static final long serialVersionUID = 6294689542092367723L;  

	private Account account;
	
	private CategoryDAO dao;

	public Pnl_Statistical() {
		setLayout(new BorderLayout());
		this.account = account;
		this.dao = new CategoryDAO();
		gui();
	}

	private void gui() {
		// Create dataset
		PieDataset dataset = createDataset();

		// Create chart
		JFreeChart chart = ChartFactory.createPieChart("Thống kê loại phòng", dataset, true, true, false);

		// Format Label
		PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} : ({2})",
				new DecimalFormat("0"), new DecimalFormat("0%"));
		((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);

		// Create Panel
		ChartPanel panel = new ChartPanel(chart);
		
		Box bh = BoxComponent.getHorizontalBox(panel, 20);
		Box bv = BoxComponent.getVerticalBox(bh, 20);
		
		add(bv);
	}

	private PieDataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(StatisticalCategory sc: dao.statisticsByCategory()) {
			dataset.setValue(sc.getCategoryName(), sc.getCount());
		}
		return dataset;
	}


}
