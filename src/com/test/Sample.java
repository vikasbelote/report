package com.test;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;

import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

public class Sample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Sample();
	}

	public Sample() {
		this.build1();
	}

	private void build() {

		StyleBuilder boldStyle = stl.style().bold();
		StyleBuilder boldCenteredStyle = stl.style(boldStyle)
				.setHorizontalAlignment(HorizontalAlignment.CENTER);
		StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle)
				.setBorder(stl.pen1Point())
				.setBackgroundColor(Color.LIGHT_GRAY);

		try {
			report()// create new report design
			.setColumnTitleStyle(columnTitleStyle)
					.highlightDetailEvenRows()
					.columns(
							// add columns
							// title, field name data type
							col.column("Item", "item", type.stringType()),
							col.column("Quantity", "quantity",
									type.integerType()),
							col.column("Unit price", "unitprice",
									type.bigDecimalType()))
					.title(cmp.text("Getting started"))// shows report title
					.pageFooter(cmp.pageXofY())// shows number of page at page
												// footer
					.setDataSource(createDataSource())// set data source
					.show();// create and show report
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private void build1() {
		InputStream is = null;
		try {
			is = new FileInputStream("D:\\development\\workspace\\Reporting\\Blank_A4.jrxml");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			report().setTemplate(Templates.reportTemplate)
					.setTemplateDesign(is)
					.addParameter("Parameter1", "5.5.2005")
					.columns(
							col.column("Item", "item", type.stringType()),
							col.column("Quantity", "quantity",type.integerType()),
							col.column("Unit price", "unitprice",type.integerType()))
					.setDataSource(createDataSource1()).show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("item", "quantity","unitprice");
		dataSource.add("Notebook", 1, new BigDecimal(500));
		dataSource.add("DVD", 5, new BigDecimal(30));
		dataSource.add("DVD", 1, new BigDecimal(28));
		dataSource.add("DVD", 5, new BigDecimal(32));
		dataSource.add("Book", 3, new BigDecimal(11));
		dataSource.add("Book", 1, new BigDecimal(15));
		dataSource.add("Book", 5, new BigDecimal(10));
		dataSource.add("Book", 8, new BigDecimal(9));
		return dataSource;
	}
	
	private JRDataSource createDataSource1() {
		
	      DRDataSource dataSource = new DRDataSource("item", "quantity", "unitprice");
	      for (int i = 0; i < 10; i++) {
	         dataSource.add("Book", (int) (Math.random() * 10) + 1, (int) (Math.random() * 100) + 1);
	      }
	      return dataSource;
	}

	
}
