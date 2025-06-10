package ru.dponyashov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dponyashov.dto.ArmorDTO;
import ru.dponyashov.services.ArmorService;

import java.util.Collection;
import java.util.Locale;
import java.util.NoSuchElementException;

@RestController
//@CrossOrigin(origins = {"127.0.0.1:8080", "localhost:8080"})
@RequiredArgsConstructor
@RequestMapping("armors")
public class ArmorController {
    private final ArmorService armorService;

    @GetMapping
    public Collection<ArmorDTO> getArmors() {
        return armorService.getArmors();
    }

    @GetMapping("/{id:\\d+}")
    public ArmorDTO getArmorById(@PathVariable("id") Long id) {
        return armorService.getArmorById(id);
    }

    @PostMapping
    public ArmorDTO saveNewArmor(@RequestBody ArmorDTO newArmor) {
        return armorService.save(newArmor);
    }

    @PutMapping("/{id:\\d+}")
    public ArmorDTO saveArmor(@PathVariable("id") Long id, @RequestBody ArmorDTO newArmor) {
//        newArmor.setId(id);
        return armorService.save(id, newArmor);
    }

    @DeleteMapping("/{id:\\d+}")
    public void deleteArmor(@PathVariable("id") Long id) {
        armorService.deleteArmorById(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handlerNoSuchElementException(NoSuchElementException exception,
                                                                       Locale locale) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                        "Снаряжение не найдено!")
                );
    }
}
