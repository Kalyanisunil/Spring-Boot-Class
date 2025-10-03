package com.example.demo.controller;

import jakarta.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class BookPdfController {

    @GetMapping("/generate-book-pdf")
    public void generateBookPdf(HttpServletResponse response) throws Exception {
      
        String title = "The Pragmatic Programmer";
        String author = "Andrew Hunt, David Thomas";
        String description = "A classic book on software craftsmanship.";
        double price = 45.0;
        LocalDate publishedDate = LocalDate.of(1999, 10, 20);

        // Setup response
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=book.pdf");

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Title
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 750);
                contentStream.showText("Book Details");
                contentStream.endText();

                // Details
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                int yPosition = 720;

                contentStream.beginText();
                contentStream.newLineAtOffset(50, yPosition);
                contentStream.showText("Title: " + title);
                contentStream.endText();

                yPosition -= 20;
                contentStream.beginText();
                contentStream.newLineAtOffset(50, yPosition);
                contentStream.showText("Author: " + author);
                contentStream.endText();

                yPosition -= 20;
                contentStream.beginText();
                contentStream.newLineAtOffset(50, yPosition);
                contentStream.showText("Description: " + description);
                contentStream.endText();

                yPosition -= 20;
                contentStream.beginText();
                contentStream.newLineAtOffset(50, yPosition);
                contentStream.showText("Price: $" + price);
                contentStream.endText();

                yPosition -= 20;
                contentStream.beginText();
                contentStream.newLineAtOffset(50, yPosition);
                contentStream.showText("Published Date: " + publishedDate);
                contentStream.endText();
            }

            // Write PDF to response
            document.save(response.getOutputStream());
        }
    }
}
