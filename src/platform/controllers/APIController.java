package platform.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.models.CodeSnippetDTO;
import platform.services.HtmlService;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class APIController {

    private final HtmlService htmlService;

    @Autowired
    public APIController(HtmlService htmlService) {
        this.htmlService = htmlService;
    }

    @GetMapping(value = "/api/code", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CodeSnippetDTO> getStartCode() {
        CodeSnippetDTO codeSnippetDTO = htmlService.getStartCodeSnippet();
        log.info("Api code: {}", codeSnippetDTO);
        return new ResponseEntity<>(codeSnippetDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/code/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> postNewSnippet(@RequestBody CodeSnippetDTO codeSnippetDTO) {
        Long savedId = htmlService.postSnippet(codeSnippetDTO);
        return new ResponseEntity<>(Map.of("id", Long.toString(savedId)), HttpStatus.OK);
    }

    @GetMapping(value = "/api/code/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CodeSnippetDTO> getApiCode(@PathVariable Long id) {
        CodeSnippetDTO codeSnippetDTO = htmlService.getCodeSnippetById(id);
        log.info("Api code: {}", codeSnippetDTO);
        return new ResponseEntity<>(codeSnippetDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/code/latest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CodeSnippetDTO>> getLatestSnippets() {
        List<CodeSnippetDTO> codeSnippetDTO = htmlService.getLatestSnippets();
        log.info("Api code: {}", codeSnippetDTO);
        return new ResponseEntity<>(codeSnippetDTO, HttpStatus.OK);
    }

}