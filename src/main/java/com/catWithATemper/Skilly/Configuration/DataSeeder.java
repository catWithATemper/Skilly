package com.catWithATemper.Skilly.Configuration;

import java.time.LocalDate;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import com.catWithATemper.Skilly.domain.Category;
import com.catWithATemper.Skilly.domain.Skill;
import com.catWithATemper.Skilly.domain.TrainingSession;
import com.catWithATemper.Skilly.domain.User;
import com.catWithATemper.Skilly.repositories.SkillRepository;
import com.catWithATemper.Skilly.repositories.TrainingSessionRepository;
import com.catWithATemper.Skilly.repositories.UserRepository;

@Component
public class DataSeeder implements CommandLineRunner {

        private final UserRepository userRepository;
        private final SkillRepository skillRepository;
        private final TrainingSessionRepository trainingSessionRepository;

        public DataSeeder(UserRepository userRepository, SkillRepository skillRepository,
                        TrainingSessionRepository trainingSessionRepository) {
                this.userRepository = userRepository;
                this.skillRepository = skillRepository;
                this.trainingSessionRepository = trainingSessionRepository;
        }

        @Override
        public void run(String... args) {

                // 👤 Users
                User alice = new User("Alice Johnson", "alice@example.com");
                User bob = new User("Bob Smith", "bob@example.com");
                User clara = new User("Clara Nguyen", "clara@example.com");

                userRepository.saveAll(List.of(alice, bob, clara));

                // 🧠 Skills
                Skill java = new Skill("Java", Category.TECHNICAL,
                                "Object-oriented programming language");
                Skill communication = new Skill("Communication", Category.SOFT_SKILLS,
                                "Effective workplace communication");
                Skill projectManagement = new Skill("Project Management", Category.MANAGEMENT,
                                "Planning and overseeing projects");
                Skill french = new Skill("French", Category.OTHER,
                                "Beginner level French language");

                skillRepository.saveAll(List.of(java, communication, projectManagement, french));

                // 🗓 Training Sessions
                TrainingSession session1 = new TrainingSession(LocalDate.of(2024, 12, 5), 90,
                                "Completed Java course on Udemy", 5, alice, java);
                TrainingSession session2 = new TrainingSession(LocalDate.of(2025, 1, 15), 45,
                                "Watched communication TED Talk", 5, clara, communication);
                TrainingSession session3 = new TrainingSession(LocalDate.of(2025, 2, 20), 60,
                                "Agile training workshop", 5, alice, projectManagement);
                TrainingSession session4 = new TrainingSession(LocalDate.of(2025, 3, 8), 30,
                                "Duolingo practice session", 5, bob, french);
                TrainingSession session5 = new TrainingSession(LocalDate.of(2025, 4, 2), 75,
                                "Refresher on Java streams", 5, bob, java);

                trainingSessionRepository
                                .saveAll(List.of(session1, session2, session3, session4, session5));

        }
}
