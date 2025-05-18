package ru.dponyashov.services;

import ru.dponyashov.dto.ArmorDTO;
import ru.dponyashov.dto.ItemDTO;
import ru.dponyashov.dto.PersonDTO;
import ru.dponyashov.dto.WeaponDTO;

import java.util.Collection;

public interface PersonService {
    Collection<PersonDTO> getPersons();

    PersonDTO getPersonById(Long id);

    PersonDTO save(PersonDTO person);

    PersonDTO save(Long id, PersonDTO person);

    void deletePersonById(Long id);

    Collection<WeaponDTO> getWeaponsByPerson(Long id);

    Collection<WeaponDTO> saveWeaponsByPerson(Long id, Long idWeapon);

    Collection<WeaponDTO> deleteWeaponByPerson(Long id, Long idWeapon);

    Collection<ItemDTO> deleteItemByPerson(Long id, Long idItem);

    Collection<ItemDTO> saveItemsByPerson(Long id, Long idItem);

    Collection<ItemDTO> getItemsByPerson(Long id);

    Collection<ArmorDTO> deleteArmorByPerson(Long id, Long idArmor);

    Collection<ArmorDTO> saveArmorsByPerson(Long id, Long idArmor);

    Collection<ArmorDTO> getArmorsByPerson(Long id);
}
