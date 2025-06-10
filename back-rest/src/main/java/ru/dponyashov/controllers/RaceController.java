package ru.dponyashov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dponyashov.dto.RaceDTO;
import ru.dponyashov.services.RaceService;

import java.util.Collection;
import java.util.Locale;
import java.util.NoSuchElementException;

@RestController
//@CrossOrigin(origins = {"127.0.0.1:8080", "localhost:8080"})
@RequiredArgsConstructor
@RequestMapping("races")
public class RaceController {
    private final RaceService raceService;

    @GetMapping
    public Collection<RaceDTO> getRaces() {
        return raceService.getRaces();
    }

    @GetMapping("/{id:\\d+}")
    public RaceDTO getRaceById(@PathVariable("id") Long id) {
        return raceService.getRaceById(id);
    }

    @PostMapping
    public RaceDTO saveNewRace(@RequestBody RaceDTO newRace) {
        return raceService.save(newRace);
    }

    @PutMapping("/{id:\\d+}")
    public RaceDTO saveRace(@PathVariable("id") Long id, @RequestBody RaceDTO newRace) {
        return raceService.save(id, newRace);
    }

    @DeleteMapping("/{id:\\d+}")
    public void deleteRace(@PathVariable("id") Long id) {
        raceService.deleteRaceById(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handlerNoSuchElementException(NoSuchElementException exception,
                                                                       Locale locale) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                        "Раса не найдена!")
                );
    }
}
