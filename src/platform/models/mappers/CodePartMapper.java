package platform.models.mappers;

import lombok.Data;
import org.springframework.stereotype.Component;
import platform.entities.CodePart;
import platform.models.CodePartDTO;

@Data
@Component
public class CodePartMapper {

    public CodePart codePartDTOToCodePart(CodePartDTO codePartDTO) {
        CodePart codePart = new CodePart();
        codePart.setCode(codePartDTO.getCode());
        return codePart;
    }

    public CodePartDTO codePartToCodePartDTO(CodePart codePart) {
        CodePartDTO codePartDTO = new CodePartDTO();
        codePartDTO.setCode(codePart.getCode());
        return codePartDTO;
    }

}

