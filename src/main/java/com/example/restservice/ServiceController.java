package com.example.restservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ServiceController {

    private static final Logger log = LoggerFactory.getLogger(ServiceController.class);

    private PdfCreate pdfCreate;


    @RequestMapping(
            value = "/process",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<?> process(@RequestBody Map<String, Object> payload) throws Exception {

        // Define the paths to the HTML template and output PDF
        String templatePath = "src/main/resources/templates/invoice_template.html";
        String outputPdfPath = "src/main/resources/generated_invoic.pdf";

        // Convert the JSON payload to a Map for placeholder replacement
        Map<String, String> placeholders = extractPlaceholders(payload);

        // Generate the PDF
        PdfCreate.generatePdf(templatePath, outputPdfPath, placeholders);

        // Return the PDF as a response
        InputStreamResource resource = new InputStreamResource(new FileInputStream(outputPdfPath));

        //PdfCreate pdfcreater= new PdfCreate();
        //pdfcreater.PdfCreate(payload.getUserName(),payload.getTimestamp());

        return ResponseEntity.ok().body(resource);
    }

    private Map<String, String> extractPlaceholders(Map<String, Object> payload) {
        Map<String, String> placeholders = new HashMap<>();

        // Loop through the payload to extract key-value pairs
        for (Map.Entry<String, Object> entry : payload.entrySet()) {
            // Convert the value to a string and store it in the placeholders map
            placeholders.put(entry.getKey(), entry.getValue().toString());
        }

        return placeholders;
    }


}
