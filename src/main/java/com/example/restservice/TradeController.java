package com.example.restservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TradeController {

    private static final Logger log = LoggerFactory.getLogger(TradeController.class);

    @GetMapping("/read-trades")
    public List<JsonData> readTrades() {
        List<JsonData> datas = new ArrayList<>();
        JsonItemReader<JsonData> jsonItemReader = jsonItemReader();
        List<String> tradesString = new ArrayList<>();
        try {
            jsonItemReader.open(new ExecutionContext()); // Initialize the reader properly
            JsonData data;
            while ((data = jsonItemReader.read()) != null) {
                datas.add(data);
                tradesString.add(data.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jsonItemReader.close(); // Close the reader properly
        }



        return datas;
    }

    @RequestMapping(
            value = "/process",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<JsonData> process(@RequestBody JsonData payload) throws Exception {
        PdfCreate pdfcreater= new PdfCreate();
        pdfcreater.PdfCreate(payload.getUserName(),payload.getTimestamp());

        System.out.println(payload.getUserName());
        return ResponseEntity.ok(payload);
    }

    private JsonItemReader<JsonData> jsonItemReader() {
        // Create the resource object for the JSON file
        ClassPathResource resource = new ClassPathResource("json/trades.json");

        // Debug statement to check if the file exists
        System.out.println("File exists: " + resource.exists());

        // Create the JsonItemReader using the resource
        return new JsonItemReaderBuilder<JsonData>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(JsonData.class))
                .resource(resource)
                .name("tradeJsonItemReader")
                .build();
    }
}
//güncel