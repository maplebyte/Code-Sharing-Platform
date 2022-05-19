package platform.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import platform.models.CodeSnippetDTO;
import platform.services.HtmlService;

@Controller
@Slf4j
public class WebController {

    private final String START_PHRASE = "// Write your code here";
    private final HtmlService htmlService;

    @Autowired
    public WebController(HtmlService htmlService) {
        this.htmlService = htmlService;
    }

    @GetMapping(value = "/code", produces = MediaType.TEXT_HTML_VALUE)
    public String getStartCode(Model model) {
        CodeSnippetDTO code = htmlService.getCodeSnippet();
        log.info("Got code {}", code);
        model.addAttribute("code_snippet", code.getCode());
        model.addAttribute("load_date", code.getDate());
        //TODO rename
        return "codeStart";
    }

    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    public String getNewCode(Model model) {
        model.addAttribute("start_phrase", START_PHRASE);
        //TODO rename
        return "codeNew";
    }

}
