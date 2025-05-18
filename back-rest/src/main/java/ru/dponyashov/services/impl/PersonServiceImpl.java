package ru.dponyashov.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.dponyashov.dto.*;
import ru.dponyashov.entity.*;
import ru.dponyashov.repositories.ArmorRepository;
import ru.dponyashov.repositories.ItemRepository;
import ru.dponyashov.repositories.PersonRepository;
import ru.dponyashov.repositories.WeaponRepository;
import ru.dponyashov.services.PersonService;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Log4j2
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final WeaponRepository weaponRepository;
    private final ArmorRepository armorRepository;
    private final ItemRepository itemRepository;

    @Override
    public Collection<PersonDTO> getPersons() {
        return personRepository.findAll().stream()
                .map(ent->PersonDTO.builder()
                        .id(ent.getId())
                        .logo(ent.getLogo())
                        .lvl(ent.getLvl())
                        .name(ent.getName())
                        .race(RaceDTO.builder()
                                .id(ent.getRace().getId())
                                .name(ent.getRace().getName())
                                .build()
                        )
                        .profession(ProfessionDTO.builder()
                                .id(ent.getProfession().getId())
                                .name(ent.getProfession().getName())
                                .build())
                        .description(ent.getDescription())
                        .build())
                .toList();
    }

    @Override
    public PersonDTO getPersonById(Long id) {
        return personRepository.findById(id)
                .map(ent->PersonDTO.builder()
                        .id(ent.getId())
                        .logo(ent.getLogo())
                        .lvl(ent.getLvl())
                        .name(ent.getName())
                        .race(RaceDTO.builder()
                                .id(ent.getRace().getId())
                                .name(ent.getRace().getName())
                                .build()
                        )
                        .profession(ProfessionDTO.builder()
                                .id(ent.getProfession().getId())
                                .name(ent.getProfession().getName())
                                .build())
                        .description(ent.getDescription())
                        .build())
                .orElseThrow(() -> new NoSuchElementException("Person не найден!"));
    }

    @Override
    public PersonDTO save(Long id, PersonDTO profession) {
        PersonDTO professionFromDB = getPersonById(id);
        if(professionFromDB == null){
            log.info("Не найден Person с id: {} для обновления", id);
        }

        profession.setId(id);
        return save(profession);
    }

    @Override
//    @Transactional
    public PersonDTO save(PersonDTO person) {
        PersonEntity ent = PersonEntity.builder()
                .id(person.getId())
                .logo(person.getLogo())
                .lvl(person.getLvl())
                .name(person.getName())
                .race(RaceEntity.builder()
                        .id(person.getRace().getId())
                        .name(person.getRace().getName())
                        .build()
                )
                .profession(ProfessionEntity.builder()
                        .id(person.getProfession().getId())
                        .name(person.getProfession().getName())
                        .build())
                .description(person.getDescription())
                .build();

        PersonEntity savedEnt = personRepository.save(ent);
        log.info("Записаны(обновлены) данные Person с id: {}", savedEnt.getId());
        return PersonDTO.builder()
                .name(savedEnt.getName())
                .id(savedEnt.getId())
                .build();
    }

    @Override
    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
        log.info("Удалены данные Person с id: {}", id);
    }

    @Override
    public Collection<WeaponDTO> getWeaponsByPerson(Long id) {
        PersonEntity ent = personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person не найден!"));

        return ent.getWeapons().stream().map(
                weapon -> WeaponDTO.builder()
                        .id(weapon.getId())
                        .name(weapon.getName())
                        .description(weapon.getDescription())
                        .build()
        ).toList();
    }

    @Override
    public Collection<WeaponDTO> saveWeaponsByPerson(Long id, Long idWeapon) {
        PersonEntity entPerson = personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person не найден!"));

        entPerson.getWeapons().forEach(weapon -> {
            if(weapon.getId().equals(idWeapon)){
                throw new RuntimeException("Weapon уже есть в списке для Person!");
            }
        });

        WeaponEntity entWeapon = weaponRepository.findById(idWeapon)
                .orElseThrow(() -> new NoSuchElementException("Weapon не найден!"));

        entPerson.getWeapons().add(entWeapon);

        log.info("Добавлен Weapon id {} для Person с id: {}", idWeapon, id);

        return personRepository.save(entPerson).getWeapons().stream()
                .map(
                        weapon -> {
                            return WeaponDTO.builder()
                                    .id(weapon.getId())
                                    .name(weapon.getName())
                                    .description(weapon.getDescription())
                                    .build();
                        }
                ).toList();
    }

    @Override
    public Collection<WeaponDTO> deleteWeaponByPerson(Long id, Long idWeapon) {
        PersonEntity entPerson = personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person не найден!"));

        WeaponEntity entWeapon = weaponRepository.findById(idWeapon)
                .orElseThrow(() -> new NoSuchElementException("Weapon не найден!"));

        entPerson.getWeapons().remove(entWeapon);

        log.info("Удален Weapon id {} для Person с id: {}", idWeapon, id);

        return personRepository.save(entPerson).getWeapons().stream()
                .map(
                        weapon -> {
                            return WeaponDTO.builder()
                                    .id(weapon.getId())
                                    .name(weapon.getName())
                                    .description(weapon.getDescription())
                                    .build();
                        }
                ).toList();
    }

    @Override
    public Collection<ItemDTO> deleteItemByPerson(Long id, Long idItem) {
        PersonEntity entPerson = personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person не найден!"));

        ItemEntity entWeapon = itemRepository.findById(idItem)
                .orElseThrow(() -> new NoSuchElementException("Item не найден!"));

        entPerson.getItems().remove(entWeapon);

        log.info("Удален Item id {} для Person с id: {}", idItem, id);

        return personRepository.save(entPerson).getItems().stream()
                .map(
                        item -> {
                            return ItemDTO.builder()
                                    .id(item.getId())
                                    .name(item.getName())
                                    .description(item.getDescription())
                                    .build();
                        }
                ).toList();
    }

    @Override
    public Collection<ItemDTO> saveItemsByPerson(Long id, Long idItem) {
        PersonEntity entPerson = personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person не найден!"));

        entPerson.getItems().forEach(item -> {
            if(item.getId().equals(idItem)){
                throw new RuntimeException("Item уже есть в списке для Person!");
            }
        });

        ItemEntity entItem = itemRepository.findById(idItem)
                .orElseThrow(() -> new NoSuchElementException("Item не найден!"));

        entPerson.getItems().add(entItem);

        log.info("Добавлен Item id {} для Person с id: {}", idItem, id);

        return personRepository.save(entPerson).getItems().stream()
                .map(
                        item -> ItemDTO.builder()
                                .id(item.getId())
                                .name(item.getName())
                                .description(item.getDescription())
                                .build()
                ).toList();
    }

    @Override
    public Collection<ItemDTO> getItemsByPerson(Long id) {
        PersonEntity ent = personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person не найден!"));

        return ent.getItems().stream().map(
                item -> ItemDTO.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .description(item.getDescription())
                        .build()
        ).toList();
    }

    @Override
    public Collection<ArmorDTO> deleteArmorByPerson(Long id, Long idArmor) {
        PersonEntity entPerson = personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person не найден!"));

        ArmorEntity entArmor = armorRepository.findById(idArmor)
                .orElseThrow(() -> new NoSuchElementException("Armor не найден!"));

        entPerson.getArmors().remove(entArmor);

        log.info("Удален Armor id {} для Person с id: {}", idArmor, id);

        return personRepository.save(entPerson).getArmors().stream()
                .map(
                        armor -> {
                            return ArmorDTO.builder()
                                    .id(armor.getId())
                                    .name(armor.getName())
                                    .description(armor.getDescription())
                                    .build();
                        }
                ).toList();
    }

    @Override
    public Collection<ArmorDTO> saveArmorsByPerson(Long id, Long idArmor) {
        PersonEntity entPerson = personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person не найден!"));

        entPerson.getArmors().forEach(armor -> {
            if(armor.getId().equals(idArmor)){
                throw new RuntimeException("Armor уже есть в списке для Person!");
            }
        });

        ArmorEntity entArmor = armorRepository.findById(idArmor)
                .orElseThrow(() -> new NoSuchElementException("Armor не найден!"));

        entPerson.getArmors().add(entArmor);

        log.info("Добавлен Armor id {} для Person с id: {}", idArmor, id);

        return personRepository.save(entPerson).getArmors().stream()
                .map(
                        armor -> ArmorDTO.builder()
                                .id(armor.getId())
                                .name(armor.getName())
                                .description(armor.getDescription())
                                .build()
                ).toList();
    }

    @Override
    public Collection<ArmorDTO> getArmorsByPerson(Long id) {
        PersonEntity ent = personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person не найден!"));

        return ent.getArmors().stream().map(
                armor -> ArmorDTO.builder()
                        .id(armor.getId())
                        .name(armor.getName())
                        .description(armor.getDescription())
                        .build()
        ).toList();
    }
}
