package com.catWithATemper.Skilly.mapping;

import com.catWithATemper.Skilly.DTOs.TrainingSessionDTO;
import com.catWithATemper.Skilly.domain.Skill;
import com.catWithATemper.Skilly.domain.TrainingSession;
import com.catWithATemper.Skilly.domain.User;

public class TrainingSessionMapper {

    public static TrainingSession fromDTO(TrainingSessionDTO dto, User user, Skill skill) {
        TrainingSession session = new TrainingSession();

        session.setDate(dto.getDate());
        session.setDurationMinutes(dto.getDurationMinutes());
        session.setNotes(dto.getNotes());
        session.setRating(dto.getRating());
        session.setUser(user);
        session.setSkill(skill);

        return session;
    }
}
