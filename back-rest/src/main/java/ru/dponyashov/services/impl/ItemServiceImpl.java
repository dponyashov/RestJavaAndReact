package ru.dponyashov.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.dponyashov.dto.ItemDTO;
import ru.dponyashov.entity.ItemEntity;
import ru.dponyashov.repositories.ItemRepository;
import ru.dponyashov.services.ItemService;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Log4j2
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Collection<ItemDTO> getItems() {
        return itemRepository.findAll().stream()
                .map(ent->ItemDTO.builder()
                        .name(ent.getName())
                        .description(ent.getDescription())
                        .id(ent.getId())
                        .build())
                .toList();
    }

    @Override
    public ItemDTO getItemById(Long id) {
        return itemRepository.findById(id)
                .map(ent->ItemDTO.builder()
                        .name(ent.getName())
                        .description(ent.getDescription())
                        .id(ent.getId())
                        .build())
                .orElseThrow(() -> new NoSuchElementException("Item не найден!"));
    }

    @Override
    public ItemDTO save(Long id, ItemDTO item) {

        ItemDTO itemFromDB = getItemById(id);
        if(itemFromDB == null){
            log.info("Не найден Item с id: {} для обновления", id);
        }

        item.setId(id);
        return save(item);
    }

    @Override
//    @Transactional
    public ItemDTO save(ItemDTO item) {
        ItemEntity ent = ItemEntity.builder()
                .description(item.getDescription())
                .name(item.getName())
                .id(item.getId())
                .build();

        ItemEntity savedEnt = itemRepository.save(ent);
        log.info("Записаны(обновлены) данные Item с id: {}", savedEnt.getId());
        return ItemDTO.builder()
                .description(savedEnt.getDescription())
                .name(savedEnt.getName())
                .id(savedEnt.getId())
                .build();
    }

    @Override
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
        log.info("Удалены данные Item с id: {}", id);
    }
}
