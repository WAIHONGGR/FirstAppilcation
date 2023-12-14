package com.transaction.product.process.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;

@Service
public class LogToPdfService {

    public byte[] generatePdfFromLog() {
        try {
            String filePath = "transaction.log";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            float y = 700; // Y-coordinate for text positioning
            int lineNo = 1; // Line number counter

            // Write table header
            String header = String.format("%-4s %-45s %-8s %-15s %-18s %-16s %-5s",
                    "No", "Date&Time", "Txn ID", "Card Type", "Amount", "Charge", "MID");
            writeText(contentStream, PDType1Font.HELVETICA_BOLD, 11, 70, y, header);
            y -= 15; // Adjust for the next line


            String line;
            while ((line = reader.readLine()) != null) {
                String[] logData = line.split("\\[.*?\\]:");
                if (logData.length >= 6) { // Check if there are enough fields
                    String formattedLine = String.format("%-4d %-33s %-23d %-9s %-12.2f %15.2f %8d",
                            lineNo++, logData[1].trim(), Integer.parseInt(logData[2].trim()),
                            logData[3].trim(), Double.parseDouble(logData[4].trim()),
                            Double.parseDouble(logData[5].trim()), Integer.parseInt(logData[6].trim()));

                    writeText(contentStream, PDType1Font.HELVETICA, 11, 70, y, formattedLine);
                    y -= 15; // Adjust for the next line
                } else {
                    // Log or handle entries with incomplete data
                    System.err.println("Incomplete log entry: " + line);
                }
            }


            contentStream.close();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            document.close();
            reader.close();
            return baos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void writeText(PDPageContentStream contentStream, PDType1Font font, int fontSize,
                           float x, float y, String text) throws IOException {
        contentStream.beginText();
        contentStream.setFont(font, fontSize);
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(text);
        contentStream.endText();
    }
}