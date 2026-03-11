package top.lmix.sitelmix.tools.shortener;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlEntity,Long>{
    UrlEntity findByUrlOriginal(String urlOriginal);
    UrlEntity findByUrlEncurtada(String urlEncurtada);
    Boolean existsByUrlOriginal(String urlOriginal);
}
