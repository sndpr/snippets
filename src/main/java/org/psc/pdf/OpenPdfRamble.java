package org.psc.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class OpenPdfRamble {

    public static void main(String[] args) throws FileNotFoundException {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, new FileOutputStream("openPdfHelloWorld.pdf"));

        document.open();
        document.add(new Paragraph("Hello World!"));
        document.close();
    }
}
