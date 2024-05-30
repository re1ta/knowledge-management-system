package ru.richieernest.knowledgeManagementSystem.dto.historyDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditDto {

    private String title;
    private String content;
    private Long id_version;
    private List<VersionDto> versions;
}
