package com.silikonm.utilities;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.Sides;

/**
 *
 * @author harsha
 */
public class PrintServices {

    public PrintService getCheckPrintService(String printerName) {
        PrintService ps = null;
        DocFlavor doc_flavor = DocFlavor.STRING.TEXT_PLAIN;
        PrintRequestAttributeSet attr_set =
                new HashPrintRequestAttributeSet();
        attr_set.add(new Copies(1));
        // attr_set.add(MediaName.ISO_A4_WHITE);
        attr_set.add(Sides.ONE_SIDED);
//        PrintService[] service = PrintServiceLookup.lookupPrintServices(doc_flavor, attr_set);
//        for (int i = 0; i < service.length; i++) {
//            System.out.println(service[i].getName());
//            if (service[i].getName().equals(printerName)) {
//                ps = service[i];
//            }
//        }
        return PrintServiceLookup.lookupDefaultPrintService();
    }
}
