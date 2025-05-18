package ru.dponyashov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dponyashov.dto.ItemDTO;
import ru.dponyashov.services.ItemService;

import java.util.Collection;
import java.util.Locale;
import java.util.NoSuchElementException;

@RestController
//@CrossOrigin(origins = {"127.0.0.1:8080", "localhost:8080"})
@RequiredArgsConstructor
@RequestMapping("items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public Collection<ItemDTO> getItems(){
        return itemService.getItems();
    }

    @GetMapping("/{id:\\d+}")
    public ItemDTO getItemById(@PathVariable("id") Long id){
        return itemService.getItemById(id);
    }

    @PostMapping
    public ItemDTO saveNewItem(@RequestBody ItemDTO newItem){
        return itemService.save(newItem);
    }

    @PutMapping("/{id:\\d+}")
    public ItemDTO saveNewItem(@PathVariable("id") Long id, @RequestBody ItemDTO newItem){
//        newItem.setId(id);
        return itemService.save(id, newItem);
    }

    @DeleteMapping("/{id:\\d+}")
    public void deleteItem(@PathVariable("id") Long id){
        itemService.deleteById(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handlerNoSuchElementException(NoSuchElementException exception,
                                                                       Locale locale){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                        "Предмет не найден!")
                );
    }
}
