package org.psc.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PdfBoxRamble {

    public static void main(String[] args) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.setLeading(27.5f);

        contentStream.setFont(PDType1Font.COURIER, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(25, 700);
        contentStream.showText("Hello World");
        contentStream.newLine();
        contentStream.showText("asdasdsad");
		
/*
		contentStream.newLineAtOffset(0, -25);
		contentStream.newLine();
		contentStream.showText("SDSDSDA");
*/
        contentStream.endText();
        contentStream.close();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        document.save(bos);
        document.close();
        // document.save("pdfBoxHelloWorld.pdf");
        // document.close();

        File file = new File(
                "/pdfTest_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss")) + ".pdf");

        FileOutputStream fos = new FileOutputStream(file);

        fos.write(bos.toByteArray());

        fos.flush();
        fos.close();

    }
}
