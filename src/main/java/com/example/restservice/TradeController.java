package com.example.restservice;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.restservice.Trade;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TradeController {

    @GetMapping("/read-trades")
    public List<Trade> readTrades() {
        List<Trade> trades = new ArrayList<>();
        JsonItemReader<Trade> jsonItemReader = jsonItemReader();
        List<String> tradesString = new ArrayList<>();
        try {
            jsonItemReader.open(new ExecutionContext()); // Initialize the reader properly
            Trade trade;
            while ((trade = jsonItemReader.read()) != null) {
                trades.add(trade);
                tradesString.add(trade.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jsonItemReader.close(); // Close the reader properly
        }

        PdfCreate pdfCreator = new PdfCreate();
        pdfCreator.createPdf("jsonData",tradesString.toString());

        return trades;
    }

    private JsonItemReader<Trade> jsonItemReader() {
        // Create the resource object for the JSON file
        ClassPathResource resource = new ClassPathResource("json/trades.json");

        // Debug statement to check if the file exists
        System.out.println("File exists: " + resource.exists());

        // Create the JsonItemReader using the resource
        return new JsonItemReaderBuilder<Trade>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(Trade.class))
                .resource(resource)
                .name("tradeJsonItemReader")
                .build();
    }
}
