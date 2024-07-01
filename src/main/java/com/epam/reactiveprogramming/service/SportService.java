package com.epam.reactiveprogramming.service;

import com.epam.reactiveprogramming.exception.SportAlreadyExistException;
import com.epam.reactiveprogramming.model.Sport;
import com.epam.reactiveprogramming.repository.SportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class SportService {

    private final SportRepository sportRepository;

    @Autowired
    public SportService(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    public Flux<Sport> getAll() {
        return sportRepository.findAll().limitRate(20).log();
    }

    public Mono<Sport> getByName(final String name) {
        return sportRepository.findByName(name);
    }

    public Mono<Sport> create(final String name) {
        return sportRepository.existsByName(name)
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new SportAlreadyExistException());
                    } else {
                        return sportRepository.insert(new Sport(name)).log();
                    }
                });
    }
}
