package org.tisi.repository;

import org.tisi.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByIsReturnedFalse();
}
