package platform.repositories;

import org.springframework.stereotype.Repository;
import platform.entities.CodePart;

@Repository
public class HtmlMockRepository {

    public CodePart find() {
        return new CodePart("public static void main(String[] args) {\n" +
                "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
                "}");
    }

}