package org.tisi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tisi.dto.PatronDto;
import org.tisi.model.BorrowRecord;
import org.tisi.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {
    Optional<Patron> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Patron> findById(String patronId);

    Optional<Patron> findByPhoneNumber(String phoneNumber);

    Optional<Patron> findByPatronName(String patronName);

    List<Patron> getPatronsByRegistrationBetween(LocalDate start, LocalDate end);

}
