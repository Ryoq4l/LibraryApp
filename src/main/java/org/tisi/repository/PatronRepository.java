package org.tisi.repository;

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

    Optional<Patron> findByPhoneNumber(String phoneNumber);

    Optional<Patron> findByPatronName(String patronName);

    List<Patron> getPatronsByRegistrationDateBetween(LocalDate start, LocalDate end);

}
