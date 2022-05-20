package platform.models.mappers;

import lombok.Data;
import org.springframework.stereotype.Component;
import platform.entities.CodeSnippet;
import platform.models.CodeSnippetDTO;
import platform.utils.DateFormatUtil;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
        if(Objects.nonNull(codeSnippet.getDate())) {
            String date = codeSnippet.getDate().format(DateTimeFormatter.ofPattern(DateFormatUtil.DATE_FORMAT));
            codeSnippetDTO.setDate(date);
        }
        codeSnippetDTO.setCode(codeSnippet.getCode());
        return codeSnippetDTO;
    }

}

