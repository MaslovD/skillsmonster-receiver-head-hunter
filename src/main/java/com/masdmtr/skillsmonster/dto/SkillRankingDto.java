package com.masdmtr.skillsmonster.dto;

import java.util.List;

public class SkillRankingDto {

    private List<SkillRankingItem> skillRankingDtos;

    public List<SkillRankingItem> getSkillRankingDtos() {
        return skillRankingDtos;
    }

    public void setSkillRankingDtos(List<SkillRankingItem> skillRankingDtos) {
        this.skillRankingDtos = skillRankingDtos;
    }
}
