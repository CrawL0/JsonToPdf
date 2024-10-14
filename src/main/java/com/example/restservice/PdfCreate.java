package com.example.restservice;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfCreate {
    public void createPdf(String fileName,String pdfContent) {
        // Create a new Document object
        Document document = new Document();

        try {
            // Create a PdfWriter instance to write to the file
            PdfWriter.getInstance(document, new FileOutputStream(fileName));

            // Open the document to start writing content to it
            document.open();

            // Create a Font object to define the text's appearance
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

            // Create a Chunk object that represents the text content
            Chunk chunk = new Chunk(pdfContent, font);

            // Add the Chunk to the document
            document.add(chunk);

            System.out.println("PDF created successfully: " + fileName);
        } catch (FileNotFoundException | DocumentException e) {
            // Handle exceptions related to file operations and document processing
            System.err.println("Error occurred while creating PDF: " + e.getMessage());
        } finally {
            // Ensure that the document is closed properly
            if (document != null) {
                document.close();
            }
        }
    }

}
