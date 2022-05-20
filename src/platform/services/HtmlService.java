package platform.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.entities.CodeSnippet;
import platform.exceptions.NotFoundCodeSnippetException;
import platform.models.CodeSnippetDTO;
import platform.models.mappers.CodeSnippetMapper;
import platform.repositories.CodeSnippetRepository;
import platform.utils.DateFormatUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HtmlService {

    private final CodeSnippetMapper mapper;
    private final CodeSnippet currentCodeSnippet;
    private final CodeSnippetRepository codeSnippetRepository;

    @Autowired
    public HtmlService(CodeSnippetMapper mapper, CodeSnippetRepository codeSnippetRepository) {
        this.mapper = mapper;
        this.currentCodeSnippet = new CodeSnippet();
        this.codeSnippetRepository = codeSnippetRepository;
    }

    public CodeSnippetDTO getStartCodeSnippet() {
        CodeSnippetDTO codeSnippetDTO = mapper.codePartToCodePartDTO(currentCodeSnippet);
        LocalDateTime localDateTime = LocalDateTime.now();// loaded date
        String date = localDateTime.format(DateTimeFormatter.ofPattern(DateFormatUtil.DATE_FORMAT));
        codeSnippetDTO.setDate(date);
        return codeSnippetDTO;
    }

    public Long postSnippet(CodeSnippetDTO codeSnippetDTO) {
        String incomingCode = codeSnippetDTO.getCode();
        CodeSnippet codeSnippet = mapper.codePartDTOToCodePart(codeSnippetDTO);
        LocalDateTime localDateTime = LocalDateTime.now();
        codeSnippet.setDate(localDateTime);
        CodeSnippet saved = codeSnippetRepository.save(codeSnippet);
        currentCodeSnippet.setCode(incomingCode);
        log.info("Saved code snippet {}", saved);
        return saved.getId();
    }

    public CodeSnippetDTO getCodeSnippetById(Long id) {
        Optional<CodeSnippet> codeSnippet = codeSnippetRepository.findById(id);
        if (codeSnippet.isEmpty()) {
            throw new NotFoundCodeSnippetException(id);
        }
        return mapper.codePartToCodePartDTO(codeSnippet.get());
    }

    public List<CodeSnippetDTO> getLatestSnippets() {
        List<CodeSnippet> codes = codeSnippetRepository.findLast10CodeSnippets();
        return codes.stream().map(mapper::codePartToCodePartDTO).collect(Collectors.toList());
    }

}