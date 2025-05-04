package com.catWithATemper.Skilly.mapping;

import java.util.Set;
import java.util.stream.Collectors;
import com.catWithATemper.Skilly.DTOs.UserDTO;
import com.catWithATemper.Skilly.domain.TrainingSession;
import com.catWithATemper.Skilly.domain.User;

public class UserMapper {

    public static User fromDTO(UserDTO dto, Set<TrainingSession> trainingSessions) {
        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setTrainingSessions(trainingSessions);

        return user;
    }

    public static UserDTO toDTO(User entity) {
        UserDTO user = new UserDTO();

        user.setId(entity.getId());
        user.setName(entity.getName());
        user.setEmail(entity.getEmail());
        user.setTrainingSessionIds(entity.getTrainingSessions().stream().map(TrainingSession::getId)
                .collect(Collectors.toSet()));

        return user;
    }
}
