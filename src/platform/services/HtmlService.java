package platform.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.entities.CodePart;
import platform.models.CodePartDTO;
import platform.models.mappers.CodePartMapper;
import platform.repositories.HtmlMockRepository;

@Service
public class HtmlService {

    private final HtmlMockRepository htmlRepository;
    private final CodePartMapper mapper;

    @Autowired
    public HtmlService(HtmlMockRepository htmlRepository, CodePartMapper mapper) {
        this.htmlRepository = htmlRepository;
        this.mapper = mapper;
    }

    public String getCodePart() {
        return htmlRepository.find().getCode();
    }

    public CodePartDTO getApiCode() {
        CodePart code = htmlRepository.find();
        return mapper.codePartToCodePartDTO(code);
    }

}