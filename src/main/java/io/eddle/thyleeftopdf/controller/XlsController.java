package io.eddle.thyleeftopdf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class XlsController {

    @GetMapping("/{path}.xls")
    public String xsl(){
        return "my_xls_view";
    }
}
