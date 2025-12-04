package com.jadebloom.flashcards.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.jadebloom.flashcards.domain.dto.FlashcardCategoryDto;
import com.jadebloom.flashcards.domain.entity.FlashcardCategoryEntity;
import com.jadebloom.flashcards.mapper.Mapper;

@Component
public class FlashcardCategoryMapperImpl implements Mapper<FlashcardCategoryEntity, FlashcardCategoryDto> {

    private final ModelMapper modelMapper;

    public FlashcardCategoryMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public FlashcardCategoryDto mapTo(FlashcardCategoryEntity flashcardCategoryEntity) {
        return modelMapper.map(flashcardCategoryEntity, FlashcardCategoryDto.class);
    }

    @Override
    public FlashcardCategoryEntity mapFrom(FlashcardCategoryDto flashcardCategoryDto) {
        return modelMapper.map(flashcardCategoryDto, FlashcardCategoryEntity.class);
    }

}
