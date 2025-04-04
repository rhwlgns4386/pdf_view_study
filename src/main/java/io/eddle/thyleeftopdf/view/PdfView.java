package io.eddle.thyleeftopdf.view;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component("pdfview")
public class PdfView extends MyAbstractPdf {

    @Override
    protected String html(Map<String, Object> model) throws Exception {
        return (String) model.get("data");
    }
}
