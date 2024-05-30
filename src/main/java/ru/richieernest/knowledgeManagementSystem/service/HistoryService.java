package ru.richieernest.knowledgeManagementSystem.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.richieernest.knowledgeManagementSystem.dto.historyDto.EditDto;
import ru.richieernest.knowledgeManagementSystem.dto.historyDto.TextDto;
import ru.richieernest.knowledgeManagementSystem.dto.historyDto.UpdateVersionDto;
import ru.richieernest.knowledgeManagementSystem.entity.Article;
import ru.richieernest.knowledgeManagementSystem.entity.ArticleVersion;
import ru.richieernest.knowledgeManagementSystem.entity.HistoryArticle;
import ru.richieernest.knowledgeManagementSystem.repository.ArticleRepo;
import ru.richieernest.knowledgeManagementSystem.repository.ArticleVersionRepo;
import ru.richieernest.knowledgeManagementSystem.repository.HistoryRepo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepo historyRepo;
    private final ArticleRepo articleRepo;
    private final ArticleVersionRepo articleVersionRepo;

    public EditDto viewAll(Long id){
        Article article = articleRepo.findById(id).get();
        Optional<ArticleVersion> versionOp = articleVersionRepo.findById(id);
        Long version;
        if (versionOp.isEmpty()){
            HistoryArticle historyArticle = HistoryArticle.builder()
                    .title(article.getTitle())
                    .content(article.getContent())
                    .id_article(article.getId())
                    .createdAt(article.getCreatedAt())
                    .build();
            historyRepo.save(historyArticle);
            var a = ArticleVersion.builder()
                    .id_article(id)
                    .id_version(historyArticle.getId())
                    .build();
            articleVersionRepo.save(a);
            version = articleVersionRepo.findById(id).get().getId_version();
        }
        else{ version = versionOp.get().getId_version(); }

        var a = historyRepo.findAllByIdArticle(id);

        return EditDto.builder()
                .title(article.getTitle())
                .content(article.getContent())
                .id_version(version)
                .versions(a)
                .build();
    }

    public TextDto findVersion(Long id_version){
        HistoryArticle historyArticle = historyRepo.findByIdVersion(id_version);
        return TextDto.builder()
                .title(historyArticle.getTitle())
                .content(historyArticle.getContent())
                .build();
    }

    @Transactional
    public void delete(Long id_version){
        historyRepo.deleleByIdVersion(id_version);
    }
    @Transactional
    public void deleteAll(Long articleId){
        historyRepo.deleleAllById(articleId);
    }

    @Transactional
    public void saveVersion(UpdateVersionDto updateVersionDto){
        HistoryArticle historyArticle = historyRepo.findByIdVersion(updateVersionDto.getId_version());
        if (!historyArticle.getContent().equals(updateVersionDto.getContent()) ||
                !historyArticle.getTitle().equals(updateVersionDto.getTitle())){
            HistoryArticle hisArt = HistoryArticle.builder()
                    .title(updateVersionDto.getTitle())
                    .content(updateVersionDto.getContent())
                    .id_article(updateVersionDto.getId_article())
                    .createdAt(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'").format(new Date()))
                    .build();
            historyRepo.save(hisArt);
            System.out.println(hisArt.getId());
            articleVersionRepo.changeVersion(updateVersionDto.getId_article(),hisArt.getId());
        }
        else{
            articleVersionRepo.changeVersion(updateVersionDto.getId_article(),updateVersionDto.getId_version());
        }
        articleRepo.updateContent(updateVersionDto.getContent(), updateVersionDto.getTitle(), updateVersionDto.getId_article());
    }
}
