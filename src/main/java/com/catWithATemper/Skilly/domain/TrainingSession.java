package com.catWithATemper.Skilly.domain;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class TrainingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;

    private int durationMinutes;

    private String notes;

    @Min(1)
    @Max(5)
    private int rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    // @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    @JsonIgnore
    private Skill skill;

    protected TrainingSession() {
        // Required by JPA
    }

    public TrainingSession(LocalDate date, int durationMinutes, String notes, int rating, User user,
            Skill skill) {
        this.date = date;
        this.durationMinutes = durationMinutes;
        this.notes = notes;
        this.rating = rating;
        this.user = user;
        this.skill = skill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "TrainingSession{" + "id=" + id + ", date=" + date + ", durationMinutes="
                + durationMinutes + ", notes=" + notes + "rating=" + rating + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TrainingSession))
            return false;

        TrainingSession trainingSession = (TrainingSession) o;

        return getId() != null ? getId().equals(trainingSession.getId())
                : trainingSession.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
