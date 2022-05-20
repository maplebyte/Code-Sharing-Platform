package platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import platform.entities.CodeSnippet;

import java.util.List;

public interface CodeSnippetRepository extends JpaRepository<CodeSnippet, Long> {

    @Query(value = "SELECT * FROM code_snippet ORDER BY date DESC LIMIT 10", nativeQuery = true)
    List<CodeSnippet> findLast10CodeSnippets();

}
