package com.jadebloom.flashcards.domain;

public class Flashcard {

    private Long id;

    private Long flashcardCategoryId;

    private String frontText;

    private String backText;

    public Flashcard(Long id, Long flashcardCategoryid, String frontText, String backText) {
        this.id = id;
        this.flashcardCategoryId = flashcardCategoryid;
        this.frontText = frontText;
        this.backText = backText;
    }

    public Flashcard(Long flashcardCategoryid, String frontText, String backText) {
        this.flashcardCategoryId = flashcardCategoryid;
        this.frontText = frontText;
        this.backText = backText;
    }

    public Long getId() {
        return id;
    }

    public Long getFlashcardCategoryId() {
        return flashcardCategoryId;
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

    public void setFlashcardCategoryId(Long flashcardCategoryId) {
        this.flashcardCategoryId = flashcardCategoryId;
    }

    public void setFrontText(String frontText) {
        this.frontText = frontText;
    }

    public void setBackText(String backText) {
        this.backText = backText;
    }

    @Override
    public String toString() {
        String s = "Flashcard: id = %d, flashcardCategoryId = %d, frontText = %s, backText = %s";

        return String.format(s, id, flashcardCategoryId, frontText, backText);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Flashcard flashcard = (Flashcard) o;

        if (id == null || flashcard.getId() == null) {
            return false;
        }

        return id == flashcard.getId();
    }

}
