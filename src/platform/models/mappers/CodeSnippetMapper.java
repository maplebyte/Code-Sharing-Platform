package platform.models.mappers;

import lombok.Data;
import org.springframework.stereotype.Component;
import platform.entities.CodeSnippet;
import platform.models.CodeSnippetDTO;

@Data
@Component
public class CodeSnippetMapper {

    public CodeSnippet codePartDTOToCodePart(CodeSnippetDTO codeSnippetDTO) {
        CodeSnippet codeSnippet = new CodeSnippet();
        codeSnippet.setCode(codeSnippetDTO.getCode());
        return codeSnippet;
    }

    public CodeSnippetDTO codePartToCodePartDTO(CodeSnippet codeSnippet) {
        CodeSnippetDTO codeSnippetDTO = new CodeSnippetDTO();
        codeSnippetDTO.setCode(codeSnippet.getCode());
        return codeSnippetDTO;
    }

}

