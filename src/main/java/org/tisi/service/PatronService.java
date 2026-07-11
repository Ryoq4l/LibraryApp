package org.tisi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tisi.dto.PatronDto;
import org.tisi.mapper.PatronMapper;
import org.tisi.model.Patron;
import org.tisi.repository.PatronRepository;


@Service
@RequiredArgsConstructor
public class PatronService {
    private final PatronRepository patronRepo;
    private final PatronMapper patronMapper;

    public void createPatron(PatronDto patronDto){
        Patron patron = patronMapper.map(patronDto);
        patronRepo.saveAndFlush(patron);
    }
}
