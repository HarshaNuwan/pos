package com.silikonm.utilities;

//import com.pos.dao.PaymentMethodDAO;
//import com.pos.dto.Product;
//import com.pos.dto.Sale;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.print.PrintService;

import com.silikonm.commons.PropertyHandler;

/**
 * 
 * @author harsha
 */
public class BillPrinter implements Printable {

	private String bill = null;
	private DateFormat dateFormat = null;
	private int sinhalaFontSize = Integer.valueOf(PropertyHandler
			.readProperty("si_font_size"));
	private int englishFontSize = Integer.valueOf(PropertyHandler
			.readProperty("en_font_size"));
	private int printerX = Integer.valueOf(PropertyHandler
			.readProperty("printer_x"));

	public BillPrinter() {
		this.dateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm");
	}

	public void printTheBill(String bill) throws PrinterException {
		this.bill = bill;

		PrintServices ps = new PrintServices();
		// get the printer service by printer name
		PrintService pss = ps
				.getCheckPrintService("HP-Deskjet-1000-J110-series");

		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintService(pss);
		PageFormat pf = new PageFormat();
		Paper p = new Paper();
		p.setImageableArea(5, 5, 570, 600);
		p.setSize(3.5, 5.0);
		pf.setPaper(p);
		pf.setOrientation(PageFormat.PORTRAIT);

		job.setPrintable(this, pf);

		try {
			job.print();
		} catch (PrinterException ex) {
			ex.printStackTrace();
		}
	}

	public void printTheBill(List<Object[]> items, BigDecimal total,
			BigDecimal discount, BigDecimal received, int id)
			throws PrinterException {

		ArrayList<PrintableLine> list = new ArrayList<PrintableLine>();

		StringBuilder stBuilder = new StringBuilder();

		list.add(new PrintableLine("Date: " + dateFormat.format(new Date())
				+ " User: " + "Test User" + "  No: " + id));
		list.add(new PrintableLine("-----------------------------------------"));
		list.add(new PrintableLine("No  Item    Qty     Price(Rs.)    Amount"));
		list.add(new PrintableLine("-----------------------------------------"));

		// stBuilder.append("@!Date: ").append(dateFormat.format(new Date()));
		// stBuilder.append(" User: " + SystemUser.getName()).append("@!");
		// stBuilder.append("-----------------------------------------@!");
		// stBuilder.append("No  Item    Qty     Price(Rs.)    Amount @!");
		// stBuilder.append("-----------------------------------------");

		Vector<Vector> v = new Vector<Vector>();

		int count = 1;
		for (Object[] row : items) {

			String itemName = row[1].toString();
			if (itemName.length() > 41) {
				String name1 = itemName.substring(0, 40);
				String name2 = itemName.substring(40, itemName.length() - 1);
				list.add(new PrintableLine(count + "   $$" + name1));
				list.add(new PrintableLine("   $$" + name2));
			} else {
				list.add(new PrintableLine(count + "   $$" + row[1]));
			}

			double dc = Double.valueOf(row[5].toString());
			
			if (dc != 0) {
				list.add(new PrintableLine("discount : " + dc));
			}
			
			list.add(new PrintableLine(row[0]
					+ "       "
					+ new BigDecimal(row[2].toString()).setScale(2,
							RoundingMode.CEILING)
					+ "   "
					+ new BigDecimal(row[4].toString()).setScale(2,
							RoundingMode.CEILING)
					+ "       "
					+ new BigDecimal(row[2].toString())
							.multiply(new BigDecimal(row[4].toString()))
							.subtract((BigDecimal) row[5])
							.setScale(2, RoundingMode.CEILING)));

			// stBuilder.append("@!" + String.valueOf(count));// No
			// stBuilder.append("   ");
			// stBuilder.append("$$" + row[1]);// item name
			// stBuilder.append("@!    ");
			// stBuilder.append(row[0]);// item code
			// stBuilder.append(" ");
			// stBuilder.append(new BigDecimal(row[2].toString()).setScale(3,
			// RoundingMode.CEILING));// qty
			// stBuilder.append("   ");
			// stBuilder.append(new BigDecimal(row[4].toString()).setScale(3,
			// RoundingMode.CEILING));// price
			// stBuilder.append("   ");
			// stBuilder.append(new BigDecimal(row[2].toString()).multiply(
			// new BigDecimal(row[4].toString())).setScale(3,
			// RoundingMode.CEILING));// total
			count++;
		}
		list.add(new PrintableLine("-----------------------------------------"));
		list.add(new PrintableLine("Total     : "
				+ total.add(discount).setScale(2, RoundingMode.CEILING)
						.toString()));
		list.add(new PrintableLine("Discount  : "
				+ discount.setScale(2, RoundingMode.CEILING).toString()));
		list.add(new PrintableLine("-----------------------------------------"));
		list.add(new PrintableLine("Sub Total : "
				+ total.setScale(2, RoundingMode.CEILING).toString()));

		// stBuilder.append("@!-----------------------------------------@!");

		// stBuilder.append("Total     : ");
		// stBuilder.append(total.add(discount).setScale(3,
		// RoundingMode.CEILING)
		// .toString());
		// stBuilder.append("@!Discount  : ");
		// stBuilder.append(discount.setScale(3,
		// RoundingMode.CEILING).toString());
		// stBuilder.append("@!-----------------------------------------");
		// stBuilder.append("@!Sub Total : ");
		// stBuilder.append(total.setScale(3, RoundingMode.CEILING).toString());

		if (received.compareTo(BigDecimal.ZERO) > 0) {
			list.add(new PrintableLine("Received  : "
					+ received.setScale(2, RoundingMode.CEILING).toString()));

			// stBuilder.append("@!Received  : ");
			// stBuilder.append(received.setScale(3, RoundingMode.CEILING)
			// .toString());
		}
		// stBuilder.append("@!");
		list.add(new PrintableLine("Balance   : "
				+ received.subtract(total).setScale(3, RoundingMode.CEILING)
						.toString()));

		// stBuilder.append("@!Balance   : ");
		// stBuilder.append(received.subtract(total)
		// .setScale(3, RoundingMode.CEILING).toString());

		// /
		list.add(new PrintableLine("-----------------------------------------"));
		list.add(new PrintableLine("                THANK YOU!"));
		list.add(new PrintableLine("               Come Again!"));
		list.add(new PrintableLine("           Software By SilikonM"));
		list.add(new PrintableLine("         email: sales@silikonm.com"));
		list.add(new PrintableLine("           web: www.silikonm.com"));

		// stBuilder.append("@!-----------------------------------------@!");
		// stBuilder.append("                THANK YOU@!");
		// stBuilder.append("               Come Again!@!");
		// stBuilder.append("@!           Software By SilikonM@!");
		// stBuilder.append("         email: sales@silikonm.com@!");
		// stBuilder.append("           web: www.silikonm.com@!");

		// **********************************************************************
		// **********************************************************************
		// **********************************************************************

		PrintReceipt printReceipt = new PrintReceipt(list);
		// this.bill = stBuilder.toString();
		//
		// PrintServices ps = new PrintServices();
		// // get the printer service by printer name
		// PrintService pss = ps
		// .getCheckPrintService("HP-Deskjet-1000-J110-series");
		//
		// PrinterJob job = PrinterJob.getPrinterJob();
		// job.setPrintService(pss);
		// PageFormat pf = new PageFormat();
		// Paper p = new Paper();
		//
		// p.setImageableArea(5, 5, 570, 600);
		// // p.setSize(3.5, 5.0);
		// pf.setPaper(p);
		// pf.setOrientation(PageFormat.PORTRAIT);
		//
		// job.setPrintable(this, pf);
		//
		// try {
		// job.print();
		// } catch (PrinterException ex) {
		// ex.printStackTrace();
		// }
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		if (pageIndex > 0) { /* We have only one page, and 'page' is zero-based */
			return NO_SUCH_PAGE;
		}

		Graphics2D g2d = (Graphics2D) graphics;
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

		// Prepare the rendering
		// String[] bill = mText.split(";");
		String[] bill = this.bill.split("@!");
		int y = 10;
		// LocationInfo.getInstance().getCompany().getCompanyName()
		Font f = new Font(Font.MONOSPACED, Font.BOLD, 8);

		// stBuilder.append(PropertyHandler.readProperty("shopname")+"@!");
		// stBuilder.append(PropertyHandler.readProperty("shopaddress")+"@!");
		// stBuilder.append(PropertyHandler.readProperty("shopphone")+"@!");

		// y = y + 10;
		f = new Font(Font.MONOSPACED, Font.BOLD,
				Integer.valueOf(PropertyHandler
						.readProperty("header_font_size")));
		graphics.setFont(f);
		graphics.drawString(PropertyHandler.readProperty("shopname"), printerX,
				y);
		f = new Font(Font.MONOSPACED, Font.BOLD, englishFontSize);
		y = y + 10;
		graphics.setFont(f);
		graphics.drawString(PropertyHandler.readProperty("shopaddress"),
				printerX, y);
		y = y + 10;
		graphics.drawString(PropertyHandler.readProperty("shopphone"),
				printerX, y);
		y = y + 10;

		graphics.setFont(f);
		for (int i = 0; i < bill.length; i++) {
			// if (i == 0) {
			// f = new Font(Font.MONOSPACED, Font.BOLD, 10);
			// } else {
			// f = new Font(Font.MONOSPACED, Font.PLAIN, 7);
			// }
			// System.out.println(bill[i]);
			if (bill[i].contains("$$")) {
				bill[i] = bill[i].toString().replace("$$", " ");

				//f = FontService.getSinhalaFont(sinhalaFontSize);
				graphics.setFont(f);
			} else {
				f = new Font(Font.MONOSPACED, Font.PLAIN, englishFontSize);
				graphics.setFont(f);
			}

			if (bill[i].length() > 82) {
				graphics.drawString(bill[i].substring(0, 42), printerX, y);
				y = y + 10;
				graphics.drawString(bill[i].substring(42, bill[i].length()),
						printerX, y);
				y = y + 10;
				graphics.drawString(bill[i].substring(82, bill[i].length()),
						printerX, y);
				y = y + 10;
			} else if (bill[i].length() > 42) {
				graphics.drawString(bill[i].substring(0, 42), printerX, y);
				y = y + 10;
				// System.out.println(bill[i].substring(0, 42));
				graphics.drawString(bill[i].substring(42, bill[i].length()),
						printerX, y);
				y = y + 10;
			} else {
				// System.out.println(bill[i]);
				graphics.drawString(bill[i], printerX, y);
				y = y + 10;
			}

		}
		/* tell the caller that this page is part of the printed document */
		return PAGE_EXISTS;
	}
}
