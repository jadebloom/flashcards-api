package com.jadebloom.flashcards.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "flashcard")
public class FlashcardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flashcard_category_id")
    private FlashcardCategoryEntity flashcardCategory;

    @Column(nullable = false)
    private String frontText;

    @Column(nullable = false)
    private String backText;

    public FlashcardEntity() {
    }

    public FlashcardEntity(FlashcardCategoryEntity flashcardCategoryEntity, String frontText, String backText) {
        this.flashcardCategory = flashcardCategoryEntity;
        this.frontText = frontText;
        this.backText = backText;
    }

    public Long getId() {
        return id;
    }

    public FlashcardCategoryEntity getFlashcardCategory() {
        return flashcardCategory;
    }

    public String getFrontText() {
        return frontText;
    }

    public String getBackText() {
        return backText;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFlashcardCategory(FlashcardCategoryEntity flashcardCategoryEntity) {
        this.flashcardCategory = flashcardCategoryEntity;
    }

    public void setFrontText(String frontText) {
        this.frontText = frontText;
    }

    public void setBackText(String backText) {
        this.backText = backText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FlashcardEntity flashcardEntity = (FlashcardEntity) o;

        return id == flashcardEntity.getId();
    }

}
