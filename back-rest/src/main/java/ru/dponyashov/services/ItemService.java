package ru.dponyashov.services;

import ru.dponyashov.dto.ItemDTO;

import java.util.Collection;

public interface ItemService {
    Collection<ItemDTO> getItems();

    ItemDTO getItemById(Long id);

    ItemDTO save(ItemDTO item);

    ItemDTO save(Long id, ItemDTO item);

    void deleteById(Long id);
}
