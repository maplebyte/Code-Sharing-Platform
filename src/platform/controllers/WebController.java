package platform.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.models.CodeSnippetDTO;
import platform.services.HtmlService;

import java.util.List;

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
        CodeSnippetDTO code = htmlService.getStartCodeSnippet();
        log.info("Got code {}", code);
        model.addAttribute("code_snippet", code.getCode());
        model.addAttribute("load_date", code.getDate());
        return "codeStart";
    }

    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    public String getNewCode(Model model) {
        model.addAttribute("start_phrase", START_PHRASE);
        return "codeNew";
    }

    @GetMapping(value = "/code/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String getApiCode(Model model, @PathVariable Long id) {
        CodeSnippetDTO codeSnippetDTO = htmlService.getCodeSnippetById(id);
        log.info("Api code: {}", codeSnippetDTO);
        model.addAttribute("code_snippet", codeSnippetDTO.getCode());
        model.addAttribute("load_date", codeSnippetDTO.getDate());
        return "codeStart";
    }

    @GetMapping(value = "/code/latest", produces = MediaType.TEXT_HTML_VALUE)
    public String getLatestSnippets(Model model) {
        List<CodeSnippetDTO> codeSnippetDTO = htmlService.getLatestSnippets();
        log.info("Api code: {}", codeSnippetDTO);
        model.addAttribute("latestcode", codeSnippetDTO);
        return "codeLatest";
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String getStartedPage() {
        return "index";
    }

}
