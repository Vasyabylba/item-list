package ru.clevertec.service;

import ru.clevertec.dto.ItemRequest;
import ru.clevertec.dto.ItemResponse;

import java.util.List;

public interface ItemService {
    ItemResponse getOne(Long id);

    ItemResponse create(ItemRequest itemRequest);

    ItemResponse update(Long id, ItemRequest itemRequest);

    void delete(Long id);

    List<ItemResponse> getList();
}
