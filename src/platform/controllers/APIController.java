package platform.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.models.CodePartDTO;
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
    public ResponseEntity<CodePartDTO> getApiCode() {
        CodePartDTO codePartDTO = htmlService.getApiCode();
        log.info("Api code: {}", codePartDTO);
        return new ResponseEntity<>(codePartDTO, HttpStatus.OK);
    }

}