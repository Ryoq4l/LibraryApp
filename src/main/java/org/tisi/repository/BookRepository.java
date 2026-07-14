package org.tisi.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.tisi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    List<Book> findByTitleContainingIgnoreCase(String title);
@EntityGraph(value = "book.authors", type = EntityGraph.EntityGraphType.LOAD)
    List<Book>findDistinctByAuthorId(Long authorId);
    List<Book> findByPublicationBetween(Integer startY, Integer endY);


}
