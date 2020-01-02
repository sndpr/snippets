package org.psc.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

public class OpenPdfRamble {

    public static void main(String[] args) throws IOException {

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, outputStream);

            //        HeaderFooter footer = new HeaderFooter(new Phrase("Page "), new Phrase(" / " + document
            //        .getPageNumber()));
            //        footer.setAlignment(Element.ALIGN_RIGHT);
            //        footer.setBorder(Rectangle.NO_BORDER);
            //        document.setFooter(footer);

            document.open();
            document.addTitle("Title");

            document.add(new Paragraph(new Chunk("Hello World!",
                    FontFactory.getFont(FontFactory.HELVETICA, 20L, Color.ORANGE))));
            IntStream.range(0, 200).forEach(i -> document.add(new Paragraph(i + ": test")));
            Paragraph paragraph = new Paragraph();
            paragraph.setLeading(40L);
            paragraph.add("Test ");
            paragraph.add(new Phrase("phrase 1223123 "));
            paragraph.add(new Chunk("send me "));
            document.add(paragraph);

            Paragraph paragraph1 = new Paragraph();
            paragraph1.setLeading(50L);
            paragraph1.setAlignment(ElementTags.ALIGN_CENTER);
            paragraph1.add("yeet");
            document.add(paragraph1);

            document.add(new Paragraph("end it"));

            //        HeaderFooter pageCounter = new HeaderFooter(new Phrase(""), new Phrase("/" + document
            //        .getPageNumber
            //        ()));
            //pageCounter.setAlignment(ElementTags.alignmentValue(ElementTags.ALIGN_RIGHT));
            //        document.setFooter(pageCounter);
            document.close();

            byte[] stampedPdf = stampTotalPages(outputStream.toByteArray());

            Files.write(Paths.get(
                    "openPdfHelloWorld_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) +
                            ".pdf"), stampedPdf);
        }
    }

    private static byte[] stampTotalPages(byte[] source) throws IOException, DocumentException {
        try (ByteArrayOutputStream targetOutputStream = new ByteArrayOutputStream()) {
            PdfReader reader = new PdfReader(source);
            PdfStamper stamper = new PdfStamper(reader, targetOutputStream);

            int totalNumberOfPages = reader.getNumberOfPages();
            PdfContentByte pagecontent;

            for (int i = 0; i < totalNumberOfPages; ) {
                pagecontent = stamper.getOverContent(++i);
                ColumnText.showTextAligned(pagecontent, Element.ALIGN_RIGHT,
                        new Phrase(String.format("page %s of %s", i, totalNumberOfPages)), 559, 36, 0);
            }

            stamper.close();
            reader.close();

            return targetOutputStream.toByteArray();
        }
    }
}
