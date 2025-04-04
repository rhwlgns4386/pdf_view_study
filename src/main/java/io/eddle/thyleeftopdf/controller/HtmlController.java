package io.eddle.thyleeftopdf.controller;

import io.eddle.thyleeftopdf.dto.Post;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/html")
public class HtmlController {

    private final TemplateEngine templateEngine;

    @GetMapping
    public String htmlTest2(Model model) {
        model.addAttribute("post", new Post("제목", "콘텐츠"));
        return "example";
    }

    @GetMapping("/engine")
    public void htmlTest(HttpServletResponse response) throws IOException {
        Context context = new Context();
        context.setVariable("post", new Post("제목", "콘텐츠"));

        String thymeleafTemplate = templateEngine.process("example", context);

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(thymeleafTemplate);
    }
}
