package ru.dponyashov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dponyashov.dto.ArmorDTO;
import ru.dponyashov.dto.ItemDTO;
import ru.dponyashov.dto.PersonDTO;
import ru.dponyashov.dto.WeaponDTO;
import ru.dponyashov.services.PersonService;

import java.util.*;

@RestController
//@CrossOrigin(origins = {"127.0.0.1:8080", "localhost:8080"})
@RequiredArgsConstructor
@RequestMapping("persons")
public class PersonController {
    private final PersonService personService;

    @GetMapping
    public Collection<PersonDTO> getPersons(){
        return personService.getPersons();
    }

    @GetMapping("/{id:\\d+}")
    public PersonDTO getPersonById(@PathVariable("id") Long id){
        return personService.getPersonById(id);
    }

    @PostMapping
    public PersonDTO saveNewPerson(@RequestBody PersonDTO newPerson){
        return personService.save(newPerson);
    }

    @PutMapping("/{id:\\d+}")
    public PersonDTO savePerson(@PathVariable("id") Long id, @RequestBody PersonDTO newPerson){
        return personService.save(id, newPerson);
    }

    @DeleteMapping("/{id:\\d+}")
    public void deletePersonById(@PathVariable("id") Long id){
        personService.deletePersonById(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handlerNoSuchElementException(NoSuchElementException exception,
                                                                       Locale locale){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                        "Персонаж не найден!")
                );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ProblemDetail> handlerRuntimeException(RuntimeException exception,
                                                                       Locale locale){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                        "Попытка добавить уже существующие данные(Weapon, Armor, Items) для персонажа")
                );
    }

//    Weapons
    @GetMapping("/{id:\\d+}/weapons")
    public Collection<WeaponDTO> getPersonWeapon(@PathVariable("id") Long id){
        return personService.getWeaponsByPerson(id);
    }

    @PostMapping("/{id:\\d+}/weapons/{idWeapon:\\d+}")
    public Collection<WeaponDTO> savePersonWeapon(@PathVariable("id") Long id, @PathVariable("idWeapon") Long idWeapon){
        return personService.saveWeaponsByPerson(id, idWeapon);
    }

    @DeleteMapping("/{id:\\d+}/weapons/{idWeapon:\\d+}")
    public Collection<WeaponDTO> deletePersonWeapon(@PathVariable("id") Long id, @PathVariable("idWeapon") Long idWeapon){
        return personService.deleteWeaponByPerson(id, idWeapon);
    }

//    Armors
    @GetMapping("/{id:\\d+}/armors")
    public Collection<ArmorDTO> getPersonArmor(@PathVariable("id") Long id){
        return personService.getArmorsByPerson(id);
    }

    @PostMapping("/{id:\\d+}/armors/{idArmor:\\d+}")
    public Collection<ArmorDTO> savePersonArmor(@PathVariable("id") Long id, @PathVariable("idArmor") Long idArmor){
        return personService.saveArmorsByPerson(id, idArmor);
    }

    @DeleteMapping("/{id:\\d+}/armors/{idArmor:\\d+}")
    public Collection<ArmorDTO> deletePersonArmor(@PathVariable("id") Long id, @PathVariable("idArmor") Long idArmor){
        return personService.deleteArmorByPerson(id, idArmor);
    }

//    Items
    @GetMapping("/{id:\\d+}/items")
    public Collection<ItemDTO> getPersonItem(@PathVariable("id") Long id){
        return personService.getItemsByPerson(id);
    }

    @PostMapping("/{id:\\d+}/items/{idItem:\\d+}")
    public Collection<ItemDTO> savePersonItem(@PathVariable("id") Long id, @PathVariable("idItem") Long idItem){
        return personService.saveItemsByPerson(id, idItem);
    }

    @DeleteMapping("/{id:\\d+}/items/{idItem:\\d+}")
    public Collection<ItemDTO> deletePersonItem(@PathVariable("id") Long id, @PathVariable("idItem") Long idItem){
        return personService.deleteItemByPerson(id, idItem);
    }
}
