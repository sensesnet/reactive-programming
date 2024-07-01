package com.epam.reactiveprogramming.repository;

import com.epam.reactiveprogramming.model.Sport;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SportRepository extends ReactiveMongoRepository<Sport, String> {

    Mono<Sport> findByName(String name);

    Mono<Boolean> existsByName(String name);
}
