package com.epam.reactiveprogramming.controller;

import com.epam.reactiveprogramming.service.SportService;
import com.epam.reactiveprogramming.model.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/sport")
public class SportController {

    private final SportService sportService;

    @Autowired
    public SportController(SportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/all")
    public Flux<Sport> getAll() {
        return sportService.getAll();
    }

    @GetMapping
    public Mono<Sport> getByName(@RequestParam final String sportname) {
        return sportService.getByName(sportname);
    }

    @PostMapping("/{sportname}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Sport> create(final String sportname) {
        return sportService.create(sportname);
    }
}
