package platform.entities;

import lombok.*;

@Getter
@Setter
@ToString
public class CodeSnippet {

    private String code;

    public CodeSnippet() {
        this.code = "public static void main(String[] args) {\n" +
                "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
                "}";
    }

}
