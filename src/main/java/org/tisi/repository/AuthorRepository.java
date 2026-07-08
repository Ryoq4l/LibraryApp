package org.tisi.repository;

import org.springframework.data.jpa.repository.Query;
import org.tisi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository  extends JpaRepository <Author, Long>{

}
