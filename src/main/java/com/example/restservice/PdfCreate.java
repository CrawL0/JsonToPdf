package com.example.restservice;


import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.stereotype.Service;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Service
public class PdfCreate {
    public static void generatePdf(String templatePath, String outputPdfPath, Map<String, String> placeholders) throws IOException
    {
        System.out.println(placeholders.values());
        String htmlContent = new String(Files.readAllBytes(Paths.get(templatePath)), StandardCharsets.UTF_8);
        int j=0;

        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
           for (int i=1;i<placeholders.size()+1;i++) {
               if (j<i) {
                   htmlContent = htmlContent.replace("{{value"+i+"}}", entry.getValue());
                   htmlContent = htmlContent.replace("||key"+i+"||", entry.getKey());
                   j=i;
                   break;
               }
           }
        }

        HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(outputPdfPath));
    }
}
