package platform.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.entities.CodeSnippet;
import platform.models.CodeSnippetDTO;
import platform.models.mappers.CodeSnippetMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
public class HtmlService {

    private static final String DATE_FORMAT = "yyyy/dd/MM HH:mm:ss";

    private final CodeSnippetMapper mapper;
    private CodeSnippet code;

    @Autowired
    public HtmlService(CodeSnippetMapper mapper) {
        this.mapper = mapper;
        this.code = new CodeSnippet();
    }

    public CodeSnippetDTO getCodeSnippet() {
        CodeSnippetDTO codeSnippetDTO = mapper.codePartToCodePartDTO(code);
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        codeSnippetDTO.setDate(date);
        return codeSnippetDTO;
    }

    public void postSnippet(CodeSnippetDTO codeSnippetDTO) {
        String incomingCode = codeSnippetDTO.getCode();
        if(Objects.isNull(code)) {
            throw new NullPointerException();
        }
        code.setCode(incomingCode);
    }

}