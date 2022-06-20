package platform.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "code_snippet")
@Getter
@Setter
@ToString
public class CodeSnippet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String code;

    private LocalDateTime date;

}
