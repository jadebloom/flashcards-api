package com.jadebloom.flashcards.domain;

public class FlashcardCategory {

    private Long id;

    private String name;

    public FlashcardCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public FlashcardCategory(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Flashcard category: id = %d, name = %s", id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FlashcardCategory flashcardCategory = (FlashcardCategory) o;

        if (id == null || flashcardCategory.getId() == null) {
            return false;
        }

        return id == flashcardCategory.getId();
    }

}
