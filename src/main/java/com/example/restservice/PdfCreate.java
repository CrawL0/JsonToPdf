package com.example.restservice;

import com.lowagie.text.DocumentException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PdfCreate {
    private String userName;
    private String timestamp;


    public void PdfCreate(String userName,String timestamp) throws IOException, DocumentException {
        PdfCreate pdfCreator = new PdfCreate();
        String html = pdfCreator.parseThymeleafTemplate(userName,timestamp);
        pdfCreator.generatePdfFromHtml(html);
    }

    public void generatePdfFromHtml(String html) throws IOException, DocumentException {
        String outputFolder = System.getProperty("user.home") + File.separator + "thymeleaf.pdf";
        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }

    private String parseThymeleafTemplate(String userName,String timestamp) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");  // Ensures it looks in the correct folder
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        this.userName=userName;
        this.timestamp=timestamp;

        // Creating the context and populating it with JSON data
        Context context = new Context();
        context.setVariable("userName", userName);
        context.setVariable("timestamp", timestamp);
        context.setVariable("pdfTemplateName", "User Report");
        context.setVariable("params", createParams());

        return templateEngine.process("thymeleaf_template", context);
    }

    // Helper method to create the params object for Thymeleaf
    private Map<String, Object> createParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("label1", "Sample Label");

        List<Map<String, String>> tableData = new ArrayList<>();
        Map<String, String> row1 = new HashMap<>();
        row1.put("col1", "Value 1");
        row1.put("col2", "Value 2");
        tableData.add(row1);

        Map<String, String> row2 = new HashMap<>();
        row2.put("col1", "Value 3");
        row2.put("col2", "Value 4");
        tableData.add(row2);

        params.put("table1", tableData);

        return params;
    }
}
