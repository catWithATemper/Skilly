package com.catWithATemper.Skilly.DTOs;

import java.util.Set;
import com.catWithATemper.Skilly.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SkillDTO {
    private Long id;
    private String name;
    private Category category;
    private String description;
    private Set<Long> trainingSessionIds;
}
