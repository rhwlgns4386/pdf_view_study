package io.eddle.thyleeftopdf.view;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.AbstractView;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public abstract class MyAbstractPdf extends AbstractView {

    public MyAbstractPdf() {
        setContentType("application/pdf");
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ByteArrayOutputStream baos = createTemporaryOutputStream();

        PdfWriter writer = new PdfWriter(baos);
        HtmlConverter.convertToPdf(html(model), writer);
        writeToResponse(response, baos);
    }

    protected abstract String html(Map<String, Object> model) throws Exception;
}
