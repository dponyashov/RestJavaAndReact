package ru.dponyashov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dponyashov.dto.WeaponDTO;
import ru.dponyashov.services.WeaponService;

import java.util.Collection;
import java.util.Locale;
import java.util.NoSuchElementException;

@RestController
//@CrossOrigin(origins = {"127.0.0.1:8080", "localhost:8080"})
@RequiredArgsConstructor
@RequestMapping("weapons")
public class WeaponController {
    private final WeaponService weaponService;

    @GetMapping
    public Collection<WeaponDTO> getWeapons() {
        return weaponService.getWeapons();
    }

    @GetMapping("/{id:\\d+}")
    public WeaponDTO getWeaponById(@PathVariable("id") Long id) {
        return weaponService.getWeaponById(id);
    }

    @PostMapping
    public WeaponDTO saveNewWeapon(@RequestBody WeaponDTO newWeapon) {
        return weaponService.save(newWeapon);
    }

    @PutMapping("/{id:\\d+}")
    public WeaponDTO saveWeapon(@PathVariable("id") Long id, @RequestBody WeaponDTO newWeapon) {
//        newWeapon.setId(id);
        return weaponService.save(id, newWeapon);
    }

    @DeleteMapping("/{id:\\d+}")
    public void deleteWeapon(@PathVariable("id") Long id) {
        weaponService.deleteWeaponById(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handlerNoSuchElementException(NoSuchElementException exception,
                                                                       Locale locale) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                        "Оружие не найдено!")
                );
    }
}
