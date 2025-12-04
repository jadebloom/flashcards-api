package com.jadebloom.flashcards.mapper;

public interface Mapper<T, S> {

    S mapTo(T t);

    T mapFrom(S s);

}
