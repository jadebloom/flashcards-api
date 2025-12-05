package com.jadebloom.flashcards.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.jadebloom.flashcards.domain.dto.FlashcardDto;
import com.jadebloom.flashcards.domain.entity.FlashcardEntity;
import com.jadebloom.flashcards.mapper.Mapper;

@Component
public class FlashcardMapperImpl implements Mapper<FlashcardEntity, FlashcardDto> {

    private final ModelMapper mapper;

    public FlashcardMapperImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public FlashcardDto mapTo(FlashcardEntity t) {
        return mapper.map(t, FlashcardDto.class);
    }

    @Override
    public FlashcardEntity mapFrom(FlashcardDto s) {
        return mapper.map(s, FlashcardEntity.class);
    }

}
