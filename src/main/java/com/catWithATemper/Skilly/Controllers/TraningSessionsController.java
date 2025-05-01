package com.catWithATemper.Skilly.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.catWithATemper.Skilly.repositories.SkillRepository;
import com.catWithATemper.Skilly.repositories.TrainingSessionRepository;
import com.catWithATemper.Skilly.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.catWithATemper.Skilly.DTOs.TrainingSessionDTO;
import com.catWithATemper.Skilly.domain.Skill;
import com.catWithATemper.Skilly.domain.TrainingSession;
import com.catWithATemper.Skilly.domain.User;
import com.catWithATemper.Skilly.mapping.TrainingSessionMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/trainingsessions")
public class TraningSessionsController {

    @Autowired
    private TrainingSessionRepository trainingSessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping
    public List<TrainingSession> getAllTrainingSessions() {
        return trainingSessionRepository.findAll();
    }

    @GetMapping("(/id)")
    public ResponseEntity<TrainingSession> getTrainingSessionById(@PathVariable Long id) {
        return trainingSessionRepository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TrainingSession> postMethodName(@RequestBody TrainingSessionDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found."));

        Skill skill = skillRepository.findById(dto.getSkillId())
                .orElseThrow(() -> new RuntimeException("Skill not found."));

        TrainingSession session = TrainingSessionMapper.fromDTO(dto, user, skill);

        return new ResponseEntity<>(trainingSessionRepository.save(session), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingSession(@PathVariable Long id) {
        if (!(trainingSessionRepository.existsById(id))) {
            return ResponseEntity.notFound().build();
        }

        trainingSessionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
