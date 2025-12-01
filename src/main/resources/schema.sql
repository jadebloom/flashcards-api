DROP TABLE IF EXISTS flashcard_category;

DROP TABLE IF EXISTS flashcard;

CREATE TABLE flashcard_category (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE flashcard (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    flashcard_category_id BIGINT,
    front_text VARCHAR(255) NOT NULL,
    back_text VARCHAR(255) NOT NULL,
    FOREIGN KEY (flashcard_category_id) REFERENCES flashcard_category(id)
);
