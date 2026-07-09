package org.tisi.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Базовые настройки
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setAmbiguityIgnored(true);

        // ============================================================
        //  НАСТРОЙКА МАППИНГА ДЛЯ AUTHOR
        //  AuthorDto.name → Author.authorName
        // ============================================================
        modelMapper.typeMap(org.tisi.dto.AuthorDto.class, org.tisi.model.Author.class)
                .addMapping(org.tisi.dto.AuthorDto::getAuthorName, org.tisi.model.Author::setAuthorName);

        // ============================================================
        //  НАСТРОЙКА МАППИНГА ДЛЯ BOOK
        //  BookDto.year → Book.publicationYear
        //  BookDto.authorId → Book.authors (ручной маппинг в сервисе)
        // ============================================================
        modelMapper.typeMap(org.tisi.dto.BookDto.class, org.tisi.model.Book.class)
                .addMapping(org.tisi.dto.BookDto::getPublicationYear, org.tisi.model.Book::setPublicationYear);

        // ============================================================
        //  НАСТРОЙКА МАППИНГА ДЛЯ PATRON
        //  PatronDto.patronName → Patron.patronName (совпадают)
        // ============================================================
        // Если имена совпадают — ничего настраивать не нужно

        return modelMapper;
    }
}