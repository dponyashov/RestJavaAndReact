package ru.dponyashov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dponyashov.dto.ProfessionDTO;
import ru.dponyashov.services.ProfessionService;

import java.util.Collection;
import java.util.Locale;
import java.util.NoSuchElementException;

@RestController
//@CrossOrigin(origins = {"127.0.0.1:8080", "localhost:8080"})
@RequiredArgsConstructor
@RequestMapping("professions")
public class ProfessionController {
    private final ProfessionService professionService;

    @GetMapping
    public Collection<ProfessionDTO> getProfessions() {
        return professionService.getProfessions();
    }

    @GetMapping("/{id:\\d+}")
    public ProfessionDTO getProfessionById(@PathVariable("id") Long id) {
        return professionService.getProfessionById(id);
    }

    @PostMapping
    public ProfessionDTO saveNewProfession(@RequestBody ProfessionDTO newProfession) {
        return professionService.save(newProfession);
    }

    @PutMapping("/{id:\\d+}")
    public ProfessionDTO saveProfession(@PathVariable("id") Long id, @RequestBody ProfessionDTO newProfession) {
        return professionService.save(id, newProfession);
    }

    @DeleteMapping("/{id:\\d+}")
    public void deleteProfession(@PathVariable("id") Long id) {
        professionService.deleteProfessionById(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handlerNoSuchElementException(NoSuchElementException exception,
                                                                       Locale locale) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                        "Профессия не найдена!")
                );
    }
}
