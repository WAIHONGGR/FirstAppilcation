package com.transaction.product.process.controller;

import com.transaction.product.process.service.LogToPdfService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;


@Controller
@RequestMapping("/pdf")
@AllArgsConstructor
public class PdfController {

    private final LogToPdfService logToPdfService;

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadPdf() throws IOException {

        byte[] pdfContent = logToPdfService.generatePdfFromLog();

        if (pdfContent == null){
            throw new IllegalStateException("Failed to generate the pdf file");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "Transaction.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfContent.length)
                .body(pdfContent);
    }
}
