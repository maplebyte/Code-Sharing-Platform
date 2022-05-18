package platform.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import platform.services.HtmlService;

@Controller
@Slf4j
public class WebController {

    private final HtmlService htmlService;

    @Autowired
    public WebController(HtmlService htmlService) {
        this.htmlService = htmlService;
    }

    @GetMapping(value = "/code", produces = MediaType.TEXT_HTML_VALUE)
    public String getAllCode(Model model) {
        String code = htmlService.getCodePart();
        model.addAttribute("codePart", code);
        return "codePart";
    }

}
