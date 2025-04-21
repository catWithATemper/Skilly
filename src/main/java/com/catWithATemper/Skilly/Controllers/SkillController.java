package com.catWithATemper.Skilly.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.catWithATemper.Skilly.domain.Skill;
import com.catWithATemper.Skilly.repositories.SkillRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable Long id) {
        return skillRepository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Skill> postMethodName(@RequestBody Skill skill) {
        Skill savedSkill = skillRepository.save(skill);
        return new ResponseEntity<>(savedSkill, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!(skillRepository.existsById(id))) {
            return ResponseEntity.noContent().build();
        }

        skillRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
