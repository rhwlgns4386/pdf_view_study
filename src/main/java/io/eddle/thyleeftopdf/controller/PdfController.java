package io.eddle.thyleeftopdf.controller;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;
import io.eddle.thyleeftopdf.dto.Post;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pdf")
public class PdfController {

    private final TemplateEngine templateEngine;

    @GetMapping("/view/example.pdf")
    public void pdfView(HttpServletResponse response) throws IOException {
        Context context = new Context();
        context.setVariable("post", new Post("asdfdsaf", "bbbb"));

        String thymeleafTemplate = templateEngine.process("example", context);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        HtmlConverter.convertToPdf(thymeleafTemplate, writer);

        response.setContentType("application/pdf");
        response.getOutputStream().write(outputStream.toByteArray());
    }


    @GetMapping("/download/example.pdf")
    public void pdfDownload(HttpServletResponse response) throws IOException {
        Context context = new Context();
        context.setVariable("post", new Post("asdfdsaf", "bbbb"));

        String thymeleafTemplate = templateEngine.process("example", context);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        HtmlConverter.convertToPdf(thymeleafTemplate, writer);

        response.setContentType("application/pdf");
        initDownloadHeader(response);
        response.getOutputStream().write(outputStream.toByteArray());
    }

    @GetMapping("/view2/example.pdf")
    public String pdfView2(Model model){
        Context context = new Context();
        context.setVariable("post", new Post("asdfdsaf", "bbbb"));

        String thymeleafTemplate = templateEngine.process("example", context);

        model.addAttribute("data", thymeleafTemplate);

        return "pdfview";
    }

    @GetMapping("/download2/example.pdf")
    public String pdfDownload2(Model model,HttpServletResponse response){
        Context context = new Context();
        context.setVariable("post", new Post("asdfdsaf", "bbbb"));

        String thymeleafTemplate = templateEngine.process("example", context);

        model.addAttribute("filename", "example.pdf");
        model.addAttribute("data", thymeleafTemplate);

        initDownloadHeader(response);
        return "pdfview";
    }

    @GetMapping("/view3/example.pdf")
    public String pdfView3(Model model){
        Context context = new Context();
        context.setVariable("post", new Post("asdfdsaf", "bbbb"));

        model.addAttribute("context", context);
        model.addAttribute("filePath", "example");
        return "pdf_thymeleaf_view";
    }

    @GetMapping("/download3/example.pdf")
    public String pdfDownload3(Model model,HttpServletResponse response) {
        Context context = new Context();
        context.setVariable("post", new Post("asdfdsaf", "bbbb"));

        model.addAttribute("context", context);
        model.addAttribute("filePath", "example");

        initDownloadHeader(response);
        return "pdf_thymeleaf_view";
    }

    private static void initDownloadHeader(HttpServletResponse response) {
        response.addHeader("Content-Disposition", "attachment; filename=example.pdf");
    }
}
