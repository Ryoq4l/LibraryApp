package org.tisi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tisi.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowRecord, Long> {
    @Query("SELECT br FROM BorrowRecord br " +
            "JOIN FETCH br.book " +
            "JOIN FETCH br.patron " +
            "WHERE br.patron.patronId = :patronId")
    List<BorrowRecord> findByPatronIdWithDetails(@Param("patronId") Long patronId);
}

