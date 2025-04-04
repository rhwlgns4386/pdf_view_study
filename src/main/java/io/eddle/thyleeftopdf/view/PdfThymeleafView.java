package io.eddle.thyleeftopdf.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Component("pdf_thymeleaf_view")
@RequiredArgsConstructor
public class PdfThymeleafView extends MyAbstractPdf {

    private final TemplateEngine templateEngine;

    @Override
    protected String html(Map<String, Object> model){
        Context context = (Context)model.get("context");
        return templateEngine.process((String) model.get("filePath"), context);
    }
}
