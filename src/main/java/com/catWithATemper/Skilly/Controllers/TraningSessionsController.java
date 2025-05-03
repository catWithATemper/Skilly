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
import org.springframework.web.bind.annotation.PutMapping;

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
    public ResponseEntity<List<TrainingSessionDTO>> getAllTrainingSessions() {
        List<TrainingSessionDTO> sessions = trainingSessionRepository.findAll().stream()
                .map(TrainingSessionMapper::toDTO).toList();

        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingSessionDTO> getTrainingSessionById(@PathVariable Long id) {
        return trainingSessionRepository.findById(id)
                .map(session -> ResponseEntity.ok(TrainingSessionMapper.toDTO(session)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TrainingSession> createTraningSession(
            @RequestBody TrainingSessionDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found."));

        Skill skill = skillRepository.findById(dto.getSkillId())
                .orElseThrow(() -> new RuntimeException("Skill not found."));

        TrainingSession session = TrainingSessionMapper.fromDTO(dto, user, skill);

        return new ResponseEntity<>(trainingSessionRepository.save(session), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingSession> updateTrainingSession(@PathVariable Long id,
            @RequestBody TrainingSessionDTO dto) {
        return trainingSessionRepository.findById(id).map(existingSession -> {
            existingSession.setDate(dto.getDate());
            existingSession.setDurationMinutes(dto.getDurationMinutes());
            existingSession.setNotes(dto.getNotes());
            existingSession.setRating(dto.getRating());
            if (dto.getSkillId() != null) {
                skillRepository.findById(dto.getSkillId()).ifPresent(existingSession::setSkill);
            }
            trainingSessionRepository.save(existingSession);

            return ResponseEntity.ok(existingSession);
        }).orElseGet(() -> ResponseEntity.notFound().build());
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
