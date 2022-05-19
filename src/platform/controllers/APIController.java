package platform.controllers;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.models.CodeSnippetDTO;
import platform.services.HtmlService;

@RestController
@Slf4j
public class APIController {

    private final HtmlService htmlService;

    @Autowired
    public APIController(HtmlService htmlService) {
        this.htmlService = htmlService;
    }

    @GetMapping(value = "/api/code", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CodeSnippetDTO> getApiCode() {
        CodeSnippetDTO codeSnippetDTO = htmlService.getCodeSnippet();
        log.info("Api code: {}", codeSnippetDTO);
        return new ResponseEntity<>(codeSnippetDTO, HttpStatus.OK);
    }


    @PostMapping(value = "/api/code/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmptyJSONResponse> postNewSnippet(@RequestBody CodeSnippetDTO codeSnippetDTO) {
        htmlService.postSnippet(codeSnippetDTO);
        return new ResponseEntity<>(new EmptyJSONResponse(), HttpStatus.OK);
    }

    // need to return empty JSON from post method in APIController (task requirement)
    @JsonSerialize
    static
    class EmptyJSONResponse {
    }

}