package com.javarush.dto.mapper;

import com.javarush.dto.FilmDto;
import com.javarush.entity.Film;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FilmMapper {

    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    FilmDto toDto(Film film);
    Film toEntity(FilmDto filmDto);
}
